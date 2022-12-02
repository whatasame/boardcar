package LoginPackage;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Back.HttpClient;
import Back.HttpRequest;
import Back.HttpResponse;


public class IdFind {
    private final String version = "HTTP/1.1";
    private static Map<String, String> headers = new HashMap<String, String>() {{
        put("Content-Type", "text/html;charset=utf-8");
    }};
    private Context context;
    public IdFind(Context context){
        this.context = context;
    }

    public boolean isEmailEmpty(String enterEmail){
        if(enterEmail.equals("")){
            return true;
        }
        return false;
    }

    public boolean isEmailVerificationCodeEmpty(String enterVerificationCode){
        if(enterVerificationCode.equals(""))
            return true;
        return false;
    }

    public String runIdFind(String email){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("EMAIL",email);
            HttpRequest httpRequest = new HttpRequest("POST","/memberByEmail",version,
                    headers,jsonObject.toString());
            HttpClient httpClient = new HttpClient(httpRequest, context);
            httpClient.start();
            httpClient.join();
            HttpResponse httpResponse = httpClient.getHttpResponse();
            if(httpResponse.getStatusCode().equals("200")) {
                MemberInfo memberInfo = new MemberInfo(new JSONObject(httpResponse.getBody()));
                return memberInfo.getMID();
            }
            else {
                System.out.println(httpResponse.getStatusCode()+ " " +httpResponse.getStatusText());
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

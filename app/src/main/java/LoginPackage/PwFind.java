package LoginPackage;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Back.HttpClient;
import Back.HttpRequest;
import Back.HttpResponse;

public class PwFind {
    private final String version = "HTTP/1.1";
    private static Map<String, String> headers = new HashMap<String, String>() {{
        put("Content-Type", "text/html;charset=utf-8");
    }};
    private Context context;
    public PwFind(Context context){
        this.context = context;
    }

    public boolean isIdEmpty(String enterID){
        if(enterID.equals(""))
            return true;
        return false;
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

    public String runPwFind(String email,String mid){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("EMAIL",email);
            HttpRequest httpRequest = new HttpRequest("POST","/memberByEmail",version,
                    headers,jsonObject.toString());
            HttpClient httpClient = new HttpClient(httpRequest, context);
            httpClient.start();
            httpClient.join();
            HttpResponse httpResponse = httpClient.getHttpResponse();
            MemberInfo memberInfo = new MemberInfo(new JSONObject(httpResponse.getBody()));
            if(!mid.equals(memberInfo.getMID()))   return null; // id와 email이 같아야 한다.
            else return memberInfo.getPASSWORD();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


}

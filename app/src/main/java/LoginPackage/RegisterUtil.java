package LoginPackage;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Back.HttpClient;
import Back.HttpRequest;
import Back.HttpResponse;
import Back.SessionManager;

public class RegisterUtil {

    private final String version = "HTTP/1.1";
    private static Map<String, String> headers = new HashMap<String, String>() {{
        put("Content-Type", "text/html;charset=utf-8");
    }};
    private Context context;

    public RegisterUtil(Context context){
        this.context = context;
    }

    /*
    *
    *
        // JSON 생성
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MID", MID);
        jsonObject.put("PASSWORD", PASSWORD);
        jsonObject.put("NAME", NAME);
        jsonObject.put("EMAIL", EMAIL);
        jsonObject.put("CNAME", CNAME);

        // 요청
        HttpRequest registerRequest = new HttpRequest("PUT", "/register", version, headers, jsonObject.toString());

        // 응답
        HttpResponse registerResponse = HttpClientTestApp.sendHttpRequest(registerRequest);

    * */
    public boolean registerUserInfo(String MID, String PASSWORD, String NAME, String EMAIL, String CNAME){

        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("MID", MID);
            jsonObject.put("PASSWORD", PASSWORD);
            jsonObject.put("NAME", NAME);
            jsonObject.put("EMAIL", EMAIL);
            jsonObject.put("CNAME", CNAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String answer = jsonObject.toString();
        HttpRequest registerRequest = new HttpRequest("PUT", "/register", version, headers, jsonObject.toString());
        HttpClient httpClient = new HttpClient(registerRequest, context);
        httpClient.start();
        try {
            httpClient.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HttpResponse httpResponse = httpClient.getHttpResponse();
        String result = httpResponse.getStatusCode();
        System.out.println("result : " + result);
        if(result.equals("200")){
            //회원가입 성공
            return true;
        }
        else
            return false;
    }




}

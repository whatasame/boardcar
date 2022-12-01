package Community;

import Back.HttpClient;
import Back.HttpRequest;
import Back.HttpResponse;
import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EmailUtil {

    private final String version = "HTTP/1.1";
    private static Map<String, String> headers = new HashMap<String, String>() {{
        put("Content-Type", "text/html;charset=utf-8");
    }};

    private String authenticationCode;
    //이메일은 세션 검사를 하지 않을 것 같습니다.
    private Context context;

    public EmailUtil (Context context){
        this.context = context;
    }

    public boolean sendAuthenticationCode(String targetEmail){
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("EMAIL", targetEmail);
        }catch(JSONException e){
            e.printStackTrace();
        }

        HttpRequest httpRequest = new HttpRequest("POST", "/mail", version, headers, jsonObject.toString());
        HttpClient httpClient = new HttpClient(httpRequest, context);

        httpClient.start();
        try{
            httpClient.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        HttpResponse httpResponse = httpClient.getHttpResponse();
        String result = httpResponse.getStatusCode();

        //인증메일 전송 성공
        return result.equals("200");
    }

    public boolean authentication(String number){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("NUMBER", number);
        }catch (JSONException e){
            e.printStackTrace();
        }
        HttpRequest httpRequest = new HttpRequest("POST", "/auth", version, headers, jsonObject.toString());
        HttpClient httpClient = new HttpClient(httpRequest, context);
        httpClient.start();
        try{
            httpClient.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        HttpResponse httpResponse = httpClient.getHttpResponse();
        String result = httpResponse.getStatusCode();
        if(result.equals("200")){
            return true;
        }
        else return false;
    }

}

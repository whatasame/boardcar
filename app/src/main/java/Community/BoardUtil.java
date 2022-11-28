package Community;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Back.HttpClient;
import Back.HttpRequest;
import Back.HttpResponse;

public class BoardUtil {

    private final String version = "HTTP/1.1";
    private static Map<String, String> headers = new HashMap<String, String>() {{
        put("Content-Type", "text/html;charset=utf-8");
    }};
    public Context context;

    public BoardUtil(Context context){
        this.context = context;
    }

    public boolean uploadPost(String session, String postDate, String postTitle, String postBody, String postType){

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("MID", getMid(session));
            jsonObject.put("PDATE", postDate);
            jsonObject.put("TITLE", postTitle);
            jsonObject.put("BODY", postBody);
            jsonObject.put("TYPE", postType);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String answer = jsonObject.toString();
        HttpRequest httpRequest = new HttpRequest("PUT", "/uploadPost", version, headers, answer);
        HttpClient httpClient = new HttpClient(httpRequest, context);
        httpClient.start();
        try{
            httpClient.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        HttpResponse httpResponse = httpClient.getHttpResponse();
        if(httpResponse.getStatusCode().equals("200"))
            return true;
        else
            return false;
    }

    public String getMid(String session) throws IOException {
        /* GET TEST - 자신의 정보 가져오기 */

        HttpRequest infoRequest = new HttpRequest("GET", "/myInfo", version, headers, "");
        infoRequest.putHeader("Session-Key", session);
        HttpClient httpClient = new HttpClient(infoRequest, context);
        httpClient.start();

        try{
            httpClient.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        HttpResponse infoResponse = httpClient.getHttpResponse();

        if( !infoResponse.getStatusCode().equals("200")){
            //실패한 경우
            return null;
        }
        else{
            System.out.println("문자열 : " + infoResponse.getBody() + "입니다.");
            System.out.println();
            String output = infoResponse.getBody();
            String result = stringSplit(output);
            System.out.println("After split");
            System.out.println("Result : " + result);
            return result;
            //차량 정보도 같이 받아오게 Backend 수정해서 받기
        }
    }

    private String stringSplit(String input){
        String [] firstSplit = input.split(",");
        String [] secondSplit = firstSplit[1].split(":");
        String output = secondSplit[1].replaceAll("\"", "");
        return output;
    }
}

package Community;

import android.content.Context;
import android.content.SharedPreferences;
import android.se.omapi.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Back.HttpClient;
import Back.HttpRequest;
import Back.HttpResponse;
import Back.SessionManager;

public class BoardUtil {
    /*
    * 1.1 / 1.2 / 1.3 / 1.4 / 1.5 을 여기에 구현합니다.
    * (v)   ()
    *
    *
    * */

    private final String version = "HTTP/1.1";
    private static Map<String, String> headers = new HashMap<String, String>() {{
        put("Content-Type", "text/html;charset=utf-8");
    }};
    public Context context;

    public BoardUtil(Context context){
        this.context = context;
    }

    public boolean uploadPost(String postDate, String postTitle, String postBody, String postType){

        SessionManager sessionManager = new SessionManager(context);
        JSONObject jsonObject = new JSONObject();

        sessionManager.setMID(sessionManager.getUserInfo());
        String MID = sessionManager.getMID();
        headers.put("Session-Key", sessionManager.session);

        try {
            jsonObject.put("MID", MID);
            jsonObject.put("PDATE", postDate);
            jsonObject.put("TITLE", postTitle);
            jsonObject.put("BODY", postBody);
            jsonObject.put("TYPE", postType);

        } catch (JSONException e) {
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
        System.out.println("status:" + httpResponse.getStatusCode());
        System.out.println("body : " + httpResponse.getBody());

        return httpResponse.getStatusCode().equals("200");

    }

    public boolean openPostList(String postType){
        if(postType.equals("자유")){
            String unregisteredMemberSessionKey = "unregistered-member"; //비회원에게 임시 session 부여



            //자유게시판인 경우 비회원도 열람은 가능함. 이를 해결할 방법을 찾을 것.
        }
        else{ //자유게시판을 제외한 꿀팁게시판 및 차량게시판은 Session 이 있는 경우에만 열람이 가능함.



        }

        SessionManager sessionManager = new SessionManager(context);
        JSONObject jsonObject = new JSONObject();

        sessionManager.setMID(sessionManager.getUserInfo());
        String MID = sessionManager.getMID();
        headers.put("Session-Key", sessionManager.session);
    }

    public boolean updatePost(String PID, String updateBody){


    }

    public boolean deletePost(String PID){


    }




    /*
    *  public static void GET_openPostList(String TYPE) throws IOException {

        // JSON 생성
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("TYPE", TYPE);

        // 요청
        HttpRequest openPostListRequest = new HttpRequest("GET", "/openPostList", version, headers, jsonObject.toString());

        // 응답
        HttpResponse openPostListResponse = HttpClientTestApp.sendHttpRequest(openPostListRequest);

        // 결과 출력
        System.out.println("--------------------------------");
        System.out.println(openPostListResponse);
    }
    *
    *
    *
    *
    * */
}

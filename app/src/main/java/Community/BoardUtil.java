package Community;

import android.content.Context;
import android.content.SharedPreferences;
import android.se.omapi.Session;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Back.HttpClient;
import Back.HttpRequest;
import Back.HttpResponse;
import Back.SessionManager;

public class BoardUtil {
    /*
    * 1.1 / 1.2 / 1.3 / 1.4 / 1.5 을 여기에 구현합니다.
    * (v)   (v)  (
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

    /**
     * 타입에 맞는 글들의 정보의 arraylist 반환
     * @param postType{String} 글 타입 : "자유", "소나타"... 모델명으로 입력
     * @return {ArrayList<BoardInfo>} 글 정보의 ArrayList 리턴
     */
    public ArrayList<BoardInfo> openPostList(String postType){
        // request에 사용할 JSON 생성
        JSONObject jsonObject = new JSONObject();
        ArrayList<BoardInfo> boardInfoArrayList = new ArrayList<BoardInfo>();
        try {
            jsonObject.put("TYPE", postType); // request에 넣을 Type 추가
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(postType.equals("자유")){ /// 임시 세션 안되는 듯 합니다...
            headers.put("Session-Key", "unregistered-member"); //비회원에게 임시 session 부여(안됨)
            HttpRequest httpRequest = new HttpRequest("GET", "/openPostList",
                    version, headers, jsonObject.toString());
            HttpClient httpClient = new HttpClient(httpRequest,context);
            httpClient.start();
            try {
                httpClient.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            HttpResponse httpResponse = httpClient.getHttpResponse();
            if(!httpResponse.getStatusCode().equals("200")){
                System.out.println("openPostList error" + httpResponse.getStatusText());
            }
            System.out.println(httpResponse.getBody());
            //자유게시판인 경우 비회원도 열람은 가능함. 이를 해결할 방법을 찾을 것.
        }
        else{ //자유게시판을 제외한 꿀팁게시판 및 차량게시판은 Session 이 있는 경우에만 열람이 가능함.
            SessionManager sessionManager = new SessionManager(context);
            sessionManager.setMID(sessionManager.getUserInfo());
            String MID = sessionManager.getMID();
            headers.put("Session-Key", sessionManager.session);
            HttpRequest httpRequest = new HttpRequest("GET", "/openPostList", version,
                    headers, jsonObject.toString());
            HttpClient httpClient = new HttpClient(httpRequest,context);
            httpClient.start();
            try {
                httpClient.join(); // request 스레드 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            HttpResponse httpResponse = httpClient.getHttpResponse();
            if(!httpResponse.getStatusCode().equals("200")){
                System.out.println("openPostList error" + httpResponse.getStatusText());
                return null;
            }
            try {
                /* body -> JSONArray */
                System.out.println(httpResponse.getBody());
                JSONArray jsonArray = new JSONArray(httpResponse.getBody());
                for(int i =0; i<jsonArray.length(); i++) {
                    // JSONArray -> JSONObject
                    JSONObject json = new JSONObject(jsonArray.getString(i));
                    boardInfoArrayList.add(splitBodyresponse(json));
                }
                return boardInfoArrayList;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return null;

    }

    /**
     * jsonObject를 BoardInfo로 만듬!
     * @param jsonObject
     * @return boardInfo
     */
    private BoardInfo splitBodyresponse(JSONObject jsonObject){
        try {
            BoardInfo boardInfo = new BoardInfo(jsonObject.getString("MID"),
                    jsonObject.getInt("DOWNVOTE"), jsonObject.getString("PDATE"),
                    jsonObject.getInt("PID"), jsonObject.getString("TITLE"),
                    jsonObject.getString("BODY"), jsonObject.getString("TYPE"),
                    jsonObject.getInt("UPVOTE"));
            return boardInfo;
        } catch (JSONException e) {
            e.printStackTrace();
            return  null;
        }
    }
/*
    public boolean updatePost(String PID, String updateBody){

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

    }*/
}

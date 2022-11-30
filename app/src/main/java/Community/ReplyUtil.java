package Community;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Back.HttpClient;
import Back.HttpRequest;
import Back.HttpResponse;
import Back.SessionManager;

public class ReplyUtil {

    private final String version = "HTTP/1.1";
    private static Map<String, String> headers = new HashMap<String, String>() {{
        put("Content-Type", "text/html;charset=utf-8");
    }};
    public Context context;

    public ReplyUtil(Context context){
        this.context = context;
    }

    /*
    * 1. pid를 통해 해당 게시글의 댓글을 List형태로 모두 불러온다
    * 2. pid를 통해 해당 게시글에 댓글을 추가한다.
    * 3. pid를 통해 해당 댓글을 수정한다.
    * 4. pid를 통해 해당 댓글을 삭제한다.
    *
    *
    *
    * */

    public boolean uploadReply(int pid, String mid, String body){
        JSONObject jsonObject = new JSONObject();
        SessionManager sessionManager = new SessionManager(context);
        headers.put("Session-Key", sessionManager.session);
        try{
            jsonObject.put("PID", pid);
            jsonObject.put("MID", mid);
            jsonObject.put("BODY", body);
        }catch (JSONException e){
            e.printStackTrace();
        }
        HttpRequest httpRequest = new HttpRequest("PUT", "/uploadReply", version, headers, jsonObject.toString());
        HttpClient httpClient = new HttpClient(httpRequest, context);
        httpClient.start();
        try{
            httpClient.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        HttpResponse httpResponse = httpClient.getHttpResponse();
        String result = httpResponse.getStatusCode();
        //댓글 등록 성공
        return result.equals("200");
    }

    public ArrayList<ReplyInfo> openReplyList(int pid){
        SessionManager sessionManager =  new SessionManager(context);
        ArrayList<ReplyInfo> replyInfoArrayList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("PID", pid);
        }catch (JSONException e){
            e.printStackTrace();
        }
        headers.put("Session-Key", sessionManager.session);
        HttpRequest httpRequest = new HttpRequest("GET", "/openReplyList", version, headers, jsonObject.toString());
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
            try{
                JSONArray jsonArray = new JSONArray(httpResponse.getBody());
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject json = new JSONObject(jsonArray.getString(i));
                    replyInfoArrayList.add(new ReplyInfo(json));
                }
                return replyInfoArrayList;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return null;
    }

}

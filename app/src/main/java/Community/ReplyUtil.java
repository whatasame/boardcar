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

        return result.equals("200"); //댓글 등록 성공 / 실패 여부 출력
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
    public boolean updateReply(int RID, String updateBody){
        SessionManager sessionManager = new SessionManager(context);
        headers.put("Session-Key", sessionManager.session);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("RID", RID);
            jsonObject.put("BODY", updateBody);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpRequest updateReplyRequest = new HttpRequest("POST", "/updateReply",
                version, headers, jsonObject.toString());
        HttpClient httpClient = new HttpClient(updateReplyRequest, context);
        httpClient.start();
        try {
            httpClient.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HttpResponse httpResponse = httpClient.getHttpResponse();
        if(httpResponse.getStatusCode().equals("200")){
            //수정 성공
            return true;
        }
        else {
            //수정 실패
            System.out.println("*** editReply ERROR ***");
            System.out.println(httpResponse.getStatusCode() + " " + httpResponse.getStatusCode());
            System.out.println(httpResponse.getBody());
            return false;
        }
    }
    public boolean deleteReply(int rid){
        SessionManager sessionManager = new SessionManager(context);
        headers.put("Session-Key", sessionManager.session);
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("RID", rid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpRequest deletePostRequest = new HttpRequest("POST", "/deleteReply", version,
                headers,  jsonObject.toString());

        HttpClient httpClient = new HttpClient(deletePostRequest, context);
        httpClient.start();
        try {
            httpClient.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HttpResponse httpResponse = httpClient.getHttpResponse();
        String res = httpResponse.getStatusCode();
        if(res.equals("200")){
            //삭제 성공
            return true;
        }
        else {
            System.out.println("***deleteReply ERROR ***");
            System.out.println(httpResponse.getStatusCode()+" "+httpResponse.getStatusCode());
            System.out.println(httpResponse.getBody());
            return false;
        }
    }
}




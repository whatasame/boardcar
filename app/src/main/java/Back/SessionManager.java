package Back;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;


/**
 * @// TODO: 2022-11-29 API 추가 내용 최정규 선배님께 전송해서 API 받기
 */
public class SessionManager {

    private final String version = "HTTP/1.1";
    private static Map<String, String> headers = new HashMap<String, String>() {{
        put("Content-Type", "text/html;charset=utf-8");
    }};

    public Context context;

    private String MID = null;
    private String NAME = null;
    //private String EMAIL = null;
    //private String CID = null;

    public String session;

    public SharedPreferences sharedPreferences;

    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("LOGIN_INFO", MODE_PRIVATE);
        session = sharedPreferences.getString("UUID", "");
    }

    public String getUserInfo(){
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
        System.out.println("status code : " + infoResponse.getStatusCode());

        if( !infoResponse.getStatusCode().equals("200")){
            //실패한 경우
            return null;
        }
        else{
            System.out.println("get body : " + infoResponse.getBody());
            return infoResponse.getBody();
        }
    }

    public void setMID(String input){
        //input을 쪼개서 mid만 뽑아내기
        String [] firstSplit = input.split(",");
        String [] secondSplit = firstSplit[1].split(":");
        MID = secondSplit[1].replaceAll("\"", "");
    }

    public String getMID(){
        return MID;
    }

    private void setNAME (String input){
        //input 쪼개서 NAME만 뽑아내기
        String [] firstSplit = input.split(",");
        String [] secondSplit = firstSplit[3].split(":");
        NAME = secondSplit[1].replaceAll("\"", "");
    }

    public String getNAME(){
        return NAME;
    }

    /*

    private void setCID(String input){

    }

    */


    /*

    private void setEMAIL(String input){

    }

    */


}

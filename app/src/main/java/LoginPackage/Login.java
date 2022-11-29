package LoginPackage;
/*
 * Author : 이윤상
 * resent update : 11-21
 *
 * note
 *   수정내용
 *       1. MemberDAO를 전역변수 위치에 객체 선언 (null 상태)
 *       2. 전역변수로 DBUrl, DBId, DBPw를 final로 선언
 *       3. Login 생성자에서 DB연결 삭제 및 MemberDAO 생성자 작성
 *       4. 전역변수에서 Connection conn 삭제 (class Diagram에 반영하기)
 *
 *
 *
 *
 * */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Back.*;


//import Back.HttpClient;
//import Back.HttpRequest;
//import Back.HttpResponse;

public class Login {

    private final String version = "HTTP/1.1";
    private static Map<String, String> headers = new HashMap<String, String>() {{
        put("Content-Type", "text/html;charset=utf-8");
    }};

    public Context context;

    public Login(Context context) {
        this.context = context;
    }

    public boolean isIdEmpty(String Id, AlertDialog.Builder Alert) {
        if (Id.equals("")) {
            printAlertMessage("아이디를 입력하지 않았습니다.", Alert);
            return true;
        }
        return false;
    }

    public boolean isPwEmpty(String pw, AlertDialog.Builder Alert) {

        if (pw.equals("")) {
            printAlertMessage("비밀번호를 입력하지 않았습니다.", Alert);
            return true;
        }
        return false;
    }



    public String runLogin(String enterId, String enterPw, AlertDialog.Builder Alert) throws JSONException, InterruptedException {

        JSONObject loginJson = new JSONObject();
        loginJson.put("id", enterId);
        loginJson.put("password", enterPw);
        String loginInfo = loginJson.toString();
        HttpRequest loginRequest = new HttpRequest("POST", "/login", version, headers, loginInfo);

        HttpClient httpClient = new HttpClient(loginRequest, context);
        httpClient.start();
        httpClient.join();

        HttpResponse httpResponse = httpClient.getHttpResponse();

        if(httpResponse.getStatusCode().equals("200")){
            //Login 성공!
            httpClient.sessionKey = httpResponse.getHeaders().get("Session-Key");
            System.out.println("Response Status : " + httpResponse.getStatusCode());
            System.out.println(httpResponse.getBody());
            System.out.println("Session-Key is " + httpClient.sessionKey);
            System.out.println();
            //printAlertMessage("로그인에 성공하였습니다.", Alert);
        }
        else{
            printAlertMessage("ID 혹은 PW가 틀렸습니다.", Alert);
        }

        return httpClient.sessionKey;
    }

    private void printAlertMessage(String message, AlertDialog.Builder Alert) {
        Alert.setTitle("로그인 알림");
        Alert.setMessage(message);
        Alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        Alert.show();
    }
}

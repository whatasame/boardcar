package LoginPackage;

import Back.HttpClient;
import Back.HttpRequest;
import Back.HttpResponse;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/*
* Author : 이윤상
* recent update : 11-22
*
* note
*   수정내용
*       1. Register 관련 class Diagram 내용 전체 수정 필요함
*
* */
public class Register {

    private final String version = "HTTP/1.1";
    private static Map<String, String> headers = new HashMap<String, String>() {{
        put("Content-Type", "text/html;charset=utf-8");
    }};

    private Context context;
    public Register(Context context){
        this.context = context;

    }

    private void printAlertMessage(String message, AlertDialog.Builder Alert){
        Alert.setTitle("회원가입 실패");
        Alert.setMessage(message);
        Alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });
        Alert.show();
    }

    public boolean isIdEmpty(String enterId, AlertDialog.Builder Alert){
        if(enterId.equals("")){//ID가 비었을 때
            printAlertMessage("아이디를 입력해 주세요.", Alert);
            return true;
        }
        return false;
    }

    public boolean isPwEmpty(String enterPw, AlertDialog.Builder Alert){
        if(enterPw.equals("")){//ID가 비었을 때
            printAlertMessage("비밀번호를 입력해 주세요.", Alert);
            return true;
        }
        return false;
    }

    public boolean isPwSameEmpty(String enterSamePw, AlertDialog.Builder Alert){
        if(enterSamePw.equals("")){
            printAlertMessage("비밀번호 확인란을 입력해 주세요.", Alert);
            return true;
        }
        return false;
    }

    public boolean isNameEmpty(String enterName, AlertDialog.Builder Alert){
        if(enterName.equals("")){
            printAlertMessage("성함을 입력해 주세요.", Alert);
            return true;
        }
        return false;
    }

    public boolean isEmailEmpty(String enterEmail, AlertDialog.Builder Alert){
        if(enterEmail.equals("")){
            printAlertMessage("이메일을 입력해 주세요.", Alert);
            return true;
        }
        return false;
    }

    public boolean isEmailVerificationCodeEmpty(String enterVerificationCode, AlertDialog.Builder Alert){
        if(enterVerificationCode.equals("")){
            printAlertMessage("이메일 인증번호를 입력해주세요.", Alert);
            return true;
        }
        return false;
    }

    public boolean runRegister(String enterId, String enterPw, String enterName, String enterEmail, String carName){
        //MemberVO memberVO = new MemberVO(enterId, enterPw, enterName, enterEmail);
        //cid 랑 isAdmin 은 어떻게 확인하는지 성현형님께 여쭤보기
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("MID", enterId);
            jsonObject.put("PASSWORD", enterPw);
            jsonObject.put("EMAIL", enterEmail);
            jsonObject.put("NAME", enterName);
            jsonObject.put("CNAME", carName);
        }catch (JSONException e){
            e.printStackTrace();
        }

        HttpRequest httpRequest = new HttpRequest("PUT", "/register", version, headers, jsonObject.toString());
        HttpClient httpClient = new HttpClient(httpRequest, context);
        httpClient.start();
        try{
            httpClient.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        HttpResponse httpResponse = httpClient.getHttpResponse();
        String result = httpResponse.getStatusCode();

        //회원가입 성공
        return result.equals("200");
    }
}

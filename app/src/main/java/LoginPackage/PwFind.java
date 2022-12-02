package LoginPackage;

import java.util.HashMap;
import java.util.Map;

import Back.HttpRequest;

public class PwFind {
    private final String version = "HTTP/1.1";
    private static Map<String, String> headers = new HashMap<String, String>() {{
        put("Content-Type", "text/html;charset=utf-8");
    }};
    public PwFind(){

    }

    public boolean isIdEmpty(String enterID){
        if(enterID.equals(""))
            return true;
        return false;
    }

    public boolean isEmailEmpty(String enterEmail){
        if(enterEmail.equals("")){
            return true;
        }
        return false;
    }

    public boolean isEmailVerificationCodeEmpty(String enterVerificationCode){
        if(enterVerificationCode.equals(""))
            return true;
        return false;
    }

    public void runPwFind(){
        //ID랑 Email이랑 둘다 DB에 있는 데이터랑 일치하는지 확인해야 비밀번호 뽑을 수 있음
//        HttpRequest httpRequest = new HttpRequest("")
    }


}

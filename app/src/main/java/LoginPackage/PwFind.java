package LoginPackage;

public class PwFind {

    public PwFind(){
        //DB연결 필요함
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

    }


}

package LoginPackage;

import androidx.appcompat.app.AlertDialog;


public class IdFind {

    public IdFind(){

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

    public void runIdFind(){

    }
}

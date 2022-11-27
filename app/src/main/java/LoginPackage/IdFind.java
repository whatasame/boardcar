package LoginPackage;

import androidx.appcompat.app.AlertDialog;

public class IdFind {

    public IdFind(){
        //DB 연결 필요함
        try{
            Class.forName("dd");
            //Oracle 연결바람

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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

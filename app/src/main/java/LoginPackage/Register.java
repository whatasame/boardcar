package LoginPackage;

import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

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

    public Register(){

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

    public void runRegister(String enterId, String enterPw, String enterName, String enterEmail){
        //MemberVO memberVO = new MemberVO(enterId, enterPw, enterName, enterEmail);
        //cid 랑 isAdmin 은 어떻게 확인하는지 성현형님께 여쭤보기
    }
}

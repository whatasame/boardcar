package LoginPackage;

import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import java.util.regex.Pattern;

/*
* Author : 이윤상
* recent update : 11-22
*
* note
*   수정내용
*       1. Class Diagram 에서 CheckUserData 라고 되어있음. class 이름 수정할 것.
*
*
* */
public class CheckMemberData {


    public CheckMemberData(){

    }

    private void printAlertMessage(String message, AlertDialog.Builder Alert){
        Alert.setTitle("데이터 확인 오류");
        Alert.setMessage(message);
        Alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });
        Alert.show();
    }

    public boolean isIdRegexMatched(String enterId, AlertDialog.Builder Alert){
        String regex = "^[a-zA-Z]{1}[a-zA-Z0-9]{7,19}$"; //영문 및 숫자만 가능하며, 8자~20자 사이만 가능한 아이디 정규식
        boolean result = Pattern.matches(regex, enterId);
        if(!result){
            printAlertMessage("아이디는 영문과 숫자로만 이루어져야 하며 8~20자로 작성해주십시오.", Alert);
            return true;
        }
        return false;
    }

    public boolean isPwRegexMatched(String enterPw, AlertDialog.Builder Alert){
        String regex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{7,19}$";
        boolean result = Pattern.matches(regex, enterPw);
        if(!result){
            printAlertMessage("비밀번호는 특수문자를 포함하여 영문 및 숫자로만 이루어져야 하며 8~20자로 작성해주십시오.", Alert);
            return true;
        }
        return false;
    }

    public boolean isEmailRegexMatched(String enterEmail, AlertDialog.Builder Alert){
        String regex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$"; //이메일 정규식
        boolean result = Pattern.matches(regex, enterEmail);
        if(result){
            return true;
        }
        printAlertMessage("이메일 양식에 맞춰서 작성해주십시오.", Alert);
        return false;
   }

   public boolean isPwSameMatched(String originPw, String samePw, AlertDialog.Builder Alert){
        if(!originPw.equals(samePw)){
            printAlertMessage("입력한 비밀번호와 확인용이 맞지 않습니다.", Alert);
            return true;
        }
        return false;
   }
}


package com.example.boardcar;
/*
* 수정인 : 이윤상
* 수정일 : 11-22
* 수정내용
*   1. OnCreate 메소드 내에서 IdFind class 객체와 CheckMemberData class 객체 모두 생성했음
*   1-1. class Diagram 에서 관계선 표시할 때 수정해야됨.
*
*
* */
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import LoginPackage.CheckMemberData;
import LoginPackage.IdFind;

public class FindIDUI extends AppCompatActivity {
    EditText findIdEmail, findIdEmailCheck;
    Button findIdEmailCheckBtn, findIdBtn;
    TextView findIdHideCheckNumber, findIdHideSuccess;
    String findIdEmailStr, emailCheckIDStr;

    AlertDialog.Builder Alert;

    public void AlertNoEditMsg(String title, String msg) { //에러문구 나타내는 함수
        Alert.setTitle(title);
        Alert.setMessage(msg);
        Alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        Alert.show();
    }
    public void AlertIDMsg(String title, String msg) { //팝업창인데 확인 누르면 전화면으로 넘어감
        Alert.setTitle(title);
        Alert.setMessage(msg);
        Alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getApplicationContext(),LoginUI.class);
                startActivity(intent);
                finish();
            }
        });

        Alert.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_idui);
        findIdEmail = findViewById(R.id.FindIdEmail); //이메일 입력받는칸
        findIdEmailCheck = findViewById(R.id.FindIdEmailCheck);//인증번호 입력 받는칸
        findIdEmailCheckBtn = findViewById(R.id.FindIdEmailCheckBtn); //인증번호 맞는지 확인하는칸 
        findIdBtn = findViewById(R.id.FindIdBtn);//이메일로 인증번호 발송하는 칸
        findIdHideCheckNumber = findViewById(R.id.FindIdHideCheckNumber);//인증번호 틀렸다고 나오는 문구
        findIdHideSuccess = findViewById(R.id.FindIdHideSuccess);//인증번호 보냈다고 성공하는문구

        CheckMemberData checkMemberData = new CheckMemberData();
        Alert = new AlertDialog.Builder(FindIDUI.this);

        IdFind idFind = new IdFind();

        findIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                findIdEmailStr = (findIdEmail.getText().toString());

                if(!idFind.isEmailEmpty(findIdEmailStr)){ //이메일 입력란에 뭔갈 입력했다면

                    if(!checkMemberData.isEmailRegexMatched(findIdEmailStr, Alert)){//정규식을 만족했다면

                        //이메일 전송 코드 작성

                        findIdHideSuccess.setVisibility(View.VISIBLE);
                        findIdEmailCheckBtn.setEnabled(true);
                    }
                }
                else
                    AlertNoEditMsg("아이디 찾기 실패","이메일을 입력해주세요");
            }
        });
        findIdEmailCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailCheckIDStr = (findIdEmailCheck.getText().toString());
                //int test = 1234;
                String testID = "asdf1234";

                if(!idFind.isEmailVerificationCodeEmpty(emailCheckIDStr)){

                    if(emailCheckIDStr.equals("1234")){
                        // DB 연결 관련 내용 첨부바람.
                        idFind.runIdFind();
                        AlertIDMsg("아이디 찾기성공 ","회원님의 아이디를 찾았어요\n아이디는 " + testID + "입니다.");
                    }
                    else{
                        findIdHideCheckNumber.setVisibility(View.VISIBLE); //인증번호가 일치하지않아요 나타나는애
                    }
                }
                else
                    AlertNoEditMsg("아이디 찾기 실패","인증번호를 입력해주세요.");
            }
        });
    }
}
/*
if (emailCheckIDStr.equals(인증번호))
    AlertIDMsg("아이디 찾기성공 ",변수이름+ <----DB에서 아이디 불러온거"회원님의 아이디를 찾았어요"); 93번 if문안에들어가야함

}
                 */
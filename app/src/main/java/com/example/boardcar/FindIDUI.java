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

import Community.EmailUtil;
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

        IdFind idFind = new IdFind(getBaseContext());
        EmailUtil emailUtil = new EmailUtil(getBaseContext());
        findIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                findIdEmailStr = (findIdEmail.getText().toString());

                if(!idFind.isEmailEmpty(findIdEmailStr)){ //이메일 입력란에 뭔갈 입력했다면

                    if(checkMemberData.isEmailRegexMatched(findIdEmailStr, Alert)){//정규식을 만족했다면
                        if(!emailUtil.sendAuthenticationCode(findIdEmailStr)) // 이메일 전송 실패
                            AlertNoEditMsg("이메일 전송 에러","잠시 후 다시 시도해주세요.");
                        else { // 전송 성공
                            findIdHideSuccess.setVisibility(View.VISIBLE);
                            findIdEmailCheckBtn.setEnabled(true);
                        }
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
                if(!idFind.isEmailVerificationCodeEmpty(emailCheckIDStr)){

                    if(emailUtil.authentication(emailCheckIDStr)){
                        
                        String result = idFind.runIdFind(findIdEmailStr); // 여기서 id찾는 API가 있다면 찾아와야함
                        AlertIDMsg("아이디 찾기성공 ","회원님의 아이디를 찾았어요\n아이디는 " + result + "입니다.");
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
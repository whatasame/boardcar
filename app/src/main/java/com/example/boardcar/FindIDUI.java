package com.example.boardcar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FindIDUI extends AppCompatActivity {
    EditText findIdEmail, findIdEmailCheck;
    Button findIdEmailCheckBtn, findIdBtn;
    TextView findIdHideCheckNumber, findIdHideSuccess;
    String findIdEmailStr, emailCheckIDStr;

    public void AlertNoEditMsg(String title, String msg) { //에러문구 나타내는 함수
        AlertDialog.Builder Alert = new AlertDialog.Builder(FindIDUI.this);
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
        AlertDialog.Builder Alert = new AlertDialog.Builder(FindIDUI.this);
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




        findIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                findIdEmailStr = (findIdEmail.getText().toString());
                if (findIdEmailStr.length() != 0) {
                 /*if(DB에서 이메일 체크하고 있는지 없는지 확인){
                    //인증번호 날라오는거 함수? 암튼 그거 쓰기
                    hideSuccessId.setVisibility(View.VISIBLE);
                    emailCheckIdBtn.setEnabled(true);
                 }else{
                    AlertNoEditMsg("아이디 찾기 실패","회원님의 정보를 찾을수 없어요");
                 }*/

                    //밑에 애들 지워주세용
                    findIdHideSuccess.setVisibility(View.VISIBLE);
                    findIdEmailCheckBtn.setEnabled(true);
                }else {
                    AlertNoEditMsg("아이디 찾기 실패","이메일을 입력해주세요");
                }
            }
        });
        findIdEmailCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailCheckIDStr = (findIdEmailCheck.getText().toString());
                int test =123;
                if (emailCheckIDStr.length() != 0) {

               if (emailCheckIDStr.equals("1234")){
                AlertIDMsg("아이디 찾기성공 ",test+"회원님의 아이디를 찾았어요");

                }

                } else {
                    findIdHideCheckNumber.setVisibility(View.VISIBLE); //인증번호가 일치하지않아요 나타나는애
                }
            }
        });



    }
}
/*
if (emailCheckIDStr.equals(인증번호))
    AlertIDMsg("아이디 찾기성공 ",변수이름+ <----DB에서 아이디 불러온거"회원님의 아이디를 찾았어요"); 93번 if문안에들어가야함

}
                 */
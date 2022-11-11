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

public class FindPWUI extends AppCompatActivity {
    EditText findPwId , findPwEmail,findPwEmailCheck;
    Button findPwBtn , findPwEmailCheckBtn;
    TextView findPwHideSuccess ,findPwHideCheckNumber;
    String findPwIdStr, findPwEmailStr, findPwEmailCheckStr;


    public void AlertNoEditMsg(String title, String msg) { //에러문구 나타내는 함수
        AlertDialog.Builder Alert = new AlertDialog.Builder(FindPWUI.this);
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
        AlertDialog.Builder Alert = new AlertDialog.Builder(FindPWUI.this);
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
        setContentView(R.layout.activity_find_pwui);
        findPwId = findViewById(R.id.FindPwId);//아이디 입력받는칸
        findPwEmail = findViewById(R.id.FindPwEmail); //이메일 입력받는칸
        findPwEmailCheck = findViewById(R.id.FindPwEmailCheck); //인증번호 입력받는칸
        findPwBtn = findViewById(R.id.FindPwBtn); //인증번호 발송 버튼
        findPwEmailCheckBtn = findViewById(R.id.FindPwEmailCheckBtn);//인증번호 맞는지 확인 버튼
        findPwHideSuccess = findViewById(R.id.FindPwHideSuccess);// 인증번호 잘보내졋다고 문구
        findPwHideCheckNumber = findViewById(R.id.FindPwHideCheckNumber);//인증번호 틀렸다고 문구

        findPwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPwIdStr =(findPwId.getText().toString());
                findPwEmailStr= (findPwEmail.getText().toString());
                if(findPwIdStr.length() !=0 && findPwEmailStr.length() !=0){
                    /*if(DB에서 이메일 체크하고 있는지 없는지 확인 아이디도 확인해줘야함 ){
                    //인증번호 날라오는거 함수? 암튼 그거 쓰기
                    hideSuccessPw.setVisibility(View.VISIBLE);
                    emailCheckPwBtn.setEnabled(true);

                 }else{
                    AlertNoEditMsg("비밀번호 찾기 실패 ","회원님의 정보를 찾을수 없어요");
                 }*/
                    findPwHideSuccess.setVisibility(View.VISIBLE);
                    findPwEmailCheckBtn.setEnabled(true);
                }else{
                    AlertNoEditMsg("비밀번호 찾기 실패","아이디 또는 이메일을 입력해주세요");
                }
            }


        });
        findPwEmailCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPwEmailCheckStr=(findPwEmailCheck.getText().toString());
                int test = 123;
                if(findPwEmailCheckStr.length() !=0){

                    if(findPwEmailCheckStr.equals("1234")){ //
                    AlertIDMsg("비밀번호 찾기성공 ",test+"회원님의 비밀번호를 찾았어요");

                }

            }
                else{
                    findPwHideCheckNumber.setVisibility(View.VISIBLE);
                }
        }
        });
    }
}

 /*if(emailCheckPwStr.equals(인증번호)){ 검사후 93번 대체 해서 들어가야함 테스트로 위에 넣어논거
                    AlertIDMsg("비밀번호 찾기성공 ",변수이름+ <----DB에서 비밀번호 불러온거"회원님의 비밀번호를 찾았어요");

                }
                 */
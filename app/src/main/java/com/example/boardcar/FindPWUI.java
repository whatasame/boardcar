package com.example.boardcar;
/*
* 수정인 : 이윤상
* 수정일 : 11-22
* 수정내용
*   1. onCreate 메소드 내에서 PwFind 와 CheckMemberData 둘다 class 객체 선언함.
*   1-1. class Diagram 에서 필히 관계 표시선 수정할 것.
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
import LoginPackage.PwFind;
//@todo PW찾기 테스트 API 미구현-> password를 얻을 수 있는 방법이 없음
public class FindPWUI extends AppCompatActivity {
    EditText findPwId , findPwEmail,findPwEmailCheck;
    Button findPwBtn , findPwEmailCheckBtn;
    TextView findPwHideSuccess ,findPwHideCheckNumber;
    String findPwIdStr, findPwEmailStr, findPwEmailCheckStr;

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
        setContentView(R.layout.activity_find_pwui);
        findPwId = findViewById(R.id.FindPwId);//아이디 입력받는칸
        findPwEmail = findViewById(R.id.FindPwEmail); //이메일 입력받는칸
        findPwEmailCheck = findViewById(R.id.FindPwEmailCheck); //인증번호 입력받는칸
        findPwBtn = findViewById(R.id.FindPwBtn); //인증번호 발송 버튼
        findPwEmailCheckBtn = findViewById(R.id.FindPwEmailCheckBtn);//인증번호 맞는지 확인 버튼
        findPwHideSuccess = findViewById(R.id.FindPwHideSuccess);// 인증번호 잘보내졋다고 문구
        findPwHideCheckNumber = findViewById(R.id.FindPwHideCheckNumber);//인증번호 틀렸다고 문구
        EmailUtil emailUtil = new EmailUtil(getBaseContext());
        PwFind pwFind = new PwFind();
        CheckMemberData checkMemberData = new CheckMemberData();
        Alert = new AlertDialog.Builder(FindPWUI.this);

        findPwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPwIdStr =(findPwId.getText().toString());
                findPwEmailStr= (findPwEmail.getText().toString());

                if(!pwFind.isIdEmpty(findPwIdStr)){ //Id 입력란에 입력했다면

                    if(!pwFind.isEmailEmpty(findPwEmailStr)){ //Email 입력란에 입력했다면

                        if(!checkMemberData.isEmailRegexMatched(findPwEmailStr, Alert)) { //Email 정규식을 만족했다면
                            if (!emailUtil.sendAuthenticationCode(findPwEmailStr))
                                AlertNoEditMsg("네트워크 에러", "잠시 후 다시 시도해주세요");
                                //Email 전송 코드 작성하기
                            else {
                                findPwHideSuccess.setVisibility(View.VISIBLE);
                                findPwEmailCheckBtn.setEnabled(true);
                            }
                        }
                    }
                    else
                        AlertNoEditMsg("비밀번호 찾기 실패","이메일을 입력해주세요");
                }
                else
                    AlertNoEditMsg("비밀번호 찾기 실패","아이디를 입력해주세요.");

            }

        });
        findPwEmailCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPwEmailCheckStr=(findPwEmailCheck.getText().toString());
//                String testPw = "asdf1234@";

                if (!pwFind.isEmailVerificationCodeEmpty(findPwEmailCheckStr)){ //인증번호를 입력했다면

                    if(emailUtil.authentication(findPwEmailCheckStr)){

                        //DB랑 연결해서 비밀번호 뽑아내기
//                        String password = pwFind.runPwFind(String mid, String email);

                        AlertIDMsg("비밀번호 찾기성공 ","회원님의 비밀번호를 찾았어요.\n비밀번호는 " + "testpw" + "입니다.");
                    }
                    else
                        findPwHideCheckNumber.setVisibility(View.VISIBLE);
                }
                else{
                    AlertNoEditMsg("비밀번호 찾기 실패","인증번호를 입력해주세요.");
                }

        }
        });
    }
}

 /*if(emailCheckPwStr.equals(인증번호)){ 검사후 93번 대체 해서 들어가야함 테스트로 위에 넣어논거
                    AlertIDMsg("비밀번호 찾기성공 ",변수이름+ <----DB에서 비밀번호 불러온거"회원님의 비밀번호를 찾았어요");

                }
                 */
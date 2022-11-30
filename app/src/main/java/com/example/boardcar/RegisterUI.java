package com.example.boardcar;
/*
* Author : 이윤상
* recent update : 11-22
*
* note
*   수정내용
*       1. registerBtn. 눌렀을 때 발생하는 함수 내 정규식 작성
*       2. MemberVO 와 MemberDAO 사용한 DB 저장
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
import LoginPackage.Register;

public class RegisterUI extends AppCompatActivity {

    EditText regInputId,regInputPw,regCheckPw,regInputName,regSelectCar,regInputEmail,regEmailCheck;
    Button regFindCarBtn,regEmailBtn ,regEmailCheckBtn,registerBtn;
    TextView regHideSuccess,regHideCheckNumber;
    String regInputIdStr, regInputPwStr, regCheckPwStr, regInputNameStr, regFindCarStr, regInputEmailStr,regEmailCheckStr;

    AlertDialog.Builder Alert;

    Register register = new Register();
    CheckMemberData checkMemberData = new CheckMemberData();

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_ui);
        regInputId=findViewById(R.id.RegInputId); //아이디 입력 받는칸
        regInputPw=findViewById(R.id.RegInputPw); //비밀번호 입력 받는칸
        regCheckPw=findViewById(R.id.RegCheckPw); // 비밀번호 같은지 받는칸
        regInputName=findViewById(R.id.RegInputName); // 회원이름 받는칸
        regSelectCar=findViewById(R.id.RegSelectCar); //차 종류 받는칸인데 아마 내가 구현해놨을듯
        regInputEmail=findViewById(R.id.RegInputEmail); //이메일 입력받는 칸
        regEmailCheck=findViewById(R.id.RegEmailCheck); // 인증번호 입력 받는 칸 

        regFindCarBtn =findViewById(R.id.RegFindCarBtn); //차량 선택 하는 버튼 
        regEmailBtn = findViewById(R.id.RegEmailBtn);   // 이메일 입력받고 인증번호 보내는 버튼
        regEmailCheckBtn=findViewById(R.id.RegEmailCheckBtn); //인증번호 맞는지 확인하는 버튼
        registerBtn = findViewById(R.id.RegisterBtn); // 총괄 회원가입 칸 

        regHideSuccess=findViewById(R.id.RegHideSuccess); //인증번호 보낼시 하단에 나타나는 문구
        regHideCheckNumber = findViewById(R.id.RegHideCheckNumber); //인증번호 틀리면 나타나는 문구

        Alert = new AlertDialog.Builder(RegisterUI.this);
        Alert.setTitle("회원가입 Error");

        registerBtn.setOnClickListener(new View.OnClickListener() { //회원가입 버튼클릭시 실행되는 모든것 다적어야함
            @Override
            public void onClick(View view) {
                regInputIdStr=(regInputId.getText().toString());//아이디 입력받은거 String 값을 만든거
                regInputPwStr=(regInputPw.getText().toString());
                regCheckPwStr=(regCheckPw.getText().toString());
                regInputNameStr=(regInputName.getText().toString());

                if(register.isIdEmpty(regInputIdStr, Alert)) //ID 공백 여부 확인
                    return;
                else if(checkMemberData.isIdRegexMatched(regInputIdStr, Alert)) //ID 정규식 확인
                    return;
                else if(register.isPwEmpty(regInputPwStr, Alert)) //비밀번호란 공백 여부 확인
                    return;
                else if(checkMemberData.isPwRegexMatched(regInputPwStr, Alert)) //비밀번호 정규식 확인
                    return;
                else if(register.isPwSameEmpty(regCheckPwStr, Alert)) //비밀번호 확인란 공백 여부 확인
                    return;
                else if(checkMemberData.isPwSameMatched(regInputPwStr, regCheckPwStr, Alert))
                    return;
                else if(register.isNameEmpty(regInputNameStr, Alert)) // 이름란 공백 여부 확인
                    return;


                //여기까지 왔으면 위의 정규식 확인 및 공백 여부 확인 모두 통과했다는 뜻임.
                //이메일 인증번호 통과했는지 여부 및 차량 등록 여부 확인하면 됨.
                //아래에는 이제 DB에 연결해야하므로 MemberVO에 일단 넣기
                //DB 연결은 일단 해결 후에 작성


            }
        });
        regEmailBtn.setOnClickListener(new View.OnClickListener() { //인증번호 발생 버튼 눌럿을시 실행되는것
            @Override
            public void onClick(View view) {

                regInputEmailStr=(regInputEmail.getText().toString());
                if (!register.isEmailEmpty(regInputEmailStr, Alert)) { //이메일을 입력했다면


/*
                    String host = "smtp.naver.com";
                    final String username = "project_boardcar";
                    final String password = "yuse2022";
                    int port = 465;
                    String testVerificationCode = "stderr1234";
                    String receiver = "ys010610@naver.com";
                    String title = "Boardcar 인증번호 메일입니다.";
                    String body = "인증번호는 다음과 같습니다.\n  " + testVerificationCode + "\n이 인증번호를 입력해주세요";
                    MailSend ms = new MailSend();
                    new Thread(() -> {
                        ms.send(host, port, username, password, receiver, title, body);
                    }).start();*/

                    /*if(checkMemberData.isEmailRegexMatched(regInputEmailStr, Alert)){ //이메일 정규식을 만족했다면

                        //이메일 전송 코드 작성해서 넣기

                    }*/


                 /*if(DB에서 이메일 체크하고 있는지 없는지 확인){
                    //인증번호 날라오는거 함수? 암튼 그거 쓰기
                   regHideSuccess.setVisibility(View.VISIBLE);
                    regEmailCheckBtn.setEnabled(true);
                 }else{
                    AlertNoEditMsg("회원가입 실패","회원님의 정보를 찾을수 없어요");
                 }*/

                    regHideSuccess.setVisibility(View.VISIBLE); // 실제론 지워줘야함!
                    regEmailCheckBtn.setEnabled(true);
                }else {
                    AlertNoEditMsg("회원가입 실패","이메일을 입력해주세요");
                }
            }
        });

        regEmailCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regEmailCheckStr=(regEmailCheck.getText().toString());
                int test =123;
                if (!register.isEmailVerificationCodeEmpty(regEmailCheckStr, Alert)) { //인증번호란에 입력되어 있다면

                    if (regEmailCheckStr.equals("1234")){
                        AlertNoEditMsg("인증번호 일치 ","인증번호가 일치했습니다.");

                    }

                } else {
                    regHideCheckNumber.setVisibility(View.VISIBLE); //인증번호가 일치하지않아요 나타나는애
                }
            }
        });

        regFindCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SelectCarUI.class);
                intent.putExtra("SelectCar",0);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        regSelectCar.setText( intent.getStringExtra("CarName"));
    }
}
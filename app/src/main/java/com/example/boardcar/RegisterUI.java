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
import androidx.annotation.Nullable;
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
import LoginPackage.Register;

public class RegisterUI extends AppCompatActivity {
    private static final int REQUEST_CODE = 1000;
    EditText regInputId,regInputPw,regCheckPw,regInputName,regSelectCar,regInputEmail,regEmailCheck;
    Button regFindCarBtn,regEmailBtn ,regEmailCheckBtn,registerBtn;
    TextView regHideSuccess,regHideCheckNumber;
    String regInputIdStr, regInputPwStr, regCheckPwStr, regInputNameStr, regSelectCarStr, regInputEmailStr,regEmailCheckStr;
    boolean isEmailChecked = false; // 이메일 인증여부
    AlertDialog.Builder Alert;

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
        Register register = new Register(getBaseContext());
        setContentView(R.layout.activity_register_ui);
        regInputId = findViewById(R.id.RegInputId); //아이디 입력 받는칸
        regInputPw = findViewById(R.id.RegInputPw); //비밀번호 입력 받는칸
        regCheckPw = findViewById(R.id.RegCheckPw); // 비밀번호 같은지 받는칸
        regInputName = findViewById(R.id.RegInputName); // 회원이름 받는칸
        regSelectCar = findViewById(R.id.RegSelectCar); //차 종류 받는칸인데 아마 내가 구현해놨을듯
        regInputEmail = findViewById(R.id.RegInputEmail); //이메일 입력받는 칸
        regEmailCheck = findViewById(R.id.RegEmailCheck); // 인증번호 입력 받는 칸

        regFindCarBtn = findViewById(R.id.RegFindCarBtn); //차량 선택 하는 버튼
        regEmailBtn = findViewById(R.id.RegEmailBtn);   // 이메일 입력받고 인증번호 보내는 버튼
        regEmailCheckBtn = findViewById(R.id.RegEmailCheckBtn); //인증번호 맞는지 확인하는 버튼
        registerBtn = findViewById(R.id.RegisterBtn); // 총괄 회원가입 칸 

        regHideSuccess = findViewById(R.id.RegHideSuccess); //인증번호 보낼시 하단에 나타나는 문구
        regHideCheckNumber = findViewById(R.id.RegHideCheckNumber); //인증번호 틀리면 나타나는 문구

        Alert = new AlertDialog.Builder(RegisterUI.this);
        Alert.setTitle("회원가입 Error");
        EmailUtil emailUtil = new EmailUtil(getBaseContext()); // Email에 사용하는 emailUtil
        registerBtn.setOnClickListener(new View.OnClickListener() { //회원가입 버튼클릭시 실행되는 모든것 다적어야함
            @Override
            public void onClick(View view) {
                regInputIdStr = (regInputId.getText().toString());//아이디 입력받은거 String 값을 만든거
                regInputPwStr = (regInputPw.getText().toString());
                regCheckPwStr = (regCheckPw.getText().toString());
                regInputNameStr = (regInputName.getText().toString());
                regSelectCarStr = (regSelectCar.getText().toString());

                if (register.isIdEmpty(regInputIdStr, Alert)) //ID 공백 여부 확인
                    return;
                else if (checkMemberData.isIdRegexMatched(regInputIdStr, Alert)) //ID 정규식 확인
                    return;
                else if (register.isPwEmpty(regInputPwStr, Alert)) //비밀번호란 공백 여부 확인
                    return;
                else if (checkMemberData.isPwRegexMatched(regInputPwStr, Alert)) //비밀번호 정규식 확인
                    return;
                else if (register.isPwSameEmpty(regCheckPwStr, Alert)) //비밀번호 확인란 공백 여부 확인
                    return;
                else if (checkMemberData.isPwSameMatched(regInputPwStr, regCheckPwStr, Alert)) //비밀번호와 확인용 재입력 비밀번호를 비교
                    return;
                else if (register.isNameEmpty(regInputNameStr, Alert)) // 이름란 공백 여부 확인
                    return;
                else if (!isEmailChecked){ // 이메일 인증 여부
                    return;
                }
                if(register.runRegister(regInputIdStr, regInputPwStr, regInputNameStr, regInputEmailStr, regSelectCarStr)){
                    AlertNoEditMsg("회원가입 성공", "성공하였습니다!");
                    finish();
                }else{
                    AlertNoEditMsg("회원가입 실패", "실패하였습니다.");
                }
            }
        });
        regEmailBtn.setOnClickListener(new View.OnClickListener() { //인증번호 발생 버튼 눌럿을시 실행되는것
            @Override
            public void onClick(View view) {
                regInputEmailStr = (regInputEmail.getText().toString());
                if (!register.isEmailEmpty(regInputEmailStr, Alert)) { //이메일을 입력했다면
                    if(checkMemberData.isEmailRegexMatched(regInputEmailStr, Alert)){ //이메일 정규식을 만족했다면
                        if(emailUtil.sendAuthenticationCode(regInputEmailStr)){
                            regHideSuccess.setVisibility(View.VISIBLE);
                            regEmailCheckBtn.setEnabled(true);
                        }
                        else{
                            AlertNoEditMsg("이메일", "양식에 맞지 않는 이메일입니다.");
                        }
                    }
                } else {
                    AlertNoEditMsg("회원가입 실패", "이메일을 입력해주세요");
                }
            }
        });

        regEmailCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regEmailCheckStr = (regEmailCheck.getText().toString());
                if (!register.isEmailVerificationCodeEmpty(regEmailCheckStr, Alert)) { //인증번호란에 입력되어 있다면
                    if (emailUtil.authentication(regEmailCheckStr)) {
                        AlertNoEditMsg("인증번호 일치 ", "인증번호가 일치했습니다.");
                        isEmailChecked = true;
                    }
                    else {
                        regHideCheckNumber.setVisibility(View.VISIBLE); //인증번호가 일치하지않아요 나타나는애
                    }
                }
            }
        });

        regFindCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectCarUI.class);
                intent.putExtra("SelectCar", 0);
                startActivityForResult(intent,REQUEST_CODE);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String result = data.getStringExtra("CarName");
            regSelectCar.setText(result);
        }
    }
}
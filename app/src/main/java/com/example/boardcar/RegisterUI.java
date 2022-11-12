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

public class RegisterUI extends AppCompatActivity {

    EditText regInputId,regInputPw,regCheckPw,regInputName,regSelectCar,regInputEmail,regEmailCheck;
    Button regFindCarBtn,regEmailBtn ,regEmailCheckBtn,registerBtn;
    TextView regHideSuccess,regHideCheckNumber;
    String regInputIdStr, regInputPwStr, regCheckPwStr, regInputNameStr, regFindCarStr, regInputEmailStr,regEmailCheckStr;
    public void AlertNoEditMsg(String title, String msg) { //에러문구 나타내는 함수
        AlertDialog.Builder Alert = new AlertDialog.Builder(RegisterUI.this);
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
        regEmailBtn = findViewById(R.id.RegEmailBtn);   // 이메일 입력받고 인증번호 보내는 칸
        regEmailCheckBtn=findViewById(R.id.RegEmailCheckBtn); //인증번호 맞는지 확인하는칸
        registerBtn = findViewById(R.id.RegisterBtn); // 총괄 회원가입 칸 

        regHideSuccess=findViewById(R.id.RegHideSuccess); //인증번호 보낼시 하단에 나타나는 문구
        regHideCheckNumber = findViewById(R.id.RegHideCheckNumber); //인증번호 틀리면 나타나는 문구

        registerBtn.setOnClickListener(new View.OnClickListener() { //회원가입 버튼클릭시 실행되는 모든것 다적어야함
            @Override
            public void onClick(View view) {
                regInputIdStr=(regInputId.getText().toString());//아이디 입력받은거 String 값을 만든거
                regInputPwStr=(regInputPw.getText().toString());
                regCheckPwStr=(regCheckPw.getText().toString());
                regInputNameStr=(regInputName.getText().toString());
                
                

            }
        });
        regEmailBtn.setOnClickListener(new View.OnClickListener() { //인증번호 발생 버튼 눌럿을시 실행되는것
            @Override
            public void onClick(View view) {

                regInputEmailStr=(regInputEmail.getText().toString());
                if (regInputEmailStr.length() != 0) {
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
                if (regEmailCheckStr.length() != 0) {

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
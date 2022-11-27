package com.example.boardcar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangePWUI extends AppCompatActivity {
    EditText oldPw, newPw, checkPw;
    Button pwChangeBtn;
    String  oldPwStr, newPwStr, checkPwStr;

    public void AlertNoEditMsg(String title, String msg) { //에러문구 나타내는 함수
        AlertDialog.Builder Alert = new AlertDialog.Builder(ChangePWUI.this);
        Alert.setTitle(title);
        Alert.setMessage(msg);
        Alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });

        Alert.show();

    }
    public void AlertPwMsg(String title, String msg) { //팝업창인데 확인 누르면 전화면으로 넘어감
        AlertDialog.Builder Alert = new AlertDialog.Builder(ChangePWUI.this);
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
        setContentView(R.layout.activity_change_pwui);

        oldPw =findViewById(R.id.OldPw); //현재 비밀번호 입력받는칸
        newPw =findViewById(R.id.NewPw); //새로운 비밀번호 입력받는칸
        checkPw=findViewById(R.id.CheckPw); // 새로운 비밀번호 같은지 입력받는칸
        pwChangeBtn=findViewById(R.id.PwChangeBtn); //변경 받는 칸

        pwChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldPwStr = oldPw.getText().toString(); //String 타입 현재 입력받는칸
                newPwStr = newPw.getText().toString(); //String 타입 새로운 입력받는칸
                checkPwStr= checkPw.getText().toString();
                if(oldPwStr.equals("1234")){ //(1234) db에 있는 현재 비밀번호값 가져오기

                    if(newPwStr.equals(checkPwStr)){
                       // 여기서 입력한 비밀번호  DB에 업데이트하기 SQL
                        AlertPwMsg("비밀번호 변경 성공","비밀번호를 변경하셨습니다");

                    }
                    else{
                        AlertNoEditMsg("비밀번호 에러 ","입력하신 비밀번호가 일치하지않습니다.");
                    }
                }else{
                    AlertNoEditMsg("비밀번호 에러 ","현재 비밀번호가 일치하지않습니다");
                }
            }
        });



    }
}
package com.example.boardcar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.boardcar.databinding.ActivityLoginBinding;

import LoginPackage.Login;

public class LoginUI extends AppCompatActivity {
    
    private ActivityLoginBinding binding;
    String id , pw ;

    String DBUrl = "jdbc:oracle:thin:@//localhost:1521/xepdb1";
    String DBId = "scott";
    String DBPw = "tiger";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Login login = new Login();

        AlertDialog.Builder Alert = new AlertDialog.Builder(LoginUI.this);
        Alert.setTitle("로그인에러");

        binding.LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = binding.EditId.getText().toString();
                pw = binding.EditPw.getText().toString();

                if(login.isIdEmpty(id, Alert)){
                    //ID 입력란이 비었을 경우
                    return;
                }
                else if(login.isPwEmpty(pw, Alert)){
                    //PW 입력란이 비었을 경우
                    return;
                }
                else{
                    login.runLogin(id, pw, Alert);
                    //ID와 PW 두 개의 입력란에 어떤 값이든 모두 입력한 경우
                    //DB랑 연결해서 값 매칭할 것


                }

                /*if(id.length()==1) { //입력한 id 와 pw 를 db에서 가져온거랑 비교한 if문
                    
                    
                
                    if (binding.AutoLogin.isChecked()) { // 자동로그인 체크되어있는지 확인하는 if
                        Intent intent = new Intent(getApplicationContext(),MainUI.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else
                {

                    AlertDialog.Builder Alert = new AlertDialog.Builder(LoginUI.this);
                    Alert.setTitle("로그인에러");
                    Alert.setMessage("아이디 혹은 비밀번호를 잘못 입력했어요");
                    Alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {}
                    });

                    Alert.show();
                }*/
            }
        });
        binding.FindId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FindIDUI.class);
                startActivity(intent);
                finish();

            }
        });

        binding.FindPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FindPWUI.class);
                startActivity(intent);
                finish();
            }
        });
        binding.Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterUI.class);
                startActivity(intent);
                finish();
            }
        });
        
        
    }
}
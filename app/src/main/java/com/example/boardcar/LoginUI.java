package com.example.boardcar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.boardcar.databinding.ActivityLoginBinding;

import org.json.JSONException;

import LoginPackage.Login;

public class LoginUI extends AppCompatActivity {
    
    private ActivityLoginBinding binding;
    private String id, pw ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Login login = new Login(getApplicationContext());

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
                    try {
                        String sessionKey = login.runLogin(id, pw, Alert);
                        if(sessionKey != null){
                            //로그인에 성공함!
                            ((LoginInfo) getApplication()).setLogin(true);
                            SharedPreferences preferences = getSharedPreferences("LOGIN_INFO", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("UUID", sessionKey);

                            if (binding.AutoLogin.isChecked()) { // 자동로그인 체크되어있는지 확인하는 if
                                //Session 정보 활용해서 자동 로그인 기능 구현
                                editor.putBoolean("AUTO_LOGIN", true);
                            }
                            else
                                editor.putBoolean("AUTO_LOGIN", false);
                            editor.commit();
                            Intent intent = new Intent(getBaseContext(), MainUI.class);
                            startActivity(intent);
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
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
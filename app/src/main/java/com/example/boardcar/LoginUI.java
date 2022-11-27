package com.example.boardcar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
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
                        if(login.runLogin(id, pw, Alert) != null){
                            //로그인에 성공함!
                            Intent intent = new Intent(getApplicationContext(), MainUI.class);
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
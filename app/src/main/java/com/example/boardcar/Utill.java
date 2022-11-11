package com.example.boardcar;

import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Utill  extends AppCompatActivity {
    public void ToastMsg (AppCompatActivity Ma, String msg ){
        Toast.makeText(Ma, msg, Toast.LENGTH_SHORT).show();
    }
    public void AlertNoEditMsg(AppCompatActivity Ma,String title, String msg) {
        AlertDialog.Builder Alert = new AlertDialog.Builder(Ma);
        Alert.setTitle(title);
        Alert.setMessage(msg);
        Alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });

        Alert.show();

    }
}

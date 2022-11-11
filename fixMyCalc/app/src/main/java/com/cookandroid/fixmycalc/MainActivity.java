package com.cookandroid.fixmycalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText num1, num2;
    Button plus, minus, multi, divi, rema;
    TextView result;
    String box1, box2;
    Float cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("초간단 계산기(FixMyCalc)");
        num1 = (EditText) findViewById(R.id.num1);
        num2 = (EditText) findViewById(R.id.num2);
        plus =(Button) findViewById(R.id.plus);
        minus =(Button) findViewById(R.id.minus);
        multi =(Button) findViewById(R.id.multiply);
        divi =(Button) findViewById(R.id.division);
        rema = (Button) findViewById(R.id.remain);
        result = (TextView) findViewById(R.id.cal_result);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                box1 = num1.getText().toString();
                box2 = num2.getText().toString();
                if(box1.trim().equals("")||box2.trim().equals("")){
                    Toast.makeText(getApplicationContext(), "값을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else{
                    cal = Float.parseFloat(box1) + Float.parseFloat(box2);
                    result.setText("계산 결과 : "+cal.toString());}
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                box1 = num1.getText().toString();
                box2 = num2.getText().toString();
                if(box1.trim().equals("")||box2.trim().equals("")){
                    Toast.makeText(getApplicationContext(), "값을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else{
                    cal = Float.parseFloat(box1) - Float.parseFloat(box2);
                    result.setText("계산 결과 : "+cal.toString());}
            }
        });

        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                box1 = num1.getText().toString();
                box2 = num2.getText().toString();
                if(box1.trim().equals("")||box2.trim().equals("")){
                    Toast.makeText(getApplicationContext(), "값을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else{
                    cal = Float.parseFloat(box1) * Float.parseFloat(box2);
                    result.setText("계산 결과 : "+cal.toString());}
            }
        });
        divi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                box1 = num1.getText().toString();
                box2 = num2.getText().toString();
                if(box1.trim().equals("")||box2.trim().equals("")){
                    Toast.makeText(getApplicationContext(), "값을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if(box2.trim().equals("0")){
                    Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다", Toast.LENGTH_SHORT).show();
                }else{
                    cal = Float.parseFloat(box1) / Float.parseFloat(box2);
                    result.setText("계산 결과 : "+cal.toString());}
            }
        });
        rema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                box1 = num1.getText().toString();
                box2 = num2.getText().toString();
                if(box1.trim().equals("")||box2.trim().equals("")){
                    Toast.makeText(getApplicationContext(), "값을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if(box2.trim().equals("0")){
                    Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다", Toast.LENGTH_SHORT).show();
                }else{
                    cal = Float.parseFloat(box1) % Float.parseFloat(box2);
                    result.setText("계산 결과 : "+cal.toString());}
            }
        });

    }
}
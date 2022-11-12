package com.example.boardcar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectCarUI extends AppCompatActivity {

String selectCarNameStr;
TextView selectCarName;
Button selectCarBtn;
ImageView bMWLogo ,hyundaiLogo,kIALogo,toyotaLogo;
Intent intent;
    public void AlertNoEditMsg(String title, String msg) { //에러문구 나타내는 함수
        AlertDialog.Builder Alert = new AlertDialog.Builder(SelectCarUI.this);
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
        setContentView(R.layout.activity_select_car_ui);

        selectCarName=findViewById(R.id.SelectCarName);
        selectCarBtn =findViewById(R.id.SelectCarBtn);
        hyundaiLogo=findViewById(R.id.HyundaiLogo);
        kIALogo=findViewById(R.id.KIALogo);
        toyotaLogo=findViewById(R.id.ToyotaLogo);
        bMWLogo=findViewById(R.id.BMWLogo);


        RecyclerView RvCar= findViewById(R.id.RvCar);
        RecyclerViewCarAdapter adapter = new RecyclerViewCarAdapter();



        adapter.setOnClickListener(new RecyclerViewCarAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(String data) {
                selectCarName.setText(data);
            }
        });

        selectCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectCarNameStr = selectCarName.getText().toString();
                intent=getIntent();
                int type=intent.getIntExtra("SelectCar",0);
                if (selectCarNameStr.length() != 0) {
                    if(type==0){
                        intent = new Intent(getApplicationContext(),RegisterUI.class);
                        intent.putExtra("CarName",selectCarName.getText().toString());
                        startActivity(intent);
                        finish();
                    }else if(type==1){
                        //DB에서 바로 selectCarName.getText().toString() 이거 넣어주면됨
                        finish();

                    }


                } else {
                    AlertNoEditMsg("차량선택실패","차량을 선택해주세요");
                }
            }
        });
        kIALogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarList(adapter,RvCar,"모하비","k시리즈");
            }
        });
        hyundaiLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarList(adapter,RvCar,"아반떼","소나타");
            }
        });
        toyotaLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarList(adapter,RvCar,"아발론","프리우스");
            }
        });
       bMWLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarList(adapter,RvCar,"5시리즈","7시리즈");
            }
        });
        


    }
    public void CarList(RecyclerViewCarAdapter adapter, RecyclerView RvCar ,String str1 ,String str2) { //
        RvCar.setVisibility(View.VISIBLE);
        adapter.clearItem();
        adapter.addItem(new RecyclerViewCarDataModel(str1));
        adapter.addItem(new RecyclerViewCarDataModel(str2));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RvCar.setLayoutManager(layoutManager);
        RvCar.setAdapter(adapter);
    }


}
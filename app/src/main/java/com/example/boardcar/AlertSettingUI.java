package com.example.boardcar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class AlertSettingUI extends AppCompatActivity {
Switch boardAlert, consumableAlert;
private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_setting_ui);

        boardAlert=findViewById(R.id.BoardAlert);
        consumableAlert=findViewById(R.id.ConsumableAlert);
        preferences = getSharedPreferences("Alert",MODE_PRIVATE);

        boardAlert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(boardAlert.isChecked()){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("board","ON");
                    editor.commit();


                }
            }
        });
        consumableAlert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(consumableAlert.isChecked()){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("consumable","ON");
                    editor.commit();


                }
            }
        });

    }
}
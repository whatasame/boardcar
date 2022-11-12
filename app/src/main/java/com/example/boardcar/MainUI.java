package com.example.boardcar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.boardcar.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainUI extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.boardcar.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bottomNavigationView= binding.BottomNavi;
    getSupportFragmentManager().beginTransaction().add(R.id.MainFrame,new FragmentMainUI()).commit();
    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.BottomHome:
                    getSupportFragmentManager().beginTransaction().replace(R.id.MainFrame,new FragmentMainUI()).commit();
                    break;
                case R.id.BottomBoard:
                    getSupportFragmentManager().beginTransaction().replace(R.id.MainFrame,new BoardUI()).commit();
                    break;
                case R.id.BottomMyPage:
                    getSupportFragmentManager().beginTransaction().replace(R.id.MainFrame,new MyPageUI()).commit();
                    break;
            }
            return true;
        }
    });



    }
}
package com.example.boardcar;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MyPageUI extends Fragment implements View.OnClickListener {

    TextView pwChange, alertSet, carChange;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_my_page, container, false);

        pwChange = v.findViewById(R.id.PwChange);
        alertSet = v.findViewById(R.id.AlertSet);
        carChange = v.findViewById(R.id.CarChange);
        pwChange.setOnClickListener(this);
        alertSet.setOnClickListener(this);
        carChange.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.PwChange:
                Intent intent = new Intent(getActivity(), ChangePWUI.class);
                startActivity(intent);
                break;
            case R.id.AlertSet:
                intent = new Intent(getActivity(), AlertSettingUI.class);
                startActivity(intent);
                break;
            case R.id.CarChange:
                intent = new Intent(getActivity(), SelectCarUI.class);
                intent.putExtra("SelectCar",1);
                startActivity(intent);
                break;
        }
    }

}



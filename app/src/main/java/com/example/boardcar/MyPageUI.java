package com.example.boardcar;

import static android.content.Context.MODE_PRIVATE;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MyPageUI extends Fragment implements View.OnClickListener {

    TextView pwChange, alertSet, carChange ,logOut;
    View v;
    Intent intent;

    public void AlertIDMsg(String title, String msg) { //팝업창인데 확인 누르면 전화면으로 넘어감
        AlertDialog.Builder Alert = new AlertDialog.Builder(getActivity());
        Alert.setTitle(title);
        Alert.setMessage(msg);
        //확인버튼
        Alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //자동로그인 해제 및 로그인 상태 삭제
                SharedPreferences preferences = getContext().getSharedPreferences("LOGIN_INFO", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("UUID", "");
                editor.putBoolean("AUTO_LOGIN", false);
                editor.commit();
                ((LoginInfo) getActivity().getApplication()).setLogin(false);

                intent = new Intent(getActivity(),MainUI.class);
                getActivity().finish();
                startActivity(intent);

            }
        });
        //취소버튼
        Alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        Alert.show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_my_page, container, false);

        pwChange = v.findViewById(R.id.PwChange);
        alertSet = v.findViewById(R.id.AlertSet);
        carChange = v.findViewById(R.id.CarChange);
        logOut = v.findViewById(R.id.LogOut);
        pwChange.setOnClickListener(this);
        alertSet.setOnClickListener(this);
        carChange.setOnClickListener(this);
        logOut.setOnClickListener(this);

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
            case R.id.LogOut:
                AlertIDMsg("로그아웃","로그아웃을 진행 하시겠습니까?");
                break;
        }
    }

}


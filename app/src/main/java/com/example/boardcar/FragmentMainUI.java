package com.example.boardcar;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import Back.HttpClient;
import Back.SessionManager;


public class FragmentMainUI extends Fragment implements View.OnClickListener {
    TextView mainUserName, mainUserCar, nearSiteText, consumableText;
    Button tireBtn, brakeBtn, wiperBtn, engineBtn, airFilterBtn, batteryBtn;
    ProgressBar tireProgress, brakeProgress, wiperProgress, engineProgress, airFilterProgress, batteryProgress;
    ImageView gasStation, carCenter;
    LinearLayout nearSiteLinear, consumableLinear;
    View v;
    ViewGroup notLoingInfo;
    private SharedPreferences ConsumableFile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_main, container, false);
        //비회원,회원 문구 나타나는애
        mainUserName = v.findViewById(R.id.MainUserName);
        mainUserCar = v.findViewById(R.id.MainUserCar);
        notLoingInfo = v.findViewById(R.id.NotLoginInfo);
        //소모품 추적 버튼
        tireBtn = v.findViewById(R.id.TireBtn);
        brakeBtn = v.findViewById(R.id.BrakeBtn);
        wiperBtn = v.findViewById(R.id.WiperBtn);
        engineBtn = v.findViewById(R.id.EngineBtn);
        airFilterBtn = v.findViewById(R.id.AirFilterBtn);
        batteryBtn = v.findViewById(R.id.BatteryBtn);
        //소모품 추적 프로세스바
        tireProgress = v.findViewById(R.id.TireProgress);
        brakeProgress = v.findViewById(R.id.BrakeProgress);
        wiperProgress = v.findViewById(R.id.WiperProgress);
        engineProgress = v.findViewById(R.id.EngineProgress);
        airFilterProgress = v.findViewById(R.id.AirFilterProgres);
        batteryProgress = v.findViewById(R.id.BatteryProgress);
        //근처장소
        gasStation = v.findViewById(R.id.GasStation);
        carCenter = v.findViewById(R.id.CarCenter);

        //비로그인시 안보이고 로그인할떄 보여야하는애들
        nearSiteLinear = v.findViewById(R.id.NearSiteLinear);
        nearSiteText = v.findViewById(R.id.NearSiteText);
        consumableText = v.findViewById(R.id.ConsumableText);
        consumableLinear = v.findViewById(R.id.ConsumableLinear);

        //선택 눌럿을때 반응하는 리스너
        gasStation.setOnClickListener(this);
        carCenter.setOnClickListener(this);
        tireBtn.setOnClickListener(this);
        notLoingInfo.setOnClickListener(this);
        brakeBtn.setOnClickListener(this);
        wiperBtn.setOnClickListener(this);
        engineBtn.setOnClickListener(this);
        airFilterBtn.setOnClickListener(this);
        batteryBtn.setOnClickListener(this);

        //파일 생성
        ConsumableFile = getContext().getSharedPreferences("consumableinfo", MODE_PRIVATE);
        //파일불러오기  초기값은 0
        tireProgress.setProgress(ConsumableFile.getInt("tireinfo", 0));
        wiperProgress.setProgress(ConsumableFile.getInt("wiperinfo", 0));
        brakeProgress.setProgress(ConsumableFile.getInt("brakepadinfo", 0));
        engineProgress.setProgress(ConsumableFile.getInt("engineinfo", 0));
        airFilterProgress.setProgress(ConsumableFile.getInt("airFilterinfo", 0));
        batteryProgress.setProgress(ConsumableFile.getInt("batteryinfo", 0));

        /*
        * 로그인 성공시
        *         //mainUserCar.setText("Sonata");
                  //mainUserName.setText("User");
                  //nearSiteLinear.setVisibility(View.VISIBLE);
        * */
        // DB network test
        HttpClient httpClient = new HttpClient(null, getContext());
        if(!httpClient.httpTest()){
            Utill utill = new Utill();
            utill.AlertNoEditMsg(new AppCompatActivity(),"DB 네트워크 오류", "DB네트워크가 원할하지 않습니다.");
        }

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LOGIN_INFO", MODE_PRIVATE);
        // 내장에 UUID가 존재 및 AUTO_LOGIN이 true일때 (getBoolean false는 default value)
        if (sharedPreferences.contains("UUID") & sharedPreferences.getBoolean("AUTO_LOGIN", false)) {
            SessionManager sessionManager = new SessionManager(getContext()); // 자동로그인
            System.out.println("session : " + sessionManager.session);
            if (sessionManager.session != null) {
                String result = sessionManager.getUserInfo();
                ((LoginInfo) getActivity().getApplication()).setLogin(true);
                if (result != null) {
                    mainUserName.setText(sessionManager.getNAME());
                    mainUserCar.setText(sessionManager.getCarName()); //sessionManager.getCarName()
                    nearSiteLinear.setVisibility(View.VISIBLE);
                    nearSiteText.setVisibility(View.VISIBLE);
                    consumableText.setVisibility(View.VISIBLE);
                    consumableLinear.setVisibility(View.VISIBLE);
                }
            }
        }
        // 자동로그인을 안하고, 앱을 켠 이후 로그인을 한번 이상 성공하였을시
        else if (((LoginInfo) getActivity().getApplication()).isLogin()) {
            SessionManager sessionManager = new SessionManager(getContext()); //
            if (sessionManager.session != null) {
                String result = sessionManager.getUserInfo();
                if (result != null) {
                    mainUserName.setText(sessionManager.getNAME());
                    mainUserCar.setText(sessionManager.getCarName());
                    nearSiteLinear.setVisibility(View.VISIBLE);
                    nearSiteText.setVisibility(View.VISIBLE);
                    consumableText.setVisibility(View.VISIBLE);
                    consumableLinear.setVisibility(View.VISIBLE);
                }
            }
        }
        return v;
    }

    @Override
    public void onClick(View view) {
        CustomDialog log = new CustomDialog(getActivity());
        switch (view.getId()) {
            //if(여기서 로그인 세션=0일경우 실행할수있도록) 괄호처리하고 로그인 안되있을시 버튼 누르면 로그인 페이지 이동 세션 =1 일경우 무반응
            case R.id.NotLoginInfo:
                if(!((LoginInfo) getActivity().getApplication()).isLogin()) {
                    Intent intent = new Intent(getActivity(), LoginUI.class);
                    startActivity(intent);
                }
                break;
            case R.id.GasStation:
                Intent GasStaion = new Intent(getActivity(), NearSiteUI.class);
                startActivity(GasStaion);
                break;
            case R.id.CarCenter:
                Intent CarCenter = new Intent(getActivity(), NearSiteUI.class);
                startActivity(CarCenter);
                break;
            case R.id.TireBtn:
                log.callFunction(tireProgress, "tire");
                break;
            case R.id.BrakeBtn:
                log.callFunction(brakeProgress, "brakepad");
                break;
            case R.id.WiperBtn:
                log.callFunction(wiperProgress, "wiper");
                break;
            case R.id.EngineBtn:
                log.callFunction(engineProgress, "engine");
                break;
            case R.id.AirFilterBtn:
                log.callFunction(airFilterProgress, "airFilter");
                break;
            case R.id.BatteryBtn:
                log.callFunction(batteryProgress, "battery");
                break;
        }

    }


}
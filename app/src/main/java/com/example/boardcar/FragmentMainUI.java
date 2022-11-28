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

import androidx.fragment.app.Fragment;


public class FragmentMainUI extends Fragment implements View.OnClickListener {
    TextView mainUserName, mainUserCar,nearSiteText,consumableText;
    Button tireBtn,brakeBtn,wiperBtn,engineBtn,airFilterBtn,batteryBtn;
    ProgressBar tireProgress,brakeProgress,wiperProgress,engineProgress,airFilterProgress,batteryProgress;
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
        mainUserName=v.findViewById(R.id.MainUserName);
        mainUserCar=v.findViewById(R.id.MainUserCar);
        notLoingInfo=v.findViewById(R.id.NotLoginInfo);
        //소모품 추적 버튼
        tireBtn=v.findViewById(R.id.TireBtn);
        brakeBtn=v.findViewById(R.id.BrakeBtn);
        wiperBtn=v.findViewById(R.id.WiperBtn);
        engineBtn=v.findViewById(R.id.EngineBtn);
        airFilterBtn=v.findViewById(R.id.AirFilterBtn);
        batteryBtn=v.findViewById(R.id.BatteryBtn);
        //소모품 추적 프로세스바
        tireProgress=v.findViewById(R.id.TireProgress);
        brakeProgress=v.findViewById(R.id.BrakeProgress);
        wiperProgress=v.findViewById(R.id.WiperProgress);
        engineProgress=v.findViewById(R.id.EngineProgress);
        airFilterProgress=v.findViewById(R.id.AirFilterProgres);
        batteryProgress=v.findViewById(R.id.BatteryProgress);
        //근처장소
        gasStation=v.findViewById(R.id.GasStation);
        carCenter=v.findViewById(R.id.CarCenter);

        //비로그인시 안보이고 로그인할떄 보여야하는애들
        nearSiteLinear=v.findViewById(R.id.NearSiteLinear);
        nearSiteText=v.findViewById(R.id.NearSiteText);
        consumableText=v.findViewById(R.id.ConsumableText);
        consumableLinear=v.findViewById(R.id.ConsumableLinear);

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
        ConsumableFile=getContext().getSharedPreferences("consumableinfo",MODE_PRIVATE);
        //파일불러오기  초기값은 0
        tireProgress.setProgress(ConsumableFile.getInt("tireinfo",0));
        wiperProgress.setProgress(ConsumableFile.getInt("wiperinfo",0));
        brakeProgress.setProgress(ConsumableFile.getInt("brakepadinfo",0));
        engineProgress.setProgress(ConsumableFile.getInt("engineinfo",0));
        airFilterProgress.setProgress(ConsumableFile.getInt("airFilterinfo",0));
        batteryProgress.setProgress(ConsumableFile.getInt("batteryinfo",0));



        mainUserCar.setText("Sonata");
        mainUserName.setText("User");


        return v;

        /*

        if(세션 확인후)
        mainUserName.setText("id 값 DB에서 가져와서 보여주기");
        mainuserCar.setText("위와동일");

         */

        /*
        *
        * 제 생각은
        * 앱 내에 txt 파일에 NULL 저장해 놓다가
        * 그 자동로그인이 체크 된 상태에서 로그인에 성공한다면
        * txt 파일 내부에 session 값이나 어떤 값을 넣고
        * 저장을 하는거죠
        *
        * 그래서 앱을 정상 종료하고
        * 새로 앱을 실행시켰을 때 앱이 실행될 때 나오는 MainActivity 거기서 그 파일을 열고
        * 그 파일의 내용이 NULL 이라면 그 로그인 하세요 화면이 뜨는거고
        * 아니면 로그인 된 상태로 뜨는거죠
        *
        *
        * 로그아웃 버튼을 누르면 그 txt 파일을 NULL 로 수정하는거죠
        *
        *
        *
        * */


    }

    @Override
    public void onClick(View view) {
        CustomDialog log = new CustomDialog(getActivity());
        switch (view.getId()) {
            //if(여기서 로그인 세션=0일경우 실행할수있도록) 괄호처리하고 로그인 안되있을시 버튼 누르면 로그인 페이지 이동 세션 =1 일경우 무반응
            case R.id.NotLoginInfo:
                Intent intent = new Intent(getActivity(), LoginUI.class);
                startActivity(intent);
                break;
            case R.id.GasStation :
                Intent GasStaion = new Intent(getActivity(), NearSiteUI.class);
                startActivity(GasStaion);
                break;
            case R.id.CarCenter:
                Intent CarCenter = new Intent(getActivity(),NearSiteUI.class);
                startActivity(CarCenter);
                break;
            case R.id.TireBtn:
                log.callFunction(tireProgress,"tire");
                break;
            case R.id.BrakeBtn:
                log.callFunction(brakeProgress,"brakepad");
                break;
            case R.id.WiperBtn:
                log.callFunction(wiperProgress,"wiper");
                break;
            case R.id.EngineBtn:
                log.callFunction(engineProgress,"engine");
                break;
            case R.id.AirFilterBtn:
                log.callFunction(airFilterProgress,"airFilter");
                break;
            case R.id.BatteryBtn:
                log.callFunction(batteryProgress,"battery");
                break;
        }

    }


}
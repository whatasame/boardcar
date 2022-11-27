package com.example.boardcar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BoardEditUI extends AppCompatActivity {
    EditText boardEditTitle , boardEditBody;
    String boardEditTitleStr, boardEditBodyStr , rBtnTypeStr;
    Button boardEditBtn;
    Intent intent;
    RadioButton rBtnFree, rBtnCar;
    public void AlertIDMsg(String title, String msg) { //팝업창인데 확인 누르면 전화면으로 넘어감
        AlertDialog.Builder Alert = new AlertDialog.Builder(BoardEditUI.this);
        Alert.setTitle(title);
        Alert.setMessage(msg);
        Alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        Alert.show();

    }
    public void AlertNoEditMsg(String title, String msg) { //에러문구 나타내는 함수
        AlertDialog.Builder Alert = new AlertDialog.Builder(BoardEditUI.this);
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
        setContentView(R.layout.activity_board_edit_ui);
        boardEditBody=findViewById(R.id.BoardEditBody);
        boardEditTitle=findViewById(R.id.BoardEditTitle);
        boardEditBtn=findViewById(R.id.BoardEditBtn);
        rBtnFree=findViewById(R.id.RBtnFree);
        rBtnCar=findViewById(R.id.RBtnCar);




        //글 수정에서 글쓰기로 넘어왔을때 전에 적었던거 다시 적혀있게 하는애  
        intent=getIntent();
        int type=intent.getIntExtra("type",0);
        String Title=intent.getStringExtra("Title");
        String Body=intent.getStringExtra("Body");
        if(type==1){ 
            boardEditTitle.setText(Title);
            boardEditBody.setText(Body);
        }

        boardEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boardEditBodyStr = boardEditBody.getText().toString();
                boardEditTitleStr=boardEditTitle.getText().toString();




                if(boardEditTitleStr.length()!=0 && boardEditBody.length() !=0){ // 글 비어있는지 확인
                    //여기서  boardEditBodyStr = 글내용  boardEditTitleStr=글제목 DB에 저장시켜주기
                    AlertIDMsg("글쓰기 작성완료","글쓰기가 작성이 완료되었습니다");
                   if(rBtnFree.isChecked()){
                       //DB에 자유게시판으로 보내면됨 본문내용 등등 전부다
                   }else{
                       //DB에서 차량게시판으로 보내면됨 본문내용 등등 전부다;
                   };
                }else{
                    AlertNoEditMsg("글쓰기 실패","제목 또는 내용을 작성하세요");
                }

            }
        });
    }
}
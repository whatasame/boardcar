package com.example.boardcar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

import Back.HttpRequest;
import Community.BoardUtil;

public class BoardEditUI extends AppCompatActivity {
    EditText boardEditTitle, boardEditBody;
    String boardEditTitleStr, boardEditBodyStr, rBtnTypeStr;
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
        boardEditBody = findViewById(R.id.BoardEditBody);
        boardEditTitle = findViewById(R.id.BoardEditTitle);
        boardEditBtn = findViewById(R.id.BoardEditBtn);
        rBtnFree = findViewById(R.id.RBtnFree);
        rBtnCar = findViewById(R.id.RBtnCar);

        //글 수정에서 글쓰기로 넘어왔을때 전에 적었던거 다시 적혀있게 하는애  
        intent = getIntent();
        int type = intent.getIntExtra("type", 0);
        String Title = intent.getStringExtra("Title");
        String Body = intent.getStringExtra("Body");
        String pid = intent.getStringExtra("pid");

        if (type == 1) { //수정하기 버튼 클릭시 넘어오는 화면?
            boardEditTitle.setText(Title);
            boardEditBody.setText(Body);
        }

        boardEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (type == 0) {
                    //글을 새로 등록할 때
                    boardEditBodyStr = boardEditBody.getText().toString();
                    boardEditTitleStr = boardEditTitle.getText().toString();

                    BoardUtil boardUtil = new BoardUtil(getBaseContext());

                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                    if (boardEditTitleStr.length() != 0 && boardEditBody.length() != 0) { // 글 비어있는지 확인
                        //여기서  boardEditBodyStr = 글내용  boardEditTitleStr=글제목 DB에 저장시켜주기

                        if (rBtnFree.isChecked()) {
                            if (boardUtil.uploadPost(format.format(date), boardEditTitleStr, boardEditBodyStr, "자유")) {
                                AlertIDMsg("글쓰기 작성완료", "자유게시판에 글쓰기가 작성이 완료되었습니다");
                            } else {
                                AlertNoEditMsg("글쓰기 실패", "작성 실패");
                            }
                            //DB에 자유게시판으로 보내면됨 본문내용 등등 전부다
                        } else {
                            if (boardUtil.uploadPost(format.format(date), boardEditTitleStr, boardEditBodyStr, "차량")) {
                                AlertIDMsg("글쓰기 작성완료", "차량게시판에 글쓰기가 작성이 완료되었습니다");
                            } else {
                                AlertNoEditMsg("글쓰기 실패", "작성 실패");
                            }
                            //DB에서 차량게시판으로 보내면됨 본문내용 등등 전부다;
                        }
                    } else {
                        AlertNoEditMsg("글쓰기 실패", "제목 또는 내용을 작성하세요.");
                    }

                } else {
                    //글을 수정하려고 하는 경우
                    boardEditBodyStr = boardEditBody.getText().toString();

                    //게시판 종류 바꾸는 RadioButton을 일시적으로 수정할 수 없게 만들고, 게시글 제목 또한 수정 불가능하게 만들어야됌

                    BoardUtil boardUtil = new BoardUtil(getBaseContext());

                    if(boardEditBodyStr.length() != 0){
                        //수정한 글의 내용이 빈칸이 아니라면 수정할 수 있음

                        if(boardUtil.updatePost(Integer.parseInt(pid), boardEditBodyStr)) {
                            //글 수정 성공했다는 뜻.

                            AlertIDMsg("게시글 수정완료", "게시글 수정이 완료되었습니다.");
                        }
                        else{
                            AlertNoEditMsg("게시글 수정 실패", "게시글 수정에 실패하였습니다.");
                        }
                    }
                    else{
                        AlertNoEditMsg("게시글 수정 실패", "게시글의 내용은 비어있을 수 없습니다.");
                    }
                }
            }
        });
    }
}
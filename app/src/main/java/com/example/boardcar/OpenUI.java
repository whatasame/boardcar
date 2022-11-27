package com.example.boardcar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OpenUI extends AppCompatActivity {
    TextView openBoardName,openEdit,openDelete,openTitle,openBody,openWriter;
    Button openReCommendBtn,openDeprecatedBtn,commentEditBtn;
    String openTitleStr ,openBodyStr,commentEditStr;
    EditText commentEdit;
    RecyclerView commentList;
    Intent intent;

    //삭제버튼 눌렀을시 확인 버튼 한번더 나타나게하는데 확인 버튼 누를경우 삭제해야함
    public void AlertIDMsg(String title, String msg) { //팝업창인데 확인 누르면 전화면으로 넘어감
        AlertDialog.Builder Alert = new AlertDialog.Builder(OpenUI.this);
        Alert.setTitle(title);
        Alert.setMessage(msg);
        //확인버튼
        Alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //DB에서 해당글 삭제하는애 넣어주면됨
                finish();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_ui);

        openBoardName = findViewById(R.id.OpenBoardName);
        openEdit = findViewById(R.id.OpenEdit);
        openDelete=findViewById(R.id.OpenDelete);
        openTitle=findViewById(R.id.OpenTitle);
        openWriter=findViewById(R.id.OpenWriter);
        openBody=findViewById(R.id.OpenBody);
        openReCommendBtn=findViewById(R.id.OpenReCommendBtn);
        openDeprecatedBtn=findViewById(R.id.OpenDeprecatedBtn);
        commentEditBtn=findViewById(R.id.CommentEditBtn);
        commentEdit=findViewById(R.id.CommentEdit);
        commentList=findViewById(R.id.CommentList);

        //제목과 작성자를 서버로 보내서 애랑 일치하는 글을 불러와서 각각 setText로 삽입시켜서 보여주는 시스템;
        
        intent=getIntent();
        String Title=intent.getStringExtra("title");
        String Writer=intent.getStringExtra("writer");
        //openBoardName.setText(); <-안에 게시판 종류값 넣어주고
        //openTitle.setText(); <-제목값 넣어주고
        //openBody.setText();<-게시판 본문 넣어주고
        //openWriter.setText();<-작성자 넣어주기
        //openReCommendBtn.setText();<-추천수
        //openDeprecatedBtn.setText();<-비추천수
        //댓글 생성기
        RecyclerViewCommentAdapter adapter = new RecyclerViewCommentAdapter();
        //댓글 불러올때 DB에서 정보를 불러와서 for문으로 다돌린다
        CommentList(adapter,commentList,"김상원","그는신인가?","2022-11-23");
        
        //수정하기 눌렀을시 글과 함께 화면전환
        openEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTitleStr = openTitle.getText().toString();
                openBodyStr = openBody.getText().toString();
                Intent intent = new Intent(getApplicationContext(),BoardEditUI.class);
                intent.putExtra("Title",openTitleStr);
                intent.putExtra("Body",openBodyStr);
                intent.putExtra("type",1);

                startActivity(intent);
                finish();
            }
        });
        //삭제 버튼 눌렀을시
        openDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //위에보면 이친구있는데 거기에 db 삭제하는 기능 넣어야함
                AlertIDMsg("정말 삭제하시겠습니까?" ,"해당글을 삭제하고 싶으시면 확인 버튼을 눌러주세요 ");
            }
        });
        //추천 버튼 눌렀을시
        openReCommendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // openReCommendBtn.setText(); setText안에 jdbc에서 추천수 넣어주면됨
            }
        });
        //비추천 버튼 눌렀을시
        openDeprecatedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openDeprecatedBtn.setText(); 위와동일 근대 중복체크 어찌함?
            }
        });
        //댓글버튼 눌럿을시  댓글저장하는애
        commentEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentEditStr=commentEdit.getText().toString();
                //db로 작성된댓글 보낼수 있으면됨 여기서 세션에 있는 작성자 정보를 함께보낸다.
            }
        });

    }
    //댓글 보여주고 작성하는아이인데 각각 작성자 본문 작성일시 순으로 넣어주면된다
    public void CommentList(RecyclerViewCommentAdapter adapter, RecyclerView commentList,String writer,String body,String regdate) { //리스트 보여주고 추가한다

        adapter.clearItem();
        adapter.addItem(new RecyclerViewCommentDataModel (writer,body,regdate));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        commentList.setLayoutManager(layoutManager);
        commentList.setAdapter(adapter);
    }
}
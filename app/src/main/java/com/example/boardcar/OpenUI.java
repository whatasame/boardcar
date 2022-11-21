package com.example.boardcar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OpenUI extends AppCompatActivity {
    TextView openBoardName,openEdit,openDelete,openTitle,openBody;
    Button openReCommendBtn,openDeprecatedBtn,commentEditBtn;
    String openTitleStr ,openBodyStr;
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
        openBody=findViewById(R.id.OpenBody);
        openReCommendBtn=findViewById(R.id.OpenReCommendBtn);
        openDeprecatedBtn=findViewById(R.id.OpenDeprecatedBtn);
        commentEditBtn=findViewById(R.id.CommentEditBtn);
        commentEdit=findViewById(R.id.CommentEdit);
        commentList=findViewById(R.id.CommentList);


        //BoardUI 에서  예시 =intent.putExtra("Tilte",변수명)에서 넘긴걸;
        String Title=intent.getStringExtra("Title");
        String Writer=intent.getStringExtra("Writer");

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
                AlertIDMsg("정말 삭제하시겠습니까?" ,"해당글을 삭제하고 싶으시면 확인 버튼을 눌러주세요 ");
            }
        });
    }
}
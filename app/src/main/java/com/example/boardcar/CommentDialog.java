package com.example.boardcar;



import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class CommentDialog {

    private Context context;
    public CommentDialog(Context context) {
        this.context = context;
    }


    public void callFunction() {


        final Dialog dlg = new Dialog(context);

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dlg.setContentView(R.layout.comment_dialog);

        dlg.show();

        EditText message = (EditText) dlg.findViewById(R.id.CommentMesgase);
        final Button okButton = (Button) dlg.findViewById(R.id.CommentOkBtn);
        final Button cancelButton = (Button) dlg.findViewById(R.id.CommentCancelBtn);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = message.getText().toString();
                //str 을 이제 DB에 업데이트시키면됨 // 댓글수정
                Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
                dlg.dismiss();
            }
        });
        cancelButton.setOnClickListener(view -> {

            dlg.dismiss();
        });
    }
}
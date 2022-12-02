package com.example.boardcar;



import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CommentDialog {

    private Context context;
//    private int rid;
    public CommentDialog(Context context) {
        this.context = context;
//        this.rid = rid;
    }

    /**
     *
     * 댓글 수정 클릭시 사용되는 메소드 (구현실패)
     * @param position {int} 몇번째 댓글에서 선택되었는지의 값
     *
     */
    public void callFunction(int position) {


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

//                ReplyUtil replyUtil = new ReplyUtil(context);

                String str = message.getText().toString();
//                replyUtil.updateReply(rid,str);
                //str 을 이제 DB에 업데이트시키면됨 // 댓글수정
                Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
                // position 0~ size-1

                Toast.makeText(context, position+"번째", Toast.LENGTH_SHORT).show();
                dlg.dismiss();
            }
        });
        cancelButton.setOnClickListener(view -> {

            dlg.dismiss();
        });
    }
}
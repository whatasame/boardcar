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

public class CustomDialog {

    private Context context;
    private SharedPreferences ConsumableFile;
    public CustomDialog(Context context) {
        this.context = context;
    }


    public void callFunction(final ProgressBar main_label,String str) {

        ConsumableFile=context.getSharedPreferences("consumableinfo",MODE_PRIVATE);
        final Dialog dlg = new Dialog(context);

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dlg.setContentView(R.layout.custom_dialog);

        dlg.show();

        EditText message = (EditText) dlg.findViewById(R.id.mesgase);
        final Button okButton = (Button) dlg.findViewById(R.id.ConsumableOkBtn);
        final Button cancelButton = (Button) dlg.findViewById(R.id.ConsumableCancelBtn);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_label.setProgress(Integer.parseInt(message.getText().toString()));
                SharedPreferences.Editor editor = ConsumableFile.edit();
                editor.putInt(str+"info",Integer.parseInt(message.getText().toString()));
                editor.commit();
                dlg.dismiss();
            }
        });
        cancelButton.setOnClickListener(view -> {

            dlg.dismiss();
        });
    }
}
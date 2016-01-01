package com.infosouq.Util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;

import com.infosouq.R;

public class UI {

    DialogBox dialogBox;

    public DialogBox createDialogBox(Activity context,String text){
        dialogBox = new DialogBox(context,text);
        return dialogBox;
    }
    public ExitBox createExitBox(Context context){
        return new ExitBox(context);
    }
    public void Snackbar(Activity context,int v,String message,int length){
        try {
               Snackbar.make(context.findViewById(v), message, length).show();
        }catch (NullPointerException e){
            Log.d("Snackbar","user exit from current fragment");
            return;
        }catch (Exception e){
            return;
        }
    }

    public class DialogBox{
        Dialog dialog;
        public DialogBox(Activity context,String text){
            dialog = new Dialog(context,R.style.AlertDialogCustom);
            dialog.setContentView(R.layout.custom_dialog);
            TextView dialogText = (TextView)(dialog.findViewById(R.id.dialogText));
            dialogText.setText(text);
        }
        public void showDialogBox(){
            dialog.show();
        }
        public void dismisswDialogBox(){
            dialog.dismiss();
        }
    }
    public class ExitBox{
        public  ExitBox(final Context context){
            AlertDialog alertDialog = new AlertDialog.Builder(
                    context,
                    R.style.AlertDialogCustom_Destructive)
                    .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            /*((Login) context).finish();*/
                        }
                    })
                    .setTitle("Do you want to exit the app?")
                    .create();
            alertDialog.show();
        }


    }


}

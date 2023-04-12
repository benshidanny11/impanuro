package com.dannyp.impanuroapp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.dannyp.impanuroapp.AdviceActivity;
import com.dannyp.impanuroapp.R;
import com.dannyp.impanuroapp.models.User;

public class DialogUtil {

    static ProgressDialog mProgressDialog;
    public static void  showProgressDialog(Context context, String title, String content){
        mProgressDialog=new ProgressDialog(context);
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(content);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }
    public static void  hideProgressDialog(){
        if(mProgressDialog!=null)
        mProgressDialog.dismiss();
    }
    public static void showDialog(Context context, String title, String content, String refNumber, String adviceId, User user){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(content);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton("Yego", (DialogInterface.OnClickListener) (dialog, which) -> {
            // Call payment verification
            RequestUtil.sendPaymentVerification(context,refNumber,adviceId, user);
        });

        builder.setNegativeButton("Oya", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void showResponseDialog(Context context, String title, String content){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(content);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton("Okay", (DialogInterface.OnClickListener) (dialog, which) -> {
            // Call payment verification
            context.startActivity(new Intent(context, AdviceActivity.class));
            ((Activity)context).finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public static void showCustomDialog(Context context){
        Dialog customDialog;
        customDialog = new Dialog(context);
        customDialog.setContentView(R.layout.dialog_user_setup);
        customDialog.setCancelable(false);
        Button btnSubmit = customDialog.findViewById(R.id.btn_user_setup);
        EditText txtPhoneNumber=customDialog.findViewById(R.id.txt_user_phone_in_dialog_user_setup);
       customDialog.show();
       btnSubmit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(txtPhoneNumber.getText().length()<10){
                   Toast.makeText(context,"Mushyiremo numero yuzuye",Toast.LENGTH_LONG).show();
               }else{
                   RequestUtil.getUserData(context, txtPhoneNumber.getText().toString().trim());
                   customDialog.dismiss();
               }
           }
       });
    }
}

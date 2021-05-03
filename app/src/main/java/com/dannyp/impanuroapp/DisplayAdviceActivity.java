package com.dannyp.impanuroapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dannyp.impanuroapp.constants.ApiLinks;

public class DisplayAdviceActivity extends AppCompatActivity {

    TextView txtAdviceTitle,txtAdviceContent;
    Button btnShowComment;
    ImageView imgAdviceFeature;
    Toolbar toolbar;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_advice);
        txtAdviceTitle=(TextView)findViewById(R.id.txt_advice_title_in_display_advice);
        txtAdviceContent=(TextView)findViewById(R.id.txt_advice_content_in_display_advice);
        btnShowComment=(Button)findViewById(R.id.btn_show_comment_in_display_advice);
        imgAdviceFeature=(ImageView)findViewById(R.id.img_advice_feature_in_display_advice);
        toolbar=(Toolbar) findViewById(R.id.advice_toolbar);
        setSupportActionBar(toolbar);
        bundle=getIntent().getExtras();
        if(bundle!=null){
            getSupportActionBar().setTitle("Impanuro");
            getSupportActionBar().setSubtitle("Impanuro y'umunsi wa "+bundle.getInt("date")+" "+bundle.getString("month_name"));
           txtAdviceTitle.setText(bundle.getString("title"));
           txtAdviceContent.setText(bundle.getString("content"));
            Glide.with(this).load(ApiLinks.ROOT_IMAGE_LINK+bundle.getString("image_link")).placeholder(R.drawable.ic_img_place_holder).into(imgAdviceFeature);

            btnShowComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(DisplayAdviceActivity.this);
                    builder.setTitle("Umwanzuro");
                    builder.setMessage(bundle.getString("comment"));
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog dialog=builder.create();
                    dialog.show();
                }
            });


        }




    }
}
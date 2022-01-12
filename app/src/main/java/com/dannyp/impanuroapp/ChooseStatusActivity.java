package com.dannyp.impanuroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dannyp.impanuroapp.constants.StringConstants;
import com.dannyp.impanuroapp.publicdata.PublicData;

public class ChooseStatusActivity extends AppCompatActivity {

    private Button btnSingle;
    private Button btnMarried;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_status);
        btnSingle=(Button) findViewById(R.id.btn_single_in_choose_status);
        btnMarried=(Button) findViewById(R.id.btn_married_in_choose_status);
        btnSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PublicData.AdviceType= StringConstants.SINGLE;
                startActivity(new Intent(ChooseStatusActivity.this,MainActivity.class));
            }
        });

        btnMarried.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PublicData.AdviceType=StringConstants.MARRIED;
                startActivity(new Intent(ChooseStatusActivity.this,MainActivity.class));
            }
        });
    }
}
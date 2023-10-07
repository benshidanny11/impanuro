package com.dannyp.impanuroapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

public class AppInfoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView txtTitle,txtBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Impanuro");
        txtTitle=(TextView) findViewById(R.id.txt_title_in_app_info);
        txtBody=(TextView) findViewById(R.id.txt_body_in_app_info);

        Bundle bundle=getIntent().getExtras();

        if (bundle!=null){
            txtTitle.setText(bundle.getString("title"));
            txtBody.setText(bundle.getString("body"));
        }

    }
}
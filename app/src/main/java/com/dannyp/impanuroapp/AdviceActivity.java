package com.dannyp.impanuroapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.dannyp.impanuroapp.adapters.AdviceAdapter;
import com.dannyp.impanuroapp.dumydata.AdviceData;

public class AdviceActivity extends AppCompatActivity {

    RecyclerView recAdviceList;
    AdviceAdapter adapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        recAdviceList=(RecyclerView)findViewById(R.id.rec_advice_list);
        adapter=new AdviceAdapter(AdviceData.getAdviceData(),this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recAdviceList.setLayoutManager(layoutManager);
        recAdviceList.setAdapter(adapter);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Impanuro");
        getSupportActionBar().setSubtitle("Ukwezi kwa kane");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
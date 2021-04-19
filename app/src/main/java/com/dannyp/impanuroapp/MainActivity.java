package com.dannyp.impanuroapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dannyp.impanuroapp.adapters.MonthsAdapter;
import com.dannyp.impanuroapp.dumydata.MonthsData;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recMonthsList;
    private MonthsAdapter adapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter=new MonthsAdapter(MonthsData.getMothsData(),this);
        recMonthsList=(RecyclerView)findViewById(R.id.rec_months_list);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Impanuro");
        getSupportActionBar().setSubtitle("Menya uko wakitwara");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        recMonthsList.setLayoutManager(gridLayoutManager);
        recMonthsList.setAdapter(adapter);

    }
}
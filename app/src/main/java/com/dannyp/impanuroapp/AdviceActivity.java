package com.dannyp.impanuroapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dannyp.impanuroapp.adapters.AdviceAdapter;
import com.dannyp.impanuroapp.adapters.MonthsAdapter;
import com.dannyp.impanuroapp.constants.ApiLinks;
import com.dannyp.impanuroapp.constants.StringConstants;
import com.dannyp.impanuroapp.dumydata.AdviceData;
import com.dannyp.impanuroapp.items.MonthsItem;
import com.dannyp.impanuroapp.publicdata.PublicData;
import com.dannyp.impanuroapp.utils.CustomSSLSocketFactory;
import com.dannyp.impanuroapp.utils.DataUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.dannyp.impanuroapp.constants.ApiLinks.GET_DATES;
import static com.dannyp.impanuroapp.constants.ApiLinks.GET_DATES_FOR_SINGLE;
import static com.dannyp.impanuroapp.constants.ApiLinks.getAdviceAdviceByMonth;
import static com.dannyp.impanuroapp.constants.ApiLinks.getAdviceAdviceByMonthForSingle;

public class AdviceActivity extends AppCompatActivity {

    RecyclerView recAdviceList;
    AdviceAdapter adapter;
    Toolbar toolbar;
    Bundle bundle;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        recAdviceList=(RecyclerView)findViewById(R.id.rec_advice_list);
        progressBar=(ProgressBar)findViewById(R.id.progress_load_advices_in_advice_activity);
      //  adapter=new AdviceAdapter(AdviceData.getAdviceData(),this);

        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        bundle=getIntent().getExtras();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Impanuro");
        getSupportActionBar().setSubtitle("Ukwezi kwa "+bundle.getInt("month"));
        getDataToViews(bundle.getInt("month"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void getDataToViews(int month){
        try {
            String adviceMonthAPI="";
            if(PublicData.AdviceType.equals(StringConstants.SINGLE)){
                adviceMonthAPI=getAdviceAdviceByMonthForSingle(month);
            }else {
                adviceMonthAPI=getAdviceAdviceByMonth(month);
            }
            progressBar.setVisibility(View.VISIBLE);
           // ArrayList<MonthsItem> monthsItems=new ArrayList<>();
            StringRequest stringRequest = new StringRequest(Request.Method.GET,adviceMonthAPI, new Response.Listener<String>() {
                public void onResponse(String param1String) {
                    progressBar.setVisibility(View.GONE);
                    try {
                        JSONArray jSONArray = (new JSONObject(param1String)).getJSONArray("advices");

                        adapter = new AdviceAdapter(DataUtils.getAdviceList(jSONArray,AdviceActivity.this),AdviceActivity.this);
                        LinearLayoutManager layoutManager=new LinearLayoutManager(AdviceActivity.this);
                        recAdviceList.setLayoutManager(layoutManager);
                        recAdviceList.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            },new Response.ErrorListener() {
                public void onErrorResponse(VolleyError param1VolleyError) {
                    progressBar.setVisibility(View.GONE);
                    param1VolleyError.printStackTrace();

                        Toast.makeText(AdviceActivity.this, param1VolleyError.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            stringRequest.setRetryPolicy((RetryPolicy)new DefaultRetryPolicy(10000, 1, 1.0F));

            Volley.newRequestQueue(this, new HurlStack(null, new CustomSSLSocketFactory())).add(stringRequest);
        } catch (Exception exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
}
package com.dannyp.impanuroapp.utils;

import static com.dannyp.impanuroapp.constants.ApiLinks.GET_DATES;
import static com.dannyp.impanuroapp.constants.ApiLinks.GET_DATES_FOR_SINGLE;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dannyp.impanuroapp.adapters.EntityAdapter;
import com.dannyp.impanuroapp.adapters.ImageSliderViewpagerAdapter;
import com.dannyp.impanuroapp.adapters.MonthsAdapter;
import com.dannyp.impanuroapp.constants.ApiLinks;
import com.dannyp.impanuroapp.constants.StringConstants;
import com.dannyp.impanuroapp.items.EntityItem;
import com.dannyp.impanuroapp.items.MonthsItem;
import com.dannyp.impanuroapp.publicdata.PublicData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MethodWrapper {

    public static void getDataToViews(ProgressBar progressBar, RecyclerView recyclerView, Context context, String entityType, TextView txtNoDataFound) {

        try {
            String url=ApiLinks.GET_GREATING;
            String entity="greatings";
            if(entityType.equals("IGIPIMO")){
                url=ApiLinks.GET_MEASURES;
                entity="ibipimo";
            }else if (entityType.equals("IGISUBIZO")){
                url=ApiLinks.GET_ANSWERS;
                entity="ibisubizo";
            }
            progressBar.setVisibility(View.VISIBLE);
            ArrayList<MonthsItem> monthsItems = new ArrayList<>();
            String finalEntity = entity;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                public void onResponse(String param1String) {
                    progressBar.setVisibility(View.GONE);
                    try {
                        //

                        JSONArray jSONArray = (new JSONObject(param1String)).getJSONArray(finalEntity);

                        if(jSONArray.length()==0){
                            txtNoDataFound.setVisibility(View.VISIBLE);
                            return;
                        }

                        List<EntityItem> entityItems = new ArrayList<>();

                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            entityItems.add(new EntityItem(jSONObject.getInt("id"),
                                    jSONObject.getString("title"),
                                    jSONObject.getString("description"),
                                    jSONObject.getString("date")));
                        }


                      EntityAdapter  adapter = new EntityAdapter(context, entityItems,entityType);

                        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                        layoutManager.setSmoothScrollbarEnabled(true);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError param1VolleyError) {
                    progressBar.setVisibility(View.GONE);
                    param1VolleyError.printStackTrace();
                    //  Log.e("Error==>>>", param1VolleyError.getMessage());
                    if (context != null)
                        Toast.makeText(context, "Habayemo ikibazo, mwongere mugerageze.", Toast.LENGTH_LONG).show();
                }
            });
            stringRequest.setRetryPolicy((RetryPolicy) new DefaultRetryPolicy(10000, 1, 1.0F));
            RequestQueue requestQueue = Volley.newRequestQueue(context, new HurlStack(null, new CustomSSLSocketFactory()));
            requestQueue.add(stringRequest);

        } catch (Exception exception) {
            exception.printStackTrace();
            Toast.makeText(context, exception.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    public static void getAdviceDataToViews(ProgressBar progressBar, RecyclerView recMonthsList, Context context, String AdviceType, TextView txtNoDataFound){
        String adviceAPI="";
        if(AdviceType.equals(StringConstants.SINGLE)){
            adviceAPI=GET_DATES_FOR_SINGLE;
        }else {
            adviceAPI=GET_DATES;
        }
        try {
            progressBar.setVisibility(View.VISIBLE);
            ArrayList<MonthsItem> monthsItems=new ArrayList<>();
            StringRequest stringRequest = new StringRequest(Request.Method.GET, adviceAPI, new Response.Listener<String>() {
                public void onResponse(String param1String) {
                    progressBar.setVisibility(View.GONE);
                    try {
                        JSONArray jSONArray = (new JSONObject(param1String)).getJSONArray("dates");

                        if(jSONArray.length()==0){
                            txtNoDataFound.setVisibility(View.VISIBLE);
                            return;
                        }

                        MonthsAdapter  adapter = new MonthsAdapter(DataUtils.removeDuplicates(jSONArray), context);

                        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
                        layoutManager.setSmoothScrollbarEnabled(true);
                        recMonthsList.setLayoutManager(layoutManager);
                        recMonthsList.setAdapter(adapter);

                    } catch (JSONException e) {
                        Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                }
            },new Response.ErrorListener() {
                public void onErrorResponse(VolleyError param1VolleyError) {
                    progressBar.setVisibility(View.GONE);
                    param1VolleyError.printStackTrace();
                    //  Log.e("Error==>>>", param1VolleyError.getMessage());
                    Toast.makeText(context, "Habayemo ikibazo, mwongere mugerageze.", Toast.LENGTH_LONG).show();
                }
            });
            stringRequest.setRetryPolicy((RetryPolicy)new DefaultRetryPolicy(10000, 1, 1.0F));
            RequestQueue requestQueue= Volley.newRequestQueue(context, new HurlStack(null, new CustomSSLSocketFactory()));
            requestQueue.add(stringRequest);

        } catch (Exception exception) {
            exception.printStackTrace();
            Toast.makeText(context, exception.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
}

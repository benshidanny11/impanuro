package com.dannyp.impanuroapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dannyp.impanuroapp.adapters.ImageSliderViewpagerAdapter;
import com.dannyp.impanuroapp.constants.ApiLinks;
import com.dannyp.impanuroapp.items.EntityItem;
import com.dannyp.impanuroapp.utils.CustomSSLSocketFactory;
import com.dannyp.impanuroapp.utils.MethodWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewEntityActivity extends AppCompatActivity {

    ViewPager mViewPager;

    // Creating Object of ViewPagerAdapter
    ImageSliderViewpagerAdapter mViewPagerAdapter;
    Button btnBack,btnNext;
    TextView txtTitle,txtDescription;

    Toolbar toolbar;
    Bundle intent;
    String enityType;
    private int currentImageIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entity);
        mViewPager = findViewById(R.id.viewPagerMain);
        btnBack= findViewById(R.id.btn_back_in_image_slider);
        btnNext= findViewById(R.id.btn_next_in_image_slider);
        toolbar= findViewById(R.id.toolbar_in_entity_activity);
        txtTitle=findViewById(R.id.txt_entity_title);
        txtDescription=findViewById(R.id.txt_entity_full_description);
        setSupportActionBar(toolbar);
        populateData();

    }

   private void populateData(){
       intent=getIntent().getExtras();
       String title=intent.getString("title");
       String description=intent.getString("description");
       enityType=intent.getString("entity_type");
       txtTitle.setText(title);
       txtDescription.setText(description);
       String entity="Indamukanyo";
       if(enityType!=null && enityType.equals("IGIPIMO")){
           entity="Ibipimo";
       }else if (enityType.equals("IGISUBIZO")){
           entity="Ibisubizo";
       }
       getSupportActionBar().setSubtitle(entity);
//       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       getSlideImages(intent.getInt("eid"),enityType);

   }



    public void getSlideImages(Integer eid, String entityType){

        try {
            String url=ApiLinks.getImages(eid,"IBISUBIZO");
            if(entityType.equals("IGIPIMO")){
                url=ApiLinks.getImages(eid,"IBIPIMO");
            }else if (entityType.equals("GREATING")){
                url=ApiLinks.getImages(eid,"GREATINGS");
            }

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                public void onResponse(String param1String) {

                    try {
                        //

                        JSONArray jSONArray = (new JSONObject(param1String)).getJSONArray("images");
                        String[] images=new String[jSONArray.length()];
                        if(jSONArray.length()>0){
                            for (int i = 0; i < jSONArray.length(); i++) {
                                JSONObject jSONObject = jSONArray.getJSONObject(i);
                                images[i]=ApiLinks.ROOT_IMAGE_LINK+jSONObject.getString("image");
                            }
                        }

                        ImageSliderViewpagerAdapter  mViewPagerAdapter = new ImageSliderViewpagerAdapter(ViewEntityActivity.this, images);
                        mViewPager.setAdapter(mViewPagerAdapter);
                        handleButtonEvents(images);
                    } catch (JSONException e) {
                        Toast.makeText(ViewEntityActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError param1VolleyError) {
                    param1VolleyError.printStackTrace();
                    //  Log.e("Error==>>>", param1VolleyError.getMessage());
                    Toast.makeText(ViewEntityActivity.this, "Habayemo ikibazo, mwongere mugerageze.", Toast.LENGTH_LONG).show();
                }
            });
            stringRequest.setRetryPolicy((RetryPolicy) new DefaultRetryPolicy(10000, 1, 1.0F));
            RequestQueue requestQueue = Volley.newRequestQueue(ViewEntityActivity.this, new HurlStack(null, new CustomSSLSocketFactory()));
            requestQueue.add(stringRequest);

        } catch (Exception exception) {
            exception.printStackTrace();
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();

        }
    }



   private void handleButtonEvents(String[] images){
       btnNext.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (currentImageIndex < images.length - 1) {
                   currentImageIndex++;
               } else {
                   currentImageIndex = 0;
               }
               mViewPager.setCurrentItem(currentImageIndex, true);
           }
       });

       btnBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (currentImageIndex > 0 && currentImageIndex <= images.length-1) {
                   currentImageIndex--;
               } else {
                   currentImageIndex = images.length-1;
               }
               mViewPager.setCurrentItem(currentImageIndex, true);
           }
       });
   }
}
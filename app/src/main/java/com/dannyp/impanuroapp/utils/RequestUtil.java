package com.dannyp.impanuroapp.utils;

import static com.dannyp.impanuroapp.constants.ApiLinks.getAdviceAdviceByMonth;
import static com.dannyp.impanuroapp.constants.ApiLinks.getAdviceAdviceByMonthForSingle;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dannyp.impanuroapp.AdviceActivity;
import com.dannyp.impanuroapp.PaymentActivity;
import com.dannyp.impanuroapp.adapters.AdviceAdapter;
import com.dannyp.impanuroapp.constants.ApiLinks;
import com.dannyp.impanuroapp.constants.StringConstants;
import com.dannyp.impanuroapp.models.User;
import com.dannyp.impanuroapp.publicdata.PublicData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {

    public static void sendPaymentRequest(Context context,String number, String amount, String adviceId) {
        String url = ApiLinks.PAYMENT_CREATION_URL;

        DialogUtil.showProgressDialog(context,"Gushyigikira impanuro","Mube mwihanganye...");
        RequestQueue queue = Volley.newRequestQueue(context);
        JSONObject requestBody = new JSONObject();
        try{
            requestBody.put("number", number);
            requestBody.put("amount",amount);
        }catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,requestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                DialogUtil.hideProgressDialog();
                try {
                    DialogUtil.showDialog(context,"Gushyigikira","Emeza Gushyigikira kuri terephone yawe", response.getString("ref"),adviceId, new User(null,null,number));
                } catch (JSONException e) {
                    DialogUtil.hideProgressDialog();
                    e.printStackTrace();
                    Toast.makeText(context,"Unknown error",Toast.LENGTH_LONG).show();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtil.hideProgressDialog();
                Toast.makeText(context, "Fail to get response = " + error, Toast.LENGTH_LONG).show();
                DialogUtil.showResponseDialog(context,"Kwishura impanuro","Gushyigikira impanuro ntbyagenze neza ");
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    public static void sendPaymentVerification(Context context,String refCode, String adviceId, User user) {
        String url = ApiLinks.PAYMENT_VERIFICATION_URL;
        DialogUtil.showProgressDialog(context,"Gushyigikira impanuro","Mube mwihanganye...");
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url+refCode,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    DialogUtil.hideProgressDialog();
                    if (response.getString("paymentStatus").equals("pending")){
                        DialogUtil.showDialog(context,"Gushyigikira","Mubanze mwemweze Gushyigikira kuri telephone", refCode ,adviceId, user);
                    }else if(response.getString("paymentStatus").equals("successful")){
                        RequestUtil.savePaymentData(context, user, adviceId, refCode);
                    }else {
                        DialogUtil.showResponseDialog(context,"Gushyigikira impanuro","Gushyigikira ntabwo byageinze neza, mwongere mugerageze.");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtil.hideProgressDialog();
                System.out.print("Errorrrr=============++++"+error.getMessage());
                DialogUtil.showResponseDialog(context,"Kwishura impanuro","Gushyigikira impanuro ntibyagenze neza "+error.getMessage());
                Toast.makeText(context, "Fail to get response verification= ", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);
    }

    private static void savePaymentData(Context context, User user,String adviceId,String paymentRef) {
        try{
        String url = ApiLinks.PAYMENT_SAVE_DATA_URL;
        RequestQueue queue = Volley.newRequestQueue(context,new HurlStack(null, new CustomSSLSocketFactory()));


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    DialogUtil.hideProgressDialog();
                    Toast.makeText(context,object.getString("message"),Toast.LENGTH_LONG).show();
                    DialogUtil.showResponseDialog(context,"Kwishura impanuro","Gushyigikira impanuro byagenze neza ✅");
                    user.setId(object.getString("userid"));
                    SharedPrefs.saveUserData(context,user);
                } catch (JSONException e) {
                    DialogUtil.hideProgressDialog();
                    e.printStackTrace();
                    DialogUtil.showResponseDialog(context,"Kwishura impanuro","Gushyigikira impanuro ntbyagenze neza");
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtil.hideProgressDialog();
                System.err.print("Errorr=====> "+Arrays.toString(error.getStackTrace()));
                DialogUtil.showResponseDialog(context,"Kwishura impanuro","Gushyigikira impanuro ntbyagenze neza "+error+" HHhh");
                Toast.makeText(context, "Fail to get response save data = " + error, Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> requestBody = new HashMap<String, String>();
                requestBody.put("names", user.getName());
                requestBody.put("email",user.getEmail());
                requestBody.put("phone_number",user.getPhoneNumber());
                requestBody.put("amount","100");
                requestBody.put("adviceid",adviceId);
                requestBody.put("paymentref",paymentRef);

                // at last we are
                // returning our params.
                return requestBody;
            }
        };
        queue.add(request);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void getUserData(Context context, String phoneNumber){
        try {
            String url=ApiLinks.USER_URL+phoneNumber;
            DialogUtil.showProgressDialog(context,null,"Mube mwihanganye");
            // ArrayList<MonthsItem> monthsItems=new ArrayList<>();
            StringRequest stringRequest = new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
                public void onResponse(String param1String) {
                    DialogUtil.hideProgressDialog();
                    try {
                        JSONObject userObject = new JSONObject(param1String);
                        User user=SharedPrefs.getUserData(context);
                        if(!userObject.isNull("client_id") && !userObject.isNull("client_phone") ){
                            user.setId(userObject.getString("client_id"));
                            user.setPhoneNumber(userObject.getString("client_phone"));
                        }else {
                            user.setPhoneNumber(phoneNumber);
                        }
                        SharedPrefs.saveUserData(context,user);

                    } catch (JSONException e) {
                        Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                }
            },new Response.ErrorListener() {
                public void onErrorResponse(VolleyError param1VolleyError) {
                    DialogUtil.hideProgressDialog();
                    param1VolleyError.printStackTrace();

                    Toast.makeText(context, param1VolleyError.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            stringRequest.setRetryPolicy((RetryPolicy)new DefaultRetryPolicy(10000, 1, 1.0F));

            Volley.newRequestQueue(context, new HurlStack(null, new CustomSSLSocketFactory())).add(stringRequest);
        } catch (Exception exception) {
            Toast.makeText(context, exception.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
}

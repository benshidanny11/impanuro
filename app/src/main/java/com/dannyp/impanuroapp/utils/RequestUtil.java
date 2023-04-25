package com.dannyp.impanuroapp.utils;

import static com.dannyp.impanuroapp.constants.ApiLinks.getAdviceAdviceByMonth;
import static com.dannyp.impanuroapp.constants.ApiLinks.getAdviceAdviceByMonthForSingle;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
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
import com.dannyp.impanuroapp.R;
import com.dannyp.impanuroapp.adapters.AdviceAdapter;
import com.dannyp.impanuroapp.constants.ApiLinks;
import com.dannyp.impanuroapp.constants.StringConstants;
import com.dannyp.impanuroapp.models.User;
import com.dannyp.impanuroapp.publicdata.PublicData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {

    public static void sendPaymentRequest(Context context,String number, String amount, String adviceId) {
        String url = ApiLinks.PAYMENT_CREATION_URL;

        DialogUtil.showProgressDialog(context,"Ifatabuguzi ry'impanuro","Mube mwihanganye...");
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
//                    DialogUtil.showDialog(context,"Ifatabuguzi ry'imbanuro","Banza wemeze ifatabuguzi muri tekefoni ukanda 1) hanyuma ishyiremo pin yawe maze ubone kwemeza hano ukanda yego.", response.getString("ref"),response.getString("status"),adviceId, SharedPrefs.getUserData(context));
                    RequestUtil.savePaymentData(context, SharedPrefs.getUserData(context), adviceId, response.getString("ref"),response.getString("status"));
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
                DialogUtil.showResponseDialog(context,"Ifatabuguzi ry'impanuro","Ifatabuguzi ry'impanuro ntbyagenze neza ");

            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    public static void sendPaymentVerification(Context context,String refCode, String adviceId) {
        String url = ApiLinks.PAYMENT_VERIFICATION_URL;
        DialogUtil.showProgressDialog(context,"","Mube mwihanganye...");
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url+refCode,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    updatePaymentStatus(context,adviceId, response.getString("paymentStatus"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtil.hideProgressDialog();
                Log.e("PaymentVerification",error.getMessage());

            }
        });
        queue.add(request);
    }

    private static void updatePaymentStatus(Context context, String adviceId, String status ){
        String url = ApiLinks.getUpdatePaymentAPI(adviceId, SharedPrefs.getUserData(context).getId(), status);
        RequestQueue queue = null;
        try {
            queue = Volley.newRequestQueue(context, new HurlStack(null, new CustomSSLSocketFactory()));
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                DialogUtil.hideProgressDialog();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtil.hideProgressDialog();
                Log.e("UpdatePaymentStatus",error.getMessage());
            }
        });
        queue.add(request);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Log.e("UpdatePaymentStatus",e.getMessage());
        } catch (KeyManagementException e) {
            e.printStackTrace();
            Log.e("UpdatePaymentStatus",e.getMessage());
        }
    }



    public static void savePaymentData(Context context, User user,String adviceId,String paymentRef, String paymentStatus) {
        try{
        String url = ApiLinks.PAYMENT_SAVE_DATA_URL;
        RequestQueue queue = Volley.newRequestQueue(context,new HurlStack(null, new CustomSSLSocketFactory()));


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    DialogUtil.hideProgressDialog();
                    DialogUtil.showResponseDialog(context,"Ifatabuguzi ry'impanuro","Emeza ifatabuguzi ry'impanuro kuri phone yawe");
                    user.setId(object.getString("userid"));
                    SharedPrefs.saveUserData(context,user);

                } catch (JSONException e) {
                    DialogUtil.hideProgressDialog();
                    Log.e("SavePayment",e.getMessage());

                    DialogUtil.showResponseDialog(context,"Ifatabuguzi ry'impanuro","Ifatabuguzi ry'impanuro ntibyagenze neza");
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DialogUtil.hideProgressDialog();
                Log.e("SavepaymentData:=====>",error.toString());
                DialogUtil.showResponseDialog(context,"Ifatabuguzi ry'impanuro","Ifatabuguzi ry'impanuro ntibyagenze neza.");
//                Toast.makeText(context, "Fail to get response save data = " + error, Toast.LENGTH_LONG).show();
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
                requestBody.put("paymentstatus",paymentStatus);

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
                public void onResponse(String response) {
                    DialogUtil.hideProgressDialog();
                    try {
                        JSONObject userObject = new JSONObject(response.replace("{}",""));
                        User user=SharedPrefs.getUserData(context);
                        if(!userObject.isNull("client_id") && !userObject.isNull("client_phone") ){
                            user.setId(userObject.getString("client_id"));
                            user.setPhoneNumber(userObject.getString("client_phone"));
                        }
                        SharedPrefs.saveUserData(context,user);

                    } catch (JSONException e) {
                        Toast.makeText(context, R.string.payment_error_response,Toast.LENGTH_LONG).show();
                        Log.e("Requestuserdata=====+", e.toString());
                        e.printStackTrace();
                    }

                }
            },new Response.ErrorListener() {
                public void onErrorResponse(VolleyError param1VolleyError) {
                    DialogUtil.hideProgressDialog();
                    param1VolleyError.printStackTrace();

                    Toast.makeText(context, R.string.payment_error_response, Toast.LENGTH_LONG).show();
                }
            });
            stringRequest.setRetryPolicy((RetryPolicy)new DefaultRetryPolicy(10000, 1, 1.0F));

            Volley.newRequestQueue(context, new HurlStack(null, new CustomSSLSocketFactory())).add(stringRequest);
        } catch (Exception exception) {
            Toast.makeText(context, R.string.payment_error_response, Toast.LENGTH_LONG).show();

        }
    }
}

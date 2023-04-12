package com.dannyp.impanuroapp;

import static com.dannyp.impanuroapp.utils.RequestUtil.sendPaymentRequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.dannyp.impanuroapp.models.User;
import com.dannyp.impanuroapp.utils.SharedPrefs;

public class PaymentActivity extends AppCompatActivity {

    EditText phoneNumber;
    Button btnPay;
    User user;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Toolbar toolbar=(Toolbar) findViewById(R.id.tool_bar);
        phoneNumber=(EditText)findViewById(R.id.txt_user_phone_in_payment);
        btnPay=(Button)findViewById(R.id.btn_pay_in_payment);
        user=SharedPrefs.getUserData(this);
        bundle=getIntent().getExtras();
//        if(user!=null){
//            phoneNumber.setText(user.getPhoneNumber());
//        }

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement payment.
                sendPaymentRequest(PaymentActivity.this,phoneNumber.getText().toString().trim(),"100", bundle.getString("adviceid"));
            }
        });
        setSupportActionBar(toolbar);

    }
}
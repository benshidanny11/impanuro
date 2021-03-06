package com.dannyp.impanuroapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class SubscriptionActivity extends AppCompatActivity {
    Button btnRequestNumber;
    Button btnRequestSubscription;
    RelativeLayout lyt1;
    RelativeLayout lyt2;
    RadioGroup radioGroup;
    RadioGroup radioGroup1;
    EditText txtEmail;
    EditText txtPaymentNumber;
    EditText txtPaymentValue;
    EditText txtSubNumber;
    EditText txtUserNames;
    EditText txtUserPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        this.lyt1 = (RelativeLayout)findViewById(R.id.no_subscription_number_container);
        this.lyt2 = (RelativeLayout) findViewById(R.id.subscription_form_container);
        this.btnRequestNumber = (Button) findViewById(R.id.btn_request_subscription_number);
        this.btnRequestSubscription = (Button)findViewById(R.id.btn_request_subscription);
        this.txtSubNumber = (EditText) findViewById(R.id.txt_subscription_number_in_account);
        this.txtPaymentNumber = (EditText) findViewById(R.id.txt_payment_number);
        this.txtPaymentValue = (EditText) findViewById(R.id.txt_subscription_amount);
        this.radioGroup = (RadioGroup)findViewById(R.id.btn_radio_payment_method);
        this.radioGroup1 = (RadioGroup) findViewById(R.id.btn_radio_subscription_duration);
        this.btnRequestNumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                requestSubNumber();
            }
        });
        this.radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int checked) {
                if (checked ==R.id.radio_one_month) {
                    txtPaymentValue.setText(R.string.one_thousand);
                } else if (checked == R.id.radio_six_months) {
                    txtPaymentValue.setText(R.string.five_thousand);
                } else if (checked ==R.id.radio_one_year) {
                    txtPaymentValue.setText(R.string.ten_thousand);
                }
            }
        });
    }

    public void requestSubNumber() {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        View localView = LayoutInflater.from(this).inflate(R.layout.dialog_subsription_number_request_form, (ViewGroup) null);
        this.txtUserNames = (EditText) localView.findViewById(R.id.txt_full_name_in_dialog_subscription_request);
        this.txtUserPhone = (EditText) localView.findViewById(R.id.txt_user_phone_in_dialog_subscription_request);
        this.txtEmail = (EditText) localView.findViewById(R.id.txt_user_email_in_dialog_subscription_request);
        localBuilder.setView(localView);
        final AlertDialog alertDialog = localBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        ((Button) localView.findViewById(R.id.btn_cancel_in__dialog_subscription_request)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        ((Button) localView.findViewById(R.id.btn_send_request_txt_user_phone_in_dialog_subscription_request)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alertDialog.dismiss();
                txtSubNumber.setText(new StringBuilder(String.valueOf(System.currentTimeMillis()).substring(0, 7)).reverse().toString());
                lyt1.setVisibility(View.GONE);
                lyt2.setVisibility(View.VISIBLE);
            }
        });
    }
}
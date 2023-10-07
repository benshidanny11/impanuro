package com.dannyp.impanuroapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.dannyp.impanuroapp.AppInfoActivity;
import com.dannyp.impanuroapp.R;
import com.dannyp.impanuroapp.SubscriptionActivity;

import java.util.Objects;

public class SettingFragment extends Fragment {

    private RelativeLayout contact;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_settings,container,false);
        RelativeLayout userManual = (RelativeLayout) rootView.findViewById(R.id.app_description_container);
        RelativeLayout policy = (RelativeLayout) rootView.findViewById(R.id.policy_container);
        contact=(RelativeLayout) rootView.findViewById(R.id.contact_us_container);


        userManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AppInfoActivity.class);
                intent.putExtra("title",getString(R.string.manual_title));
                intent.putExtra("body",getString(R.string.manual_body));
                startActivity(intent);
            }
        });
        policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AppInfoActivity.class);
                intent.putExtra("title",getString(R.string.policy_title));
                intent.putExtra("body",getString(R.string.policy_body));
                startActivity(intent);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AppInfoActivity.class);
                intent.putExtra("title",getString(R.string.contact_title));
                intent.putExtra("body",getString(R.string.contact_body));
                startActivity(intent);
            }
        });
        return rootView;
    }


}

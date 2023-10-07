package com.dannyp.impanuroapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.dannyp.impanuroapp.fragment.AnswersFragment;
import com.dannyp.impanuroapp.fragment.GreatingFragment;
import com.dannyp.impanuroapp.fragment.HomeFragment;
import com.dannyp.impanuroapp.fragment.MeasureFragment;
import com.dannyp.impanuroapp.fragment.SettingFragment;
import com.dannyp.impanuroapp.models.User;
import com.dannyp.impanuroapp.utils.RequestUtil;
import com.dannyp.impanuroapp.utils.SharedPrefs;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Impanuro");
        getSupportActionBar().setSubtitle("Menya gukunda / gukundwa");
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);


        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_baseline_home_24, "Ahabanza"))
                .addItem(new BottomNavigationItem(R.drawable.baseline_handshake_24, "Indamukanyo"))
                .addItem(new BottomNavigationItem(R.drawable.baseline_thermostat_24, "Ibipimo"))
                .addItem(new BottomNavigationItem(R.drawable.baseline_thumb_up_24, "Ibisubizo"))
                .addItem(new BottomNavigationItem(R.drawable.ic_baseline_menu_24, "Ibindi"))
                .setActiveColor(R.color.purple_500)
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                if(position==0){
                    openFragment(new HomeFragment());
                }else if(position==1){
                    openFragment(new GreatingFragment());
                }
                else if(position==2){
                    openFragment(new MeasureFragment());
                }
                else if(position==3){
                    openFragment(new AnswersFragment());
                }
                else if(position==4){
                    openFragment(new SettingFragment());
                }

            }
            @Override
            public void onTabUnselected(int position) {
            }
            @Override
            public void onTabReselected(int position) {
            }
        });
        user= SharedPrefs.getUserData(this);
        assert user != null;

        if(user.getPhoneNumber().equals("")){
//            DialogUtil.showCustomDialog(this);
            RequestUtil.getUserData(this,  System.currentTimeMillis()/1000+ UUID.randomUUID().toString());
        }
        openFragment(new HomeFragment());
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
package com.dannyp.impanuroapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.dannyp.impanuroapp.fragment.AnswersFragment;
import com.dannyp.impanuroapp.fragment.GreatingFragment;
import com.dannyp.impanuroapp.fragment.MeasureFragment;
import com.dannyp.impanuroapp.fragment.SettingFragment;
import com.dannyp.impanuroapp.models.User;
import com.dannyp.impanuroapp.utils.RequestUtil;
import com.dannyp.impanuroapp.utils.SharedPrefs;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener{
    Toolbar toolbar;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Impanuro");
        getSupportActionBar().setSubtitle("Menya imibanire ya muntu");
        BottomNavigationView bottomNavigationBar = (BottomNavigationView) findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar
                .setOnNavigationItemSelectedListener(this);
        bottomNavigationBar.setSelectedItemId(R.id.item_greatings_in_bottom_nav_item);

        user= SharedPrefs.getUserData(this);
        assert user != null;

        if(user.getPhoneNumber().equals("")){
//            DialogUtil.showCustomDialog(this);
            RequestUtil.getUserData(this,  System.currentTimeMillis()/1000+ UUID.randomUUID().toString());
        }
        openFragment(new GreatingFragment());
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    GreatingFragment greatingFragment = new GreatingFragment();
    MeasureFragment measureFragment = new MeasureFragment();
    AnswersFragment answersFragment = new AnswersFragment();

    SettingFragment settingFragment = new SettingFragment();
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.item_greatings_in_bottom_nav_item){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, greatingFragment)
                    .commit();
            return true;
        }else if(item.getItemId()==R.id.item_measure_in_bottom_nav_item){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, measureFragment)
                    .commit();
            return true;
        }
        else if(item.getItemId()==R.id.item_answer_in_bottom_nav_item){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, answersFragment)
                    .commit();
            return true;
        }else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, settingFragment)
                    .commit();
            return true;
        }
    }
}
package com.dannyp.impanuroapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;

public class WelcomeActivity extends AppCompatActivity {

    private AppUpdateManager updateManager;
    private int RC_APP_UPDATE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView tv=(TextView)findViewById(R.id.txt_welcome_message) ;
        Button ntnCotinue=(Button)findViewById(R.id.btn_continue_in_welcome);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim);
        updateManager= AppUpdateManagerFactory.create(this);
        anim.setInterpolator((new AccelerateDecelerateInterpolator()));
        anim.setFillAfter(true);
        tv.setAnimation(anim);
        ntnCotinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();
            }
        });
        updateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>()
        {


            @Override
            public void onSuccess(AppUpdateInfo result)
            {
                if(result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        && result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE))
                {
                    try
                    {
                        updateManager.startUpdateFlowForResult(result,AppUpdateType.IMMEDIATE, WelcomeActivity.this
                                ,RC_APP_UPDATE);

                    } catch (IntentSender.SendIntentException e)
                    {
                        e.printStackTrace();
                    }
                }else {
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
            }
        });
       // updateManager.registerListener(installStateUpdatedListener);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode == RC_APP_UPDATE && resultCode != RESULT_OK)
        {
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override

    protected void onResume()
    {
        super.onResume();
        updateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>()
        {
            @Override
            public void onSuccess(AppUpdateInfo result)
            {
                if(result.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS)
                {
                    try
                    {
                        updateManager.startUpdateFlowForResult(result,AppUpdateType.IMMEDIATE, WelcomeActivity.this
                                ,RC_APP_UPDATE);
                    } catch (IntentSender.SendIntentException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
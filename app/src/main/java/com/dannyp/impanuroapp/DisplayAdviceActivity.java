package com.dannyp.impanuroapp;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.dannyp.impanuroapp.constants.ApiLinks;

public class DisplayAdviceActivity extends AppCompatActivity {
    Bundle bundle;
    ImageView imgAdviceFeature;
    Toolbar toolbar;
    TextView txtAdviceTitle;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView((int) R.layout.activity_display_advice);
        txtAdviceTitle = (TextView) findViewById(R.id.txt_advice_title_in_display_advice);

        imgAdviceFeature = (ImageView) findViewById(R.id.img_advice_feature_in_display_advice);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.advice_toolbar);
        Button btnShowComment=(Button)findViewById(R.id.btn_show_comment_in_display_advice);;
        toolbar = toolbar2;
        setSupportActionBar(toolbar2);
        Bundle extras = getIntent().getExtras();
        this.bundle = extras;
        if (extras != null) {
            getSupportActionBar().setTitle((CharSequence) "Impanuro");
            ActionBar supportActionBar = getSupportActionBar();
            supportActionBar.setSubtitle("Impanuro yo kwitariki ya " +bundle.getInt("date") + " " + bundle.getString("month_name"));
            txtAdviceTitle.setText(bundle.getString("title").trim());
            Glide.with( this).load(ApiLinks.ROOT_IMAGE_LINK + bundle.getString("image_link"))
                    .apply(new RequestOptions().dontTransform().dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL)
                            .skipMemoryCache(false).placeholder(R.drawable.ic_img_place_holder)).into(imgAdviceFeature);

        }


        btnShowComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(DisplayAdviceActivity.this);
                builder.setTitle("Impanuro");
                if(bundle.getString("advice").equals("")){
                    builder.setMessage("No advice available");
                }else{
                    builder.setMessage(bundle.getString("advice"));
                }
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
    }
}

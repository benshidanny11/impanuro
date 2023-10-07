package com.dannyp.impanuroapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.dannyp.impanuroapp.AdviceActivity;
import com.dannyp.impanuroapp.R;
import com.dannyp.impanuroapp.adapters.AdviceViewPagerAdapter;
import com.dannyp.impanuroapp.adapters.MonthsAdapter;
import com.dannyp.impanuroapp.constants.ApiLinks;
import com.dannyp.impanuroapp.constants.StringConstants;
import com.dannyp.impanuroapp.dumydata.MonthsData;
import com.dannyp.impanuroapp.items.AdviceItem;
import com.dannyp.impanuroapp.items.MonthsItem;
import com.dannyp.impanuroapp.publicdata.PublicData;
import com.dannyp.impanuroapp.utils.CustomSSLSocketFactory;
import com.dannyp.impanuroapp.utils.DataUtils;
import com.dannyp.impanuroapp.utils.DateUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import static com.dannyp.impanuroapp.constants.ApiLinks.GET_DATES;
import static com.dannyp.impanuroapp.constants.ApiLinks.GET_DATES_FOR_SINGLE;

public class HomeFragment extends Fragment {

    private RecyclerView recMonthsList;
    private MonthsAdapter adapter;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_home,container,false);


        TabLayout tabLayout = rootView.findViewById(R.id.advice_tab_view);
        ViewPager2 viewPager = rootView.findViewById(R.id.advice_view_pager);
        FragmentActivity parentActivity = getActivity();
        if(parentActivity!=null){
            AdviceViewPagerAdapter tabPagerAdapter = new AdviceViewPagerAdapter(parentActivity);

            viewPager.setAdapter(tabPagerAdapter);
            new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
                // Set tab text and other properties
                switch (position) {
                    case 0:
                        tab.setText("Ingaragu");
                        break;
                    case 1:
                        tab.setText("Abashakanye");
                        break;
                }
            }).attach();

        }


        // Connect TabLayout and ViewPager


//        getDataToViews();

        return rootView;
    }
}

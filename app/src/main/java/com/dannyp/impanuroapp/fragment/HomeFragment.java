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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        recMonthsList=(RecyclerView)rootView.findViewById(R.id.rec_months_list);
        progressBar=(ProgressBar)rootView.findViewById(R.id.progress_load_advices);
        getDataToViews();

        return rootView;
    }

    public void getDataToViews(){
        String adviceAPI="";
        if(PublicData.AdviceType.equals(StringConstants.SINGLE)){
            adviceAPI=GET_DATES_FOR_SINGLE;
        }else {
            adviceAPI=GET_DATES;
        }
        try {
            progressBar.setVisibility(View.VISIBLE);
            ArrayList<MonthsItem> monthsItems=new ArrayList<>();
            StringRequest stringRequest = new StringRequest(Request.Method.GET, adviceAPI, new Response.Listener<String>() {
                public void onResponse(String param1String) {
                    progressBar.setVisibility(View.GONE);
                    try {
                        JSONArray jSONArray = (new JSONObject(param1String)).getJSONArray("dates");

                        adapter = new MonthsAdapter(DataUtils.removeDuplicates(jSONArray), getActivity());

                        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
                        layoutManager.setSmoothScrollbarEnabled(true);
                        recMonthsList.setLayoutManager(layoutManager);
                        recMonthsList.setAdapter(adapter);

                    } catch (JSONException e) {
                        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                }
            },new Response.ErrorListener() {
                public void onErrorResponse(VolleyError param1VolleyError) {
                    progressBar.setVisibility(View.GONE);
                    param1VolleyError.printStackTrace();
                    //  Log.e("Error==>>>", param1VolleyError.getMessage());
                    if (getActivity() != null)
                        Toast.makeText(getActivity(), "Habayemo ikibazo, mwongere mugerageze.", Toast.LENGTH_LONG).show();
                }
            });
            stringRequest.setRetryPolicy((RetryPolicy)new DefaultRetryPolicy(10000, 1, 1.0F));
            RequestQueue requestQueue= Volley.newRequestQueue(requireContext(), new HurlStack(null, new CustomSSLSocketFactory()));
            requestQueue.add(stringRequest);

        } catch (Exception exception) {
            exception.printStackTrace();
            Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
}

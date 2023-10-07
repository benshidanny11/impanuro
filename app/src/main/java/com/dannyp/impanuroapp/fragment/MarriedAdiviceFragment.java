package com.dannyp.impanuroapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dannyp.impanuroapp.R;
import com.dannyp.impanuroapp.adapters.MonthsAdapter;
import com.dannyp.impanuroapp.constants.StringConstants;
import com.dannyp.impanuroapp.utils.MethodWrapper;

public class MarriedAdiviceFragment extends Fragment {

    private RecyclerView recMonthsList;
    private MonthsAdapter adapter;
    private ProgressBar progressBar;

    public MarriedAdiviceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_married_adivice, container, false);
        recMonthsList=rootView.findViewById(R.id.rec_months_list_married);
        progressBar=rootView.findViewById(R.id.progress_load_advices_married);
        TextView txtNoDataFound=rootView.findViewById(R.id.txt_no_data_found_advice_maried);
        MethodWrapper.getAdviceDataToViews(progressBar,recMonthsList,getActivity(), StringConstants.MARRIED, txtNoDataFound);
        return rootView;

    }
}
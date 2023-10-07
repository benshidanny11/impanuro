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


public class SingleAdviceFragment extends Fragment {


    private RecyclerView recMonthsList;
    private MonthsAdapter adapter;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_single_advice, container, false);
        recMonthsList=rootView.findViewById(R.id.rec_months_list_single);
        progressBar=rootView.findViewById(R.id.progress_load_advices_single);
        TextView txtNoDataFound=rootView.findViewById(R.id.txt_no_data_found_advice_single);
        MethodWrapper.getAdviceDataToViews(progressBar,recMonthsList,getActivity(), StringConstants.SINGLE, txtNoDataFound);
        return rootView;
    }
}
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
import com.dannyp.impanuroapp.adapters.EntityAdapter;
import com.dannyp.impanuroapp.utils.MethodWrapper;

public class MeasureFragment extends Fragment {

    private RecyclerView recyclerView;
    private EntityAdapter adapter;
    private ProgressBar progressBar;

    private TextView txtNoDataFound;
    public MeasureFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_measure, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rec_measures_list);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_load_in_measures);
        txtNoDataFound=(TextView)rootView.findViewById(R.id.txt_no_data_found_measure);
        MethodWrapper.getDataToViews(progressBar,recyclerView,getActivity(),"IGIPIMO",txtNoDataFound);
        return rootView;
    }
}
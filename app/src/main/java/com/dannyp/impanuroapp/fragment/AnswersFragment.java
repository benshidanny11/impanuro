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

public class AnswersFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private TextView txtNoDataFound;

    public AnswersFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_answers, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rec_answers_list);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_load_in_answers);
        txtNoDataFound=(TextView)rootView.findViewById(R.id.txt_no_data_found_answers);
        MethodWrapper.getDataToViews(progressBar,recyclerView,getActivity(),"IGISUBIZO",txtNoDataFound);
        return rootView;
    }
}
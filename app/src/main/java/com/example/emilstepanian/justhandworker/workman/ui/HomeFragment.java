package com.example.emilstepanian.justhandworker.workman.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.shared.controller.JSONParser;
import com.example.emilstepanian.justhandworker.shared.controller.JobAdapter;
import com.example.emilstepanian.justhandworker.shared.model.Job;
import com.example.emilstepanian.justhandworker.shared.model.TestData;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private JobAdapter jobAdapter;

    public HomeFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rec_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Job> jobList = JSONParser.getListData(getContext(), "job");


        jobAdapter = new JobAdapter(jobList, getActivity());

        recyclerView.setAdapter(jobAdapter);


        return rootView;
    }
}

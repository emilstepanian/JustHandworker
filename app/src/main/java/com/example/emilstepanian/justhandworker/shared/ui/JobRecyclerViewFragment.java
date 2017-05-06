package com.example.emilstepanian.justhandworker.shared.ui;

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

import java.util.List;

/**
 * Created by emilstepanian on 06/05/2017.
 */

public class JobRecyclerViewFragment extends Fragment{

    private RecyclerView recyclerView;
    private JobAdapter jobAdapter;


    public JobRecyclerViewFragment() {
    }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.job_recycler_view, container, false);
            recyclerView = (RecyclerView) rootView.findViewById(R.id.rec_list);

            recyclerView.setLayoutManager(new LinearLayoutManager(getParentFragment().getActivity()));

            List<Job> jobList = JSONParser.getListData(getParentFragment().getContext(), "job");


            jobAdapter = new JobAdapter(jobList, getParentFragment().getActivity());

            recyclerView.setAdapter(jobAdapter);

            return rootView;

        }
}

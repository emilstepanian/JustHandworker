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
import com.example.emilstepanian.justhandworker.shared.model.User;

import java.util.ArrayList;
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

            Bundle userInfo = this.getActivity().getIntent().getExtras();
            User currentUser = new User(userInfo.getInt("id"), userInfo.getInt("professionId"), userInfo.getString("firstName"), userInfo.getString("lastName"), userInfo.getString("username"), userInfo.getString(("password")));

            List<Job> jobList = JSONParser.getListData(getParentFragment().getContext(), "job");
            ArrayList<Job> filteredJobList = new ArrayList<>();

            recyclerView.setLayoutManager(new LinearLayoutManager(getParentFragment().getActivity()));


            if(currentUser.getProfessionId() == 0) {
                for(int i = 0; i < jobList.size(); i++){
                    Job job = jobList.get(i);
                    if(currentUser.getId() == job.getUserId()){
                        filteredJobList.add(jobList.get(i));
                    }
                }

                jobAdapter = new JobAdapter(filteredJobList, getParentFragment().getActivity());

            } else {
                jobAdapter = new JobAdapter(jobList, getParentFragment().getActivity());

            }




            recyclerView.setAdapter(jobAdapter);

            return rootView;

        }
}

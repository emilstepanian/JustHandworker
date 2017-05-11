package com.example.emilstepanian.justhandworker.jobowner.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.shared.ui.JobRecyclerViewFragment;

public class JobOwnerHomeContainerFragment extends Fragment {


    public JobOwnerHomeContainerFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_jobowner_home_container, container, false);

        Fragment jobRecyclerViewFragment = new JobRecyclerViewFragment();


        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.jobownerHomeContainer, jobRecyclerViewFragment);
       // transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
       // transaction.addToBackStack(null); 
        transaction.commit();

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.jobowner_home_container_fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), CreateJobActivity.class);
                i.putExtras(getActivity().getIntent());
                startActivity(i);

            }
        });



        return rootView;
    }

}

package com.example.emilstepanian.justhandworker.controller.jobowner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.controller.JobRecyclerViewFragment;

public class JobOwnerHomeContainerFragment extends Fragment {

    Fragment jobRecyclerViewFragment;

    public JobOwnerHomeContainerFragment() {
        this.jobRecyclerViewFragment = new JobRecyclerViewFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_jobowner_home_container, container, false);



        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.jobownerHomeContainer, jobRecyclerViewFragment);
        // transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // transaction.addToBackStack(null);
        transaction.commit();




        return rootView;
    }

}

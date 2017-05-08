package com.example.emilstepanian.justhandworker.jobtaker.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.shared.ui.ProfilePageFragment;

public class ProfileContainerFragment extends Fragment {
    public ProfileContainerFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_jobtaker_profile_container, container, false);

        Fragment profilePageFragment = new ProfilePageFragment();

        //Pass along any extras to the fragment replacing the containerfragment
        Bundle extras = getArguments();
        if(extras != null){
            profilePageFragment.setArguments(extras);
        }


        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.jobtakerProfileContainer, profilePageFragment);
        transaction.commit();

        return rootView;    }
}

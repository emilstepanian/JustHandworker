package com.example.emilstepanian.justhandworker.jobtaker.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emilstepanian.justhandworker.R;

public class ProfileContainerFragment extends Fragment {
    public ProfileContainerFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jobtaker_profile_container, container, false);
    }
}

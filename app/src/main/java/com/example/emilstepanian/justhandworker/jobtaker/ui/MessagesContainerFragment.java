package com.example.emilstepanian.justhandworker.jobtaker.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emilstepanian.justhandworker.R;

public class MessagesContainerFragment extends Fragment {
    public MessagesContainerFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jobtaker_messages_container, container, false);
    }
}

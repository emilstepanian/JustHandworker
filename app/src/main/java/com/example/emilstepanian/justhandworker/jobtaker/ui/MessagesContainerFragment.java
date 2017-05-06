package com.example.emilstepanian.justhandworker.jobtaker.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.shared.ui.JobRecyclerViewFragment;
import com.example.emilstepanian.justhandworker.shared.ui.MessagesTabFragment;

public class MessagesContainerFragment extends Fragment {
    public MessagesContainerFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_jobtaker_messages_container, container, false);
        Fragment messagesTabContainerFragment = new MessagesTabContainerFragment();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.jobtakerMessagesContainer, messagesTabContainerFragment);
        // transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // transaction.addToBackStack(null);
        transaction.commit();

        return rootView;


        //super.onCreate(savedInstanceState);

    }
}

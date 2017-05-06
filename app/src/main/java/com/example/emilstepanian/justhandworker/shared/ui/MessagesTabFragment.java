package com.example.emilstepanian.justhandworker.shared.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emilstepanian.justhandworker.R;


/**
 * Fragment to be used in the messages activity to switch between tabs.
 *
 * Code inspired by:
 * http://www.truiton.com/2015/06/android-tabs-example-fragments-viewpager/
 */
public class MessagesTabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages_tab, container, false);
    }

}

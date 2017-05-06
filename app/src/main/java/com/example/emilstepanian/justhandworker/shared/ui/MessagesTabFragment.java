package com.example.emilstepanian.justhandworker.shared.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
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

    public  MessagesTabFragment(){
        //Det er ikke tillad at ændre på constructoren.
        //Titlen på tab baren skal i stedes smides med i et bundle.
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_messages_tab, container, false);
    }

}

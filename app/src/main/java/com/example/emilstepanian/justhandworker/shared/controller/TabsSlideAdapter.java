package com.example.emilstepanian.justhandworker.shared.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.emilstepanian.justhandworker.shared.ui.BidRecyclerViewFragment;


/**
 * Created by Kasper on 06/05/2017.
 */

public class TabsSlideAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public TabsSlideAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    //Create the fragments to be shown inside the tabs in this method. Any data needed for the
    //creation of the fragment should be passed alog in a Bundle, and retrieved in the
    //onCreateView method.
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                BidRecyclerViewFragment tab1 = new BidRecyclerViewFragment();
                Bundle b = new Bundle();
                b.putString("content", "nonAcceptedBids");
                tab1.setArguments(b);
                return tab1;

            case 1:
                BidRecyclerViewFragment tab2 = new BidRecyclerViewFragment();
                Bundle b2 = new Bundle();
                b2.putString("content", "AcceptedBids");
                tab2.setArguments(b2);
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}

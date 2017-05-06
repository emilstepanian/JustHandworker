package com.example.emilstepanian.justhandworker.shared.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.emilstepanian.justhandworker.shared.ui.MessagesTabFragment;

/**
 * Created by Kasper on 06/05/2017.
 */

public class TabsSlideAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public TabsSlideAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                MessagesTabFragment tab1 = new MessagesTabFragment();
                return tab1;
            case 1:
                MessagesTabFragment tab2 = new MessagesTabFragment();
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

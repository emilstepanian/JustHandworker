package com.example.emilstepanian.justhandworker.jobtaker.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.shared.controller.TabsSlideAdapter;



public class MessagesTabContainerFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View messagesTabContainerView = inflater.inflate(R.layout.fragment_messages_tab_container, container, false);

        //Retrieve any extra arguments
        Bundle extras = getArguments();

        //Get reference to tablayout
        TabLayout tabLayout = (TabLayout) messagesTabContainerView.findViewById(R.id.tabs_layout);

        //Set title of tabs based on which activity we're coming from.
        if(extras != null){
            String [] tabTitles = extras.getStringArray("tabTitles");
            tabLayout.addTab(tabLayout.newTab().setText(tabTitles[0]));
            tabLayout.addTab(tabLayout.newTab().setText(tabTitles[1]));
        }


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) messagesTabContainerView.findViewById(R.id.tabs_pager);

        //final PagerAdapter adapter = new TabsSlideAdapter((AppCompatActivity) getActivity().getSupportFragmentManager(), tabLayout.getTabCount())
        final PagerAdapter adapter = new TabsSlideAdapter(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);



        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab){
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return messagesTabContainerView;
    }

    //Todo: Tjek lige om der er noget funky ved den her... er den nødvendig?
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        //if(id == R.id.action_settings) {
          //  return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        //If the activity is paused, show the actionbar again
        if(!((AppCompatActivity) getActivity()).getSupportActionBar().isShowing()){
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        }
    }
}















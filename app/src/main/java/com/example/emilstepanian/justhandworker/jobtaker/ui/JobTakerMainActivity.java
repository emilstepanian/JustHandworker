package com.example.emilstepanian.justhandworker.jobtaker.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;

import com.example.emilstepanian.justhandworker.R;

public class JobTakerMainActivity extends AppCompatActivity {

    private Fragment fragment, homeFragment, messagesFragment, profileFragment;
    Bundle bundle;
    private FragmentManager fragmentManager;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = homeFragment;
                    break;

                case R.id.navigation_messages:
                    //Only set argument of fragment if it is not already the active fragment
                    if(fragment != messagesFragment){
                        fragment = messagesFragment;
                        //Set parameters to be used for the name of the tabs in the MessagesView for the jobtaker.
                        bundle = new Bundle();
                        bundle.putStringArray("tabTitles", new String[]{getString(R.string.tab1_title_from_bundle), getString(R.string.tab2_title_from_bundle)});
                        fragment.setArguments(bundle);
                    }
                    break;

                case R.id.navigation_profile:
                    fragment = profileFragment;
                    break;
            }

            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_container, fragment).commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobtaker_main);

        homeFragment = new HomeContainerFragment();
        messagesFragment = new MessagesContainerFragment();
        profileFragment = new ProfileContainerFragment();

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager = getSupportFragmentManager();

        fragment = new HomeContainerFragment();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, fragment).commit();



    }

}

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
                    if(fragment != messagesFragment) {
                        fragment = messagesFragment;
                        //Set parameters to be used for the name of the tabs in the MessagesView for the jobtaker.
                        bundle = new Bundle();
                        bundle.putStringArray("tabTitles", new String[]{getString(R.string.tab1_title_from_bundle), getString(R.string.tab2_title_from_bundle)});
                        fragment.setArguments(bundle);
                        break;
                    }

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
        //Prøvede at fjerne actionbar fra activity, virkede ikke rigtig
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getActionBar().hide();

        //Nedenstående giver bare følgende: This activity already has an action bar supplied by the window decore. Do not request wi........
        //ActionBar actionbar = getSupportActionBar();
        //actionbar.hide();

        setContentView(R.layout.activity_jobtaker_main);

        homeFragment = new HomeContainerFragment();
        messagesFragment = new MessagesContainerFragment();
        profileFragment = new ProfileContainerFragment();

        //Message that shows which menuitem we have selected

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager = getSupportFragmentManager();

        fragment = new HomeContainerFragment();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, fragment).commit();



    }

}

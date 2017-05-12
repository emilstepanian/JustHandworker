package com.example.emilstepanian.justhandworker.jobowner.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.jobtaker.ui.*;
import com.example.emilstepanian.justhandworker.jobtaker.ui.HomeContainerFragment;
import com.example.emilstepanian.justhandworker.shared.model.User;

public class JobOwnerMainActivity extends AppCompatActivity {

    private User currentUser;



    private Fragment fragment, homeFragment, profileFragment;
    private FragmentManager fragmentManager;

    public User getCurrentUser() {
        return currentUser;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.jobowner_navigation_home:
                    if(fragment != homeFragment) {
                        fragment = homeFragment;
                    }

                    break;
                case R.id.jobowner_navigation_add_job:
                    Intent i = new Intent(getApplicationContext(), CreateJobActivity.class);
                    i.putExtras(getIntent());
                    fragment = homeFragment;
                    startActivity(i);
                    break;


                case R.id.jobowner_navigation_profile:

                    if(fragment != profileFragment){
                        fragment = profileFragment;
                    }

                    break;
            }

            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.jobowner_main_container, fragment).commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobowner_main);
        Bundle userInfo = getIntent().getExtras();

        currentUser = new User(userInfo.getInt("id"), userInfo.getInt("professionId"), userInfo.getString("firstName"), userInfo.getString("lastName"), userInfo.getString("username"), userInfo.getString(("password")));

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Velkommen " + currentUser.getFirstName());



        homeFragment = new JobOwnerHomeContainerFragment();
        profileFragment = new JobOwnerProfileContainerFragment();



        //Message that shows which menuitem we have selected

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.jobowner_main_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager = getSupportFragmentManager();

        fragment = new HomeContainerFragment();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.jobowner_main_container, fragment).commit();

    }

}

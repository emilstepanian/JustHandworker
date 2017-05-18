package com.example.emilstepanian.justhandworker.jobowner.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.jobtaker.ui.*;
import com.example.emilstepanian.justhandworker.jobtaker.ui.HomeContainerFragment;
import com.example.emilstepanian.justhandworker.shared.model.User;

public class JobOwnerMainActivity extends AppCompatActivity {



    private static User currentUser;



    private Fragment fragment, homeFragment, profileFragment;
    private FragmentManager fragmentManager;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        JobOwnerMainActivity.currentUser = currentUser;
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


    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Tryk igen for at logge ud",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    currentUser = null;
                    exit = false;
                }
            }, 3 * 1000);

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings_menu_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                if(fragment == homeFragment) {

                    Toast.makeText(this, "Slet opgaver\n",
                            Toast.LENGTH_SHORT).show();

                } else if(fragment == profileFragment) {
                    Toast.makeText(this, "Rediger oplysninger.\n",
                            Toast.LENGTH_SHORT).show();


                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

package com.example.emilstepanian.justhandworker.controller.jobtaker;

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
import android.widget.Toast;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.model.User;

public class JobTakerMainActivity extends AppCompatActivity {

    private static User currentUser;

    private Fragment fragment, homeFragment, messagesFragment, profileFragment;
    Bundle bundle;
    private FragmentManager fragmentManager;
    private BottomNavigationView navigation;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        JobTakerMainActivity.currentUser = currentUser;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(fragment != homeFragment) {
                        fragment = homeFragment;
                    }

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

                    if(fragment != profileFragment){
                        fragment = profileFragment;
                    }

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


        Bundle userInfo = getIntent().getExtras();

        currentUser = new User(userInfo.getInt("id"), userInfo.getInt("professionId"), userInfo.getString("firstName"), userInfo.getString("lastName"), userInfo.getString("username"), userInfo.getString(("password")));

        ActionBar actionBar = getSupportActionBar();


        actionBar.setTitle("Velkommen " + currentUser.getFirstName());

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

                if(fragment == homeFragment){
                    Toast.makeText(this, "Ret søgekriterier",
                            Toast.LENGTH_SHORT).show();

                } else if (fragment == messagesFragment) {
                    Toast.makeText(this, "Ret/Slet",
                            Toast.LENGTH_SHORT).show();

                } else if(fragment == profileFragment) {
                    Toast.makeText(this, "Rediger oplysninger",
                            Toast.LENGTH_SHORT).show();

                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

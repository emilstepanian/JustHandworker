package com.example.emilstepanian.justhandworker.shared.ui;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.shared.controller.SlideAdapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class JobPageActivity extends AppCompatActivity {

        private ViewPager mPager;
    private int currentPage = 0;
    private final Integer[] imageInts = {R.drawable.balcony, R.drawable.wc, R.drawable.sink};
    private ArrayList<Integer> imagesIntArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_page);

        initJobPage();
    }

    private void initJobPage() {
        for(int i=0; i<imageInts.length; i++) {
            imagesIntArray.add(imageInts[i]);
        }

            mPager = (ViewPager) findViewById(R.id.pager);
            mPager.setAdapter(new SlideAdapter(JobPageActivity.this, imagesIntArray));

            CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
            indicator.setViewPager(mPager);

            //Autostart of viewpager

            final Handler handler = new Handler();
            final Runnable update = new Runnable() {
                @Override
                public void run() {
                    if (currentPage ==imageInts.length) {
                        currentPage = 0;
                    }

                    mPager.setCurrentItem(currentPage++, true);
                }
            };

            Timer swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, 2500, 2500);






    }
}

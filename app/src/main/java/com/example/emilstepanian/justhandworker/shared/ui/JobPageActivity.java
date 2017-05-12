package com.example.emilstepanian.justhandworker.shared.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.jobtaker.ui.JobTakerMainActivity;
import com.example.emilstepanian.justhandworker.jobtaker.ui.SendBidFragment;
import com.example.emilstepanian.justhandworker.shared.controller.JSONParser;
import com.example.emilstepanian.justhandworker.shared.controller.SlideAdapter;
import com.example.emilstepanian.justhandworker.shared.model.RequiredInfo;
import com.example.emilstepanian.justhandworker.shared.model.RequiredInfoValue;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class JobPageActivity extends AppCompatActivity {

    private TextView fullName, date, location, title, description;


    private ViewPager mPager;
    private int currentPage = 0;
    private final Integer[] imageInts = {R.drawable.balcony, R.drawable.wc, R.drawable.sink};
    private ArrayList<Integer> imagesIntArray = new ArrayList<>();
    private FloatingActionButton sendBidBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_page);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle jobData = getIntent().getExtras();

        loadJob(jobData);

        initImageSlider();

        sendBidBtn = (FloatingActionButton) findViewById(R.id.send_bid_button);

        sendBidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendBidFragment sendBidFragment = new SendBidFragment();
                sendBidFragment.show(getSupportFragmentManager(), "hej");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

            finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void loadJob(final Bundle jobData) {



        fullName = (TextView) findViewById(R.id.full_name_job_page);
        final Handler handler = new Handler();
        final Runnable retrieveUser = new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonUser = JSONParser.getJSONObjectById(getApplicationContext(), "user", jobData.getInt("userId"));
                    fullName.setText(jsonUser.getString("firstName") + " " + jsonUser.getString("lastName"));

                } catch (Exception e){

                }
            }
        };
        handler.post(retrieveUser);

        date = (TextView) findViewById(R.id.text_date_job_page);
        date.setText(jobData.getString("date"));

        location = (TextView) findViewById(R.id.location_text_job_page);
        location.setText(jobData.getString("location"));

        title = (TextView) findViewById(R.id.title_job_page);
        title.setText(jobData.getString("title"));

        description = (TextView) findViewById(R.id.description_job_page);
        description.setText(jobData.getString("description"));

        getRequiredFields(jobData.getInt("categoryId"));



    }

    private void getRequiredFields(final int categoryId) {
        final Context context = this;

        final Handler handler = new Handler();
        final Runnable getRequiredFields = new Runnable() {
            @Override
            public void run() {
                List<RequiredInfo> requiredInfoList = JSONParser.getListData(getApplicationContext(), "requiredInfo");
                List<RequiredInfoValue> requiredInfoValues = JSONParser.getListData(getApplicationContext(), "requiredInfoValue" );


                LinearLayout requiredInfoLayout = (LinearLayout) findViewById(R.id.requiredInfo_job_page_container);

                LayoutInflater inflater = LayoutInflater.from(context);

                for(RequiredInfo requiredInfo : requiredInfoList) {

                    if (categoryId == requiredInfo.getCategoryId()) {

                        View view = inflater.inflate(R.layout.requiredinfo, requiredInfoLayout, false);

                        TextView left = (TextView) view.findViewById(R.id.requiredInfo_field);

                        left.setText(requiredInfo.getTitle());


                        EditText requiredInfoValueField = (EditText) view.findViewById(R.id.requiredInfoValue_field);
                        requiredInfoValueField.setEnabled(false);


                        for (RequiredInfoValue value : requiredInfoValues) {

                            if (value.getRequiredInfoId() == requiredInfo.getId()) {
                                requiredInfoValueField.setText(value.getValue());
                            }
                        }

                        requiredInfoLayout.addView(view);
                    }
                }

            }
        };

        handler.post(getRequiredFields);



    }

    private void initImageSlider() {
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
            }, 4000, 4000);






    }
}

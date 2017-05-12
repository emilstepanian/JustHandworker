package com.example.emilstepanian.justhandworker.shared.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.jobowner.ui.JobOwnerMainActivity;
import com.example.emilstepanian.justhandworker.jobtaker.ui.HomeContainerFragment;
import com.example.emilstepanian.justhandworker.jobtaker.ui.JobTakerMainActivity;
import com.example.emilstepanian.justhandworker.jobtaker.ui.SendBidFragment;
import com.example.emilstepanian.justhandworker.shared.controller.JSONParser;
import com.example.emilstepanian.justhandworker.shared.controller.SlideAdapter;
import com.example.emilstepanian.justhandworker.shared.model.Bid;
import com.example.emilstepanian.justhandworker.shared.model.Image;
import com.example.emilstepanian.justhandworker.shared.model.RequiredInfo;
import com.example.emilstepanian.justhandworker.shared.model.RequiredInfoValue;
import com.example.emilstepanian.justhandworker.shared.model.User;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class JobPageActivity extends AppCompatActivity {

    private TextView fullName, date, location, title, description;


    private ViewPager mPager;
    private int currentPage = 0;
    private FloatingActionButton sendBidBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        sendBidBtn = (FloatingActionButton) findViewById(R.id.send_bid_button);
        final Bundle jobData = getIntent().getExtras();


        if(JobOwnerMainActivity.getCurrentUser() != null){
            sendBidBtn.setVisibility(View.INVISIBLE);
            LinearLayout contentLayout = (LinearLayout) findViewById(R.id.job_page_content_layout);
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());


            //Tilføjer bids en efter en i stedet for at bruge recyclerviewet. Det er for tilpasset til tablayoutet, at det ikke kan bruges,
            //så dette er et hotfix
            List<Bid> bidList = JSONParser.getListData(getApplicationContext(), "bid");
            List<User> userList = JSONParser.getListData(getApplicationContext(), "user");
            for (Bid bid : bidList) {
                if( bid.getJobId() == jobData.getInt("id")){
                    View bidView = inflater.inflate(R.layout.bid, contentLayout, false);
                    bidView.setBackgroundColor(Color.WHITE);
                    (contentLayout.findViewById(R.id.bids_section)).setVisibility(View.VISIBLE);
                    ((TextView) bidView.findViewById(R.id.bid_title)).setText(jobData.getString("title"));
                    for(User user : userList){
                        if(user.getId() == bid.getUserId()) {
                            ((TextView) bidView.findViewById(R.id.bid_jobOwner)).setText("Budt af " + user.getFirstName() + " " + user.getLastName());

                        }
                    }

                    ((TextView) bidView.findViewById(R.id.bid_location_text)).setText("");

                    TextView bidPrice = (TextView) bidView.findViewById(R.id.bid_price);

                    bidPrice.setText(String.valueOf(bid.getPrice()) + " kr.");
                    bidPrice.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));

                    if(bid.isAccepted()){
                        ((TextView) bidView.findViewById(R.id.bid_date)).setText("Bud Accepteret");
                        ((TextView) bidView.findViewById(R.id.bid_date)).setTextColor(Color.GREEN);


                    } else {
                        ((TextView) bidView.findViewById(R.id.bid_date)).setText("Ikke accepteret");

                    }

                    ((ImageView) bidView.findViewById(R.id.bid_image)).setImageResource(getResources().getIdentifier(jobData.getString("imageTitle"), "drawable", getApplicationContext().getPackageName()));
                    contentLayout.addView(bidView);
                    final Bid finalBid = bid;
                    bidView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getApplicationContext(), ChatActivity.class);
                            i.putExtra("bidJobId", jobData.getInt("id"));
                            i.putExtra("bidId", finalBid.getId());

                            startActivity(i);
                        }
                    });

                }
            }



        }




        loadJob(jobData);

        initImageSlider();


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

        final ArrayList<Integer> imagesIntArray = new ArrayList<>();
        int jobId = getIntent().getExtras().getInt("id");

        List<Image> imageList = JSONParser.getListData(getApplicationContext(), "image");
        for(Image image : imageList) {
            if(jobId == image.getJobId()) {
                imagesIntArray.add(getResources().getIdentifier(image.getImageTitle(), "drawable", getApplicationContext().getPackageName()));
            }
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
                    if (currentPage ==imagesIntArray.size()) {
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
            }, 3000, 3000);






    }
}

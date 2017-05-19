package com.example.emilstepanian.justhandworker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.model.Bid;
import com.example.emilstepanian.justhandworker.model.Job;

import org.json.JSONObject;

import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private List<Job> jobList;
    int bidJobId, bidId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        jobList = JSONParser.getListData(this, "job");
        bidJobId = getIntent().getIntExtra("bidJobId", 0);

        bidId = getIntent().getExtras().getInt("bidId");



        setContentView(R.layout.activity_chat);


    }


    //Inflate the menu with the see job task
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
        if(bidId != 999) {
             menu.getItem(0).setTitle("Acceptér bud");
        }
        return true;
    }

    //Add functionality to menu buttons
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;

            //TODO: har hentet hele listen her.. burde måske bare lave et template objekt i stedet?
            //TODO: mindre detalje, men er det problematisk at starte en ny jobactivity? Principielt set sidder byd knappen der jo..
            case R.id.chatView_see_job_item:

                if(bidId != 999) {

                List<Bid> bidList = JSONParser.getListData(getApplicationContext(), "bid");
                    for(Bid bid: bidList) {
                        if(bid.getId() == bidId){
                            try {
                                JSONObject jsonBid = JSONParser.getJSONObjectById(getApplicationContext(), "bid", bidId);
                                JSONParser.updateBidInDatabase(getApplicationContext(), jsonBid, "bid");
                                Toast.makeText(getApplicationContext(), "Bud accepteret",
                                        Toast.LENGTH_LONG).show();
                                finish();

                            } catch (Exception e) {

                            }
                        }
                    }


                } else {

                    for (Job job : jobList) {
                        if (job.getId() == bidJobId) {
                            Intent i = new Intent(this, JobPageActivity.class);
                            i.putExtra("id", job.getId());
                            i.putExtra("title", job.getTitle());
                            i.putExtra("description", job.getDescription());
                            i.putExtra("location", job.getLocation());
                            i.putExtra("date", job.getDate());
                            i.putExtra("imageTitle", job.getMainImageTitle());
                            i.putExtra("imageResourceId", job.getMainImageResourceId());
                            i.putExtra("categoryId", job.getCategoryId());
                            i.putExtra("userId", job.getUserId());

                            startActivity(i);
                        }
                    }
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

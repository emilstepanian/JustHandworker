package com.example.emilstepanian.justhandworker.shared.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.shared.controller.JSONParser;
import com.example.emilstepanian.justhandworker.shared.model.Job;

import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private List<Job> dummyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dummyData = JSONParser.getListData(this, "job");

        setContentView(R.layout.activity_chat);
    }


    //Inflate the menu with the see job task
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
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
                Job job = dummyData.get(0);
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



            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

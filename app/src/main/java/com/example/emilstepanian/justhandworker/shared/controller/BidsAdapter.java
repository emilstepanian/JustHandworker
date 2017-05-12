package com.example.emilstepanian.justhandworker.shared.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.shared.model.Bid;
import com.example.emilstepanian.justhandworker.shared.model.Job;
import com.example.emilstepanian.justhandworker.shared.model.User;
import com.example.emilstepanian.justhandworker.shared.ui.ChatActivity;

import java.util.List;

/**
 * Created by Kasper on 07/05/2017.
 */


public class BidsAdapter extends RecyclerView.Adapter<BidsAdapter.BidHolder> {

    private List<Bid> bidList;
    private LayoutInflater inflater;
    private List<User> userList;
    private List<Job> jobsList;


    public BidsAdapter(List<Bid> bidsList, Context context) {
        this.bidList  = bidsList;
        this.inflater = LayoutInflater.from(context);

        userList = JSONParser.getListData(context, "user");
        jobsList = JSONParser.getListData(context, "job");
    }


    @Override
    public BidsAdapter.BidHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.bid, parent, false);
        return new BidHolder(view);
    }

    @Override
    public void onBindViewHolder(BidHolder holder, int position) {

        final BidHolder bidHolder = holder;
        final Bid bidItem = bidList.get(position);

        new android.os.Handler().post(
                new Runnable() {
                    @Override
                    public void run() {


                        bidHolder.price.setText(Double.toString(bidItem.getPrice()) + " kr.");
                        bidHolder.date.setText(bidItem.getDate());

                        for (User user : userList) {
                            if (user.getId() == bidItem.getUserId()) {
                                bidHolder.userId.setText(user.getFirstName() + " " + user.getLastName());
                            }
                        }

                        for (Job job : jobsList) {
                            if (bidItem.getJobId() == job.getId()) {
                                bidHolder.title.setText(job.getTitle());
                                //bidHolder.image.setImageResource(job.getMainImageResourceId());
                                bidHolder.image.setImageResource(bidHolder.container.getResources().getIdentifier(job.getMainImageTitle(), "drawable", bidHolder.container.getContext().getPackageName()));
                                bidHolder.location.setText(job.getLocation());
                            }
                        }
                    }
                });



        bidHolder.container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(bidHolder.container.getContext(), ChatActivity.class);
                int bidJobId = bidItem.getJobId();

                //Jobid needed so that we know which job to show, when the 'see job' is pressed in ChatView
                i.putExtra("bidJobId", bidJobId);
                bidHolder.container.getContext().startActivity(i);
            }
        });
    }


/*
    //Model members
    String name;
    int jobId;
    double price;
    String comment;
    int userId;
    boolean isAccepted;
    Date date;

    //View members
    Drawable image;

    */

    @Override
    public int getItemCount() {
        return bidList.size();
    }

    class BidHolder extends RecyclerView.ViewHolder{

        private View container;
        private ImageView image;
        private TextView title;
        private TextView price;
        private TextView location;
        private TextView date;
        private TextView userId;



        public BidHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.bid_image);
            title = (TextView) itemView.findViewById(R.id.bid_title);
            price = (TextView) itemView.findViewById(R.id.bid_price);
            location = (TextView) itemView.findViewById(R.id.bid_location_text);
            date = (TextView) itemView.findViewById(R.id.bid_date);
            userId = (TextView) itemView.findViewById(R.id.bid_jobOwner);
            container = itemView.findViewById(R.id.bid_card_view);
        }


    }
}

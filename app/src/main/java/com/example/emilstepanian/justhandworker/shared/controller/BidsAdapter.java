package com.example.emilstepanian.justhandworker.shared.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.shared.model.Bid;
import com.example.emilstepanian.justhandworker.shared.ui.ChatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Kasper on 07/05/2017.
 */


public class BidsAdapter extends RecyclerView.Adapter<BidsAdapter.BidHolder> {

    private List<Bid> bidList;
    private LayoutInflater inflater;


    public BidsAdapter(List<Bid> bidsList, Context context) {
        this.bidList  = bidsList;
        this.inflater = LayoutInflater.from(context);
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
        bidHolder.name.setText(bidItem.getName());

        bidHolder.container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(bidHolder.container.getContext(), ChatActivity.class);
                bidHolder.container.getContext().startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return bidList.size();
    }

    class BidHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private View container;

        public BidHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.bid_name);
            container = itemView.findViewById(R.id.bid_card_view);
        }


    }
}

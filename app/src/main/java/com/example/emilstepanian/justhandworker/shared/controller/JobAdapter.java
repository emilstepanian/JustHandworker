package com.example.emilstepanian.justhandworker.shared.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.shared.model.Job;

import java.util.List;

/**
 * Created by emilstepanian on 04/05/2017.
 */

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobHolder>{

    private List<Job> listData;
    private LayoutInflater inflater;

    public JobAdapter(List<Job> listData, Context context) {

        this.inflater = LayoutInflater.from(context);
        this.listData = listData;
    }


    @Override
    public JobHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.job, parent, false);


        return new JobHolder(view);
    }

    @Override
    public void onBindViewHolder(JobHolder holder, int position) {
            Job jobItem = listData.get(position);
        holder.title.setText(jobItem.getTitle());
        holder.description.setText(jobItem.getDescription());
        holder.location.setText(jobItem.getLocation());
        holder.date.setText(jobItem.getDate());
        holder.image.setImageResource(holder.container.getResources().getIdentifier(jobItem.getMainImageTitle(),"drawable",holder.container.getContext().getPackageName()));


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class JobHolder extends RecyclerView.ViewHolder {


        private TextView title, location, date, description;

        private ImageView image;
        private View container;

        public JobHolder(View itemView) {
            super(itemView);



            title = (TextView) itemView.findViewById(R.id.lbl_item_text);
            description = (TextView) itemView.findViewById(R.id.comment_text);
            date = (TextView) itemView.findViewById(R.id.date_text);
            location = (TextView) itemView.findViewById(R.id.location_text);
           image = (ImageView) itemView.findViewById(R.id.im_item_icon);
            container = itemView.findViewById(R.id.card_view);
        }
    }

}

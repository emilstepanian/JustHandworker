package com.example.emilstepanian.justhandworker.shared.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.shared.model.Job;
import com.example.emilstepanian.justhandworker.shared.ui.JobPageActivity;

import java.util.List;

/**
 * Created by emilstepanian on 04/05/2017.
 *
 * test emil
 */

//Test kasper what up

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
        final JobHolder finalHolder = holder;
            final Job jobItem = listData.get(position);
        finalHolder.title.setText(jobItem.getTitle());
        finalHolder.description.setText(jobItem.getDescription());
        finalHolder.location.setText(jobItem.getLocation());
        finalHolder.date.setText(jobItem.getDate());
        finalHolder.image.setImageResource(holder.container.getResources().getIdentifier(jobItem.getMainImageTitle(),"drawable",holder.container.getContext().getPackageName()));

        finalHolder.container.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(finalHolder.container.getContext(), JobPageActivity.class);
                i.putExtra("id", jobItem.getId());
                i.putExtra("title", jobItem.getTitle());
                i.putExtra("description", jobItem.getDescription());
                i.putExtra("location", jobItem.getLocation());
                i.putExtra("date", jobItem.getDate());
                i.putExtra("imageTitle", jobItem.getMainImageTitle());
                i.putExtra("imageResourceId", jobItem.getMainImageResourceId());
                i.putExtra("categoryId", jobItem.getCategoryId());
                i.putExtra("userId", jobItem.getUserId());

                finalHolder.container.getContext().startActivity(i);
            }

        });

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

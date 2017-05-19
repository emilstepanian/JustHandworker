package com.example.emilstepanian.justhandworker.controller;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.emilstepanian.justhandworker.R;

import java.util.ArrayList;

/**
 * Created by emilstepanian on 06/05/2017.
 * https://www.androidtutorialpoint.com/basics/android-image-slider-tutorial/
 */

public class SlideAdapter extends PagerAdapter {

    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;

    public SlideAdapter(Context context, ArrayList<Integer> images) {
this.images = images;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }



    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slide, view, false);
        ImageView image = (ImageView) imageLayout.findViewById(R.id.slideImage);

        image.setImageResource(images.get(position));

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}

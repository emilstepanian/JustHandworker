package com.example.emilstepanian.justhandworker.shared.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.jobowner.ui.JobOwnerMainActivity;
import com.example.emilstepanian.justhandworker.jobtaker.ui.JobTakerMainActivity;
import com.example.emilstepanian.justhandworker.shared.controller.JSONParser;
import com.example.emilstepanian.justhandworker.shared.model.Profession;
import com.example.emilstepanian.justhandworker.shared.model.User;

import org.json.JSONObject;


public class ProfilePageFragment extends Fragment {

    public ProfilePageFragment() {
    }

    View fragmentView;
    TextView nameField, professionField;
    LinearLayout professionIdLayout;
    RatingBar ratingBar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_profile_page, container, false);
        ratingBar = (RatingBar) fragmentView.findViewById(R.id.rating);
        nameField = (TextView) fragmentView.findViewById(R.id.profile_page_fullname);
        professionField = (TextView) fragmentView.findViewById(R.id.profile_page_profession);
        professionIdLayout = (LinearLayout) fragmentView.findViewById(R.id.professionIdLayout);

        setUser();
        return fragmentView;


    }

    public void setUser() {
        User currentUser;

        if(getActivity().getClass() == JobOwnerMainActivity.class){
            currentUser = ((JobOwnerMainActivity) getActivity()).getCurrentUser();
    } else {
            currentUser = ((JobTakerMainActivity) getActivity()).getCurrentUser();
        }

        nameField.setText(currentUser.getFirstName() + " " + currentUser.getLastName());

        if (currentUser.getProfessionId() == 0) {
            professionField.setVisibility(View.INVISIBLE);
            professionField.setHeight(0);
            professionIdLayout.setVisibility(View.INVISIBLE);
            ratingBar.setVisibility(View.INVISIBLE);

        } else {
            try {
                Profession profession = new Profession();
                JSONObject jsonObject = JSONParser.getJSONObjectById(getContext(), "profession", currentUser.getProfessionId());
                profession.setId(jsonObject.getInt("id"));
                profession.setTitle(jsonObject.getString("title"));
                professionField.setText(profession.getTitle());

            } catch (Exception e) {

            }
        }
    }

}

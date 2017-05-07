package com.example.emilstepanian.justhandworker.shared.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.shared.controller.BidsAdapter;
import com.example.emilstepanian.justhandworker.shared.controller.JSONParser;
import com.example.emilstepanian.justhandworker.shared.controller.JobAdapter;
import com.example.emilstepanian.justhandworker.shared.model.Bid;
;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kasper on 07/05/2017.
 */

//Recyclerview for the jobtakers messagesView.
public class BidRecyclerViewFragment extends Fragment {

    private RecyclerView recyclerView;
    private BidsAdapter bidsAdapter;


    //TODO: skal den ligge her, eller skal den ligge i shared?
    public BidRecyclerViewFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bid_recycler_view, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.bids_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(getParentFragment().getActivity()));

        //List<Bid> bidsList = JSONParser.getListData(getParentFragment().getContext(), "bid");

        //Test data
        List<Bid> bidsList = new ArrayList<>();
        Bid b1 = new Bid("Bud 1");
        Bid b2 = new Bid("Bud 2");
        Bid b3 = new Bid("Bud 3");
        Bid b4 = new Bid("Bud 4");

        bidsList.add(b1);
        bidsList.add(b2);
        bidsList.add(b3);
        bidsList.add(b4);


        bidsAdapter = new BidsAdapter(bidsList, getParentFragment().getActivity());

        recyclerView.setAdapter(bidsAdapter);

        return rootView;

    }
}

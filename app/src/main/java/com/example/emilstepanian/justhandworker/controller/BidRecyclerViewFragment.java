package com.example.emilstepanian.justhandworker.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.model.Bid;
import com.example.emilstepanian.justhandworker.model.User;
;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kasper on 07/05/2017.
 */

//Recyclerview for the jobtakers messagesView.
public class BidRecyclerViewFragment extends Fragment {

    private RecyclerView recyclerView;
    private BidAdapter bidAdapter;
    List<Bid> bidsList, filteredBidsList;


    //TODO: skal den ligge her, eller skal den ligge i shared?
    public BidRecyclerViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bid_recycler_view, container, false);
        bidsList = JSONParser.getListData(container.getContext(), "bid");


        recyclerView = (RecyclerView) rootView.findViewById(R.id.bids_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        //Tilføjet for kun at vise håndværkerens egne bud
        Bundle userInfo = this.getActivity().getIntent().getExtras();
        User currentUser = new User(userInfo.getInt("id"), userInfo.getInt("professionId"), userInfo.getString("firstName"), userInfo.getString("lastName"), userInfo.getString("username"), userInfo.getString(("password")));
        filteredBidsList = new ArrayList<>();

        for (int i = 0; i < bidsList.size(); i++) {
            Bid bid = bidsList.get(i);
            if (currentUser.getId() == bid.getUserId()) {
                filteredBidsList.add(bidsList.get(i));
            }
        }


        Bundle b = null;
        if (getArguments() != null) {
            b = getArguments();
        }


        switch (b.getString("content")) {
            case "nonAcceptedBids":
                filteredBidsList = createNonAcceptedBidsList();
                break;

            case "AcceptedBids":
                filteredBidsList = createAcceptedBidsList();
                break;

            default:
                ArrayList<Bid> emptyList = new ArrayList<>();
                filteredBidsList = emptyList;
                break;
        }

        bidAdapter = new BidAdapter(filteredBidsList, getActivity());
        recyclerView.setAdapter(bidAdapter);

        return rootView;
    }


    private List<Bid> createNonAcceptedBidsList() {
        List<Bid> nonAcceptedBidsList = new ArrayList();
        for (Bid bid : filteredBidsList) {
            if (bid.isAccepted() == false) {
                nonAcceptedBidsList.add(bid);
            }
        }
        return nonAcceptedBidsList;
    }

    private List<Bid> createAcceptedBidsList() {
        List<Bid> acceptedBidsList = new ArrayList();
        for (Bid bid : filteredBidsList) {
            if (bid.isAccepted() == true) {
                acceptedBidsList.add(bid);
            }
        }
        return acceptedBidsList;
    }

}

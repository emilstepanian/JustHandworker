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
import com.example.emilstepanian.justhandworker.shared.model.Bid;
;

import java.util.ArrayList;
import java.util.List;

import static com.example.emilstepanian.justhandworker.R.id.container;

/**
 * Created by Kasper on 07/05/2017.
 */

//Recyclerview for the jobtakers messagesView.
public class BidRecyclerViewFragment extends Fragment {

    private RecyclerView recyclerView;
    private BidsAdapter bidsAdapter;
    List<Bid> bidsList;


    //TODO: skal den ligge her, eller skal den ligge i shared?
    public BidRecyclerViewFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bid_recycler_view, container, false);
        bidsList = JSONParser.getListData(container.getContext(), "bid");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.bids_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getParentFragment().getActivity()));

        Bundle b = null;
        if (getArguments() != null) {
            b = getArguments();
        }


        switch (b.getString("content")){
            case "nonAcceptedBids":
                bidsList = createNonAcceptedBidsList();
                break;

            case "AcceptedBids":
                bidsList = createAcceptedBidsList();
                break;

            default:
                ArrayList<Bid> emptyList = new ArrayList<>();
                bidsList = emptyList;
                break;
        }

        bidsAdapter = new BidsAdapter(bidsList, getParentFragment().getActivity());
        recyclerView.setAdapter(bidsAdapter);

        return rootView;
    }


    private List<Bid> createNonAcceptedBidsList(){
        List<Bid> nonAcceptedBidsList = new ArrayList();
        for(Bid bid : bidsList){
            if(bid.isAccepted() == false){
                nonAcceptedBidsList.add(bid);
            }
        }
        return nonAcceptedBidsList;
    }

    private List<Bid> createAcceptedBidsList(){
        List<Bid> acceptedBidsList = new ArrayList();
        for(Bid bid : bidsList){
            if(bid.isAccepted() == true){
                acceptedBidsList.add(bid);
            }
        }
        return acceptedBidsList;
    }

}

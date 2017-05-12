package com.example.emilstepanian.justhandworker.jobtaker.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.shared.controller.JSONParser;
import com.example.emilstepanian.justhandworker.shared.model.Bid;
import com.example.emilstepanian.justhandworker.shared.model.Job;
import com.example.emilstepanian.justhandworker.shared.model.User;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class SendBidFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final View dialogContent = getActivity().getLayoutInflater().inflate( R.layout.fragment_send_bid_dialog, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Send et bud");


        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    JSONObject jsonBid = new JSONObject();
                    EditText price = (EditText) dialogContent.findViewById(R.id.new_bid_price);
                    EditText comment = (EditText) dialogContent.findViewById(R.id.new_bid_comment);

                    jsonBid.put("price", price.getText().toString());
                    jsonBid.put("isAccepted", "false");

                    long yourmilliseconds = System.currentTimeMillis();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                    Date resultdate = new Date(yourmilliseconds);

                    jsonBid.put("date", resultdate);

                    List<Bid> bidList = JSONParser.getListData(getContext(), "bid");
                    int bidId = 0;
                    for (Bid bid : bidList){
                        if(bidId <= bid.getId()){
                            bidId = bid.getId();
                            bidId++;
                        }
                    }

                    jsonBid.put("id", bidId);



                    //Gået i stå her. Kan ikke finde frem til parent activiteten - jobtakermain. Skal have current users Id, som bliver loadet i den, men problemet er, at vi kommer til denne side gennem jobpageactivity
                    //Nedenstående virker ikke
                    Bundle currentUserInfo = getActivity().getParentActivityIntent().getExtras();
                    jsonBid.put("userId", currentUserInfo.getInt("id"));

                    System.out.println(currentUserInfo.getString("firstName"));
                } catch (Exception e){

                }

                        /*
                              "id": "1",
      "jobId": "1",
      "price": "650",
      "userId": "4",
      "isAccepted": "false",
      "date": "28/02/2017"

                         */
            }
        });

        builder.setNegativeButton("Annullér", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Bud annulleret",
                        Toast.LENGTH_SHORT).show();

            }
        });


        builder.setView(dialogContent);


        return builder.create();
    }

}

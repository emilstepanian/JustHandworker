package com.example.emilstepanian.justhandworker.jobtaker.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emilstepanian.justhandworker.R;
import com.example.emilstepanian.justhandworker.shared.controller.JSONParser;
import com.example.emilstepanian.justhandworker.shared.model.Bid;

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

                    jsonBid.put("date", sdf.format(resultdate).toString());

                    List<Bid> bidList = JSONParser.getListData(getContext(), "bid");
                    int bidId = 0;
                    for (Bid bid : bidList){
                        if(bidId <= bid.getId()){
                            bidId = bid.getId();
                            bidId++;
                        }
                    }

                    jsonBid.put("id", bidId);

                    jsonBid.put("userId", JobTakerMainActivity.getCurrentUser().getId());

                    Bundle jobInfo = getActivity().getIntent().getExtras();

                    jsonBid.put("jobId", jobInfo.getInt("id"));

                    JSONParser.insertIntoDatabase(getContext(), jsonBid, "bid");


                    Toast.makeText(getContext(), "Bud oprettet",
                            Toast.LENGTH_LONG).show();
                    getActivity().finish();


                } catch (Exception e){

                }


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

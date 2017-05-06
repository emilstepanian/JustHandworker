package com.example.emilstepanian.justhandworker.workman.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.emilstepanian.justhandworker.R;


/*
  Lav messages for håndværker:
  Skal kunne skelne i toppen mellem bud og aftaler.
  Vi differentierer mellem bud og aftaler i databasen gennem attributten isAccepted.
  Todo: Find elementet der kan skifte mellem bud og aftaler.
  Todo: Tilpas messagesfragmentet til at indeholde recyclerviewet

  Todo: Lav et fragment indeholdende data hvor isAccepted = true
  Todo: Lav et fragment indeholdende data hvor isAccepted = false

  Todo: Når der trykkes på knappen skal der skiftes mellem fragmenterne

  Eksempel fundet på:
  http://www.truiton.com/2015/06/android-tabs-example-fragments-viewpager/
 */

public class MessagesFragment extends Fragment {
    public MessagesFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_messages, container, false);






        return rootView;
    }
}

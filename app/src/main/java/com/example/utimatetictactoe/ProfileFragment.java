package com.example.utimatetictactoe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ProfileFragment extends Fragment {
    DBHelper db;
    TextView tvUsername, tvGames, tvWins, tvLosses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        tvUsername = v.findViewById(R.id.tvUsername);
        tvUsername.setText(RegisterActivity.getUsername());

        tvGames = v.findViewById(R.id.tvTotalGames);
        tvWins = v.findViewById(R.id.tvWins);
        tvLosses = v.findViewById(R.id.tvLosses);

        DBHelper db = new DBHelper(this.getContext());

        ArrayList<Integer>data= db.getUserData();

        tvGames.setText(tvGames.getText()+" "+data.get(1));
        tvWins.setText(tvWins.getText()+" "+data.get(2));
        tvLosses.setText(tvLosses.getText()+" "+data.get(3));

        return v;
    }
}
package com.example.utimatetictactoe;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

/**
 * A simple {@link Fragment} subclass.
 * This is the home page of the app.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    ImageButton ibx, ibo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ibx = view.findViewById(R.id.btn_x);
        ibo = view.findViewById(R.id.btn_o);

        ibx.setOnClickListener(this);
        ibo.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(@NonNull View view) {
        Bundle args = new Bundle();
        SkinsFragment fragment = new SkinsFragment();
        if (view.equals(ibx)) {
            args.putInt("pos", 0);
            fragment.setArguments(args);
        }
        else if (view.equals(ibo)) {
            args.putInt("pos", 2);
            fragment.setArguments(args);
        }
        MainActivity.bottomNavigationView.setSelectedItemId(R.id.shop);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

}
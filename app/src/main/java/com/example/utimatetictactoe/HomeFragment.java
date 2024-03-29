package com.example.utimatetictactoe;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

/**
 * A simple {@link Fragment} subclass.
 * This is the home page of the app.
 */
public class HomeFragment extends Fragment{// implements View.OnClickListener {

    static ImageButton ibx, ibo;
    Button play,btn_logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ibx = view.findViewById(R.id.btn_x);
        ibo = view.findViewById(R.id.btn_o);

        /*ibx.setOnClickListener(this);
        ibo.setOnClickListener(this);*/

        play = view.findViewById(R.id.btnPlay);
        play.setOnClickListener(this::play);

        btn_logout = view.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(this::logout);
        return view;
    }

    private void logout(View view) {
        Intent intent = new Intent(getContext(), RegisterActivity.class);
        startActivity(intent);
    }


    /*@Override
    public void onClick(@NonNull View view) {
        Bundle args = new Bundle();
        SkinsFragment fragment = new SkinsFragment();
        if(ShopFragment.isShopInit==true) {
            if (view.equals(ibx)) {
                args.putInt("pos", 1);
                fragment.setArguments(args);
            } else if (view.equals(ibo)) {
                args.putInt("pos", 2);
                fragment.setArguments(args);
            }
        }
        else{
            args.putInt("pos", 0);
            fragment.setArguments(args);
        }
        MainActivity.bottomNavigationView.setSelectedItemId(R.id.shop);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }*/


    public void play(View view) {
        /* update db for:
        * win
        * lose
        * buy skin
         */
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new PlayFragment()).commit();

    }
}
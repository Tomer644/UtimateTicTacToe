package com.example.utimatetictactoe;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.os.LocaleListCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.checkerframework.common.returnsreceiver.qual.This;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
    DBHelper db;
    TextView tvUsername, tvTrophies, tvGames, tvWins, tvLosses, tvSkinsOwn;
    ImageView pfp;

    Button add;
    public int pfps_uploaded;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        tvUsername = v.findViewById(R.id.tvUsername);
        tvUsername.setText(RegisterActivity.getUsername());

        tvTrophies = v.findViewById(R.id.tvTrophies);
        tvGames = v.findViewById(R.id.tvTotalGames);
        tvWins = v.findViewById(R.id.tvWins);
        tvLosses = v.findViewById(R.id.tvLosses);
        tvSkinsOwn = v.findViewById(R.id.tvSkinsOwn);

        pfp = v.findViewById(R.id.imageViewPfp);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            byte[]icon = bundle.getByteArray("image");
            Bitmap pic = BitmapFactory.decodeByteArray(icon, 0, icon.length);
            pfp.setImageBitmap(pic);
        }
        pfp.setOnClickListener(this);
        add = v.findViewById(R.id.btnUploadImg);
        add.setOnClickListener(this);//::addImage);

        db = new DBHelper(this.getContext());

        ArrayList<Integer>data= db.getUserData(RegisterActivity.getUsername());

        tvTrophies.setText(tvTrophies.getText()+" "+data.get(0));
        tvGames.setText(tvGames.getText()+" "+data.get(1));
        tvWins.setText(tvWins.getText()+" "+data.get(2));
        tvLosses.setText(tvLosses.getText()+" "+data.get(3));
        tvSkinsOwn.setText(tvSkinsOwn.getText()+""+data.get(4));

        pfps_uploaded = data.get(5);
        //Toast.makeText(getContext(), pfps_uploaded+"", Toast.LENGTH_SHORT).show();
        return v;
    }

//    public void tapImage(View view) {
//        Bundle args = new Bundle();
//        if(view == pfp){
//            RetrivePfpFragment fragment = new RetrivePfpFragment();
//            args.putInt("pfps_uploaded", pfps_uploaded);
//            fragment.setArguments(args);
//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
//        }
//        if(view == add){
//            UploadImageFragment fragment = new UploadImageFragment();
//            args.putInt("pfps_uploaded", pfps_uploaded);
//            fragment.setArguments(args);
//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
//        }
//
//    }
//
//    private void addImage(View view){
//        Bundle args = new Bundle();
//        UploadImageFragment fragment = new UploadImageFragment();
//        args.putInt("pfps_uploaded", pfps_uploaded);
//        fragment.setArguments(args);
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
//    }


    @Override
    public void onClick(View view) {
        Bundle args = new Bundle();
        if(view == pfp){
            RetrivePfpFragment fragment = new RetrivePfpFragment();
            args.putInt("pfps_uploaded", pfps_uploaded);
            fragment.setArguments(args);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        }
        else if(view == add){
            UploadImageFragment fragment = new UploadImageFragment();
            args.putInt("pfps_uploaded", pfps_uploaded);
            fragment.setArguments(args);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        }
    }
}
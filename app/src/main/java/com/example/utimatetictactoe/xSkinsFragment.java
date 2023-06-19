package com.example.utimatetictactoe;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class xSkinsFragment extends Fragment implements ShopInterface{

    static ImageView equipedX;
    static Bitmap xBtm;

    private static RecyclerView recyclerView;
    static RecyclerView.LayoutManager layoutManager;
    static PhotosRecyclerAdapter recyclerAdapter;

    static ArrayList<Bitmap> xOwnedSkins;

    static List<Skin> skins;

    static boolean isXinit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_x_skins, container, false);

        equipedX = v.findViewById(R.id.currentSkinX);
        equipedX.setImageResource(R.drawable.x);

        recyclerView = v.findViewById(R.id.xSkinsRecycler);
        //xOwnedSkins = new ArrayList<>();

        inisialiseX();
        isXinit = true;
//        skins = ShopFragment.skinsList;
//        for (Skin skin: skins) {
//            if(skin.isOwned()){
//                xOwnedSkins.add(ShopFragment.photos.get(skin.getId()));
//            }
//        }
//
//        layoutManager = new GridLayoutManager(getContext(), 3);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerAdapter = new PhotosRecyclerAdapter(getContext(), xOwnedSkins);//bitmap list
//        recyclerView.setAdapter(recyclerAdapter);

        return v;
    }

    public static void inisialiseX(){
        xOwnedSkins = new ArrayList<>();
        skins = ShopFragment.skinsList;
        for (Skin skin: skins) {
            if(skin.isOwned()){
                if(!xOwnedSkins.contains(ShopFragment.photos.get(skin.getId())) && skin.getId().charAt(0)=='X') {
                    xOwnedSkins.add(ShopFragment.photos.get(skin.getId()));
                }
            }
        }

        layoutManager = new GridLayoutManager(recyclerView.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new PhotosRecyclerAdapter(recyclerView.getContext(), xOwnedSkins, new ShopInterface() {
            @Override
            public void onItemClick(int position) {
                equipedX.setImageBitmap(xOwnedSkins.get(position));
                xBtm = xOwnedSkins.get(position);
                HomeFragment.ibx.setImageBitmap(xOwnedSkins.get(position));
            }
        });//bitmap list
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onItemClick(int position) {

    }
}
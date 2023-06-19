package com.example.utimatetictactoe;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class oSkinsFragment extends Fragment implements ShopInterface{

    static ImageView equipedO;
    static Bitmap oBtm;

    private static RecyclerView recyclerView;
    static RecyclerView.LayoutManager layoutManager;
    static PhotosRecyclerAdapter recyclerAdapter;

    static ArrayList<Bitmap> oOwnedSkins;

    static List<Skin> skins;
    static boolean isOinit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_o_skins, container, false);

        equipedO = v.findViewById(R.id.currentSkinO);

        recyclerView = v.findViewById(R.id.oSkinsRecycler);
        //oOwnedSkins = new ArrayList<>();

        inisialiseO();
        isOinit = true;

        return v;
    }

    public static void inisialiseO(){
        oOwnedSkins = new ArrayList<>();
        skins = ShopFragment.skinsList;
        for (Skin skin: skins) {
            if(skin.isOwned()){
                if(!oOwnedSkins.contains(ShopFragment.photos.get(skin.getId())) && skin.getId().charAt(0)=='O') {
                    oOwnedSkins.add(ShopFragment.photos.get(skin.getId()));
                }
            }
        }

        layoutManager = new GridLayoutManager(recyclerView.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new PhotosRecyclerAdapter(recyclerView.getContext(), oOwnedSkins, new ShopInterface() {
            @Override
            public void onItemClick(int position) {
                equipedO.setImageBitmap(oOwnedSkins.get(position));
                oBtm = oOwnedSkins.get(position);
            }
        });//bitmap list
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onItemClick(int position) {

    }
}
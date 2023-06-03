package com.example.utimatetictactoe;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopFragment extends Fragment {

    RecyclerView recyclerView;
    StorageReference storageReference;
    //List<Skin>skins;
    FirebaseFirestore firestore;
    ProgressDialog progressDialog;
    MyAdapter adapter;
    List<Skin>skinsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_shop, container, false);

        recyclerView = v.findViewById(R.id.shopRecycler);
        //skins = RegisterActivity.getDefaultSkinsList();

        progressDialog = new ProgressDialog(this.getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        /*List<Skin>xOwned = new ArrayList<>();
        List<Skin>oOwned = new ArrayList<>();*/

        firestore = FirebaseFirestore.getInstance();
        DocumentReference doc = firestore.collection("users").document(RegisterActivity.getUsername());
        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){

                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                    //Bitmap
                    //Toast.makeText(getActivity(), "res: "+task.getResult().getData(), Toast.LENGTH_SHORT).show();
                    Map<String, Object>map = task.getResult().getData();
                    //task.getResult().getd
                    //skins = (ArrayList<Skin>) map.get(RegisterActivity.getUsername());
                    List<Object>objectList = (List<Object>) map.get(RegisterActivity.getUsername());
                    skinsList = new ArrayList<>();
                    for (int i = 0; i < objectList.size(); i++) {
                        HashMap<String,Object>objectHashMap = (HashMap<String, Object>) objectList.get(i);
                        SkinType st = setTypeByPrice((Long) objectHashMap.get("price"));
                        Skin skin = new Skin((String) objectHashMap.get("id"), (Boolean) objectHashMap.get("owned"), st);
                        //Toast.makeText(getActivity(), ""+skin, Toast.LENGTH_SHORT).show();
                        skinsList.add(skin);
                    }
                    startRecyclerView(skinsList);
                }
                else{
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        recyclerView.setAdapter(new MyAdapter(getContext(),skins));
//        recyclerView.setHasFixedSize(true);

        /*storageReference = FirebaseStorage.getInstance().getReference().child("Skins/");
        try {
            File localFile = File.createTempFile("tempfile", ".jpg");
            storageReference.getFile(localFile).add
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return v;
    }

    private void startRecyclerView(List<Skin>skins){
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new MyAdapter(getContext(),skins));
        recyclerView.setHasFixedSize(true);
    }

    public SkinType setTypeByPrice(long price) {
        if (price == 50) {
            return SkinType.rare;
        } else if (price == 150) {
            return SkinType.epic;
        } else if (price == 200) {
            return SkinType.legendary;
        }
        return SkinType.common;
    }

    public void buySkin(View view){

    }

}
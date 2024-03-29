package com.example.utimatetictactoe;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopFragment extends Fragment implements ShopInterface{ //implement

    RecyclerView recyclerView;
    StorageReference storageReference;
    //List<Skin>skins;
    FirebaseFirestore firestore;
    ProgressDialog progressDialog;
    MyAdapter adapter;

    public static List<Skin>skinsList;//skins object list

    int count;

    public static Map<String, Bitmap>photos;//skins photos
    DBHelper db;

    public static boolean isShopInit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_shop, container, false);

        recyclerView = v.findViewById(R.id.shopRecycler);
        //skins = RegisterActivity.getDefaultSkinsList();

        progressDialog = new ProgressDialog(this.getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        isShopInit = false;
        /*List<Skin>xOwned = new ArrayList<>();
        List<Skin>oOwned = new ArrayList<>();*/

        photos = new HashMap<>();
        db = new DBHelper(getContext());

        firestore = FirebaseFirestore.getInstance();
        DocumentReference doc = firestore.collection("users").document(RegisterActivity.getUsername());
        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){

//                    if(progressDialog.isShowing())
//                        progressDialog.dismiss();
                    //Bitmap
                    //Toast.makeText(getActivity(), "res: "+task.getResult().getData(), Toast.LENGTH_SHORT).show();
                    Map<String, Object>map = task.getResult().getData();
                    //task.getResult().getd
                    //skins = (ArrayList<Skin>) map.get(RegisterActivity.getUsername());
                    List<Object>objectList = (List<Object>) map.get(RegisterActivity.getUsername());
                    skinsList = new ArrayList<>();
                    for (int i = 0; i < objectList.size(); i++) {
                        count = i;
                        HashMap<String, Object> objectHashMap = (HashMap<String, Object>) objectList.get(i);
                        SkinType st = setTypeByPrice((Long) objectHashMap.get("price"));
                        Skin skin = new Skin((String) objectHashMap.get("id"), (Boolean) objectHashMap.get("owned"), st);
                        //Toast.makeText(getActivity(), ""+skin, Toast.LENGTH_SHORT).show();
                        skinsList.add(skin);

                        storageReference = FirebaseStorage.getInstance().getReference("Skins/"+skinsList.get(count).getId()+".png");//+skinsList.get(count).getId());//.child("Skins/");
                        try {
                            File localFile = File.createTempFile(skinsList.get(count).getId() + "", ".png");
                            //Toast.makeText(getActivity(), "file "+skinsList.get(count).getId()+" created", Toast.LENGTH_SHORT).show();
                            storageReference.getFile(localFile)
                                    .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                Bitmap tempbitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                                photos.put(skin.getId(), tempbitmap);
                                                //Toast.makeText(getActivity(), count+" done", Toast.LENGTH_SHORT).show();
                                                if (progressDialog.isShowing() && count == objectList.size() - 1) {
                                                    progressDialog.dismiss();
                                                    startRecyclerView(skinsList);
                                                    isShopInit = true;
                                                }
                                            }
                                            else{
                                                Toast.makeText(getActivity(), "error "+count, Toast.LENGTH_SHORT).show();
                                                progressDialog.dismiss();
                                            }
                                        }
                                    });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                }
                else{
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        recyclerView.setAdapter(new MyAdapter(getContext(),skins));
//        recyclerView.setHasFixedSize(true);


        return v;
    }

    private void startRecyclerView(List<Skin>skins){
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new MyAdapter(getContext(),skins, this));
        recyclerView.setHasFixedSize(true);
    }
    
    /*private void getPhotos(){
        storageReference = FirebaseStorage.getInstance().getReference().child("Skins/");
        
        try {
            for (int i = 0; i < 13; i++) {
                File localFile = File.createTempFile(skinsList.get(i).getId(), ".jpg");
                storageReference.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                if(task.isSuccessful()){
                                    Bitmap tempbitmap = BitmapFactory.decodeFile()
                                }
                            }
                        });
            }
            File localFile = File.createTempFile(skinsList.get(), ".jpg");
            storageReference.getFile(localFile).add
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

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

    @Override
    public void onItemClick(int position) {
        boolean canBuy = db.buySkin(RegisterActivity.getUsername(), skinsList.get(position).getPrice());
        //FirebaseFirestore firebaseFirestore = firestore = FirebaseFirestore.getInstance();
        if(canBuy){
            progressDialog = new ProgressDialog(this.getContext());
            progressDialog.setMessage("updating data...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            skinsList.get(position).setOwned(true);
            //Toast.makeText(getContext(), "Bought Skin", Toast.LENGTH_SHORT).show();

            Map<String, Object>data = new HashMap<>();
            data.put(RegisterActivity.getUsername(), skinsList);
            firestore.collection("users").document(RegisterActivity.getUsername()).set(data)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                               @Override
                                               public void onComplete(@NonNull Task<Void> task) {
                                                   if(progressDialog.isShowing())
                                                       progressDialog.dismiss();
                                                   if(task.isSuccessful()){
                                                       startRecyclerView(skinsList);
                                                       Toast.makeText(getContext(), "Bought Skin", Toast.LENGTH_SHORT).show();
                                                       xSkinsFragment.inisialiseX();
                                                       oSkinsFragment.inisialiseO();
                                                   }
                                                   else {
                                                       Toast.makeText(getContext(), "error updating data", Toast.LENGTH_SHORT).show();
                                                   }
                                               }
                                           });
        }
        else Toast.makeText(getContext(), "Failed! cant buy skin", Toast.LENGTH_SHORT).show();
    }

//    public static void buySkin(View view){
//
//        boolean canBuy = db.buySkin(RegisterActivity.getUsername(), view.get);
//        if(canBuy)
//
//    }

}
package com.example.utimatetictactoe;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class RetrivePfpFragment extends Fragment implements ShopInterface{


    ImageView rtrnArrow;
    private StorageReference storageReference;
    int pfps_uploaded;
    int count;

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    PhotosRecyclerAdapter recyclerAdapter;
    ArrayList<Bitmap>bitmapArrayList;

    public static Bitmap bitmappfp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_retrive_pfp, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pfps_uploaded = bundle.getInt("pfps_uploaded", 0);
        }

        ProgressDialog progressDialog = new ProgressDialog(this.getContext());
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        recyclerView = v.findViewById(R.id.pfpsRecyclerView);
        rtrnArrow = v.findViewById(R.id.rtrnArrow2);
        rtrnArrow.setOnClickListener(this::cancelUpload);

//        layoutManager = new GridLayoutManager(getContext(), 3);
//        recyclerView.setLayoutManager(layoutManager);
        //recyclerAdapter = new PhotosRecyclerAdapter(this, )
        //need to download all the pfps first into a bitmap list
        //for i : 0 - pfpsuploaded

        bitmapArrayList = new ArrayList<>();
        count = 0;
        for (int i = 0; i < pfps_uploaded; i++) {
            count = i;
            storageReference = FirebaseStorage.getInstance().getReference("image/"+RegisterActivity.getUsername()+"_pfp_"+count+".jpeg");
            try {
                File localFile = File.createTempFile(RegisterActivity.getUsername()+"_pfp_"+count, ".jpeg");
                //Toast.makeText(getContext(), "_pfp_"+count+" created", Toast.LENGTH_SHORT).show();
                storageReference.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                if(task.isSuccessful()){
                                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                    //retrivedImage.setImageBitmap(bitmap);
                                    bitmapArrayList.add(bitmap);
                                    if(count==pfps_uploaded-1){
                                        //recyclerAdapter = new PhotosRecyclerAdapter(getContext(), bitmapArrayList);
                                        startRecycler(bitmapArrayList);
                                        if (progressDialog.isShowing())
                                            progressDialog.dismiss();
                                    }
                                }
                                else{
                                    Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(pfps_uploaded==0){
            if (progressDialog.isShowing())
                progressDialog.dismiss();
            Toast.makeText(getContext(), "no pics uploaded", Toast.LENGTH_SHORT).show();
        }


        return v;
    }

    private void startRecycler(ArrayList<Bitmap>list){
        layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new PhotosRecyclerAdapter(getContext(), list, new ShopInterface() {
            @Override
            public void onItemClick(int position) {
                //ProfileFragment.pfp.setImageBitmap(list.get(position));
//                Bundle args = new Bundle();
//                ProfileFragment fragment = new ProfileFragment();
//
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                list.get(position).compress(Bitmap.CompressFormat.JPEG, 100, stream);
//                byte[] byteArray = stream.toByteArray();
//
//                args.putByteArray("image", byteArray);
//                fragment.setArguments(args);
                bitmappfp = list.get(position);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();

            }
        });
        recyclerView.setAdapter(recyclerAdapter);
    }

    public void cancelUpload(View view) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();
    }

    @Override
    public void onItemClick(int position) {

    }
}
package com.example.utimatetictactoe;

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

import java.io.File;
import java.io.IOException;


public class RetrivePfpFragment extends Fragment {

    private ImageView retrivedImage;
    private StorageReference storageReference;
    int pfps_uploaded;

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    PhotosRecyclerAdapter recyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_retrive_pfp, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pfps_uploaded = bundle.getInt("pfpsCount", 0);
        }

        recyclerView = v.findViewById(R.id.pfpsRecyclerView);
        layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerAdapter = new PhotosRecyclerAdapter(this, )
        //need to download all the pfps first into a bitmap list

        storageReference = FirebaseStorage.getInstance().getReference("image/");//.child(RegisterActivity.getUsername()+"_pfp_"+pfps_uploaded);

        try {
            File localFile = File.createTempFile(RegisterActivity.getUsername()+"_pfp_"+pfps_uploaded, ".jpeg");
            storageReference.getFile(localFile)
                    .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                            if(task.isSuccessful()){
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                retrivedImage.setImageBitmap(bitmap);
                            }
                            else{
                                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return v;
    }
}
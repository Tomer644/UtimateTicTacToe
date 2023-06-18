package com.example.utimatetictactoe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UploadImageFragment extends Fragment {

    Button selectImg, uploadImg;
    ImageView img, rtrnArrow;
    StorageReference storageReference;
    Uri imageUri;
    DBHelper sqldb;
    int pfps_uploaded;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_upload_image, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pfps_uploaded = bundle.getInt("pfps_uploaded", 1);
        }
        Toast.makeText(getActivity(), "pfp_" + pfps_uploaded, Toast.LENGTH_SHORT).show();

        selectImg = v.findViewById(R.id.selectImgBtn);
        uploadImg = v.findViewById(R.id.uploadImgBtn);
        img = v.findViewById(R.id.profileImgView);
        selectImg.setOnClickListener(this::uploadImage);
        uploadImg.setOnClickListener(this::uploadImage);

        rtrnArrow = v.findViewById(R.id.rtrnArrow);
        rtrnArrow.setOnClickListener(this::cancelUpload);

        sqldb = new DBHelper(this.getContext());

        return v;
    }

    public void uploadImage(View view) {
        if(view==selectImg){
            Intent intent = new Intent();
            intent.setType("image/");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 100);
        }
        if(view==uploadImg){
            ProgressDialog progressDialog = new ProgressDialog(this.getContext());
            progressDialog.setTitle("Uploading file...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            Toast.makeText(getActivity(), "_pfp_" + pfps_uploaded, Toast.LENGTH_SHORT).show();

            //String fileName = format.format(now);
            String fileName = RegisterActivity.getUsername()+"_pfp_" + pfps_uploaded + ".jpeg";
            storageReference = FirebaseStorage.getInstance().getReference("image/").child(fileName);
            if(imageUri!=null) {
                storageReference.putFile(imageUri)
                        .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if (progressDialog.isShowing())
                                    progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    img.setImageURI(null);
                                    Toast.makeText(getActivity(), "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                                    //boolean update =
                                    sqldb.userUploadedPfp(RegisterActivity.getUsername());
                                    //                                if(!update){
                                    //                                    Toast.makeText(getActivity(), "Failed to update db", Toast.LENGTH_SHORT).show();
                                    //                                }
                                } else {
                                    Toast.makeText(getActivity(), "Failed to Upload", Toast.LENGTH_SHORT).show();
                                }
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();
                            }
                        });
            }
            else{
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                Toast.makeText(getActivity(), "Error! choose an image first!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && data!=null && data.getData()!=null){
            imageUri = data.getData();
            img.setImageURI(imageUri);
        }
    }

    public void cancelUpload(View view) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();
    }
}
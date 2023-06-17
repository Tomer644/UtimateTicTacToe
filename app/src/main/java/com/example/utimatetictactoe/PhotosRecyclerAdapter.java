package com.example.utimatetictactoe;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PhotosRecyclerAdapter extends RecyclerView.Adapter<PhotosRecyclerAdapter.PhotosViewHolder> {

    Context context;
    ArrayList<Bitmap>photos;

    public PhotosRecyclerAdapter(Context context, ArrayList<Bitmap> photos) {
        this.context = context;
        this.photos = photos;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_photo_view, parent, false);
        PhotosViewHolder photosViewHolder = new PhotosViewHolder(view);
        return photosViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        holder.photo.setImageBitmap(photos.get(position));

    }

    @Override
    public int getItemCount() {
        return photos.size();
    }


    public class PhotosViewHolder extends RecyclerView.ViewHolder{

        ImageView photo;
        TextView type;
        Button btnEquip;

        public PhotosViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo);
            btnEquip = itemView.findViewById(R.id.btnEquip);
            type = itemView.findViewById(R.id.type);
        }
    }
}


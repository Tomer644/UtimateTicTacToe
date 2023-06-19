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

    private ShopInterface shopInterface;
    Context context;
    ArrayList<Bitmap>photos;

    public PhotosRecyclerAdapter(Context context, ArrayList<Bitmap> photos, ShopInterface shopInterface) {
        this.context = context;
        this.photos = photos;
        this.shopInterface = shopInterface;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_photo_view, parent, false);
        PhotosViewHolder photosViewHolder = new PhotosViewHolder(view, shopInterface);
        return photosViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        holder.photo.setImageBitmap(photos.get(position));
        //holder.photo.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }


    public static class PhotosViewHolder extends RecyclerView.ViewHolder{

        ImageView photo;
        //TextView type;
        Button btnEquip;

        public PhotosViewHolder(@NonNull View itemView, ShopInterface shopInterface) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo);
            btnEquip = itemView.findViewById(R.id.btnEquip);
            //type = itemView.findViewById(R.id.type);

            btnEquip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(shopInterface!=null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            shopInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}


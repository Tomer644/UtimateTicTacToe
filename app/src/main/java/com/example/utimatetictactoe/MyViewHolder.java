package com.example.utimatetictactoe;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView skinImg;
    TextView skinId, textPrice, intPrice, skin_type;
    Button btnBuy;
    CardView cardview;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        skinImg = itemView.findViewById(R.id.imageview);
        skinId = itemView.findViewById(R.id.skinId);
        textPrice = itemView.findViewById(R.id.textprice);
        intPrice = itemView.findViewById(R.id.price);
        btnBuy = itemView.findViewById(R.id.btnBuy);
        skin_type = itemView.findViewById(R.id.skin_type);
        cardview = itemView.findViewById(R.id.cardview);

        /*btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
    }
}

package com.example.utimatetictactoe;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {//interface set onclick

    private final ShopInterface shopInterface;
    Context context;
    List<Skin>skins;

    public MyAdapter(Context context, List<Skin> skins, ShopInterface shopInterface) {
        this.context = context;
        this.skins = skins;
        this.shopInterface = shopInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.skin_shop_view, parent, false), shopInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.skinId.setText(skins.get(position).getId());
        //holder img!
        if(!skins.get(position).isOwned()){
            holder.textPrice.setText("Price:");
            holder.intPrice.setText(skins.get(position).getPrice()+"");
            holder.btnBuy.setText("Buy");
            holder.btnBuy.setClickable(true);
        }
        else {
            holder.textPrice.setText("already owned");
            holder.intPrice.setText("");
            holder.btnBuy.setText("");
            holder.btnBuy.setBackgroundResource(R.color.white);
            holder.btnBuy.setClickable(false);
        }
        SkinType st = skins.get(position).getSkinType();
        switch (st){
            case common:
                holder.skin_type.setText("Common");
                holder.skin_type.setTextColor(Color.rgb(0,0,0));
                holder.cardview.setBackgroundResource(R.drawable.game_bg);
                break;
            case rare:
                holder.skin_type.setText("Rare");
                //holder.skin_type.setTextColor(Color.parseColor("#ffa21e"));
                holder.cardview.setBackgroundResource(R.drawable.rare_skin_bg);
                break;
            case epic:
                holder.skin_type.setText("Epic");
                //holder.skin_type.setTextColor(Color.parseColor("#a400d3"));
                holder.cardview.setBackgroundResource(R.drawable.epic_skin_bg);
                break;
            case legendary:
                holder.skin_type.setText("Legendary");
                //holder.skin_type.setTextColor(Color.parseColor("#00fff7"));
                holder.cardview.setBackgroundResource(R.drawable.legendary_skin_bg);
                break;
        }
        Bitmap bitmap = ShopFragment.photos.get(skins.get(position).getId());
        if(bitmap==null)
            Toast.makeText(context.getApplicationContext(), "null", Toast.LENGTH_SHORT);
        else
            holder.skinImg.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return skins.size();
    }
}

package com.example.utimatetictactoe;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.Contract;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private Board board;// = new Board("x.png", "o.png");
    private ItemClickListener mClickListener;

    public RecyclerViewAdapter(Board board) {
        this.board = board;
    }

    @NonNull
    @Override //on layout creation, inflate the layout and gives it to the ViewHolder
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basic_board, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //position 0 - 8
        int[]loc = getLocFromPos(position);
        if(loc==null){
            throw new RuntimeException("no button pressed");
        }
        int row = loc[0];
        int col = loc[1];
        BoardButton bb = board.board[row][col];
        if(bb.isPressed){
            if(bb.player=='x'){
                changeCellByPos(holder, position, board.xSkinPath);
            }
            else if(bb.player=='o'){
                changeCellByPos(holder, position, board.oSkinPath);
            }
        }

    }

    @Override
    public int getItemCount() {
        return board.board.length + board.board[0].length;
    }

    @NonNull
    @Contract(pure = true)
    private int[] getLocFromPos(int position){
        int[]loc = new int[2];
        switch (position){
            case 0:
                loc[0]=0;
                loc[1]=0;
                break;
            case 1:
                loc[0]=0;
                loc[1]=1;
                break;
            case 2:
                loc[0]=0;
                loc[1]=2;
                break;
            case 3:
                loc[0]=1;
                loc[1]=0;
                break;
            case 4:
                loc[0]=1;
                loc[1]=1;
                break;
            case 5:
                loc[0]=1;
                loc[1]=2;
                break;
            case 6:
                loc[0]=2;
                loc[1]=0;
                break;
            case 7:
                loc[0]=2;
                loc[1]=1;
                break;
            case 8:
                loc[0]=2;
                loc[1]=2;
                break;
        }
        return loc;
    }

    private void changeCellByPos(@NonNull ViewHolder holder, int position, String skinPath){
        switch (position){
            case 0: holder.imageView00.setBackground(Drawable.createFromPath(skinPath)); break;
            case 1: holder.imageView01.setBackground(Drawable.createFromPath(skinPath)); break;
            case 2: holder.imageView02.setBackground(Drawable.createFromPath(skinPath)); break;
            case 3: holder.imageView10.setBackground(Drawable.createFromPath(skinPath)); break;
            case 4: holder.imageView11.setBackground(Drawable.createFromPath(skinPath)); break;
            case 5: holder.imageView12.setBackground(Drawable.createFromPath(skinPath)); break;
            case 6: holder.imageView20.setBackground(Drawable.createFromPath(skinPath)); break;
            case 7: holder.imageView21.setBackground(Drawable.createFromPath(skinPath)); break;
            case 8: holder.imageView22.setBackground(Drawable.createFromPath(skinPath)); break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView00;//0
        public ImageView imageView01;
        public ImageView imageView02;

        public ImageView imageView10;
        public ImageView imageView11;
        public ImageView imageView12;

        public ImageView imageView20;
        public ImageView imageView21;
        public ImageView imageView22;//8

        ViewHolder(View itemView) {
            super(itemView);
            imageView00 = itemView.findViewById(R.id.imageView00);
            imageView00.setOnClickListener(this);
            imageView01 = itemView.findViewById(R.id.imageView01);
            imageView01.setOnClickListener(this);
            imageView02 = itemView.findViewById(R.id.imageView02);
            imageView02.setOnClickListener(this);

            imageView10 = itemView.findViewById(R.id.imageView10);
            imageView10.setOnClickListener(this);
            imageView11 = itemView.findViewById(R.id.imageView11);
            imageView11.setOnClickListener(this);
            imageView12 = itemView.findViewById(R.id.imageView12);
            imageView12.setOnClickListener(this);

            imageView20 = itemView.findViewById(R.id.imageView20);
            imageView20.setOnClickListener(this);
            imageView21 = itemView.findViewById(R.id.imageView21);
            imageView21.setOnClickListener(this);
            imageView22 = itemView.findViewById(R.id.imageView22);
            imageView22.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    // convenience method for getting data at click position
    BoardButton getItem(int position) {//int row, col instead of pos
        int[]loc = getLocFromPos(position);
        if(loc==null){
            throw new RuntimeException("no button pressed");
        }
        int i = loc[0];
        int j = loc[1];
        return this.board.board[i][j];
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position); //int row, col instead of pos
    }
}

package com.example.utimatetictactoe;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.Contract;

public class RecyclerViewAdapter {/*extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
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
            case 0: holder.imageButton00.setBackground(Drawable.createFromPath(skinPath)); break;
            case 1: holder.imageButton01.setBackground(Drawable.createFromPath(skinPath)); break;
            case 2: holder.imageButton02.setBackground(Drawable.createFromPath(skinPath)); break;
            case 3: holder.imageButton10.setBackground(Drawable.createFromPath(skinPath)); break;
            case 4: holder.imageButton11.setBackground(Drawable.createFromPath(skinPath)); break;
            case 5: holder.imageButton12.setBackground(Drawable.createFromPath(skinPath)); break;
            case 6: holder.imageButton20.setBackground(Drawable.createFromPath(skinPath)); break;
            case 7: holder.imageButton21.setBackground(Drawable.createFromPath(skinPath)); break;
            case 8: holder.imageButton22.setBackground(Drawable.createFromPath(skinPath)); break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageButton imageButton00;//0
        public ImageButton imageButton01;
        public ImageButton imageButton02;

        public ImageButton imageButton10;
        public ImageButton imageButton11;
        public ImageButton imageButton12;

        public ImageButton imageButton20;
        public ImageButton imageButton21;
        public ImageButton imageButton22;//8

        ViewHolder(View itemView) {
            super(itemView);
            imageButton00 = itemView.findViewById(R.id.imageButton00);
            imageButton00.setOnClickListener(this);
            imageButton01 = itemView.findViewById(R.id.imageButton01);
            imageButton01.setOnClickListener(this);
            imageButton02 = itemView.findViewById(R.id.imageButton02);
            imageButton02.setOnClickListener(this);

            imageButton10 = itemView.findViewById(R.id.imageButton10);
            imageButton10.setOnClickListener(this);
            imageButton11 = itemView.findViewById(R.id.imageButton11);
            imageButton11.setOnClickListener(this);
            imageButton12 = itemView.findViewById(R.id.imageButton12);
            imageButton12.setOnClickListener(this);

            imageButton20 = itemView.findViewById(R.id.imageButton20);
            imageButton20.setOnClickListener(this);
            imageButton21 = itemView.findViewById(R.id.imageButton21);
            imageButton21.setOnClickListener(this);
            imageButton22 = itemView.findViewById(R.id.imageButton22);
            imageButton22.setOnClickListener(this);
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
    */
}

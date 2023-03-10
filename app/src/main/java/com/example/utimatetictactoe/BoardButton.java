package com.example.utimatetictactoe;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class BoardButton{// implements View.OnClickListener{
    //protected Button button;
    protected ImageButton img_btn; @NonNull
    protected String id;
    protected char player;
    protected boolean isPressed;

    public BoardButton(/*Button button,*/String id) {
        //this.button = button;
        //this.img = new
        this.id = id;
        this.player = '-';
        this.isPressed = false;
    }

    //public Button getButton() {return button;}

    //public void setButton(Button button) {this.button = button;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public char getPlayer() {
        return player;
    }

    public void setPlayer(char player) {
        this.player = player;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        this.isPressed = pressed;
    }

    public void setImg(ImageButton imageButton) {
        this.img_btn = imageButton;
    }

    /*public void clickButton(@NonNull Board board){
        this.isPressed = true;
        this.img.setClickable(false);
        if(board.Xturn){
            this.player = 'x';
            this.img.setBackground(Drawable.createFromPath(board.xSkinPath));
        }
        else{
            this.player = 'o';
            this.img.setBackground(Drawable.createFromPath(board.oSkinPath));
        }
    }*/

    /*
    @Override
    public void onClick(View view) {
        this.isPressed = true;
        this.img_btn.setClickable(false);
//        if(board.Xturn){
//            this.player = 'x';
//            view.setBackground(Drawable.createFromPath(board.xSkinPath));
//        }
//        else{
//            this.player = 'o';
//            view.setBackground(Drawable.createFromPath(board.oSkinPath));
//        }
    }*/

}

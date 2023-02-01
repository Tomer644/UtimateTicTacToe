package com.example.utimatetictactoe;

import android.media.Image;
import android.widget.Button;

public class Board {
    protected Button[][]mat;
    protected Image X;
    protected Image O;


    public Board() {
        this.mat = new Button[3][3];
    }

    private void setButtonsOnClick(){
        for (int i = 0; i < this.mat.length; i++){
            for (int j = 0; j < this.mat[0].length; j++){

            }
        }
    }

    public void clickButton(int row, int col) {
        this.mat[row][col].setClickable(false);
    }
}

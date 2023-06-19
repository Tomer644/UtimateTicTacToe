package com.example.utimatetictactoe;

//import static androidx.core.graphics.drawable.IconCompat.getResources;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.Contract;

public class Board {
    protected BoardButton[][] board;
    /*protected int xSkinPath;
    protected int oSkinPath;*/

    protected Bitmap xLogo, oLogo;
    //protected boolean Xturn;
    protected int turnCount;

    //for the big game:
    protected int boardId;
    protected char boardWinner; //maybe useless
    protected boolean finished;


//    public Board(int xPath, int oPath) {
//        this.board = new BoardButton[3][3];
//
//        this.xSkinPath = xPath;
//        this.oSkinPath = oPath;
//        //this.Xturn = true;
//        this.turnCount = 0;
//        createButtons();
//
//        //this.boardWinner = '-';
//        this.finished = false;
//    }

    public Board(Bitmap xLogo, Bitmap oLogo) {
        this.board = new BoardButton[3][3];

        this.xLogo = xLogo;
        this.oLogo = oLogo;
        //this.Xturn = true;
        this.turnCount = 0;
        createButtons();

        //this.boardWinner = '-';
        this.finished = false;
    }

    private void createButtons(){
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < this.board[0].length; j++){
                String buttonId = "button_"+ i + j; //what do i do with this id?
                this.board[i][j] = new BoardButton(buttonId);

            }
        }
    }


    public void setAllPressed(){
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                if(!this.board[i][j].isPressed())
                    this.board[i][j].setPressed(true);
            }
        }
    }

    public void buttonClicked(ImageButton view, int row, int col, boolean xTurn){
        if(this.board[row][col].isPressed) {
            return;
        }
        this.board[row][col].setPressed(true);
        if(xTurn){
            this.board[row][col].player = 'X';
            view.setImageBitmap(this.xLogo);
            //view.setImageResource(this.xSkinPath);
        }
        else{
            this.board[row][col].player = 'O';
            view.setImageBitmap(this.oLogo);
            //view.setImageResource(this.oSkinPath);
        }
        this.turnCount++;

    }


    public char checkVictory() {
        int sumX, sumO;
        //check rows
        for (int i = 0; i < this.board.length; i++)
        {
            sumX=0;
            sumO=0;
            for (int j = 0; j < this.board.length; j++)
            {
                if(this.board[i][j].player=='X')
                    sumX++;
                else if(this.board[i][j].player=='O')
                    sumO++;
            }
            if(sumO == 3){
                this.finished = true;
                this.boardWinner = 'O';
                return 'O';
            }
            else if(sumX == 3) {
                this.finished = true;
                this.boardWinner = 'X';
                return 'X';
            }
        }

        //check columns
        for (int i = 0; i < this.board.length; i++)
        {
            sumX=0;
            sumO=0;
            for (int j = 0; j < this.board.length; j++)
            {
                if(this.board[j][i].player=='X')
                    sumX++;
                else if(this.board[j][i].player=='O')
                    sumO++;
            }
            //i turned the row to the col and the oposite
            if(sumO == 3) {
                this.finished = true;
                this.boardWinner = 'O';
                return 'O';
            }
            else if(sumX == 3) {
                this.finished = true;
                this.boardWinner = 'X';
                return 'X';
            }
        }

        //check first cross
        sumX=0;
        sumO=0;
        for (int i = 0; i < this.board.length; i++)
        {
            if(this.board[i][i].player=='X')
                sumX++;
            else if(this.board[i][i].player=='O')
                sumO++;
        }
        if(sumO == 3) {
            this.finished = true;
            this.boardWinner = 'O';
            return 'O';
        }
        else if(sumX == 3) {
            this.finished = true;
            this.boardWinner = 'X';
            return 'X';
        }

        //check second cross
        sumX=0;
        sumO=0;
        int j = this.board.length-1;
        for (int i = 0; i < this.board.length; i++)
        {
            if(this.board[i][j].player=='X')
                sumX++;
            else if(this.board[i][j].player=='O')
                sumO++;
            j--;
        }
        if(sumO == 3){
            this.finished = true;
            this.boardWinner = 'O';
            return 'O';
        }
        else if(sumX == 3){
            this.finished = true;
            this.boardWinner = 'X';
            return 'X';
        }

        this.finished = false;
        return '-';
    }


}

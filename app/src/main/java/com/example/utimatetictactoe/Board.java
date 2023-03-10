package com.example.utimatetictactoe;

//import static androidx.core.graphics.drawable.IconCompat.getResources;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.Contract;

public class Board {//implements View.OnClickListener {
    //protected ImageView[][] board;
    protected BoardButton[][] board;
    protected int xSkinPath;
    protected int oSkinPath;
    protected boolean Xturn;
    protected int turnCount;
    //setContentView(R.layout.activity_login);

    public Board(int xPath, int oPath) {
        this.board = new BoardButton[3][3];
        //this.board = new ImageView[3][3];
        /* with imageButton:
        this.x.setImageResource(int xSkinId);
        this.o.setImageResource(int oSkinId);*/
        this.xSkinPath = xPath;
        this.oSkinPath = oPath;
        this.Xturn = true;
        this.turnCount = 0;
        createButtons();
    }

    private void createButtons(){
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < this.board[0].length; j++){
                String buttonId = "button_"+ i + j; //what do i do with this id?
                this.board[i][j] = new BoardButton(buttonId);
                //this.board[i][j].set
                //this.board[i][j].setOnClickListener(this);
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

    public void buttonClicked(ImageButton view, int row, int col){
        this.board[row][col].setPressed(true);
        if(this.Xturn){
            this.board[row][col].player = 'X';
            //view.setBackground(Drawable.createFromPath(this.xSkinPath));
            view.setImageResource(this.xSkinPath);
            this.Xturn = false;
        }
        else{
            this.board[row][col].player = 'O';
            //view.setBackground(Drawable.createFromPath(this.oSkinPath));
            view.setImageResource(this.oSkinPath);
            this.Xturn = true;
        }
        this.turnCount++;

    }

    @Nullable
    @Contract(pure = true)
    private int[] findPressedButton(View v){
        int[]loc = new int[2];
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < this.board[0].length; j++){
                if(this.board[i][j].equals(v)){
                    loc[0]=i;
                    loc[1]=j;
                    return loc;
                }
            }
        }
        return null;
    }

    public void setTurn(boolean xturn)
    {
        this.Xturn = xturn;
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
            if(sumO == 3)
                return 'O';
            else if(sumX == 3)
                return 'X';
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
            if(sumO == 3)
                return 'O';
            else if(sumX == 3)
                return 'X';
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
        if(sumO == 3)
            return 'O';
        else if(sumX == 3)
            return 'X';

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
        if(sumO == 3)
            return 'O';
        else if(sumX == 3)
            return 'X';

        return '-';
    }


}

package com.example.utimatetictactoe;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.ImageButton;

public class UltBoard {
    protected Board[][]bigBoard;
    protected int nextBoard; //number 0 - 8 of the small board
    protected Context context;

    public UltBoard(int xPath, int oPath, Context context){
        this.bigBoard = new Board[3][3];
        this.context = context;
        createBoards(xPath, oPath);
    }

    private void createBoards(int xPath, int oPath){
        int count = 0;
        GridLayout[]gridLayouts = new GridLayout[9];
        for (int i = 0; i<this.bigBoard.length; i++){
            for (int j = 0; j<this.bigBoard[0].length; j++){
                this.bigBoard[i][j] = new Board(xPath, oPath);
                this.bigBoard[i][j].boardId = count;
                gridLayouts[count] = new GridLayout(this.context);
                gridLayouts[count].setColumnCount(3);
                gridLayouts[count].setRowCount(3);
                count++;
            }
        }


    }

    public void buttonClicked(ImageButton view, int row, int col, int tappedCell){
        //this.bigBoard[][].buttonClicked(view, row, col);
    }

    public boolean boardFull(){
        for (int i = 0; i < this.bigBoard.length; i++) {
            for (int j = 0; j < this.bigBoard[0].length; j++) {
                if(!this.bigBoard[i][j].finished)
                    return false;
            }
        }
        return true;
    }

    //uses the same algorithm for the normal board checkVictory() func, just changed the variable names and etc
    public char checkTotalVictory() {
        int sumX, sumO;
        //check rows
        for (int i = 0; i < this.bigBoard.length; i++)
        {
            sumX=0;
            sumO=0;
            for (int j = 0; j < this.bigBoard.length; j++)
            {
                if(this.bigBoard[i][j].boardWinner=='X')
                    sumX++;
                else if(this.bigBoard[i][j].boardWinner=='O')
                    sumO++;
            }
            if(sumO == 3){
                return 'O';
            }
            else if(sumX == 3) {
                return 'X';
            }
        }

        //check columns
        for (int i = 0; i < this.bigBoard.length; i++)
        {
            sumX=0;
            sumO=0;
            for (int j = 0; j < this.bigBoard.length; j++)
            {
                if(this.bigBoard[j][i].boardWinner=='X')
                    sumX++;
                else if(this.bigBoard[j][i].boardWinner=='O')
                    sumO++;
            }
            //i turned the row to the col and the oposite
            if(sumO == 3) {
                return 'O';
            }
            else if(sumX == 3) {
                return 'X';
            }
        }

        //check first cross
        sumX=0;
        sumO=0;
        for (int i = 0; i < this.bigBoard.length; i++)
        {
            if(this.bigBoard[i][i].boardWinner=='X')
                sumX++;
            else if(this.bigBoard[i][i].boardWinner=='O')
                sumO++;
        }
        if(sumO == 3) {
            return 'O';
        }
        else if(sumX == 3) {
            return 'X';
        }

        //check second cross
        sumX=0;
        sumO=0;
        int j = this.bigBoard.length-1;
        for (int i = 0; i < this.bigBoard.length; i++)
        {
            if(this.bigBoard[i][j].boardWinner=='X')
                sumX++;
            else if(this.bigBoard[i][j].boardWinner=='O')
                sumO++;
            j--;
        }
        if(sumO == 3){
            return 'O';
        }
        else if(sumX == 3){
            return 'X';
        }

        return '-';
    }
}

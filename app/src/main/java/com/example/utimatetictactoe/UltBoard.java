package com.example.utimatetictactoe;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;

public class UltBoard {
    protected Board[][]bigBoard;
    protected int nextBoard; //number 0 - 8 of the small board; -1 == all boards
    protected int boardsFullCount;

    public static final int ALL_BOARDS_ALLOWED = -1;


    public UltBoard(int xPath, int oPath){
        this.bigBoard = new Board[3][3];
        this.nextBoard = ALL_BOARDS_ALLOWED;
        this.boardsFullCount = 0;
        createBoards(xPath, oPath);
    }

    private void createBoards(int xPath, int oPath){
        int count = 0;
        for (int i = 0; i<this.bigBoard.length; i++){
            for (int j = 0; j<this.bigBoard[0].length; j++){
                this.bigBoard[i][j] = new Board(xPath, oPath);
                this.bigBoard[i][j].boardId = count;
                count++;
            }
        }


    }

    public void buttonClicked(ImageButton view, int tappedCell, int currentGrid){

        int[]current = numToLoc(currentGrid);//current board
        int[]cell = numToLoc(tappedCell);//cell tapped - also the next board
        this.bigBoard[current[0]][current[1]].buttonClicked(view, cell[0], cell[1]);

        if(this.bigBoard[cell[0]][cell[1]].finished){
            this.setNextBoard(UltBoard.ALL_BOARDS_ALLOWED);
            //if a small board already full -
            // you can choose (at what small board) your next turn will be
        }
        else{
            this.setNextBoard(tappedCell);
            //set all the small boards unclickable - except for the one he sent his opponent to
        }
    }


    public boolean isBoardAtNumFinished(int boardNum){
        //returns if the small board at number "num" is finished
        int row, col;
        int[]loc = numToLoc(boardNum);
        row = loc[0];
        col = loc[1];
        return this.bigBoard[row][col].finished;
    }

    public int getNextBoard() {
        return nextBoard;
    }

    public void setNextBoard(int nextBoard) {
        this.nextBoard = nextBoard;
    }

    public int getBoardsFullCount() {
        return boardsFullCount;
    }

    public void addToBoardsFullCount() {
        this.boardsFullCount++;
    } //!



    @NonNull
    @Contract(pure = true)
    private int[] numToLoc(int num){
        int row = 0, col = 0;
        switch (num){
            case 0:
                row=0;
                col=0;
                break;
            case 1:
                row=0;
                col=1;
                break;
            case 2:
                row=0;
                col=2;
                break;
            case 3:
                row=1;
                col=0;
                break;
            case 4:
                row=1;
                col=1;
                break;
            case 5:
                row=1;
                col=2;
                break;
            case 6:
                row=2;
                col=0;
                break;
            case 7:
                row=2;
                col=1;
                break;
            case 8:
                row=2;
                col=2;
                break;
        }
        int[]loc = new int[]{row, col};
        return loc;
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

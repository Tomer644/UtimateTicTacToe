package com.example.utimatetictactoe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.Contract;

public class UltraGameActivity extends AppCompatActivity{

//    Button lose, win;
    GridLayout gridLayout;
    Intent get, back;
    UltBoard ultBoard;
    GridLayout grid0, grid1, grid2, grid3, grid4, grid5, grid6, grid7, grid8;
    TextView tvWinner, tvTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultra_game);/*
        win = findViewById(R.id.btnWult);
        lose = findViewById(R.id.btnLult);
        win.setOnClickListener(this);
        lose.setOnClickListener(this);*/

        get = getIntent();
        gridLayout = findViewById(R.id.grid_layout);

        ultBoard = new UltBoard(R.drawable.x, R.drawable.o);
        /*Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        //point.x = screen width, point.y = screen height
        int gridHeight = (int) (point.y * 0.75);*/

        grid0 = findViewById(R.id.grid0);
        grid1 = findViewById(R.id.grid1);
        grid2 = findViewById(R.id.grid2);
        grid3 = findViewById(R.id.grid3);
        grid4 = findViewById(R.id.grid4);
        grid5 = findViewById(R.id.grid5);
        grid6 = findViewById(R.id.grid6);
        grid7 = findViewById(R.id.grid7);
        grid8 = findViewById(R.id.grid8);
        tvWinner = findViewById(R.id.tvWinner);
        tvTurn = findViewById(R.id.tvTurn);

    }


    //@Override
//    public void onClick(View view) {
//        back = new Intent();
//        back.putExtra("ult", true);
//        if(view.equals(win)) {
//            back.putExtra("winner", 'X');
//        }
//        else {
//            back.putExtra("winner", 'O');
//        }
//        setResult(RESULT_OK, back);
//        finish();
//    }


    public void playerTap(View view) {
        ImageButton img = (ImageButton) view;
        img.setClickable(false);
        //11 = grid, 12 = cell
        int id = img.getId();
        String strId = img.getResources().getResourceEntryName(id);
        Toast.makeText(this, "id:"+strId, Toast.LENGTH_SHORT).show();

        int currentGrid = strId.charAt(11) - '0';
        if(ultBoard.nextBoard!=currentGrid && ultBoard.nextBoard!=ultBoard.ALL_BOARDS_ALLOWED){ //cant be full cuz we checked that last turn
            img.setClickable(true);
            Toast.makeText(this, "you cant use that board!", Toast.LENGTH_SHORT).show();
            return;
        }

        int[]current = numToLoc(currentGrid);
        if(ultBoard.nextBoard != ultBoard.ALL_BOARDS_ALLOWED) {
            GridLayout thisGrid = numToGrid(currentGrid);
            thisGrid.setBackgroundResource(R.drawable.game_bg);
        }
        else{
            removeHighlights();
        }

        int cell = strId.charAt(12)- '0';
        ultBoard.buttonClicked(img, cell, currentGrid);
        if (ultBoard.xTurn) {tvTurn.setText("X turn");}
        else {tvTurn.setText("O turn");}
        //activates the small board button clocked method,
        //and handles the nextTurn param to set the next board you play at
        boolean smallWon = checkSmallWin(current[0], current[1], currentGrid);
        if(smallWon && ultBoard.nextBoard==currentGrid){
            ultBoard.setNextBoard(ultBoard.ALL_BOARDS_ALLOWED);
        }
        //remove the last selector

        //add a selector!
        if(ultBoard.nextBoard!=ultBoard.ALL_BOARDS_ALLOWED) {
            GridLayout nextGrid = numToGrid(cell);
            nextGrid.setBackgroundResource(R.drawable.highlighted_grid_bg);
        }
        else{
            highlightAllBoards();
        }
        //till here - handeld the id and the wanted board that is the current and next turn

        if(ultBoard.getBoardsFullCount()>=3){
            checkUltimateWinner();
        }

    }

    private void checkUltimateWinner(){
        char chWin = ultBoard.checkTotalVictory();
        if(chWin!='-'){
            tvWinner.setText("The winner is "+chWin+" !");

            tvWinner.animate().translationY(-1400).setDuration(2700).setStartDelay(0);
            tvWinner.animate().translationY(2000).setDuration(2000).setStartDelay(2900);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    goBack(chWin);
                }
            }, 5000);
        }
        if(ultBoard.getBoardsFullCount()==9){
            tvWinner.setText("Draw! no winner!");

            tvWinner.animate().translationY(-1400).setDuration(2700).setStartDelay(0);
            tvWinner.animate().translationY(2000).setDuration(2000).setStartDelay(2900);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    goBack(chWin);
                }
            }, 5000);
        }
    }


    private boolean checkSmallWin(int bRow, int bCol, int gridNum) {
        if(ultBoard.bigBoard[bRow][bCol].turnCount<3){
            return false;
        }
        char chWin = ultBoard.bigBoard[bRow][bCol].checkVictory();
        GridLayout wGrid = numToGrid(gridNum);
        if (chWin != '-') {
            ImageView bg = new ImageView(this);
            ultBoard.bigBoard[bRow][bCol].setAllPressed();
            if (chWin == 'X') {
                bg.setImageResource(ultBoard.bigBoard[bRow][bCol].xSkinPath);
                //wGrid.setBackgroundResource(ultBoard.bigBoard[bRow][bCol].xSkinPath);
            }
            else {
                bg.setImageResource(ultBoard.bigBoard[bRow][bCol].oSkinPath);
                //wGrid.setBackgroundResource(ultBoard.bigBoard[bRow][bCol].oSkinPath);
            }
            wGrid.removeAllViews();
            wGrid.addView(bg);
            ultBoard.addToBoardsFullCount();
            return true;
        }
        else if(ultBoard.bigBoard[bRow][bCol].finished){
            wGrid.setBackgroundResource(R.color.black);
            ultBoard.bigBoard[bRow][bCol].boardWinner = '-';
            return true;
        }
        return false;
        //else if finished (draw) background = '-' ?

    }

    private void highlightAllBoards(){
        for (int i = 0; i < 9; i++) {
            if(!ultBoard.isBoardAtNumFinished(i)){
                GridLayout grid = numToGrid(i);
                grid.setBackgroundResource(R.drawable.highlighted_grid_bg);
            }
        }
    }

    private void removeHighlights(){
        for (int i = 0; i < 9; i++) {
            if(!ultBoard.isBoardAtNumFinished(i)){
                GridLayout grid = numToGrid(i);
                grid.setBackgroundResource(R.drawable.game_bg);
            }
        }
    }


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

    @Nullable
    @Contract(pure = true)
    private GridLayout numToGrid(int num){
        switch (num){
            case 0:
                return grid0;
            case 1:
                return grid1;
            case 2:
                return grid2;
            case 3:
                return grid3;
            case 4:
                return grid4;
            case 5:
                return grid5;
            case 6:
                return grid6;
            case 7:
                return grid7;
            case 8:
                return grid8;
            default:
                return null;
        }
    }

    public void goBack(char chWin) {
        back = new Intent();
        back.putExtra("ult", true);
        back.putExtra("winner", chWin);
        setResult(RESULT_OK, back);
        finish();
    }

    public void retrnToHome(View view) {
        back = new Intent();
        back.putExtra("ult", true);
        back.putExtra("winner", '-');
        setResult(RESULT_OK, back);
        finish();
    }
}


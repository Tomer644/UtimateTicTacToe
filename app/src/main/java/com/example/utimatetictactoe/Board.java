package com.example.utimatetictactoe;

//import static androidx.core.graphics.drawable.IconCompat.getResources;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;

public class Board implements View.OnClickListener {
    //protected ImageView[][] board;
    protected BoardButton[][] board;
    protected String xSkinPath;
    protected String oSkinPath;
    protected boolean Xturn;
    //setContentView(R.layout.activity_login);

    public Board(String xPath, String oPath) {
        this.board = new BoardButton[3][3];
        //this.board = new ImageView[3][3];
        /* with imageButton:
        this.x.setImageResource(int xSkinId);
        this.o.setImageResource(int oSkinId);*/
        this.xSkinPath = xPath;
        this.oSkinPath = oPath;
        this.Xturn = true;
        createButtons();
    }

    private void createButtons(){
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < this.board[0].length; j++){
                String buttonId = "button_"+ i + j; //what do i do with this id?
                this.board[i][j] = new BoardButton(buttonId);
                //this.board[i][j].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {
        int loc[] = findPressedButton(view);
        if(loc==null){
            throw new RuntimeException("no button pressed");
        }
        //view.getId(); //10 row, 11 col
        //BoardButton cell = new BoardButton()
        //int row = .id; //8
        //int col = //8
        int row = loc[0];
        int col = loc[1];
        this.board[row][col].onClick(view);
        //put the image
        if(this.Xturn){
            this.board[row][col].player = 'x';
            view.setBackground(Drawable.createFromPath(this.xSkinPath));
            this.Xturn = false;
        }
        else{
            this.board[row][col].player = 'o';
            view.setBackground(Drawable.createFromPath(this.oSkinPath));
            this.Xturn = true;
        }
    }

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

    /*
    public boolean checkVictory() {
        int sumX, sumO;
        //check row
        for (int i = 0; i < this.board.length; i++)
        {
            sumX=0;
            sumO=0;
            for (int j = 0; j < this.board.length; j++)
            {
                if(this.board[i][j]=='X')
                    sumX++;
                else if(this.board[i][j]=='O')
                    sumO++;
            }
            if(sumO == 3)
                return 'O';
            else if(sumX == 3)
                return 'X';
        }

        //check col
        for (int i = 0; i < this.board.length; i++)
        {
            sumX=0;
            sumO=0;
            for (int j = 0; j < this.board.length; j++)
            {
                if(this.board[j][i]=='X')
                    sumX++;
                else if(this.board[j][i]=='O')
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
            if(this.board[i][i]=='X')
                sumX++;
            else if(this.board[i][i]=='O')
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
            if(this.board[i][j]=='X')
                sumX++;
            else if(this.board[i][j]=='O')
                sumO++;
            j--;
        }
        if(sumO == 3)
            return 'O';
        else if(sumX == 3)
            return 'X';

        return '-';
    }*/

}

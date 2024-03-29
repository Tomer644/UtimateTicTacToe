package com.example.utimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class normalGameActivity extends AppCompatActivity{

    //Button lose, win;
    protected Intent get, back;
    protected Board board;
    protected ImageView winner;
    protected TextView tvWinner;
    //int turnCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_game);
//        win = findViewById(R.id.btnWnormal);
//        lose = findViewById(R.id.btnLnormal);
//        win.setOnClickListener(this);
//        lose.setOnClickListener(this);

        get = getIntent();

        Bitmap xicon, oicon;
        if(xSkinsFragment.isXinit){
            xicon = xSkinsFragment.xBtm;
        }
        else{
            xicon = BitmapFactory.decodeResource(getResources(), R.drawable.x);//xicon = R.drawable.x;
        }

        if(oSkinsFragment.isOinit){
            oicon = oSkinsFragment.oBtm;
        }
        else {
            oicon = BitmapFactory.decodeResource(getResources(), R.drawable.o);
        }
        board = new Board(xicon, oicon);

        winner = findViewById(R.id.winnerImg);
        tvWinner = findViewById(R.id.tvWinner);
    }


    public void playerTap(View view) {
        ImageButton img = (ImageButton) view;
        img.setClickable(false);
        int tappedImage = Integer.parseInt(img.getTag().toString());

        int row = 0, col = 0;
        switch (tappedImage){
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
        board.buttonClicked(img, row, col, board.turnCount%2==0);
        if(board.turnCount>4){
            checkWinner();
        }
    }

    public void checkWinner(){
        char chWin = board.checkVictory();
        if(chWin!='-'){
            board.setAllPressed();
            if(chWin=='X')
                winner.setImageBitmap(board.xLogo);
                //winner.setImageResource(board.xSkinPath);
            else
                winner.setImageBitmap(board.oLogo);
                //winner.setImageResource(board.oSkinPath);
            //animation - like the splash screen
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
        if(board.turnCount==9){
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


    public void goBack(char chWin) {
        back = new Intent();
        back.putExtra("ult", false);
        back.putExtra("winner", chWin);
        setResult(RESULT_OK, back);
        finish();
    }

    public void retrnToHome(View view) {
        back = new Intent();
        back.putExtra("ult", false);
        back.putExtra("winner", '-');
        setResult(RESULT_OK, back);
        finish();
    }
}
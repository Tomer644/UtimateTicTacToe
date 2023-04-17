package com.example.utimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

public class UltraGameActivity extends AppCompatActivity{//} implements View.OnClickListener, IgameActivity {

    Button lose, win;
    GridLayout gridLayout;
    Intent get, back;

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

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        //point.x = screen width, point.y = screen height
        int gridHeight = (int) (point.y * 0.75);


    }


    //@Override
    public void onClick(View view) {
        back = new Intent();
        back.putExtra("ult", true);
        if(view.equals(win)) {
            back.putExtra("winner", 'X');
        }
        else {
            back.putExtra("winner", 'O');
        }
        setResult(RESULT_OK, back);
        finish();
    }


    public void playerTap(View view) {
    }
}


package com.example.utimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UltraGameActivity extends AppCompatActivity implements View.OnClickListener {

    Button lose, win;
    Intent get, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultra_game);
        win = findViewById(R.id.btnWult);
        lose = findViewById(R.id.btnLult);
        win.setOnClickListener(this);
        lose.setOnClickListener(this);

        get = getIntent();
    }


    @Override
    public void onClick(View view) {
        back = new Intent();
        back.putExtra("ult", true);
        if(view.equals(win)) {
            back.putExtra("win", true);
        }
        else {
            back.putExtra("win", false);
        }
        setResult(RESULT_OK, back);
        finish();
    }
}


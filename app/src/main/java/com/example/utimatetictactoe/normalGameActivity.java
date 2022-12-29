package com.example.utimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class normalGameActivity extends AppCompatActivity implements View.OnClickListener {

    Button lose, win;
    Intent get, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_game);
        win = findViewById(R.id.btnWnormal);
        lose = findViewById(R.id.btnLnormal);
        win.setOnClickListener(this);
        lose.setOnClickListener(this);

        get = getIntent();
    }

    @Override
    public void onClick(View view) {
        back = new Intent();
        back.putExtra("ult", false);
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
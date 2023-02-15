package com.example.utimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class normalGameActivity extends AppCompatActivity implements View.OnClickListener {

    Button lose, win;
    Intent get, back;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_game);
//        win = findViewById(R.id.btnWnormal);
//        lose = findViewById(R.id.btnLnormal);
//        win.setOnClickListener(this);
//        lose.setOnClickListener(this);

        get = getIntent();

        Board board = new Board("x.png", "o.png");

        RecyclerView recyclerView = findViewById(R.id.rvBoard);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new RecyclerViewAdapter(board);
        //adapter.setClickListener((RecyclerViewAdapter.ItemClickListener) this);
        recyclerView.setAdapter(adapter);
    }

    public void onItemClick(View view, int position) {
        Toast.makeText(normalGameActivity.this, position,Toast.LENGTH_SHORT).show();
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
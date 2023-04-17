package com.example.utimatetictactoe;

import android.view.View;

public interface IgameActivity {
    void playerTap(View view);
    //on click that handles the cell click event

    void checkWinner();
    //uses the class 'checkVictory()' func to check if won the game, and handling it if so
}

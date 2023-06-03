package com.example.utimatetictactoe;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Random;


public class PlayFragment extends Fragment implements View.OnClickListener {

    Button btnNormal, btnUltimate;
    Switch playerSwitch;

    //get result, currently temp cuz we will prevent all of that
    // by putting the game over method in the class and activate it at the end of the game
    ActivityResultLauncher<Intent>activityResultLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==RESULT_OK){
                        Intent intent = result.getData();
                        if (intent!=null)
                        {
                            char res = intent.getCharExtra("winner", '-');
                            boolean isUlt = intent.getBooleanExtra("ult", false);
                            gameOver(res, isUlt);
                        }
                        else
                            if(result.getResultCode()==RESULT_CANCELED)
                                Toast.makeText(getContext(),"no result", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play, container, false);

        btnNormal = view.findViewById(R.id.btnNormal);
        btnUltimate = view.findViewById(R.id.btnUltimate);
        btnNormal.setOnClickListener(this);
        btnUltimate.setOnClickListener(this);

        playerSwitch = view.findViewById(R.id.switchPlayer);


        playerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    playerSwitch.setText("X");
                }
                else{
                    playerSwitch.setText("O");
                }
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent play;
        if (view.equals(btnNormal)) {
            play = new Intent(getContext(), normalGameActivity.class/*3x3*/);
        }
        else {
            play = new Intent(getContext(), UltraGameActivity.class/*9x9*/);
        }
        //startActivity(play);
        //no need for returning cus i am not returning any variable
        //so when i finish just activate the win/lose method and (new) intent to home page

        //temporary:
        activityResultLauncher.launch(play);
    }

    public void gameOver(char winner, boolean isUltimate){//normal 15-25, ultra 25-35
        boolean pWon = false;
        //importing Random;
        Random rnd = new Random();
        int trophies; //(y-x+1)+x
        if(winner=='X' && playerSwitch.isChecked() || winner=='O' && !playerSwitch.isChecked()) {
            if (isUltimate) {//won in ult
                trophies = rnd.nextInt(35-25+1)+25; //25-35
            }
            else{//won normal mode
                trophies = rnd.nextInt(20-10+1)+15; //10-20
            }
            pWon = true;
        }
        else if(winner!='-'){
            if (isUltimate) //lost ult
                trophies = rnd.nextInt(-10-(-20)+1)-20; //-10 to -20
            else//lost normal
                trophies = rnd.nextInt(-5-(-10)+1)-10; //-5 to -10
        }
        else
            trophies = 0;
        //if tie then 0

        DBHelper db = new DBHelper(getActivity());
        Toast.makeText(getContext(),"username: "+RegisterActivity.getUsername(), Toast.LENGTH_SHORT).show();

        int result = db.updateData(RegisterActivity.getUsername(),trophies, pWon);
        if(result != -1)
            Toast.makeText(getContext(),"Update Successfully, you got "+trophies, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getContext(),"Update failed", Toast.LENGTH_SHORT).show();
        Intent gotoHome = new Intent(getContext(), MainActivity.class);
        startActivity(gotoHome);
    }
}
package com.example.utimatetictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    TextView appname;
    LottieAnimationView lottie;
    public static Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        serviceIntent = new Intent(getApplicationContext(), MyStartedService.class);
        startService(serviceIntent);

        appname = findViewById(R.id.appname);
        lottie = findViewById(R.id.lottie);

        appname.animate().translationY(-1400).setDuration(2700).setStartDelay(0);
        appname.animate().translationY(2000).setDuration(2000).setStartDelay(2900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        }, 5000);
    }
}
package com.example.utimatetictactoe;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyStartedService extends Service {
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "service created", Toast.LENGTH_SHORT).show();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.upup);
        mediaPlayer.setLooping(true);
    }

    public MyStartedService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service started", Toast.LENGTH_SHORT).show();
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "service stopped", Toast.LENGTH_SHORT).show();
        mediaPlayer.stop();
    }

}
package com.example.utimatetictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    static BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    SkinsFragment skinsFragment = new SkinsFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    //Intent serviceIntent;
    boolean isMediaOn;
    ImageButton musicBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return goToPage(item);
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.home);

        //serviceIntent = new Intent(getApplicationContext(), MyStartedService.class);

        isMediaOn = true;
        musicBtn = findViewById(R.id.btnMusic);
        musicBtn.setOnClickListener(this::mediaClick);
        //startService(serviceIntent);
        musicBtn.setBackgroundResource(R.drawable.ic_baseline_volume_up_24);
        //change photo
    }

    public boolean goToPage(@NonNull MenuItem item)
    {
        switch (item.getItemId()){
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                return true;
            case R.id.shop:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, skinsFragment).commit();
                return true;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                return true;
        }
        return false;
    }

    public void mediaClick(View v){
        if(isMediaOn){
            stopService(SplashScreen.serviceIntent);
            isMediaOn = false;
            musicBtn.setBackgroundResource(R.drawable.ic_baseline_volume_off_24);
        }
        else{
            startService(new Intent(getApplicationContext(), MyStartedService.class));
            isMediaOn = true;
            musicBtn.setBackgroundResource(R.drawable.ic_baseline_volume_up_24);
        }
    }


}
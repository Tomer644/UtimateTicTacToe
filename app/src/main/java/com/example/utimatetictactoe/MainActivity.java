package com.example.utimatetictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Intent signin;
    //static String username;
    static BottomNavigationView bottomNavigationView;
    static TextView tvTrophies;

    HomeFragment homeFragment = new HomeFragment();
    SkinsFragment skinsFragment = new SkinsFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //signin = getIntent();
        //username = signin.getStringExtra("username");

        bottomNavigationView = findViewById(R.id.nav_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return goToPage(item);
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.home);

        //tvTrophies = findViewById(R.id.trophiesCount);
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

    //public static String getUsername(){return username;}

    public static void setTrophies(int trophies){

    }

}
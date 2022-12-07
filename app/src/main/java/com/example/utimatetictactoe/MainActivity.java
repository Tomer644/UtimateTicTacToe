package com.example.utimatetictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    VpAdapter vpAdapter;
    static BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    SkinsFragment skinsFragment = new SkinsFragment();
    FriendsFragment friendsFragment = new FriendsFragment();

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
            case R.id.friends:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, friendsFragment).commit();
                return true;
        }
        return false;
    }


}
package com.example.utimatetictactoe;

import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;


public class SkinsFragment extends Fragment {

    static TabLayout tabLayout;
    static ViewPager2 viewPager2;
    VpAdapter vpAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skins, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager2 = view.findViewById(R.id.pager);
        vpAdapter = new VpAdapter(this);
        viewPager2.setAdapter(vpAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

        tabLayout.selectTab(tabLayout.getTabAt(1));

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int pos = bundle.getInt("pos", -1);
            if (pos!=-1)
                selectSkinPage(pos);
        }

        return view;
    }

    public void selectSkinPage(int pos)
    {
        viewPager2.setCurrentItem(pos);
        tabLayout.getTabAt(pos).select();
    }

}
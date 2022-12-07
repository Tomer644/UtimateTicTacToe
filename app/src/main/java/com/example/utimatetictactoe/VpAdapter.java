package com.example.utimatetictactoe;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class VpAdapter extends FragmentStateAdapter {

    public VpAdapter(@NonNull Fragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new xSkinsFragment();
            case 1:
                return new ShopFragment();
            case 2:
                return new oSkinsFragment();
        }
        return new ShopFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
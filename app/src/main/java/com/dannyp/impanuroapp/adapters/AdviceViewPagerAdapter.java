package com.dannyp.impanuroapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.dannyp.impanuroapp.fragment.MarriedAdiviceFragment;
import com.dannyp.impanuroapp.fragment.SingleAdviceFragment;

public class AdviceViewPagerAdapter extends FragmentStateAdapter {

    public AdviceViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new MarriedAdiviceFragment();
            default:
                return new SingleAdviceFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

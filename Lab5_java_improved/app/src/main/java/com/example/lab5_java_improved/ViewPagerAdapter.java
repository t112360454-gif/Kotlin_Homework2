package com.example.lab5_java_improved;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Arrays;
import java.util.List;

// ViewPagerAdapter：管理 ViewPager2 每一頁要出現的 Fragment
public class ViewPagerAdapter extends FragmentStateAdapter {

    private final List<Fragment> fragments = Arrays.asList(
            new FirstFragment(),
            new SecondFragment(),
            new ThirdFragment()
    );

    public ViewPagerAdapter(@NonNull FragmentManager fm, @NonNull Lifecycle lifecycle) {
        super(fm, lifecycle);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }
}


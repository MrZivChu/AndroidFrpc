package com.ironxiao.frpc;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class ViewPageAdapter extends FragmentStateAdapter {

    List<Fragment> fragmentList;

    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragmentLis) {
        super(fragmentActivity);
        this.fragmentList = fragmentLis;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}

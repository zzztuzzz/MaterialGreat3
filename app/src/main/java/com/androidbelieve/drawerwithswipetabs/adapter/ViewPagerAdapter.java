package com.androidbelieve.drawerwithswipetabs.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.androidbelieve.drawerwithswipetabs.domain.sharedusecase.HomeTab;
import com.androidbelieve.drawerwithswipetabs.fragment.ListViewFragmentOne;

import java.util.List;

/**
 * Created by teiyuueki on 2016/04/23.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<HomeTab> tabs;

    public ViewPagerAdapter(FragmentManager manager, List<HomeTab> tabs) {
        super(manager);
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        HomeTab tab = tabs.get(position);
        return ListViewFragmentOne.newInstance();
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }
}

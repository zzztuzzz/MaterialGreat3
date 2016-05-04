package com.androidbelieve.drawerwithswipetabs.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.androidbelieve.drawerwithswipetabs.domain.sharedusecase.HomeTab;
import com.androidbelieve.drawerwithswipetabs.fragment.ListViewFragmentOne;

import java.util.List;

/**
 * Created by teiyuueki on 2016/04/23.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<HomeTab> tabs;

    public static final int MAX_PAGE_NUM = 1000;
    private static final int OBJECT_NUM = 4;


    //MainActivityから呼び出し。
    public ViewPagerAdapter(FragmentManager manager, List<HomeTab> tabs) {
        super(manager);
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
//        HomeTab tab = tabs.get(position);

        Fragment fragment = null;
//        String aaa = tabs.get(position).getTitle();
//        System.out.print("でるかな"+aaa);

//        int diff = (position - (MAX_PAGE_NUM / 2)) % OBJECT_NUM;
//        int index = (0 < diff) ? (OBJECT_NUM + diff) : diff;
        int index = position;
        switch(index) {
            case 0:
                Log.d("case0","case0");
                fragment = new ListViewFragmentOne();
                break;
            case 1:
                fragment = new ListViewFragmentOne();
                break;
            case 2:
                fragment = new ListViewFragmentOne();
                break;
            case 3:
                fragment = new ListViewFragmentOne();
                break;
            default:
                fragment = new ListViewFragmentOne();
        }
        return  fragment;
    }

    @Override
    public int getCount() {
        return tabs.size();
//        return MAX_PAGE_NUM;
    }

//    @Override
//    public CharSequence getPageTitle(int position){
//        return "ページ"+(position);
//    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }
}

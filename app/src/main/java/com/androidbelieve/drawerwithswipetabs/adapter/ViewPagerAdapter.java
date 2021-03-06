package com.androidbelieve.drawerwithswipetabs.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.androidbelieve.drawerwithswipetabs.domain.sharedusecase.HomeTab;
import com.androidbelieve.drawerwithswipetabs.fragment.ListViewFragment;
import com.androidbelieve.drawerwithswipetabs.fragment.ListViewFragmentOne;
import com.androidbelieve.drawerwithswipetabs.fragment.MessagesFragemnt;

import java.util.List;

/**
 * Created by teiyuueki on 2016/04/23.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<HomeTab> tabs;

//    public static final int MAX_PAGE_NUM = 1000;
//    private static final int OBJECT_NUM = 4;


    //MainActivityから呼び出し。
    public ViewPagerAdapter(FragmentManager manager, List<HomeTab> tabs) {
        super(manager);
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
//        HomeTab tab = tabs.get(position);

        Fragment fragment = null;

        int index = position;
        System.out.println("indexの表示"+index);
        switch(index) {
            case 0:
                System.out.println("index:case0:"+index);
                fragment = new ListViewFragmentOne();
                break;
            case 1:
                System.out.println("index:case1"+index);
                fragment = ListViewFragment.newInstance();
                break;
            case 2:
                System.out.println("index:case2"+index);
                fragment = new MessagesFragemnt();
                break;
            case 3:
                System.out.println("index:case3"+index);
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

    @Override
    public CharSequence getPageTitle(int position){
        return "ページ"+(position);
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return tabs.get(position).getTitle();
//    }
}

package com.androidbelieve.drawerwithswipetabs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.adapter.ViewPagerAdapter;
import com.androidbelieve.drawerwithswipetabs.domain.sharedusecase.HomeTab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ratan on 7/27/2015.
 */
public class SearchListViewFragment extends Fragment{


    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private View x;



    //mainviewっぽく振る舞わせる。tablayout4っぽっく。

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        x =  inflater.inflate(R.layout.tab_layout,null);

        getAllwigets();
        setAllwigets();
        setViewPageAdapter();

        return x;

    }
    public void getAllwigets() {
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewPager);
    }

    private void setAllwigets() {
        setupViewPager();
    }

    private void setupViewPager() {
        List<HomeTab> tabs = new ArrayList<>();
        tabs.add(new HomeTab("top"));
        tabs.add(new HomeTab("match"));
        tabs.add(new HomeTab("message"));
        tabs.add(new HomeTab("setting"));
        tabs.add(new HomeTab("setting"));

        adapter = new ViewPagerAdapter(getFragmentManager() , tabs);
        //この見えない部分の再利用まわり。
        viewPager.setOffscreenPageLimit(1);
//        fragmentTwo = new FragmentTwo();
        setViewPageAdapter();

    }

    private void setViewPageAdapter() {

        /**
         *Set an Apater for the View Pager
         */

        //1.setadapter
        viewPager.setAdapter(adapter);

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        //2.setupwidthviewpager

        tabLayout.setupWithViewPager(viewPager);
    }



}

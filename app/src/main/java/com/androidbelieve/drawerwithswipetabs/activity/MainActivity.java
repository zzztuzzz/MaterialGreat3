package com.androidbelieve.drawerwithswipetabs.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.adapter.ViewPagerAdapter;
import com.androidbelieve.drawerwithswipetabs.databinding.MainBinding;
import com.androidbelieve.drawerwithswipetabs.domain.sharedusecase.HomeTab;
import com.androidbelieve.drawerwithswipetabs.domain.sharedusecase.SendBirdHelper;
import com.androidbelieve.drawerwithswipetabs.fragment.MessagesFragemnt;
import com.sendbird.android.SendBird;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static MainActivity instance;
    private MainBinding binding;

    //sendbird

    String USER_ID = SendBirdHelper.generateDeviceUUID(MainActivity.this); /* Generate Device UUID */
    String USER_NICKNAME = SendBirdHelper.setUserName();
    String APP_ID = "EC610DEE-E219-4AC6-B727-96CBE60E4559";

    private static final int REQUEST_SENDBIRD_CHAT_ACTIVITY = 100;
    private static final int REQUEST_SENDBIRD_CHANNEL_LIST_ACTIVITY = 101;
    private static final int REQUEST_SENDBIRD_MESSAGING_ACTIVITY = 200;
    private static final int REQUEST_SENDBIRD_MESSAGING_CHANNEL_LIST_ACTIVITY = 201;
    private static final int REQUEST_SENDBIRD_USER_LIST_ACTIVITY = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);

        //set sendbird init
        SendBird.init(APP_ID);
        SendBird.login(USER_ID, USER_NICKNAME);

        instance = this;
        setAllwigets();

    }

    private void setAllwigets() {
        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */


        List<HomeTab> tabs = new ArrayList<>();
        tabs.add(new HomeTab("top"));
        tabs.add(new HomeTab("match"));
        tabs.add(new HomeTab("message"));
        tabs.add(new HomeTab("setting"));

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabs);
        //この見えない部分の再利用まわり。
        binding.viewPager.setOffscreenPageLimit(2);
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        setNavigationClickAction();
        setToggleAction();

    }


    private void setNavigationClickAction() {
        /**
         * Setup click events on the Navigation View Items.
         */

        binding.shitstuff.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                binding.drawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.nav_item_search) {
                    //TODO:fragment 相談。
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.replace(R.id.containerView, new SearchListViewFragment()).commit();
                }

                if (menuItem.getItemId() == R.id.nav_item_match) {
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
//                    xfragmentTransaction.replace(R.id.containerView, new MatchFragment()).commit();
//                      startChannelList();
//                    startUserList();
                    startMessagingChannelList();
                }

                if (menuItem.getItemId() == R.id.nav_item_message) {
                    FragmentTransaction xfragmentTransaction = getSupportFragmentManager().beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new MessagesFragemnt()).commit();
                }

                return false;
            }

        });
    }

    public static Context getInstance() {
        return instance;
    }

    private void setToggleAction() {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        binding.drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    // Show chat channel list
    private void startChannelList() {
        Intent intent = new Intent(MainActivity.this, SendBirdChannelListActivity.class);
        Bundle args = SendBirdChannelListActivity.makeSendBirdArgs(APP_ID, USER_ID, USER_NICKNAME);

        intent.putExtras(args);
        startActivityForResult(intent, REQUEST_SENDBIRD_CHANNEL_LIST_ACTIVITY);
    }

    private void startUserList() {
        Intent intent = new Intent(MainActivity.this, SendBirdUserListActivity.class);
        Bundle args = SendBirdUserListActivity.makeSendBirdArgs(APP_ID, USER_ID, USER_NICKNAME);
        intent.putExtras(args);

        startActivityForResult(intent, REQUEST_SENDBIRD_USER_LIST_ACTIVITY);
    }

    private void startMessagingChannelList() {
        Intent intent = new Intent(MainActivity.this, SendBirdMessagingChannelListActivity.class);
        Bundle args = SendBirdMessagingChannelListActivity.makeSendBirdArgs(APP_ID, USER_ID, USER_NICKNAME);
        intent.putExtras(args);

        startActivityForResult(intent, REQUEST_SENDBIRD_MESSAGING_CHANNEL_LIST_ACTIVITY);
    }
}

package com.androidbelieve.drawerwithswipetabs.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.domain.sharedusecase.SendBirdHelper;
import com.androidbelieve.drawerwithswipetabs.fragment.MessagesFragemnt;
import com.androidbelieve.drawerwithswipetabs.fragment.SearchListViewFragment;
import com.sendbird.android.SendBird;

public class MainActivity extends AppCompatActivity {

    public static MainActivity instance;

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

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
        setContentView(R.layout.activity_main);

        //set sendbird init
        SendBird.init(APP_ID);
        SendBird.login(USER_ID, USER_NICKNAME);

        instance = this;
        getAllwigets();
        setAllwigets();

    }

    private void setAllwigets() {
        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        setFirstFragment();
        setNavigationClickAction();
        setToggleAction();

    }


    private void setNavigationClickAction() {
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.nav_item_search) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new SearchListViewFragment()).commit();
                }

                if (menuItem.getItemId() == R.id.nav_item_match) {
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
//                    xfragmentTransaction.replace(R.id.containerView, new MatchFragment()).commit();
//                      startChannelList();
//                    startUserList();
                    startMessagingChannelList();
                }


                if (menuItem.getItemId() == R.id.nav_item_message) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new MessagesFragemnt()).commit();
                }

                return false;
            }

        });
    }



    private void setFirstFragment() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new SearchListViewFragment()).commit();

    }

    public static Context getInstance() {
        return instance;
    }

    public void getAllwigets() {
        /**
         *Setup the DrawerLayout and NavigationView
         */
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);
        toolbar = (Toolbar) findViewById(R.id.tabs);
    }
    private void setToggleAction() {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

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

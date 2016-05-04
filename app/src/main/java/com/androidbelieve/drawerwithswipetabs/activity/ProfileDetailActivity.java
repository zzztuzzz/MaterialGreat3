package com.androidbelieve.drawerwithswipetabs.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.databinding.ProfileDetailBinding;

import icepick.Icepick;
import icepick.State;

public class ProfileDetailActivity extends Activity {

    private static final String EXTRA_KITTEN_NUMBER = "kitten_number";

    public static void startActivity(Context context, int kittenNumber) {
        Intent intent = new Intent(context, ProfileDetailActivity.class);
        intent.putExtra(EXTRA_KITTEN_NUMBER, kittenNumber);
        context.startActivity(intent);
    }

    @State int kittenNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProfileDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_detail);
        kittenNumber = getIntent().getIntExtra(EXTRA_KITTEN_NUMBER, 0);

//        ActionBar actionBar = getActionBar();
//        actionBar.hide();



        switch (kittenNumber) {
            case 1:
                binding.image.setImageResource(R.drawable.w6);
                break;
            case 2:
                binding.image.setImageResource(R.drawable.w10);
                break;
            case 3:
                binding.image.setImageResource(R.drawable.w1);
                break;
            case 4:
                binding.image.setImageResource(R.drawable.w2);
                break;
            case 5:
                binding.image.setImageResource(R.drawable.w3);
                break;
            case 6:
                binding.image.setImageResource(R.drawable.w4);
                break;
        }
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.restoreInstanceState(this, outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }
}

package com.androidbelieve.drawerwithswipetabs.entity;

import android.support.annotation.DrawableRes;
import android.util.Log;

import com.androidbelieve.drawerwithswipetabs.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teiyuueki on 2016/05/06.
 */
public class Photo {

    public static List<Photo> createDummyList(int count) {
        List<Photo> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            switch (i%6) {
                case 0:
                    list.add(new Photo(R.drawable.w1));
                    break;
                case 1:
                    list.add(new Photo(R.drawable.w2));
                    break;
                case 2:
                    list.add(new Photo(R.drawable.w3));
                    break;
                case 3:
                    list.add(new Photo(R.drawable.w4));
                    break;
                case 4:
                    list.add(new Photo(R.drawable.w6));
                    break;
                case 5:
                    list.add(new Photo(R.drawable.w10));
                    break;
                default:
                    Log.d("Photo.java", "i == " + (i%6));
                    throw new RuntimeException();
            }
        }
        return list;
    }

    public @DrawableRes int drawableResId;

    public Photo(@DrawableRes int drawableResId) {
        this.drawableResId = drawableResId;
    }
}

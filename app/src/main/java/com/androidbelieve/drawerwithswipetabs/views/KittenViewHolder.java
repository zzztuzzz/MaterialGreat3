package com.androidbelieve.drawerwithswipetabs.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.androidbelieve.drawerwithswipetabs.R;

/**
 * Created by teiyuueki on 2016/04/23.
 */
public class KittenViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;

    public KittenViewHolder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image);
    }
}

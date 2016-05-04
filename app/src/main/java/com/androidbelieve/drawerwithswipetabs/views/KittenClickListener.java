package com.androidbelieve.drawerwithswipetabs.views;

import android.support.v7.widget.RecyclerView;

/**
 * Created by teiyuueki on 2016/04/23.
 */
public interface KittenClickListener {
    /**
     * Called when a kitten is clicked
     *  @param holder   The ViewHolder for the clicked kitten
     * @param position The position in the grid of the kitten that was clicked
     */
    void onKittenClicked(RecyclerView.ViewHolder holder, int position);

}

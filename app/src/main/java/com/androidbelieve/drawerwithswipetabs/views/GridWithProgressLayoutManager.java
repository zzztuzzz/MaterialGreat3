package com.androidbelieve.drawerwithswipetabs.views;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.androidbelieve.drawerwithswipetabs.adapter.PhotoGridAdapter;

/**
 * Created by teiyuueki on 2016/05/06.
 */
public class GridWithProgressLayoutManager extends GridLayoutManager {

    public GridWithProgressLayoutManager(Context context,
                                         final int spanCount,
                                         final PhotoGridAdapter adapter) {
        super(context, spanCount);
        //progressbar の位置セットアップ
        setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (adapter != null
                        && adapter.getItemViewType(position) == PhotoGridAdapter.TYPE_PROG) {
                    System.out.println("GriddWithProgressLayoutManger:GetSpanSize:"+adapter.getItemViewType(position));
                    System.out.println("Rerutn:"+spanCount);
                    return spanCount;
                }
                return 1;
            }
            @Override
            public int getSpanIndex(int position, int spanCount) {
                if (adapter != null
                        && adapter.getItemViewType(position) == PhotoGridAdapter.TYPE_PROG) {
                    return 0;
                }
                return position % spanCount;
            }
        });
    }

}

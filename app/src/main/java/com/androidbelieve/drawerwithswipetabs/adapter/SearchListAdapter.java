package com.androidbelieve.drawerwithswipetabs.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidbelieve.drawerwithswipetabs.R;

import java.util.List;

/**
 * Created by teiyuueki on 2016/05/03.
 */

public class SearchListAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // リサイクラービューアダプターの継承、拡張クラスを作成する；
    //　新規読み込み前に,最終スクロール値を保存したり、loading動作の割り込みロック、追加処理、ロック解除
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    //datasetの宣言 mdatasetが、配列.
    private List<T> mDataset;

    //
    // The minimum amount of items to have below your current scroll position before loading more.

    //TODO:なにこれ
    private int visibleThreshold = 2;

    //最後参照したitem数,合計item数
    private int lastVisibleItem, totalItemCount;
    //loadフラグ
    private boolean loading;

    //ロードの動作を検知するする
    private OnLoadMoreListener onLoadMoreListener;

    //
    public SearchListAdapter(List<T> myDataSet, RecyclerView recyclerView) {
        mDataset = myDataSet;

        //リサイクラーのlayoutmanagerをインスタンス化して、開始。
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            //スクロールリスナのセット。これでスクロールのたびに、数値を検知して、処理を走らせる
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                    //
                    super.onScrolled(recyclerView, dx, dy);
                    System.out.println("dx:" + dx + "dy:" + dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    System.out.println("totalItemcount:"+totalItemCount);
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    System.out.println("lastvisibleitem"+lastVisibleItem);

                    //ここでスクロール地点と、取得数を比較して、最深部きてたら、追加発行する。
                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        // End has been reached
                        // Do something
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        //
                        loading = true;
                    }
                }
            });
        }
    }


    @Override
    public int getItemViewType(int position) {
        return mDataset.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    //recyclerivewのviewholderで、渡ってくる値に応じて、対応xmlと
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);

            vh = new TextViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.progress, parent, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    //recyclerview 本体のviewadapter。ここでは、
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).mTextView.setText(mDataset.get(position).toString());
        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    //recyle viewの内部で使うコンテンツひとつずつをviewholder内部で宣言、追加。

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public TextViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(android.R.id.text1);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }
}

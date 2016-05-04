package com.androidbelieve.drawerwithswipetabs.views;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.fragment.ListViewFragmentOne;

import java.util.List;

/**
 * Created by teiyuueki on 2016/04/23.
 */
public class KittenGridAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int mSize;
    private final KittenClickListener mListener;
    private List<T> mDataset;
    private int visibleThreshold = 2;

    //最後参照したitem数,合計item数
    private int lastVisibleItem, totalItemCount;
    //loadフラグ
    private boolean loading;
    // リサイクラービューアダプターの継承、拡張クラスを作成する；
    //　新規読み込み前に,最終スクロール値を保存したり、loading動作の割り込みロック、追加処理、ロック解除
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private OnLoadMoreListener onLoadMoreListener;

    /**
     * Constructor
     * @param size The number of kittens to show
     * @param listener A listener for kitten click events
     */
//    public KittenGridAdapter(int size, ListViewFragmentOne listener) {
//        mSize = size;
//        mListener = listener;
//    }

    public KittenGridAdapter(List<T> myDataSet,int size,ListViewFragmentOne listener,RecyclerView recyclerView) {
        mSize = size;
        mListener = listener;
        mDataset = myDataSet;
        System.out.println("=============KittenGridAdapter==========:");
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
        System.out.println("=============EndGridAdapter==========:");
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup container, int position) {

        //1.if文でa.griditemと,progressbarのxmlの読み込み
//        LayoutInflater inflater = LayoutInflater.from(container.getContext());
//        View cell = inflater.inflate(R.layout.grid_item, container, false);
//        return new KittenViewHolder(cell);

        RecyclerView.ViewHolder vh;
        if (position == VIEW_ITEM) {
            View v = LayoutInflater.from(container.getContext())
                    .inflate(R.layout.grid_item, container, false);

            vh = new ImageViewHolder(v);
        } else {
            View v = LayoutInflater.from(container.getContext())
                    .inflate(R.layout.progress_serarchview_footer, container, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        //リサイクラービューに要素の挿入。
//
//        //リサイクラービューに要素の挿入。
//        if (holder instanceof TextViewHolder) {
//            ((TextViewHolder) holder).mTextView.setText(mDataset.get(position).toString());
//        } else {
//            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
//        }
        if (holder instanceof ImageViewHolder) {
            switch (position % 6) {
                case 0:
                    ((ImageViewHolder) holder).mImageView.setImageResource(R.drawable.w6);
                    break;
                case 1:
                    ((ImageViewHolder) holder).mImageView.setImageResource(R.drawable.w10);
                    break;
                case 2:
                    ((ImageViewHolder) holder).mImageView.setImageResource(R.drawable.w1);
                    break;
                case 3:
                    ((ImageViewHolder) holder).mImageView.setImageResource(R.drawable.w2);
                    break;
                case 4:
                    ((ImageViewHolder) holder).mImageView.setImageResource(R.drawable.w3);
                    break;
                case 5:
                    ((ImageViewHolder) holder).mImageView.setImageResource(R.drawable.w4);
                    break;
            }
        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }

        ViewCompat.setTransitionName(((ImageViewHolder) holder).mImageView, String.valueOf(position) + "_image");

        ((ImageViewHolder) holder).mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onKittenClicked(holder, position);
            }
        });
    }

//    @Override
//    public void onBindViewHolder(final KittenViewHolder viewHolder, final int position) {
//
//
//        switch (position % 6) {
//            case 0:
//                viewHolder.image.setImageResource(R.drawable.w6);
//                break;
//            case 1:
//                viewHolder.image.setImageResource(R.drawable.w10);
//                break;
//            case 2:
//                viewHolder.image.setImageResource(R.drawable.w1);
//                break;
//            case 3:
//                viewHolder.image.setImageResource(R.drawable.w2);
//                break;
//            case 4:
//                viewHolder.image.setImageResource(R.drawable.w3);
//                break;
//            case 5:
//                viewHolder.image.setImageResource(R.drawable.w4);
//                break;
//        }
//
//        // It is important that each shared element in the source screen has a unique transition name.
//        // For example, we can't just give all the images in our grid the transition name "kittenImage"
//        // because then we would have conflicting transition names.
//        // By appending "_image" to the position, we can support having multiple shared elements in each
//        // grid cell in the future.
//        ViewCompat.setTransitionName(viewHolder.image, String.valueOf(position) + "_image");
//
//        viewHolder.image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mListener.onKittenClicked(viewHolder, position);
//            }
//        });
//    }



    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemViewType(int position) {
        System.out.println("getItemViewの出力"+mDataset.get(position));
        return mDataset.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        System.out.println("=========setOnLoadMoreListener");
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    @Override
    public int getItemCount() {
        return mSize;
    }

    //recyle viewの内部で使うコンテンツひとつずつをviewholder内部で宣言、追加。

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        public ImageViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.image_tablist);
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

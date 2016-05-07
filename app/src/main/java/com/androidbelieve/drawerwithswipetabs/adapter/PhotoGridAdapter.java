package com.androidbelieve.drawerwithswipetabs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.entity.Photo;
import com.androidbelieve.drawerwithswipetabs.views.ProgressBarLayoutHolder;
import com.androidbelieve.drawerwithswipetabs.views.ProgressStub;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by teiyuueki on 2016/05/06.
 */

public class PhotoGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private int imageSize;
    private final Object lock = new Object();
    public final static int TYPE_ITEM = 1;
    public final static int TYPE_PROG = 10;
    protected final List<Photo> photos;

    public PhotoGridAdapter(@NonNull Context context, @NonNull List<Photo> items, int spanCount) {
        super();
        this.photos = items ;
        this.context = context;

        inflater = LayoutInflater.from(context);
        int unitMargin = context.getResources().getDimensionPixelSize(R.dimen.unit_margin);
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        System.out.println("widthpicsels:"+widthPixels);

        imageSize = (widthPixels - (spanCount - 1) * unitMargin) / spanCount;
        System.out.println("imageSize:"+imageSize);

    }
    //xml指定
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if (viewType == TYPE_ITEM) {
            FrameLayout view = (FrameLayout) inflater.inflate(R.layout.photo_layout, parent, false);
            //xml指定.ここにimage,ハート,名前,年齢の表示コンテンツも入れる。
            AppCompatImageView photoImageView = (AppCompatImageView) view.findViewById(R.id.photo_image_view);

            //iamgeのサイズ指定と正方形刈り込みの設定,ここで表示サイズを横幅/2- マージンしたサイズ　＝正方形になる。
            photoImageView.setLayoutParams(new FrameLayout.LayoutParams(imageSize, imageSize));
            viewHolder = new PhotoLayoutHolder(view);
        } else {
            //xml指定.loading時の
            FrameLayout view = (FrameLayout) inflater.inflate(R.layout.progress_bar_layout, parent, false);
            viewHolder = new ProgressBarLayoutHolder(view);
        }
        return viewHolder;
    }
    //value指定
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof PhotoLayoutHolder) {

            Photo photo =  photos.get(position);
            FrameLayout photoLayout = ((PhotoLayoutHolder) holder).photoLayout;

            AppCompatImageView photoImageView = (AppCompatImageView) photoLayout.findViewById(R.id.photo_image_view);
            Picasso.with(context).load(photo.drawableResId).into(photoImageView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isProgressBarShowing && position >= photos.size()) {
            return ITEM_PROG;
        }
        return TYPE_ITEM;

//        final Object object = objects.get(position);
//        if (object instanceof ProgressStub) {
//            System.out.println("PhotoGridAdapter  If getItemViewType"+TYPE_PROG);
//            return TYPE_PROG;
//        } else {
//            System.out.println("PhotoGridAdapter  If getItemViewType"+TYPE_ITEM);
//            return TYPE_ITEM;
//        }
    }

    public void setProgressBarShowingVisivility(boolean visible) {
        isProgressBarShowing = visible;
        notifyDataSetChanged();
    }

    public final class PhotoLayoutHolder extends RecyclerView.ViewHolder {
        public final FrameLayout photoLayout;

        public PhotoLayoutHolder(FrameLayout view) {
            super(view);
            photoLayout = view;
        }
    }

    @Override
    public int getItemCount() {
        if (isProgressBarShowing) {
            return photos.size() + 1;
        }
        return photos.size();
    }

    public void add(@NonNull Photo photo) {
        int position = objects.size();
        synchronized (lock) {
            objects.add(photo);
        }
        notifyItemInserted(position);
    }

    public void remove(Object object) {
        int position = objects.indexOf(object);
        synchronized (lock) {
            objects.remove(object);
        }
        notifyItemRemoved(position);
    }

    public void clear() {
        synchronized (lock) {
            objects.clear();
        }
        notifyDataSetChanged();
    }

}
package com.androidbelieve.drawerwithswipetabs.fragment;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.activity.ProfileDetailActivity;
import com.androidbelieve.drawerwithswipetabs.views.GridViewAdapter;
import com.androidbelieve.drawerwithswipetabs.views.KittenClickListener;
import com.androidbelieve.drawerwithswipetabs.views.KittenGridAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by teiyuueki on 2016/04/24.
 */
public class ListViewFragmentOne extends Fragment implements KittenClickListener {

    public static Fragment newInstance() {
        return new ListViewFragmentOne();
    }

    private GridView gridView;
    ArrayList<Drawable> allDrawableImages = new ArrayList<>();
    private TypedArray allImages;
    private View  rootView;
    KittenGridAdapter mAdapter;
    protected Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.listview_fragment_one, container, false);
        ButterKnife.bind(this, rootView);
        getAllWidgets(rootView);

        // start ListViewFragemntOne
        System.out.println("===============listviewfragmentone開始============");

        // main action
        setRecyclerViews();
        setAdapter();
        return rootView;
    }

    private void setRecyclerViews() {
        //recycler view　init
        //mAdapter = new KittenGridAdapter(6, this);
        System.out.println("=============1.setrecyclerviews()start============");
        RecyclerView recyclerView = (RecyclerView)  rootView.findViewById(R.id.recyclerview);
        mAdapter = new KittenGridAdapter<Drawable>(allDrawableImages,6,this,recyclerView);

        //recyclerviewに,gridadapterで値渡し。
        recyclerView.setHasFixedSize(true);

        //2列に設定。
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        //laytout manager のセット
        recyclerView.setAdapter(mAdapter);

        //スクロール時のクリックリスナ宣言
        //2.recycleradapterの拡張adapterのセット
        //recylcerviewに、adapterのセット/setAdapter

        handler = new Handler();
        mAdapter.setOnLoadMoreListener(new KittenGridAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //add progress item
                allDrawableImages.add(null);

                mAdapter.notifyItemInserted(allDrawableImages.size() - 1);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("a:ハンドラリスナ開始");
                        //remove progress item これでプログレス
                        allDrawableImages.remove(allDrawableImages.size() - 1);
                        System.out.println("b:プログレスの指定.リストのサイズを取得.");
                        System.out.println(allDrawableImages.size());


                        //アニメーションの入る項目の指定
                        mAdapter.notifyItemRemoved(allDrawableImages.size());

                        //add items one by one
                        for (int i = 0; i < 6; i++) {
                            allDrawableImages.add(allImages.getDrawable(i));
                        }
                        mAdapter.notifyItemInserted(allDrawableImages.size());

                        //adapterのロード
                        mAdapter.setLoaded();
                        //or you can add all at once but do not forget to call mAdapter.notifyDataSetChanged();
                    }
                }, 1000);
                System.out.println("load");
            }
        });
        System.out.println("=============1.setrecyclerviews()End============");

    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void getAllWidgets(View view) {
        gridView = (GridView) view.findViewById(R.id.gridViewFragment);
        allImages = getResources().obtainTypedArray(R.array.all_images);
    }

    private void setAdapter()
    {
        for (int i = 0; i < allImages.length(); i++) {
            allDrawableImages.add(allImages.getDrawable(i));
        }

        GridViewAdapter gridViewAdapter = new GridViewAdapter(com.androidbelieve.drawerwithswipetabs.activity.MainActivity.getInstance(), allDrawableImages);
        gridView.setAdapter(gridViewAdapter);
    }
    @Override
    public void onKittenClicked(RecyclerView.ViewHolder holder, int position) {
        int kittenNumber = (position % 6) + 1;
        ProfileDetailActivity.startActivity(getActivity(), kittenNumber);


    }


}

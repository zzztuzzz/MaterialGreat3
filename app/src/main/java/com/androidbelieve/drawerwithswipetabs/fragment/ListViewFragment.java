package com.androidbelieve.drawerwithswipetabs.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.adapter.PhotoGridAdapter;
import com.androidbelieve.drawerwithswipetabs.databinding.ListFragmentBinding;
import com.androidbelieve.drawerwithswipetabs.entity.Photo;
import com.androidbelieve.drawerwithswipetabs.views.EndlessScrollListener;
import com.androidbelieve.drawerwithswipetabs.views.GridSpacingItemDecoration;
import com.androidbelieve.drawerwithswipetabs.views.GridWithProgressLayoutManager;
import com.androidbelieve.drawerwithswipetabs.views.ProgressStub;

import java.util.ArrayList;

import butterknife.BindDimen;

/**
 * Created by teiyuueki on 2016/05/06.
 */
public class ListViewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, EndlessScrollListener.OnLoadMoreListener {

    public static Fragment newInstance() {
        return new ListViewFragment();
    }

    private static Handler handler = new Handler(Looper.getMainLooper());
    private PhotoGridAdapter adapter;
    private EndlessScrollListener scrollListener;

//    private ListFragmentBinding binding;

    private ListFragmentBinding binding;


    @BindDimen(R.dimen.unit_margin) int unitMargin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.listview_fragment, container, false);

        int spanCount = 2;

        if (com.androidbelieve.drawerwithswipetabs.Util.Landscape.isLandscape(getActivity())) {
            spanCount = 3;
        }

        //
        //gridviewadapter setup
        //

        //1.画像のセットアップアダプターの作成
        adapter = new PhotoGridAdapter(getActivity(), new ArrayList<>(), spanCount);
        //2.プログレスバーのレイアウトマネージャーの作成,引数(activityとspancount,画像のアダプタ)を代入
        GridWithProgressLayoutManager layoutManager = new GridWithProgressLayoutManager(getActivity(), spanCount, adapter);
        //3.スクロールリスナを作成.
        scrollListener = new EndlessScrollListener(1, layoutManager);
        //4.スクロールリスナをon
        scrollListener.setOnLoadMoreListener(this);

        //
        //recyclerview setup
        //

        //1.リサイクラーレイアウトマネージャーに、プログレスバーのセットアダプターを反映
        binding.recyclerView.setLayoutManager(layoutManager);
        //2.リサイクラーviewにスクロールリスナを反映
        binding.recyclerView.addOnScrollListener(scrollListener);
        //3.
        binding.recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, unitMargin));
        //4.リサイクラーに、画像のセットアップアダプターの反映
        binding.recyclerView.setAdapter(adapter);

        //swiperrefreshの設定
        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        binding.swipeRefreshLayout.setOnRefreshListener(this);


        loadData(1);
        return binding.getRoot();
    }

    private void loadData(final int page) {
        // Set the Stub for display the ProgressBar
        final ProgressStub progressStub = new ProgressStub();
        if (page > 1) {
            adapter.add(progressStub);
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Remove the Stub
                if (page > 1) {
                    adapter.remove(progressStub);
                }
                if (binding.swipeRefreshLayout.isRefreshing()) {
                    binding.swipeRefreshLayout.setRefreshing(false);
                }
                adapter.add(Photo.createDummyList(6));
            }
        }, 1000);
    }



    @Override
    public void onLoadMore(int currentPage) {

    }

    @Override
    public void onRefresh() {
        adapter.clear();
        scrollListener.onRefresh();
        loadData(1);
    }


}

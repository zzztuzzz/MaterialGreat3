package com.androidbelieve.drawerwithswipetabs.fragment;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.views.DetailsTransition;
import com.androidbelieve.drawerwithswipetabs.views.GridViewAdapter;
import com.androidbelieve.drawerwithswipetabs.views.KittenClickListener;
import com.androidbelieve.drawerwithswipetabs.views.KittenGridAdapter;
import com.androidbelieve.drawerwithswipetabs.views.KittenViewHolder;

import java.util.ArrayList;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.listview_fragment_one, null);
        getAllWidgets(rootView);
        setAdapter();
        return rootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(new KittenGridAdapter(6, this));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
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
    public void onKittenClicked(KittenViewHolder holder, int position) {
        int kittenNumber = (position % 6) + 1;

        PersonDetailsFragment kittenDetails = PersonDetailsFragment.newInstance(kittenNumber);

        // Note that we need the API version check here because the actual transition classes (e.g. Fade)
        // are not in the support library and are only available in API 21+. The methods we are calling on the Fragment
        // ARE available in the support library (though they don't do anything on API < 21)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            kittenDetails.setSharedElementEnterTransition(new DetailsTransition());
            kittenDetails.setEnterTransition(new Fade());
            setExitTransition(new Fade());
            kittenDetails.setSharedElementReturnTransition(new DetailsTransition());
        }

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(holder.image, "kittenImage")
                .replace(R.id.containerView, kittenDetails)
                .addToBackStack(null)
                .commit();
    }


}

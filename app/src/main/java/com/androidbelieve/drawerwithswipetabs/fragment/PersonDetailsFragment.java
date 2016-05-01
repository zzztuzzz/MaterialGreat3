package com.androidbelieve.drawerwithswipetabs.fragment;

import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidbelieve.drawerwithswipetabs.R;

/**
 * Created by teiyuueki on 2016/04/23.
 */
public class PersonDetailsFragment extends Fragment {
    private static final String ARG_KITTEN_NUMBER = "argKittenNumber";


    /**
     * Create a new PersonDetailsFragment
     * @param kittenNumber The number (between 1 and 6) of the kitten to display
     */
    public static PersonDetailsFragment newInstance(@IntRange(from = 1, to = 6) int kittenNumber) {
        Bundle args = new Bundle();
        args.putInt(ARG_KITTEN_NUMBER, kittenNumber);

        PersonDetailsFragment fragment = new PersonDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView image = (ImageView) view.findViewById(R.id.image);

        Bundle args = getArguments();
        int kittenNumber = args.containsKey(ARG_KITTEN_NUMBER) ? args.getInt(ARG_KITTEN_NUMBER) : 1;

        switch (kittenNumber) {
            case 1:
                image.setImageResource(R.drawable.w6);
                break;
            case 2:
                image.setImageResource(R.drawable.w10);
                break;
            case 3:
                image.setImageResource(R.drawable.w1);
                break;
            case 4:
                image.setImageResource(R.drawable.w2);
                break;
            case 5:
                image.setImageResource(R.drawable.w3);
                break;
            case 6:
                image.setImageResource(R.drawable.w4);
                break;
        }
    }
}

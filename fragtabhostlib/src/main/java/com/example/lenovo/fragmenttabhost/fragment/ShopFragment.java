package com.example.lenovo.fragmenttabhost.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.fragmenttabhost.R;

/**
 * Created by lenovo on 2017/9/30.
 */

public class ShopFragment extends Fragment {

    private View mRootView;

    public static ShopFragment newInstance(Bundle bundle) {
        ShopFragment shopft = new ShopFragment();
        shopft.setArguments(bundle);
        return shopft;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_shop, container, false);

        return mRootView;
    }
}

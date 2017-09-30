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

public class CustomerFragment extends Fragment {

    private View mRootView;

    public static CustomerFragment newInstance(Bundle bundle) {
        CustomerFragment customerft = new CustomerFragment();
        customerft.setArguments(bundle);
        return customerft;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_customer, container, false);

        return mRootView;
    }


}

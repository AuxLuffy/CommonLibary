package com.lenovo.service.basicpubliclibrary.shinebutton;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.lenovo.service.basicpubliclibrary.R;
import com.sackcentury.shinebuttonlib.ShineButton;

/**
 * Created by tangrenmei on 2017/9/25.
 */

public class FragmentDemo extends Fragment {

    View rootView;
    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.common_fragment, null);
        return rootView;
    }

    @Override
    public void onResume() {
        initData();
        super.onResume();
    }

    private void initData() {
        ShineButton shineButton1 = (ShineButton) rootView.findViewById(R.id.po_image1);
        shineButton1.init((ShineButtonActivity) getActivity());

        ShineButton shineButton2 = (ShineButton) rootView.findViewById(R.id.po_image2);
        ShineButton shineButton3 = (ShineButton) rootView.findViewById(R.id.po_image3);
        final Button hideBtn = (Button) rootView.findViewById(R.id.hide_button);
        hideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideFragment();
            }
        });
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFragment();
            }
        });
    }

    public void showFragment(final FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(
                R.anim.fragmentv_slide_bottom_enter,
                0,
                0,
                R.anim.fragmentv_slide_top_exit);
        transaction.add(Window.ID_ANDROID_CONTENT, FragmentDemo.this, "FragmentDemo");
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    public void hideFragment() {
        fragmentManager.popBackStack();
    }

}

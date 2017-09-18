package com.lenovo.service.basicpubliclibrary.floatingmenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lenovo.service.basicpubliclibrary.R;

import terranovaproductions.newcomicreader.FloatingActionMenu;

/**
 * Created by sunzf
 */
public class DemoLineMenuFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_line_menu, container, false);
        FloatingActionMenu menu = (FloatingActionMenu) v.findViewById(R.id.fab_menu_line);
        menu.setmItemGap(48);
        return v;
    }
}

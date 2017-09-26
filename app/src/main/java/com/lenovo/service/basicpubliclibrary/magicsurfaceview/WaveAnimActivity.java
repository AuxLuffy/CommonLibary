package com.lenovo.service.basicpubliclibrary.magicsurfaceview;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gplibs.magicsurfaceview.MagicSurface;
import com.gplibs.magicsurfaceview.MagicSurfaceView;
import com.gplibs.magicsurfaceview.MagicUpdaterListener;
import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.magicsurfaceview.common.Direction;
import com.lenovo.service.basicpubliclibrary.magicsurfaceview.common.MagicActivity;
import com.lenovo.service.basicpubliclibrary.magicsurfaceview.updater.WaveAnimUpdater;

/**
 * Created by tangrenmei on 2017/9/26.
 */

public class WaveAnimActivity extends MagicActivity {

    private MagicSurfaceView mSurfaceView;
    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_anim);
        setTitle("WaveAnim");

        mSurfaceView = (MagicSurfaceView) findViewById(R.id.surface_view);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        findViewById(R.id.view_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTvContent.getVisibility() == View.VISIBLE) {
                    hide(Direction.RIGHT);
                } else {
                    show(Direction.RIGHT);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSurfaceView.onDestroy();
    }

    private void show(int direction) {
        WaveAnimUpdater updater = new WaveAnimUpdater(false, direction, false);
        updater.addListener(new MagicUpdaterListener() {
            @Override
            public void onStart() {
                mTvContent.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onStop() {
                mTvContent.setVisibility(View.VISIBLE);
                mSurfaceView.setVisibility(View.GONE);
                // 释放资源
                mSurfaceView.release();
            }
        });
        MagicSurface s = new MagicSurface(mTvContent)
                .setGrid(getRowLineCount(direction), getColLineCount(direction))
                .drawGrid(false)
                .setModelUpdater(updater);
        mSurfaceView.setVisibility(View.VISIBLE);
        mSurfaceView.render(s);
    }

    private void hide(int direction) {
        WaveAnimUpdater updater = new WaveAnimUpdater(true, direction, false);
        updater.addListener(new MagicUpdaterListener() {
            @Override
            public void onStart() {
                mTvContent.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onStop() {
                mSurfaceView.setVisibility(View.GONE);
                // 释放资源
                mSurfaceView.release();
            }
        });
        MagicSurface s = new MagicSurface(mTvContent)
                .setGrid(getRowLineCount(direction), getColLineCount(direction))
                .drawGrid(false)
                .setModelUpdater(updater);
        mSurfaceView.setVisibility(View.VISIBLE);
        mSurfaceView.render(s);
    }

    private int getRowLineCount(int direction) {
        return Direction.isVertical(direction) ? 50 : 8;
    }

    private int getColLineCount(int direction) {
        return Direction.isVertical(direction) ? 8 : 50;
    }
}


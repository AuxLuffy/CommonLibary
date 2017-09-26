package com.lenovo.service.basicpubliclibrary.magicsurfaceview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.gplibs.magicsurfaceview.MagicSurfaceModelUpdater;
import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.magicsurfaceview.common.Direction;
import com.lenovo.service.basicpubliclibrary.magicsurfaceview.common.MagicActivity;
import com.lenovo.service.basicpubliclibrary.magicsurfaceview.updater.VortexAnimUpdater;
import com.lenovo.service.basicpubliclibrary.magicsurfaceview.updater.WaveAnimUpdater;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by tangrenmei on 2017/9/25.
 */

public class MagicSurfaceviewActivity extends MagicActivity {

    private Map.Entry<String, Class<? extends Activity>>[] mArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magicsurfaceview);
        setTitle("MagicSurfaceViewSample");
        initData();
        initView();
    }

    private void initData() {
        Map<String, Class<? extends Activity>> items = new LinkedHashMap<>();
        items.put("ScrapAnim", MultiScrapAnimActivity.class);
        items.put("MultiSlideAnim", MultiSlideAnimActivity.class);
        items.put("MacWindowAnim", MacWindowAnimActivity.class);
        items.put("WaveAnim", WaveAnimActivity.class);
        items.put("VortexAnim", VortexAnimActivity.class);
        items.put("MatrixAnimSample", MatrixAnimSampleActivity.class);
        items.put("LightSample", LightSampleActivity.class);
        mArray = new Map.Entry[items.size()];
        items.entrySet().toArray(mArray);
    }

    private void initView() {
        ((ListView) findViewById(R.id.list_view)).setAdapter(new Adapter(this, R.layout.item_activity));
    }

    @Override
    protected MagicSurfaceModelUpdater getPageUpdater(boolean isHide) {
        if (isHide) {
            return new VortexAnimUpdater(true);
        } else {
            return new WaveAnimUpdater(false, Direction.RIGHT, false);
        }
    }

    @Override
    protected int pageAnimRowCount() {
        return 60;
    }

    @Override
    protected int pageAnimColCount() {
        return 60;
    }

    private void start(Class<? extends Activity> activityClass) {
        startActivity(new Intent(this, activityClass));
    }


    class Adapter extends ArrayAdapter<Map.Entry<String, Class<? extends  Activity>>> {
        private int mResourceId;

        public Adapter(@NonNull Context context, @LayoutRes int resource) {
            super(context, resource);
            this.mResourceId = resource;
        }

        @Override
        public int getCount() {
            return mArray.length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = getLayoutInflater().inflate(mResourceId, null);
            }
            final Map.Entry<String, Class<? extends Activity>> entry = mArray[position];
            ((Button) convertView).setText(entry.getKey());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    start(entry.getValue());
                }
            });
            return convertView;
        }
    }

}

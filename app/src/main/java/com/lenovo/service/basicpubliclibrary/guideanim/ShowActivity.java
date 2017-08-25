package com.lenovo.service.basicpubliclibrary.guideanim;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.dev.sacot41.scviewpager.DotsView;
import com.dev.sacot41.scviewpager.SCPositionAnimation;
import com.dev.sacot41.scviewpager.SCViewAnimation;
import com.dev.sacot41.scviewpager.SCViewAnimationUtil;
import com.dev.sacot41.scviewpager.SCViewPager;
import com.dev.sacot41.scviewpager.SCViewPagerAdapter;
import com.lenovo.service.basicpubliclibrary.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

public class ShowActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 4;

    private SCViewPager mViewPager;
    private SCViewPagerAdapter mPageAdapter;
    private DotsView mDotsView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        mContext = this;
        mViewPager = (SCViewPager) findViewById(R.id.viewpager_main_activity);
        mDotsView = (DotsView) findViewById(R.id.dotsview_main);
        mDotsView.setDotRessource(R.drawable.dot_selected, R.drawable.dot_unselected);
        mDotsView.setNumberOfPage(NUM_PAGES);

        mPageAdapter = new SCViewPagerAdapter(getSupportFragmentManager());
        mPageAdapter.setNumberOfPage(NUM_PAGES);
        mPageAdapter.setFragmentBackgroundColor(R.color.my_blue);
        mViewPager.setAdapter(mPageAdapter);
        mViewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mDotsView.selectDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        final Point size = SCViewAnimationUtil.getDisplaySize(this);

        int iconOffsetX = dpToPx(28);
        int iconOffsetY = dpToPx(28);

        SCViewAnimation sc0 = new SCViewAnimation(findViewById(R.id.icon_4));
        sc0.startToPosition(size.x / 4 - iconOffsetX, size.y * 2 / 7 - iconOffsetY);
        sc0.addPageAnimation(new SCPositionAnimation(this, 0, 0, size.y));
        mViewPager.addAnimation(sc0);

        SCViewAnimation sc1 = new SCViewAnimation(findViewById(R.id.icon_11));
        sc1.startToPosition(size.x * 3 / 4 - iconOffsetX, size.y * 3 / 7 - iconOffsetY);
        sc1.addPageAnimation(new SCPositionAnimation(this, 0, -size.x, 0));
        mViewPager.addAnimation(sc1);

        SCViewAnimation sc2 = new SCViewAnimation(findViewById(R.id.icon_12));
        sc2.startToPosition(size.x / 4 - iconOffsetX, size.y * 4 / 7 - iconOffsetY);
        sc2.addPageAnimation(new SCPositionAnimation(this, 0, size.x, 0));
        mViewPager.addAnimation(sc2);

        SCViewAnimation sc3 = new SCViewAnimation(findViewById(R.id.icon_19));
        sc3.startToPosition(size.x * 3 / 4 - iconOffsetX, size.y * 5 / 7 - iconOffsetY);
        sc3.addPageAnimation(new SCPositionAnimation(this, 0, 0, -size.y));
        mViewPager.addAnimation(sc3);

//        ((TextView)findViewById(R.id.text_0)).setTypeface(CoCoinUtil.getInstance().typefaceLatoLight);
        SCViewAnimation sc4 = new SCViewAnimation(findViewById(R.id.text_0));
        sc4.addPageAnimation(new SCPositionAnimation(this, 0, -size.x, 0));
        mViewPager.addAnimation(sc4);

        PieChartView pie = (PieChartView)findViewById(R.id.pie);
        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < 5; ++i) {
            SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ContextCompat.getColor(mContext, R.color.white));
            values.add(sliceValue);
        }
        PieChartData pieData = new PieChartData(values);
        pieData.setHasLabels(false);
        pieData.setHasLabelsOnlyForSelected(false);
        pieData.setHasLabelsOutside(false);
        pieData.setHasCenterCircle(true);
        pie.setPieChartData(pieData);
        pie.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        SCViewAnimation sc5 = new SCViewAnimation(pie);
        sc5.startToPosition(size.x / 2, size.y / 9 - size.y);
        sc5.addPageAnimation(new SCPositionAnimation(this, 0, 0, size.y));
        sc5.addPageAnimation(new SCPositionAnimation(this, 1, 0, size.y));
        mViewPager.addAnimation(sc5);

        LineChartView line = (LineChartView)findViewById(R.id.line);
        List<Line> lines = new ArrayList<Line>();
        for (int i = 0; i < 1; ++i) {
            List<PointValue> pointValues = new ArrayList<PointValue>();

            pointValues.add(new PointValue(0, 50));
            pointValues.add(new PointValue(1, 100));
            pointValues.add(new PointValue(2, 20));
            pointValues.add(new PointValue(3, 0));
            pointValues.add(new PointValue(4, 10));
            pointValues.add(new PointValue(5, 15));
            pointValues.add(new PointValue(6, 40));
            pointValues.add(new PointValue(7, 60));
            pointValues.add(new PointValue(8, 100));

            Line aLine = new Line(pointValues);
            aLine.setColor(ContextCompat.getColor(mContext, R.color.white));
            aLine.setShape(ValueShape.CIRCLE);
            aLine.setCubic(false);
            aLine.setFilled(false);
            aLine.setHasLabels(false);
            aLine.setHasLabelsOnlyForSelected(false);
            aLine.setHasLines(true);
            aLine.setHasPoints(true);
            lines.add(aLine);
        }
        LineChartData linedata = new LineChartData(lines);
        linedata.setBaseValue(Float.NEGATIVE_INFINITY);
        line.setLineChartData(linedata);
        line.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        SCViewAnimation sc6 = new SCViewAnimation(line);
        sc6.startToPosition(-size.x, null);
        sc6.addPageAnimation(new SCPositionAnimation(this, 0, size.x, 0));
        sc6.addPageAnimation(new SCPositionAnimation(this, 1, size.x, 0));
        mViewPager.addAnimation(sc6);

        ColumnChartView histogram = (ColumnChartView)findViewById(R.id.histogram);
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> subcolumnValues;
        for (int i = 0; i < 5; ++i) {
            subcolumnValues = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < 1; ++j) {
                subcolumnValues.add(new SubcolumnValue((float) Math.random() * 50f + 5, ContextCompat.getColor(mContext, R.color.white)));
            }
            Column column = new Column(subcolumnValues);
            column.setHasLabels(false);
            column.setHasLabelsOnlyForSelected(false);
            columns.add(column);
        }
        ColumnChartData histogramData = new ColumnChartData(columns);
        histogram.setColumnChartData(histogramData);
        histogram.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        SCViewAnimation sc7 = new SCViewAnimation(histogram);
        sc7.startToPosition(size.x / 2 - dpToPx(140), size.y * 8 / 9 - dpToPx(140) + size.y);
        sc7.addPageAnimation(new SCPositionAnimation(this, 0, 0, -size.y));
        sc7.addPageAnimation(new SCPositionAnimation(this, 1, 0, size.y));
        mViewPager.addAnimation(sc7);

//        ((TextView)findViewById(R.id.text_1)).setTypeface(CoCoinUtil.getInstance().typefaceLatoLight);
        SCViewAnimation sc8 = new SCViewAnimation(findViewById(R.id.text_1));
        sc8.startToPosition(size.x, null);
        sc8.addPageAnimation(new SCPositionAnimation(this, 0, -size.x, 0));
        sc8.addPageAnimation(new SCPositionAnimation(this, 1, -size.x, 0));
        mViewPager.addAnimation(sc8);

        SCViewAnimation sc9 = new SCViewAnimation(findViewById(R.id.cloud));
        sc9.startToPosition(size.x / 2 - dpToPx(100) + size.x, size.y / 7);
        sc9.addPageAnimation(new SCPositionAnimation(this, 1, -size.x, 0));
        sc9.addPageAnimation(new SCPositionAnimation(this, 2, 0, size.y));
        mViewPager.addAnimation(sc9);

        SCViewAnimation sc10 = new SCViewAnimation(findViewById(R.id.mobile));
        sc10.startToPosition(size.x / 2 - size.x, size.y * 6 / 7 - dpToPx(100));
        sc10.addPageAnimation(new SCPositionAnimation(this, 1, size.x, 0));
        sc10.addPageAnimation(new SCPositionAnimation(this, 2, 0, -size.y));
        mViewPager.addAnimation(sc10);

//        ((TextView)findViewById(R.id.text_2)).setTypeface(CoCoinUtil.getInstance().typefaceLatoLight);
        SCViewAnimation sc11 = new SCViewAnimation(findViewById(R.id.text_2));
        sc11.startToPosition(size.x, null);
        sc11.addPageAnimation(new SCPositionAnimation(this, 1, -size.x, 0));
        sc11.addPageAnimation(new SCPositionAnimation(this, 2, -size.x, 0));
        mViewPager.addAnimation(sc11);

        ImageView remind1 = (ImageView)findViewById(R.id.remind_1);
        remind1.getLayoutParams().width = size.x / 3;
        remind1.getLayoutParams().height = size.x / 3 * 653 / 320;
        SCViewAnimation sc12 = new SCViewAnimation(findViewById(R.id.remind_1));
        sc12.startToPosition(size.x / 2 - size.x, size.y / 11);
        sc12.addPageAnimation(new SCPositionAnimation(this, 2, size.x, 0));
        sc12.addPageAnimation(new SCPositionAnimation(this, 3, size.x, 0));
        mViewPager.addAnimation(sc12);

        ImageView remind2 = (ImageView)findViewById(R.id.remind_2);
        remind2.getLayoutParams().width = size.x / 3;
        remind2.getLayoutParams().height = size.x / 3 * 653 / 320;
        SCViewAnimation sc13 = new SCViewAnimation(findViewById(R.id.remind_2));
        sc13.startToPosition(size.x / 2 + size.x - size.x / 3, size.y * 10 / 11 - remind1.getLayoutParams().height);
        sc13.addPageAnimation(new SCPositionAnimation(this, 2, -size.x, 0));
        sc13.addPageAnimation(new SCPositionAnimation(this, 3, -size.x, 0));
        mViewPager.addAnimation(sc13);

//        ((TextView)findViewById(R.id.text_3)).setTypeface(CoCoinUtil.getInstance().typefaceLatoLight);
        SCViewAnimation sc14 = new SCViewAnimation(findViewById(R.id.text_3));
        sc14.startToPosition(size.x, null);
        sc14.addPageAnimation(new SCPositionAnimation(this, 2, -size.x, 0));
        sc14.addPageAnimation(new SCPositionAnimation(this, 3, -size.x, 0));
        mViewPager.addAnimation(sc14);

        View background = findViewById(R.id.background);
        SCViewAnimation backgroundAnimation = new SCViewAnimation(background);
        backgroundAnimation.startToPosition(null, -size.y - 100);
        backgroundAnimation.addPageAnimation(new SCPositionAnimation(this, 3, 0, size.y + 100));
        mViewPager.addAnimation(backgroundAnimation);

    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}

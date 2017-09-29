package com.lenovo.service.basicpubliclibrary.smallchart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;

public class BaseActivity extends AppCompatActivity {
    private boolean isShowRight;
    private int rightType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected float[][] points = new float[][]{{1,10}, {2,47}, {3,11}, {4,38}, {5,9},{6,52}, {7,14}, {8,37}, {9,29}, {10,31}};
    protected float[][] points2 = new float[][]{{1,52}, {2,13}, {3,51}, {4,20}, {5,19},{6,20}, {7,54}, {8,7}, {9,19}, {10,41}};
    protected int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    protected float pxTodp(float value){
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float valueDP= TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,metrics);
        return valueDP;
    }

    public void initToolBar(Toolbar toolbar, String name, boolean showHomeAsUp) {
        initToolBar(toolbar, name, showHomeAsUp, false);
    }

    public void initToolBar(Toolbar toolbar, String name, boolean showHomeAsUp, boolean isShowRight) {
        initToolBar(toolbar, name, showHomeAsUp, isShowRight, 0);
    }

    public void initToolBar(Toolbar toolbar, String name, boolean showHomeAsUp, boolean isShowRight, int rightType) {
        this.isShowRight = isShowRight;
        this.rightType = rightType;
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem;
        if (isShowRight) {
            getMenuInflater().inflate(R.menu.toolbar_right, menu);
            menuItem = menu.findItem(R.id.action_icon);
            switch (rightType) {
                case 1:
                    //清除缓存
                    menuItem.setTitle("清除缓存");
                    break;
                case 2:
                    //清除缓存
                    menuItem.setTitle("BLOG");
                    break;
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_icon:
                switch (rightType) {
                    case 0:
                        //通讯录添加Item动画
                        add();
                        break;
                    case 1:
                        //清除缓存
                        clearCache();
                        break;
                    case 2:
                        //打开webview
                        openWebview();
                        break;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openWebview() {

    }

    public void add() {
    }

    public void clearCache() {

    }

    /**
     * @param str 弹出的文字
     */
    public void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}

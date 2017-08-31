package com.lenovo.service.basicpubliclibrary.multitype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.multitype.bilibili.BilibiliActivity;
import com.lenovo.service.basicpubliclibrary.multitype.communicate_with_binder.SimpleActivity;
import com.lenovo.service.basicpubliclibrary.multitype.extra_apis.SeldomUsedApisPlayground;
import com.lenovo.service.basicpubliclibrary.multitype.multi_select.MultiSelectActivity;
import com.lenovo.service.basicpubliclibrary.multitype.normal.NormalActivity;
import com.lenovo.service.basicpubliclibrary.multitype.one2many.OneDataToManyActivity;
import com.lenovo.service.basicpubliclibrary.multitype.payload.TestPayloadActivity;
import com.lenovo.service.basicpubliclibrary.multitype.weibo.WeiboActivity;

/**
 * @author drakeet
 */
public class MenuBaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.NormalActivity:
                intent.setClass(this, NormalActivity.class);
                break;
            case R.id.MultiSelectActivity:
                intent.setClass(this, MultiSelectActivity.class);
                break;
            case R.id.communicate_with_binder:
                intent.setClass(this, SimpleActivity.class);
                break;
            case R.id.BilibiliActivity:
                intent.setClass(this, BilibiliActivity.class);
                break;
            case R.id.WeiboActivity:
                intent.setClass(this, WeiboActivity.class);
                break;
            case R.id.OneDataToManyActivity:
                intent.setClass(this, OneDataToManyActivity.class);
                break;
            case R.id.TestPayloadActivity:
                intent.setClass(this, TestPayloadActivity.class);
                break;
            case R.id.SeldomUsedApisPlayground:
                intent.setClass(this, SeldomUsedApisPlayground.class);
                break;
            default:
                return false;
        }
        startActivity(intent);
        this.finish();
        return true;
    }
}

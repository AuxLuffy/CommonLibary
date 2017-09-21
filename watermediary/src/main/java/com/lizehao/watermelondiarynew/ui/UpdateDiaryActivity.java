package com.lizehao.watermelondiarynew.ui;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lizehao.watermelondiarynew.R;
import com.lizehao.watermelondiarynew.R2;
import com.lizehao.watermelondiarynew.db.DiaryDatabaseHelper;
import com.lizehao.watermelondiarynew.utils.AppManager;
import com.lizehao.watermelondiarynew.utils.GetDate;
import com.lizehao.watermelondiarynew.utils.StatusBarCompat;
import com.lizehao.watermelondiarynew.widget.LinedEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.trity.floatingactionbutton.FloatingActionButton;
import cc.trity.floatingactionbutton.FloatingActionsMenu;

/**
 * Created by 李 on 2017/1/26.
 * 修改页面
 */
public class UpdateDiaryActivity extends AppCompatActivity {

    @BindView(R2.id.update_diary_tv_date)
    TextView mUpdateDiaryTvDate;
    @BindView(R2.id.update_diary_et_title)
    EditText mUpdateDiaryEtTitle;
    @BindView(R2.id.update_diary_et_content)
    LinedEditText mUpdateDiaryEtContent;
    @BindView(R2.id.update_diary_fab_back)
    FloatingActionButton mUpdateDiaryFabBack;
    @BindView(R2.id.update_diary_fab_add)
    FloatingActionButton mUpdateDiaryFabAdd;
    @BindView(R2.id.update_diary_fab_delete)
    FloatingActionButton mUpdateDiaryFabDelete;
    @BindView(R2.id.right_labels)
    FloatingActionsMenu mRightLabels;
    @BindView(R2.id.common_tv_title)
    TextView mCommonTvTitle;
    @BindView(R2.id.common_title_ll)
    LinearLayout mCommonTitleLl;
    @BindView(R2.id.common_iv_back)
    ImageView mCommonIvBack;
    @BindView(R2.id.common_iv_test)
    ImageView mCommonIvTest;
    @BindView(R2.id.update_diary_tv_tag)
    TextView mTvTag;

    private DiaryDatabaseHelper mHelper;

    // 修改页面
    public static void startActivity(Context context, String title, String content, String tag) {
        Intent intent = new Intent(context, UpdateDiaryActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.putExtra("tag", tag);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_diary);
        AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);
        mHelper = new DiaryDatabaseHelper(this, "Diary.db", null, 1);
        initTitle();
        StatusBarCompat.compat(this, Color.parseColor("#161414"));
        Intent intent = getIntent();
        // 设置显示的值
        mUpdateDiaryTvDate.setText("今天，" + GetDate.getDate());
        mUpdateDiaryEtTitle.setText(intent.getStringExtra("title"));
        mUpdateDiaryEtContent.setText(intent.getStringExtra("content"));
        mTvTag.setText(intent.getStringExtra("tag"));

    }

    private void initTitle() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mCommonTvTitle.setText("修改日记");
    }

    @OnClick({R2.id.common_iv_back, R2.id.update_diary_tv_date, R2.id.update_diary_et_title, R2.id.update_diary_et_content, R2.id.update_diary_fab_back, R2.id.update_diary_fab_add, R2.id.update_diary_fab_delete})
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.common_iv_back) {
            WatermelonDiayActivity.startActivity(this);


        } else if (i == R.id.update_diary_tv_date) {
        } else if (i == R.id.update_diary_et_title) {
        } else if (i == R.id.update_diary_et_content) {
            // 垃圾箱删除日记
        } else if (i == R.id.update_diary_fab_back) {
            android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("确定要删除该日记吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

//                      String title = mUpdateDiaryEtTitle.getText().toString();
                    String tag = mTvTag.getText().toString();
                    SQLiteDatabase dbDelete = mHelper.getWritableDatabase();
                    dbDelete.delete("Diary", "tag = ?", new String[]{tag});
                    WatermelonDiayActivity.startActivity(UpdateDiaryActivity.this);
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            }).show();

            // 添加日记
        } else if (i == R.id.update_diary_fab_add) {
            SQLiteDatabase dbUpdate = mHelper.getWritableDatabase();
            ContentValues valuesUpdate = new ContentValues();
            String title = mUpdateDiaryEtTitle.getText().toString();
            String content = mUpdateDiaryEtContent.getText().toString();
            valuesUpdate.put("title", title);
            valuesUpdate.put("content", content);
            dbUpdate.update("Diary", valuesUpdate, "title = ?", new String[]{title});
            dbUpdate.update("Diary", valuesUpdate, "content = ?", new String[]{content});
            WatermelonDiayActivity.startActivity(this);

            // 后退
        } else if (i == R.id.update_diary_fab_delete) {
            WatermelonDiayActivity.startActivity(this);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        WatermelonDiayActivity.startActivity(this);
    }
}
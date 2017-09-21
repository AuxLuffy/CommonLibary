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
import android.support.v7.app.AlertDialog;
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
 * 添加日记
 */
public class AddDiaryActivity extends AppCompatActivity {

    @BindView(R2.id.add_diary_tv_date)
    TextView mAddDiaryTvDate;
    @BindView(R2.id.add_diary_et_title)
    EditText mAddDiaryEtTitle;
    @BindView(R2.id.add_diary_et_content)
    LinedEditText mAddDiaryEtContent;
    @BindView(R2.id.add_diary_fab_back)
    FloatingActionButton mAddDiaryFabBack;
    @BindView(R2.id.add_diary_fab_add)
    FloatingActionButton mAddDiaryFabAdd;

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

    private DiaryDatabaseHelper mHelper;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AddDiaryActivity.class);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, String title, String content) {
        Intent intent = new Intent(context, AddDiaryActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);
        AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Intent intent = getIntent();
        mAddDiaryEtTitle.setText(intent.getStringExtra("title"));
        StatusBarCompat.compat(this, Color.parseColor("#161414"));

        mCommonTvTitle.setText("添加日记");
        mAddDiaryTvDate.setText("今天，" + GetDate.getDate());
        mAddDiaryEtContent.setText(intent.getStringExtra("content"));
        mHelper = new DiaryDatabaseHelper(this, "Diary.db", null, 1);
    }


    @OnClick({R2.id.common_iv_back, R2.id.add_diary_et_title, R2.id.add_diary_et_content, R2.id.add_diary_fab_back, R2.id.add_diary_fab_add})
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.common_iv_back) {
            WatermelonDiayActivity.startActivity(this);


        } else if (i == R.id.add_diary_et_title) {
        } else if (i == R.id.add_diary_et_content) {
        } else if (i == R.id.add_diary_fab_back) {
            String date = GetDate.getDate().toString();
            String tag = String.valueOf(System.currentTimeMillis());
            String title = mAddDiaryEtTitle.getText().toString() + "";
            String content = mAddDiaryEtContent.getText().toString() + "";
            if (!title.equals("") || !content.equals("")) {
                SQLiteDatabase db = mHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("date", date);
                values.put("title", title);
                values.put("content", content);
                values.put("tag", tag);
                db.insert("Diary", null, values);
                values.clear();
            }
            WatermelonDiayActivity.startActivity(this);

        } else if (i == R.id.add_diary_fab_add) {
            final String dateBack = GetDate.getDate().toString();
            final String titleBack = mAddDiaryEtTitle.getText().toString();
            final String contentBack = mAddDiaryEtContent.getText().toString();
            if (!titleBack.isEmpty() || !contentBack.isEmpty()) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("是否保存日记内容？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = mHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("date", dateBack);
                        values.put("title", titleBack);
                        values.put("content", contentBack);
                        db.insert("Diary", null, values);
                        values.clear();
                        WatermelonDiayActivity.startActivity(AddDiaryActivity.this);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        WatermelonDiayActivity.startActivity(AddDiaryActivity.this);
                    }
                }).show();
            } else {
                WatermelonDiayActivity.startActivity(this);
            }


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        WatermelonDiayActivity.startActivity(this);
    }
}












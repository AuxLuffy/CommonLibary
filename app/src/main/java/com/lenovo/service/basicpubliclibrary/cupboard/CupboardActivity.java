package com.lenovo.service.basicpubliclibrary.cupboard;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.cupboard.model.SimpleBook;
import com.lenovo.service.basicpubliclibrary.permissionDemo.other.ToastUtil;

import java.util.List;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class CupboardActivity extends Activity {
    protected CupboardSQLiteOpenHelper cupboardSQLiteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupboard);
        ButterKnife.bind(this);
        cupboardSQLiteOpenHelper = new CupboardSQLiteOpenHelper(this);
    }

    @OnClick(R.id.store_single_object)
    public void store_single_object() {
        SimpleBook simpleBook = new SimpleBook();
        simpleBook.title = "title_single_" + System.currentTimeMillis();
        cupboard().withDatabase(cupboardSQLiteOpenHelper.getWritableDatabase()).put(simpleBook);

        get_all_objects();
    }

    @OnClick(R.id.store_multi_objects)
    public void store_multi_objects() {
        for (int i = 0; i < 2; i++) {
            SimpleBook simpleBook = new SimpleBook();
            simpleBook.title = "title_multi_" + System.currentTimeMillis();
            cupboard().withDatabase(cupboardSQLiteOpenHelper.getWritableDatabase()).put(simpleBook);
        }

        get_all_objects();
    }

    @OnClick(R.id.get_single_object)
    public void get_single_object() {
        SimpleBook simpleBook = cupboard().withDatabase(cupboardSQLiteOpenHelper.getWritableDatabase())
                .query(SimpleBook.class).get();

    }

    @OnClick(R.id.get_all_objects)
    public void get_all_objects() {
        StringBuilder sb = new StringBuilder();
        List<SimpleBook> items = cupboard().withDatabase(cupboardSQLiteOpenHelper.getWritableDatabase()).query(SimpleBook.class).list();
        for (SimpleBook item : items) {
            sb.append(item);
        }

        ToastUtil.show(sb.toString());
    }

    @OnClick(R.id.update_single_object)
    public void update_single_object() {
        SimpleBook simpleBook = cupboard().withDatabase(cupboardSQLiteOpenHelper.getWritableDatabase())
                .query(SimpleBook.class).get();

        if (simpleBook != null) {
            ContentValues values = new ContentValues(1);
            values.put("title", "update_single_object");
            int updateCount = cupboard().withDatabase(cupboardSQLiteOpenHelper.getWritableDatabase())
                    .update(SimpleBook.class, values, "title=?", simpleBook.title);
        }

        get_all_objects();
    }

    @OnClick(R.id.update_all_objects)
    public void update_all_objects() {
        ContentValues values = new ContentValues(1);
        values.put("title", "update_all_objects");
        cupboard().withDatabase(cupboardSQLiteOpenHelper.getWritableDatabase()).update(SimpleBook.class, values);

        get_all_objects();
    }

    @OnClick(R.id.delete_single_object)
    public void delete_single_object() {
        SimpleBook simpleBook = cupboard().withDatabase(cupboardSQLiteOpenHelper.getWritableDatabase())
                .query(SimpleBook.class).get();

        if (simpleBook != null) {
            boolean ret = cupboard().withDatabase(cupboardSQLiteOpenHelper.getWritableDatabase())
                    .delete(SimpleBook.class, simpleBook._id);

        }

        get_all_objects();
    }

    @OnClick(R.id.delete_all_object)
    public void delete_all_object() {

        cupboard().withDatabase(cupboardSQLiteOpenHelper.getWritableDatabase())
                .delete(SimpleBook.class, null);

        get_all_objects();
    }
}
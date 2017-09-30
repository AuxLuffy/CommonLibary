package com.example.lenovo.gridview_drag.dao;

import android.content.ContentValues;

import com.example.lenovo.gridview_drag.bean.ChannelItem;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/9/30.
 */

public interface ChannelDaoInface {
    public boolean addCache(ChannelItem item);

    public boolean deleteCache(String whereClause, String[] whereArgs);

    public boolean updateCache(ContentValues values, String whereClause,
                               String[] whereArgs);

    public Map<String, String> viewCache(String selection,
                                         String[] selectionArgs);

    public List<Map<String, String>> listCache(String selection,
                                               String[] selectionArgs);

    public void clearFeedTable();
}

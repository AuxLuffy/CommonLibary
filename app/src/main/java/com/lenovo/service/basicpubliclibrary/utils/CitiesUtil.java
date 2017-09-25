package com.lenovo.service.basicpubliclibrary.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lenovo.service.basicpubliclibrary.recyclerview.suspension.bean.City;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @description: 获取城市列表
 * @author:袁东华
 * @time:2017/9/23 下午4:06
 */
public class CitiesUtil {

    private static CitiesUtil instance;
    private City city;

    public static CitiesUtil getInstance() {
        if (instance == null) {
            synchronized (CitiesUtil.class) {
                if (instance == null) {
                    instance = new CitiesUtil();
                }
            }
        }
        return instance;
    }

    private CitiesUtil() {
    }

    /**
     * 获取城市列表
     *
     * @param context
     * @return
     */
    public City getCities(Context context) {

        if (city == null) {

            try {
                InputStream inputStream = context.getAssets().open("cities.txt");
                int available = inputStream.available();
                byte[] bytes = new byte[available];
                int read = inputStream.read(bytes);
                String cities = new String(bytes, "utf-8");
                Gson g = new Gson();
                city = g.fromJson(cities, City.class);


            } catch (IOException e) {
               LogUtil.e("异常:"+e.getMessage());
            }


        }

        return city;
    }


}

package com.lenovo.service.basicpubliclibrary.headerfloatanimation.tools;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.service.basicpubliclibrary.headerfloatanimation.adapter.GridLayoutRecyclerViewAdapter;
import com.lenovo.service.basicpubliclibrary.headerfloatanimation.mode.HomePageMode;
import com.lenovo.service.basicpubliclibrary.headerfloatanimation.mode.HomePageModeMapping;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by shengtao on 2016/4/20.
 */
public class Utils {
    /**
     * 数据映射
     *
     * @param homePageMode
     * @return
     */
    public static ArrayList<HomePageModeMapping> executeHomePageModeMapping(HomePageMode homePageMode) {
        ArrayList<HomePageModeMapping> mHomePageModeMappings = new ArrayList<HomePageModeMapping>();

        for (int i = 0; i < homePageMode.getCategory_one().size(); i++) {
            // 第一个数组下
            HomePageMode.CategoryOne categoryOne = homePageMode.getCategory_one().get(i);
            //1、header的情况要单独处理。header整体作为第一条，因为header中的viewpager没必要缓存，并且整体作为RecycleView的一条完整的item
            if (categoryOne.type == GridLayoutRecyclerViewAdapter.TYPE_HEADER) {
                HomePageModeMapping homePageModeMapping = new HomePageModeMapping();
                homePageModeMapping.setDiscription("");
                homePageModeMapping.setImage_url("");
                homePageModeMapping.setUrl("");
                homePageModeMapping.setType(GridLayoutRecyclerViewAdapter.TYPE_HEADER);
                homePageModeMapping.setBanner(categoryOne.getCategory_two());
                mHomePageModeMappings.add(homePageModeMapping);
            }
            //2、非header的情况，需要缓存
            else {
                for (int j = 0; j < categoryOne.getCategory_two().size(); j++) {
                    //2.1,人为造的title部分每次要填充的数据
                    //情况1、逢第1条之前，人为先造一条title的数据，type为1
                    if (0 == j) {
                        HomePageModeMapping homePageModeMapping = new HomePageModeMapping();
                        homePageModeMapping.setType(GridLayoutRecyclerViewAdapter.TYPE_TITLE);
                        homePageModeMapping.setRootIndexInOriginData(i);
                        mHomePageModeMappings.add(homePageModeMapping);
                    }
                    //2.2,grid格子里的item，情况2、第二个数组下
                    //设置CategoryTwo类的变量
                    HomePageModeMapping homePageModeMapping = new HomePageModeMapping();
                    HomePageMode.CategoryOne.CategoryTwo categoryTwo = categoryOne.getCategory_two().get(j);
                    homePageModeMapping.setDiscription(categoryTwo.getDiscription());
                    homePageModeMapping.setImage_url(categoryTwo.getImage_url());
                    homePageModeMapping.setUrl(categoryTwo.getUrl());
                    //设置CategoryTwo子类HomePageModeMapping类
                    homePageModeMapping.setRootIndexInOriginData(i);
                    homePageModeMapping.setType(categoryOne.getType());
                    mHomePageModeMappings.add(homePageModeMapping);
                }
            }
        }
        return mHomePageModeMappings;
    }

    /**
     * 方法1、
     * 最古老的方法，通过实现指定Mode类，调用来实现转换TypeToken.getType来实现转换
     *
     * @param json
     * @return
     */
    public static HomePageMode jsonStringToModel(String json) {
        Type typeOfObject = new TypeToken<HomePageMode>() {
        }.getType();
        HomePageMode persnModel = new Gson().fromJson(json, typeOfObject);
        return persnModel;
    }

    /**
     * 方法2,通过调用Gson库自带的fromJson(String json, Type typeOfT)，将泛型的Class强转为Type
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T jsonStringToModelByType(String json, Class<T> cls) {
        T t = null;
        //Type typeOfObject=new TypeToken<T>(){}.getType();
        //此处调用的是fromJson(String json, Class<T> classOfT)
        t = new Gson().fromJson(json, (Type) cls);
        //还可以调用fromJson(String json, Type typeOfT)，
        //t= new Gson().fromJson(json,(Type)cls);
        return t;

    }

    /**
     * 方法3，通过调用Gson库自带的方法fromJson，传入泛型的Class
     *
     * @param result
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJsonStringToModeByClass(String result, Class<T> clazz) {
        if (result == null) {
            return null;
        }
        try {
            return new Gson().fromJson(result, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取Asset目录下的文件，转换为String
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String readAsssetText(Context context, String fileName) {
        String jsonString = "";
        AssetManager manager = context.getAssets();
        try {
            InputStream inputStream = manager.open(fileName);
            jsonString = changeStream2String(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    /**
     * 将inputStream转为String
     *
     * @param inputStream
     * @return
     */

    public static String changeStream2String(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String result = null;
        int len = 0;
        byte[] buffer = new byte[4 * 1024];
        try {
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
            result = new String(outputStream.toByteArray(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
//    public static void loadImage(String url, ImageView mImageView) {
//        loadImage(url, mImageView, R.drawable.ic_launcher);
//    }

//    /**
//     * 显示图片，defImgId 默认图片资源id
//     **/
//    public static void loadImage(String url, ImageView mImageView, int defImgId) {
//        DisplayImageOptions.Builder builder = ImageLoaderManager.getDisplayImageOptionsBuilder();
//        builder.showImageOnFail(defImgId);
//        builder.showImageOnLoading(defImgId);
//        builder.showImageForEmptyUri(defImgId);
//        ImageLoaderManager.getInstance().displayImage(url, mImageView, builder.build(), new ImageLoadingListener() {
//            @Override
//            public void onLoadingStarted(String imageUri, View view) {
////                Logger.e("LoadImage","onLoadingStarted loadImage: "+imageUri);
//                if (view != null) {
//                    ((ImageView) view).setScaleType(ImageView.ScaleType.CENTER);
//                }
//            }
//
//            @Override
//            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//              /*  if (failReason != null) {
//                    Logger.e("LoadImage", "onLoadingFailed loadImage: " + imageUri + " fail reason " + failReason.getType());
//                } else {
//                 //   Logge*/r.e("LoadImage", "onLoadingFailed loadImage: " + imageUri);
//               // }
//            }
//
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
////                Logger.e("LoadImage", "onLoadingComplete loadImage: " + imageUri);
//                if (loadedImage != null && view != null)
//                    ((ImageView) view).setScaleType(ImageView.ScaleType.CENTER_CROP);
//            }
//
//        @Override
//        public void onLoadingCancelled(String imageUri, View view) {
//            //Logger.e("LoadImage", "onLoadingCancelled loadImage: " + imageUri);
//        }
//    });
//    }

}

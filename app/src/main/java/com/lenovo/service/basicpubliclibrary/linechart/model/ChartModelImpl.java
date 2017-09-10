package com.lenovo.service.basicpubliclibrary.linechart.model;


import com.lenovo.service.basicpubliclibrary.linechart.common.OnRequestCallback;

import java.util.Random;

/**
 * Created by issuser on 2016/12/15.
 */

public class ChartModelImpl implements IChartModel {

    /**
     * 网络请求，此处设置假数据
     * @param callback
     */
    @Override
    public void requestData(final OnRequestCallback<UploadRecord> callback) {

        UploadRecord uploadRecord = new UploadRecord();

        UploadRecord.SevendaysBean.PushBean pushBean = new UploadRecord.SevendaysBean.PushBean(getRandomNum(),getRandomNum(),getRandomNum(),getRandomNum(),getRandomNum(),getRandomNum(),getRandomNum());

        UploadRecord.SevendaysBean.SaleBean saleBean = new UploadRecord.SevendaysBean.SaleBean(getRandomNum(),getRandomNum(),getRandomNum(),getRandomNum(),getRandomNum(),getRandomNum(),getRandomNum());

        UploadRecord.SevendaysBean.UserBean userBean = new UploadRecord.SevendaysBean.UserBean(getRandomNum(),getRandomNum(),getRandomNum(),getRandomNum(),getRandomNum(),getRandomNum(),getRandomNum());

        UploadRecord.SevendaysBean sevendaysBean = new UploadRecord.SevendaysBean(saleBean,userBean,pushBean);

        uploadRecord.setSevendays(sevendaysBean);

        callback.onSuccess(uploadRecord);

    }

    @Override
    public int getRandomNum() {

        int max = 900;

        int min = 0;

        Random random = new Random();

        int randomnum = random.nextInt(max)%(max-min+1) + min;

        return randomnum;
    }

}

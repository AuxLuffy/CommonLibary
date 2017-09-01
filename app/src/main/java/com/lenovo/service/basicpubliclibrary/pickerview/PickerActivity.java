package com.lenovo.service.basicpubliclibrary.pickerview;


import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.lenovo.service.basicpubliclibrary.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author zhaotong
 * @time 2017-09-01 01:29
 * 本演示类推荐一个通用的条件选择框，仿iOS滚轮，简单介绍一下基本用法
 * gradle文件中引入 compile 'com.contrarywind:Android-PickerView:3.2.6' 即可
 * 若想查看更加详细的使用信息，可以参考地址
 * https://github.com/Bigkoo/Android-PickerView
 */
public class PickerActivity extends AppCompatActivity {
    private List<String> listChars;//一级菜单数据
    private List<String> listHeight, listWeight;//二级无联动数据
    private List<String> list1;//三级联动数据
    private List<List<String>> list2;
    private List<List<List<String>>> list3;
    protected String mCurrentProviceName;
    protected String mCurrentCityName;
    protected String mCurrentDistrictName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
        setData();
    }

    private void setData() {
        listChars = new ArrayList<>();
        listHeight = new ArrayList<>();
        listWeight = new ArrayList<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        boolean stop = false;
        int count = 0;
        //list1 赋值
        while (!stop) {
            listChars.add("A" + count);
            count++;
            if (count == 10) {
                stop = true;
                count = 0;
            }
        }
        //list2 赋值
        for (int h = 150; h < 220; h++) {
            listHeight.add(h + "cm");
        }
        for (int w = 30; w < 150; w++) {
            listWeight.add(w + "kg");
        }
        //list3 赋值
        readAddrDatas(this);
    }

    /**
     * 时间选择框弹出
     *
     * @param view
     */
    public void showTimePicker(View view) {
        TimePickerView timePickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                showToast(date.toString());
            }
        }).setSubmitText("确定")
                .setCancelText("取消").build();
        timePickerView.setDate(Calendar.getInstance());
        timePickerView.show();
    }

    /**
     * 单一选项条件选择
     *
     * @param view
     */
    public void showSingleOption(View view) {
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                showToast(listChars.get(options1));
            }
        }).setCancelText("取消").setSubmitText("确定").build();
        optionsPickerView.setPicker(listChars);
        optionsPickerView.show();
    }

    /**
     * 无联动条件选择
     *
     * @param view
     */
    public void showNoBindOption(View view) {
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                showToast(listHeight.get(options1) + listWeight.get(options2));
            }
        }).setLabels("身高", "体重", null).setCancelText("取消").setSubmitText("确定").build();
        optionsPickerView.setNPicker(listHeight, listWeight, null);
        optionsPickerView.show();
    }

    /**
     * 三级联动条件选择
     *
     * @param view
     */
    public void showBindOption(View view) {
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                showToast(list1.get(options1) + list2.get(options1).get(options2) + list3.get(options1).get(options2).get(options3));
            }
        }).setCancelText("取消").setSubmitText("确定").build();
        optionsPickerView.setPicker(list1, list2, list3);
        optionsPickerView.show();
    }

    private void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    protected boolean readAddrDatas(Context context) {
        List<ProvinceInfoModel> provinceList = null;
        AssetManager asset = context.getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();
            AddrXmlParser handler = new AddrXmlParser();
            parser.parse(input, handler);
            input.close();
            provinceList = handler.getDataList();
            if (provinceList != null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityInfoModel> cityList = provinceList.get(0).getCityList();
                if (cityList != null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictInfoModel> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                }
            }
            for (int i = 0; i < provinceList.size(); i++) {
                list1.add(provinceList.get(i).getName());
                List<CityInfoModel> cityList = provinceList.get(i).getCityList();
                List<List<String>> dist = new ArrayList<>();
                ArrayList<String> cityNames = new ArrayList<String>();
                for (int j = 0; j < cityList.size(); j++) {
                    cityNames.add(cityList.get(j).getName());
                    List<DistrictInfoModel> districtList = cityList.get(j).getDistrictList();
                    ArrayList<String> distrinctNameArray = new ArrayList<String>();
                    DistrictInfoModel[] distrinctArray = new DistrictInfoModel[districtList.size()];
                    for (int k = 0; k < districtList.size(); k++) {
                        DistrictInfoModel districtModel = new DistrictInfoModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray.add(districtModel.getName());
                    }
                    dist.add(distrinctNameArray);
                }
                list2.add(cityNames);
                list3.add(dist);
            }
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }
}

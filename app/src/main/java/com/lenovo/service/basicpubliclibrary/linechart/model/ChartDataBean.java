package com.lenovo.service.basicpubliclibrary.linechart.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cx on 2016/3/16.
 */
public class ChartDataBean implements Parcelable {
    private int one;
    private int two;
    private int three;
    private int four;
    private int five;
    private int six;
    private int seven;
    private int last_month_num;
    private int month_num;
    private int last_year_num;
    private int year_num;

    protected ChartDataBean(Parcel in) {
        one = in.readInt();
        two = in.readInt();
        three = in.readInt();
        four = in.readInt();
        five = in.readInt();
        six = in.readInt();
        seven = in.readInt();
        last_month_num = in.readInt();
        month_num = in.readInt();
        last_year_num = in.readInt();
        year_num = in.readInt();
    }

    public static final Creator<ChartDataBean> CREATOR = new Creator<ChartDataBean>() {
        @Override
        public ChartDataBean createFromParcel(Parcel in) {
            return new ChartDataBean(in);
        }

        @Override
        public ChartDataBean[] newArray(int size) {
            return new ChartDataBean[size];
        }
    };

    public int getOne() {
        return one;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    public int getThree() {
        return three;
    }

    public void setThree(int three) {
        this.three = three;
    }

    public int getFour() {
        return four;
    }

    public void setFour(int four) {
        this.four = four;
    }

    public int getFive() {
        return five;
    }

    public void setFive(int five) {
        this.five = five;
    }

    public int getSix() {
        return six;
    }

    public void setSix(int six) {
        this.six = six;
    }

    public int getSeven() {
        return seven;
    }

    public void setSeven(int seven) {
        this.seven = seven;
    }

    public int getLast_month_num() {
        return last_month_num;
    }

    public void setLast_month_num(int last_month_num) {
        this.last_month_num = last_month_num;
    }

    public int getMonth_num() {
        return month_num;
    }

    public void setMonth_num(int month_num) {
        this.month_num = month_num;
    }

    public int getLast_year_num() {
        return last_year_num;
    }

    public void setLast_year_num(int last_year_num) {
        this.last_year_num = last_year_num;
    }

    public int getYear_num() {
        return year_num;
    }

    public void setYear_num(int year_num) {
        this.year_num = year_num;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(one);
        dest.writeInt(two);
        dest.writeInt(three);
        dest.writeInt(four);
        dest.writeInt(five);
        dest.writeInt(six);
        dest.writeInt(seven);
        dest.writeInt(last_month_num);
        dest.writeInt(month_num);
        dest.writeInt(last_year_num);
        dest.writeInt(year_num);
    }
}

package com.lenovo.service.basicpubliclibrary.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xuxiaowei on 2017/9/28.
 */

public class MessageX implements Parcelable {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
    }

    public MessageX(String text) {
        this.text = text;
    }

    public MessageX(Parcel in) {
        this.text = in.readString();
    }

    public static final Creator<MessageX> CREATOR = new Creator<MessageX>() {
        @Override
        public MessageX createFromParcel(Parcel source) {
            return new MessageX(source);
        }

        @Override
        public MessageX[] newArray(int size) {
            return new MessageX[size];
        }
    };

    public void readFromParcel(Parcel dest){
        text = dest.readString();
    }
}

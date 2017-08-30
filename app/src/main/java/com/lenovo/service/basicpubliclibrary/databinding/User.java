package com.lenovo.service.basicpubliclibrary.databinding;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/8/30.
 */
public class User implements Parcelable {
    public String lastName;
    public String firstName;
    public int age;


    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.lastName);
        dest.writeString(this.firstName);
        dest.writeInt(this.age);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.lastName = in.readString();
        this.firstName = in.readString();
        this.age = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

package com.lenovo.service.basicpubliclibrary.sugar.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import static android.R.attr.author;

/**
 * Created by xuxiaowei on 2017/9/14.
 */

public class Book extends SugarRecord implements Parcelable {
    private String isbn;
    private String title;
    private String edition;

    // Default constructor is necessary for SugarRecord
    public Book() {

    }

    public Book(String isbn, String title, String edition) {
        this.isbn = isbn;
        this.title = title;
        this.edition = edition;
    }

    protected Book(Parcel in) {
        isbn = in.readString();
        title = in.readString();
        edition = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getEdition() {
        return edition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(isbn);
        parcel.writeString(title);
        parcel.writeString(edition);
    }
}

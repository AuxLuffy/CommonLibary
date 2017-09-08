package com.lenovo.service.basicpubliclibrary.tagviewgroup;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by tangrenmei on 2017/9/8.
 */

public class ImageLoader {
    public static void loadImage(String url, ImageView imageView) {
        Picasso.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}

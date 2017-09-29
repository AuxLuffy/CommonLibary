package com.example.lenovo.cutphoto.utils;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.lenovo.cutphoto.R;
import com.example.lenovo.cutphoto.dialog.RxDialog;

/**
 * Created by lenovo on 2017/9/29.
 */

public class RxImageUtils {

    /**
     * 显示大图
     *
     * @param context
     * @param uri     图片的Uri
     */
    public static void showBigImageView(Context context, Uri uri) {
        final RxDialog rxDialog = new RxDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.image, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialog.cancel();
            }
        });
        ImageView imageView = (ImageView) view.findViewById(R.id.page_item);

        imageView.setImageURI(uri);

        rxDialog.setContentView(view);
        rxDialog.show();
        rxDialog.setFullScreen();
    }

}

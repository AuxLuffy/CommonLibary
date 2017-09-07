package com.lenovo.service.basicpubliclibrary.jike;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.lenovo.service.basicpubliclibrary.R;

import java.util.ArrayList;
import java.util.List;


public class GalleryView extends FrameLayout {

    private Gallery mGallery;
    private TitleView mTitleView;

    public GalleryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GalleryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View.inflate(getContext(), R.layout.gallery_view, this);
        mGallery = (Gallery) findViewById(R.id.gallery);
        mTitleView = (TitleView) findViewById(R.id.title_view);
    }

    public void startSmooth() {
        mGallery.startSmooth();
        mTitleView.startSmooth();
    }

    public void addGalleryData(List<GalleryEntity> listEntities) {
        List<String> imgList = new ArrayList<>();
        for (GalleryEntity entity : listEntities) {
            imgList.add(entity.imgUrl);
        }
        mGallery.setImgList(imgList);

        List<String> titleList = new ArrayList<>();
        for (GalleryEntity entity : listEntities) {
            titleList.add(entity.title);
        }
        mTitleView.setTitleList(titleList);
    }

}

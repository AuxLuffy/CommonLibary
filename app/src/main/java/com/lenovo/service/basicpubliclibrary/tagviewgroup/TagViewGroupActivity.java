package com.lenovo.service.basicpubliclibrary.tagviewgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.MainActivity;
import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.tagviewgroup.utils.DataRepo;
import com.lenovo.service.basicpubliclibrary.tagviewgroup.view.ITagView;
import com.lenovo.service.basicpubliclibrary.tagviewgroup.view.TagImageView;
import com.lenovo.service.basicpubliclibrary.tagviewgroup.view.TagTextView;
import com.lenovo.service.basicpubliclibrary.tagviewgroup.view.TagViewGroup;

/**
 * Created by tangrenmei on 2017/9/8.
 */

public class TagViewGroupActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CREATE_TAG = 0x001;

    private TagImageView mTagImageView;
    private Button mButton;
    private Button mListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tagview);
        DataRepo.initData();
        mTagImageView = (TagImageView) findViewById(R.id.tagImageView);
        mButton = (Button) findViewById(R.id.transButton);
        mListBtn = (Button) findViewById(R.id.listBtn);
        mTagImageView.setImageUrl("http://ci.xiaohongshu.com/0c62c1d9-8183-4410-82cf-80492b88fdad@r_1280w_1280h.jpg");
        mTagImageView.setTagGroupClickListener(mTagGroupClickListener);
        mTagImageView.setTagList(DataRepo.tagGroupList);
        mTagImageView.setOnClickListener(this);
        mButton.setOnClickListener(this);
        mListBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tagImageView:
                mTagImageView.excuteTagsAnimation();
                break;
            case R.id.transButton:
                startActivityForResult(new Intent(TagViewGroupActivity.this, TagEditActivity.class), CREATE_TAG);
                break;
            case R.id.listBtn:
                startActivity(new Intent(TagViewGroupActivity.this, TagListActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CREATE_TAG) {
            mTagImageView.setTagList(DataRepo.tagGroupList);
        }
    }

    private TagViewGroup.OnTagGroupClickListener mTagGroupClickListener = new TagViewGroup.OnTagGroupClickListener() {
        @Override
        public void onCircleClick(TagViewGroup container) {
            Toast.makeText(TagViewGroupActivity.this, "点击中心圆", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onTagClick(TagViewGroup container, ITagView tag, int position) {
            Toast.makeText(TagViewGroupActivity.this, "点击Tag->" + ((TagTextView) tag).getText().toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLongPress(final TagViewGroup group) {
            Toast.makeText(TagViewGroupActivity.this, "点击中心圆", Toast.LENGTH_SHORT).show();
        }
    };
}

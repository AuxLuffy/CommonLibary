package com.lenovo.service.basicpubliclibrary.tagviewgroup.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.tagviewgroup.TagGroupModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangrenmei on 2017/9/8.
 */

public class TagEditDialog extends Dialog implements View.OnClickListener {

    private EditText tag01, tag02, tag03;
    private Button cancel, confirm;
    private OnTagEditDialogClickListener listener;

    public TagEditDialog(@NonNull Context context, OnTagEditDialogClickListener listener) {
        super(context);
        setContentView(R.layout.dialog_tag_edit);
        this.listener = listener;
        tag01 = (EditText) findViewById(R.id.tag01);
        tag02 = (EditText) findViewById(R.id.tag02);
        tag03 = (EditText) findViewById(R.id.tag03);
        cancel = (Button) findViewById(R.id.cancelBtn);
        confirm = (Button) findViewById(R.id.confirmBtn);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    public TagGroupModel createTagGroup() {
        TagGroupModel model = new TagGroupModel();
        List<TagGroupModel.Tag> tagList = new ArrayList<>();
        String str01 = tag01.getText().toString();
        String str02 = tag02.getText().toString();
        String str03 = tag03.getText().toString();
        if (!TextUtils.isEmpty(str01)) {
            TagGroupModel.Tag tag = new TagGroupModel.Tag();
            tag.setName(str01);
            tagList.add(tag);
        }
        if (!TextUtils.isEmpty(str02)) {
            TagGroupModel.Tag tag = new TagGroupModel.Tag();
            tag.setName(str02);
            tagList.add(tag);
        }
        if (!TextUtils.isEmpty(str03)) {
            TagGroupModel.Tag tag = new TagGroupModel.Tag();
            tag.setName(str03);
            tagList.add(tag);
        }
        setTagDirection(tagList);
        model.getTags().addAll(tagList);
        model.setPercentX(0.5f);
        model.setPercentY(0.5f);
        return model;
    }

    private void setTagDirection(List<TagGroupModel.Tag> tagList) {
        switch (tagList.size()) {
            case 3:
                tagList.get(0).setDirection(DIRECTION.RIGHT_TOP.getValue());
                tagList.get(1).setDirection(DIRECTION.RIGHT_CENTER.getValue());
                tagList.get(2).setDirection(DIRECTION.RIGHT_BOTTOM.getValue());
                break;
            case 2:
                tagList.get(0).setDirection(DIRECTION.RIGHT_TOP.getValue());
                tagList.get(1).setDirection(DIRECTION.RIGHT_BOTTOM.getValue());
                break;
            case 1:
                tagList.get(0).setDirection(DIRECTION.RIGHT_CENTER.getValue());
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelBtn:
                listener.onCancel();
                break;
            case R.id.confirmBtn:
                listener.onTagGroupCreated(createTagGroup());
                break;
        }
    }

    public interface OnTagEditDialogClickListener {
        void onCancel();

        void onTagGroupCreated(TagGroupModel group);
    }
}


package com.lenovo.service.basicpubliclibrary.tagviewgroup.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.tagviewgroup.ImageLoader;
import com.lenovo.service.basicpubliclibrary.tagviewgroup.TagGroupModel;
import com.lenovo.service.basicpubliclibrary.tagviewgroup.utils.AnimatorUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangrenmei on 2017/9/8.
 */

public class TagImageView extends FrameLayout {

    private ImageView mImageView;
    private FrameLayout mContentLayout;
    private List<TagGroupModel> mTagGroupModelList = new ArrayList<>();
    private List<TagViewGroup> mTagGroupViewList = new ArrayList<>();
    private TagViewGroup.OnTagGroupClickListener mClickListener;
    private TagViewGroup.OnTagGroupDragListener mDragListener;
    private boolean mIsEditMode;
    private int num;

    public TagImageView(Context context) {
        this(context, null);
    }

    public TagImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.layout_tag_imageview, this, true);
        mImageView = (ImageView) rootView.findViewById(R.id.imageview);
        mContentLayout = (FrameLayout) rootView.findViewById(R.id.tagsGroup);
    }

    public void setTagList(List<TagGroupModel> tagGroupList) {
        clearTags();
        for (TagGroupModel model : tagGroupList) {
            addTagGroup(model);
        }
    }

    public void setTag(TagGroupModel tagGroupModel) {
        clearTags();
        addTagGroup(tagGroupModel);
    }

    public void addTagGroup(TagGroupModel model) {
        mTagGroupModelList.add(model);
        TagViewGroup tagViewGroup = getTagViewGroup(model);
        tagViewGroup.setOnTagGroupClickListener(mClickListener);
        tagViewGroup.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mContentLayout.addView(tagViewGroup);
        mTagGroupViewList.add(tagViewGroup);
    }

    public void clearTags() {
        mTagGroupModelList.clear();
        mContentLayout.removeAllViews();
        mTagGroupViewList.clear();
    }

    public TagViewGroup getTagViewGroup(final TagGroupModel model) {
        TagViewGroup tagViewGroup = new TagViewGroup(getContext());
        if (!mIsEditMode) {
            setTagGroupAnimation(tagViewGroup);
        } else {
            tagViewGroup.setOnTagGroupDragListener(mDragListener);
        }
        tagViewGroup.setTagAdapter(new TagAdapter() {
            @Override
            public int getCount() {
                return model.getTags().size();
            }

            @Override
            public ITagView getItem(int position) {
                return makeTagTextView(model.getTags().get(position));
            }
        });
        tagViewGroup.setPercent(model.getPercentX(), model.getPercentY());
        return tagViewGroup;
    }

    public TagTextView makeTagTextView(TagGroupModel.Tag tag) {
        TagTextView tagTextView = new TagTextView(getContext());
        tagTextView.setDirection(DIRECTION.valueOf(tag.getDirection()));
        tagTextView.setText(tag.getName());
        return tagTextView;
    }

    public void setTagGroupAnimation(final TagViewGroup group) {
        Animator showAnimator = AnimatorUtils.getTagShowAnimator(group);
        showAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                group.setVisibility(VISIBLE);
            }
        });
        Animator hideAnimator = AnimatorUtils.getTagHideAnimator(group);
        hideAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                group.setVisibility(INVISIBLE);
            }
        });
        group.setHideAnimator(hideAnimator).setShowAnimator(showAnimator).addRipple();
    }

    public void excuteTagsAnimation() {
        for (TagViewGroup tagViewGroup : mTagGroupViewList) {
            if (tagViewGroup.getVisibility() == INVISIBLE) {
                tagViewGroup.startShowAnimator();
            } else {
                tagViewGroup.startHideAnimator();
            }
        }
    }

    public List<TagGroupModel> getTagGroupModels() {
        return mTagGroupModelList;
    }

    public void onTagClicked(TagViewGroup group, ITagView tagView, int position) {
        mTagGroupModelList.get(mTagGroupViewList.indexOf(group)).getTags().get(position).setDirection(tagView.getDirection().getValue() % 10 + 1);
        group.getTagAdapter().notifyDataSetChanged();
    }

    public void onDrag(TagViewGroup group, float percentX, float percentY) {
        mTagGroupModelList.get(mTagGroupViewList.indexOf(group)).setPercentX(percentX);
        mTagGroupModelList.get(mTagGroupViewList.indexOf(group)).setPercentY(percentY);
    }

    public void setEditMode(boolean editMode) {
        mIsEditMode = editMode;
    }

    public void setImageUrl(String url) {
        ImageLoader.loadImage(url, mImageView);
    }

    public void removeTagGroup(TagViewGroup tagViewGroup) {
        mContentLayout.removeView(tagViewGroup);
    }

    public void setTagGroupClickListener(TagViewGroup.OnTagGroupClickListener tagGroupClickListener) {
        mClickListener = tagGroupClickListener;
    }

    public void setTagGroupScrollListener(TagViewGroup.OnTagGroupDragListener scrollListener) {
        mDragListener = scrollListener;
    }
}


package com.lenovo.service.basicpubliclibrary.scratchview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.scratchview.customview.ScratchTextView;


/**
 * Created by xuxiaowei on 2017/9/1.
 */

public class DemoClothingActivity extends AppCompatActivity {

    private TextView mScratchTitleView;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing);

        mScratchTitleView = (TextView) findViewById(R.id.scratch_title_text);
        ScratchTextView scratchTextView = (ScratchTextView) findViewById(R.id.scratch_view);

        if(scratchTextView != null) {
            scratchTextView.setRevealListener(new ScratchTextView.IRevealListener() {
                @Override
                public void onRevealed(ScratchTextView tv) {
                    showPrice();
                    mScratchTitleView.setText(R.string.flat_200_offer);
                }

                @Override
                public void onRevealPercentChangedListener(ScratchTextView stv, float percent) {
                    // on percent reveal.
                }
            });
        }

    }

    /**
     * Reveals the discounted price.
     */
    private void showPrice() {
        View priceBeforeView = findViewById(R.id.price_before_text);
        View priceAfterText = findViewById(R.id.price_after_text);
        View priceContainer = findViewById(R.id.price_container);
        FlipAnimator animator = new FlipAnimator(priceBeforeView, priceAfterText, priceContainer.getWidth()/2, priceContainer.getHeight()/2);
        animator.setDuration(800);
        animator.setRotationDirection(FlipAnimator.DIRECTION_Y);
        priceContainer.startAnimation(animator);
    }


}


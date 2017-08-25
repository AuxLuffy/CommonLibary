package com.lenovo.service.basicpubliclibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lenovo.service.basicpubliclibrary.AppStartGuide.AppStartGuide;
import com.lenovo.service.basicpubliclibrary.questionnaire.QuestionActivity;
import com.lenovo.service.basicpubliclibrary.scoringstrip.ScoringStripActivity;
import com.lenovo.service.basicpubliclibrary.smilepraiseview.SmilePraiseActivity;

public class WidgetActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mText1;
    private TextView smileview;
    private TextView loaddata;
    private TextView mTvRatingbar;
    private TextView mTextQuestionnaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
        init_view();
        init_listener();
    }



    private void init_view() {
        mText1 = (TextView) findViewById(R.id.text1);
        smileview=(TextView) findViewById(R.id.smileview);
        loaddata =(TextView) findViewById(R.id.loaddata);
        mTvRatingbar = (TextView) findViewById(R.id.tv_ratingbar);
        mTextQuestionnaire=(TextView) findViewById(R.id.questionnaire);
    }

    private void init_listener() {
        mText1.setOnClickListener(this);
        smileview.setOnClickListener(this);
        mTvRatingbar.setOnClickListener(this);
        mTextQuestionnaire.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.text1:
                Intent intent=new Intent();
                intent.setClass(WidgetActivity.this,
                        AppStartGuide.class);
                startActivity(intent);
                break;

            case R.id.smileview:
                Intent intent2=new Intent();
                intent2.setClass(WidgetActivity.this,
                        SmilePraiseActivity.class);
                startActivity(intent2);
                break;

            case R.id.tv_ratingbar:
                Intent intent3 = new Intent();
                intent3.setClass(WidgetActivity.this,
                        ScoringStripActivity.class);
                startActivity(intent3);
                break;
            case R.id.questionnaire:
                Intent intent4=new Intent();
                intent4.setClass(WidgetActivity.this,
                        QuestionActivity.class);
                startActivity(intent4);
                break;
            case R.id.loaddata:
                Intent intent3=new Intent();
                intent3.setClass(WidgetActivity.this,
                        LoadDataActivity.class);
                startActivity(intent3);
                break;
        }
    }
}

package com.lenovo.service.basicpubliclibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lenovo.service.basicpubliclibrary.jobservice.JobServiceActivity;
import com.lenovo.service.basicpubliclibrary.jobservice.MyJobService;

public class OptimizeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimize);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.job_service:
                Intent intent=new Intent(this, JobServiceActivity.class);
                startActivity(intent);
                break;
        }
    }
}

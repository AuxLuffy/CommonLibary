package com.lenovo.service.basicpubliclibrary.addsub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;

public class AddSubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub);
        AddSubUtils addSubUtils = (AddSubUtils) findViewById(R.id.add_sub_0);
        addSubUtils.setBuyMax(30)
                .setInventory(50)
                .setCurrentNumber(5)
                .setStep(5)
                .setBuyMin(2)
                .setOnWarnListener(new AddSubUtils.OnWarnListener() {
                    @Override
                    public void onWarningForInventory(int inventory) {
                        Toast.makeText(AddSubActivity.this, "当前库存:" + inventory, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onWarningForBuyMax(int max) {
                        Toast.makeText(AddSubActivity.this, "超过最大购买数:" + max, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onWarningForBuyMin(int min) {
                        Toast.makeText(AddSubActivity.this, "低于最小购买数:" + min, Toast.LENGTH_SHORT).show();
                    }

                });
    }
}

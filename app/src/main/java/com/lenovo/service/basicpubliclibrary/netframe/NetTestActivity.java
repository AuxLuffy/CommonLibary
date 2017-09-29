package com.lenovo.service.basicpubliclibrary.netframe;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.netframe.net.cases.GetCitiesCase;
import com.lenovo.service.basicpubliclibrary.netframe.net.extension.BaseSubscriber;
import com.lenovo.service.basicpubliclibrary.netframe.net.models.City;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

public class NetTestActivity extends RxAppCompatActivity {
    private TextView getCitiesTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_test);
        getCitiesTv = (TextView) findViewById(R.id.cities_tv);
    }

    public void getCities(View view) {
        new GetCitiesCase().getCities()
                .compose(this.<List<City>>bindToLifecycle())
                .subscribe(new BaseSubscriber<List<City>>() {
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(NetTestActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(List<City> o) {
                        getCitiesTv.setText("");
                        if (o != null && o.size() != 0) {
                            for (City city : o) {
                                getCitiesTv.setText(getCitiesTv.getText().toString() + city.id.intValue() + "  " + city.name + "\n");
                            }
                        }
                    }
                });

    }
}

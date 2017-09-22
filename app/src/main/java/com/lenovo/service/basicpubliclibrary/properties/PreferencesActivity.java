package com.lenovo.service.basicpubliclibrary.properties;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.github.fernandodev.androidproperties.lib.AssetsProperties;
import com.lenovo.service.basicpubliclibrary.R;


public class PreferencesActivity extends ActionBarActivity {
  Config myConfig;
  AnotherConfig anotherConfig;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_preferences);
    myConfig = new Config(this);
    anotherConfig = new AnotherConfig(this);

    TextView configView = (TextView) findViewById(R.id.values_for_config);
    TextView anotherView = (TextView) findViewById(R.id.values_for_another);
    configView.setText(
        "message : " + myConfig.message + "\n" +
            "max : " + myConfig.max + "\n" +
            "rate_value : " + myConfig.rateValue + "\n" +
            "temperature : " + myConfig.temperature + "\n"
    );

    anotherView.setText(
        "staging_url : " + anotherConfig.stagingUrl + "\n" +
            "production_url : " + anotherConfig.productionUrl + "\n" +
            "token_url : " + anotherConfig.token + "\n" +
            "max_messages_count : " + anotherConfig.maxMessagesCount
    );
  }
}

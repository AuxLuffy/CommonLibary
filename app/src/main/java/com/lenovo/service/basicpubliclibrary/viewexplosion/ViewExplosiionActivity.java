package com.lenovo.service.basicpubliclibrary.viewexplosion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.viewexplosion.factory.ExplodeParticleFactory;
import com.lenovo.service.basicpubliclibrary.viewexplosion.factory.FallingParticleFactory;
import com.lenovo.service.basicpubliclibrary.viewexplosion.factory.FlyawayFactory;
import com.lenovo.service.basicpubliclibrary.viewexplosion.factory.VerticalAscentFactory;

public class ViewExplosiionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_explosiion);

        ExplosionField explosionField = new ExplosionField(this, new FallingParticleFactory());
        explosionField.addListener(findViewById(R.id.text));
        explosionField.addListener(findViewById(R.id.layout1));

        ExplosionField explosionField2 = new ExplosionField(this, new FlyawayFactory());
        explosionField2.addListener(findViewById(R.id.text2));
        explosionField2.addListener(findViewById(R.id.layout2));

        ExplosionField explosionField4 = new ExplosionField(this, new ExplodeParticleFactory());
        explosionField4.addListener(findViewById(R.id.text3));
        explosionField4.addListener(findViewById(R.id.layout3));

        ExplosionField explosionField5 = new ExplosionField(this, new VerticalAscentFactory());
        explosionField5.addListener(findViewById(R.id.text4));
        explosionField5.addListener(findViewById(R.id.layout4));
    }
}

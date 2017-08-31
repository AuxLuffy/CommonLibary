package com.lenovo.service.basicpubliclibrary.boommenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lenovo.service.basicpubliclibrary.R;

public class BoomMenuMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_boommenu);

        listenClickEventOf(R.id.square_and_piece_corner_radius_example);
        listenClickEventOf(R.id.actionbar_example);
        listenClickEventOf(R.id.ease_example);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.square_and_piece_corner_radius_example:
                startActivity(SquareAndPieceCornerRadiusActivity.class);
                break;
            case R.id.actionbar_example:
                startActivity(ActionBarActivity.class);
                break;
            case R.id.ease_example:
                startActivity(EaseActivity.class);
                break;
        }
    }
    
    private void listenClickEventOf(int id) {
        findViewById(id).setOnClickListener(this);
    }
    
    private void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
}

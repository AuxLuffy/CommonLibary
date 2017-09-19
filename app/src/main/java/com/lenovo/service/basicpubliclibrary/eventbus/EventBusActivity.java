package com.lenovo.service.basicpubliclibrary.eventbus;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.lenovo.service.basicpubliclibrary.R;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class EventBusActivity extends BaseActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        tv = findViewById(R.id.tvResult);
        registEventBus();
        findViewById(R.id.btnPost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseEvent.MessageEvent msg = new BaseEvent.MessageEvent();
                msg.setMessage("toast");
                EventBus.getDefault().post(msg);
            }
        });
        findViewById(R.id.btnPostEnty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseEvent.SomeOtherEvent msg = new BaseEvent.SomeOtherEvent();
                msg.setMessage(new String("post过来的实体内容"));
                Object obj = new Object();
                msg.setObject(obj);
                EventBus.getDefault().post(msg);
            }
        });
    }


    // This method will be called when a MessageEvent is posted
    @Subscribe
    public void onMessageEvent(BaseEvent.MessageEvent event) {
        Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
    }

    // This method will be called when a SomeOtherEvent is posted
    @Subscribe
    public void handleSomethingElse(BaseEvent.SomeOtherEvent event) {
        doSomethingWith(event);
    }

    private void doSomethingWith(BaseEvent.SomeOtherEvent event) {
        tv.setText(event.getMessage() + "\n" + event.getObject().hashCode());
    }

}

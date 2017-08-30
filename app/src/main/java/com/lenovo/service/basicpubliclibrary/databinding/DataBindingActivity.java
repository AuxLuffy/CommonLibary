package com.lenovo.service.basicpubliclibrary.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.lenovo.service.basicpubliclibrary.R;

public class DataBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  Binding 类是自动生成的
        com.lenovo.service.basicpubliclibrary.databinding.MainDataBinding binding = DataBindingUtil.setContentView(this, R.layout.main_data);
        User user = new User();
        // 所有的 set 方法也是根据布局中 variable 名称生成的
        binding.setUser(user);
    }
}

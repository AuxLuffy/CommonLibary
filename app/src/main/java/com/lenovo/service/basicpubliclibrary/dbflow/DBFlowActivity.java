package com.lenovo.service.basicpubliclibrary.dbflow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.dbflow.model.UserData;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import java.util.ArrayList;

public class DBFlowActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    ModelAdapter<UserData> adapter;

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etAge)
    EditText etAge;
    @BindView(R.id.etID)
    EditText etID;
    @BindView(R.id.tvResult)
    TextView tvResult;
    @BindView(R.id.cbMan)
    CheckBox cbMan;
    @BindView(R.id.cbWman)
    CheckBox cbWman;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    @BindView(R.id.btnDelete)
    Button btnDelete;
    @BindView(R.id.btnSelect)
    Button btnSelect;

    private int sex;

    private <T extends View> T find(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbflow);
        ButterKnife.bind(this);

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<UserData> list = (ArrayList<UserData>) new Select().from(UserData.class).queryList();
                tvResult.setText(list.toString());
            }
        });
//
        cbMan.setOnCheckedChangeListener(this);
        cbWman.setOnCheckedChangeListener(this);

        adapter = FlowManager.getModelAdapter(UserData.class);

    }

    private UserData getUserData() {

        String age = etAge.getText().toString().trim();
        if (TextUtils.isEmpty(age)) {
            Toast.makeText(this, "年龄不能为空", Toast.LENGTH_LONG).show();
            return null;
        }
        String name = etName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_LONG).show();
            return null;
        }

        if (TextUtils.isEmpty(age)) {
            Toast.makeText(this, "年龄不能为空", Toast.LENGTH_LONG).show();
            return null;
        }

        UserData data = new UserData();
        data.age = Integer.valueOf(age);
        data.name = name;
        data.sex = sex;
        return data;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton.getId() == R.id.cbMan) {
            if (b) {
                cbWman.setChecked(false);
            }
            sex = b ? 1 : 0;
        }
        if (compoundButton.getId() == R.id.cbWman) {
            if (b) {
                cbMan.setChecked(false);
            }
            sex = b ? 0 : 1;
        }
    }

    @OnClick({R.id.btnAdd,R.id.btnUpdate,R.id.btnDelete})
    public void onClick(View view) {
        UserData userData = getUserData();
        if (userData == null) return;
        switch (view.getId()) {
            case R.id.btnAdd:
                long inserRes = adapter.insert(userData);
                tvResult.setText("" + inserRes);
                break;
            case R.id.btnUpdate:
                String idStr = etID.getText().toString().trim();
                if (TextUtils.isEmpty(idStr)) {
                    Toast.makeText(this, "更新ID不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                userData.id = Long.parseLong(idStr);
                boolean updateRes = adapter.update(userData);
                tvResult.setText("" + updateRes);
                break;
            case R.id.btnDelete:
                boolean deleteRes = adapter.delete(userData);
                tvResult.setText("" + deleteRes);
                break;
        }
    }
}

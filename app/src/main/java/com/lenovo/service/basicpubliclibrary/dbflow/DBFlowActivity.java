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
import com.lenovo.service.basicpubliclibrary.dbflow.model.UserData_Table;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import java.util.List;

public class DBFlowActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    ModelAdapter<UserData> adapter;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etAge)
    EditText etAge;
    @BindView(R.id.etID)
    EditText etID;
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
    @BindView(R.id.activity_dbflow)
    LinearLayout activityDbflow;
    private int sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbflow);
        ButterKnife.bind(this);
        cbMan.setOnCheckedChangeListener(this);
        cbWman.setOnCheckedChangeListener(this);


        adapter = FlowManager.getModelAdapter(UserData.class);



//再来点福利，update高级用法，增删改查都是同理，就不一一列举了
        SQLite.update(UserData.class).set(UserData_Table.name.eq("888")).where(UserData_Table.id.eq((long) 1)).execute();
//UserData_Table就是DBFlow自动生成的表明，在(5)的备注中已经提到了

//查询
        List<UserData> list = SQLite.select().from(UserData.class).queryList();

    }


    @OnClick({R.id.btnAdd, R.id.btnUpdate, R.id.btnDelete})
    public void onViewClicked(View view) {
        UserData userData = getUserData();
        if (userData == null) return;
        switch (view.getId()) {
            case R.id.btnAdd:
                adapter.insert(userData);
                break;
            case R.id.btnUpdate:
                adapter.update(userData);
                break;
            case R.id.btnDelete:
                adapter.delete(userData);
                break;
        }
    }

    private UserData getUserData() {
        String id = etID.getText().toString().trim();
        if (TextUtils.isEmpty(id)) {
            Toast.makeText(this, "ID不能为空", Toast.LENGTH_LONG).show();
            return null;
        }
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
        data.id = Long.parseLong(id);
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
}

package com.lenovo.service.basicpubliclibrary.dbflow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import butterknife.ButterKnife;
import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.dbflow.model.UserData;
import com.lenovo.service.basicpubliclibrary.dbflow.model.UserData_Table;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import java.util.ArrayList;
import java.util.List;

public class DBFlowActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    ModelAdapter<UserData> adapter;
    EditText etName;
    EditText etAge;
    EditText etID;
    TextView tvResult;
    CheckBox cbMan;
    CheckBox cbWman;
    Button btnAdd;
    Button btnUpdate;
    Button btnDelete;
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

        btnSelect = find(R.id.btnSelect);
        tvResult = find(R.id.tvResult);
        btnAdd = find(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnUpdate = find(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        btnDelete = find(R.id.btnDelete);
        btnDelete.setOnClickListener(this);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<UserData> list = (ArrayList<UserData>) new Select().from(UserData.class).queryList();
                tvResult.setText(list.toString());
            }
        });

        etName = find(R.id.etName);
        etAge = find(R.id.etAge);
        etID = find(R.id.etID);
        cbMan = find(R.id.cbMan);
        cbWman = find(R.id.cbWman);

        cbMan.setOnCheckedChangeListener(this);
        cbWman.setOnCheckedChangeListener(this);

        adapter = FlowManager.getModelAdapter(UserData.class);


//再来点福利，update高级用法，增删改查都是同理，就不一一列举了
        SQLite.update(UserData.class).set(UserData_Table.name.eq("888")).where(UserData_Table.id.eq((long) 1)).execute();
//UserData_Table就是DBFlow自动生成的表明，在(5)的备注中已经提到了

//查询
        List<UserData> list = SQLite.select().from(UserData.class).queryList();

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

    @Override
    public void onClick(View view) {
        UserData userData = getUserData();
        if (userData == null) return;
        switch (view.getId()) {
            case R.id.btnAdd:
                long inserRes = adapter.insert(userData);
                tvResult.setText(""+inserRes);
                break;
            case R.id.btnUpdate:
                String idStr = etID.getText().toString().trim();
                if (TextUtils.isEmpty(idStr)){
                    Toast.makeText(this,"更新ID不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                userData.id = Long.parseLong(idStr);
                boolean updateRes = adapter.update(userData);
                tvResult.setText(""+updateRes);
                break;
            case R.id.btnDelete:
                boolean deleteRes = adapter.delete(userData);
                tvResult.setText(""+deleteRes);
                break;
        }
    }
}

package com.lenovo.service.basicpubliclibrary.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.greendao.entity.BaseEntity;
import com.lenovo.service.basicpubliclibrary.greendao.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GreenDaoDemoActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView mListView;
    private ArrayAdapter adapter;
    private ArrayList<User> mUsers;
    private ArrayList<String> names;
    private EditText mEditAdd, mEditDelete, mEditUpdate, mEditQuery;
    private Button btnAdd, btnDelete, btnUpdate, btnQuery;
    private UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao_demo);
        initViews();
        setData();
    }

    private void initViews() {
        mListView = (ListView) findViewById(R.id.listResult);
        mEditAdd = (EditText) findViewById(R.id.editAddData);
        mEditDelete = (EditText) findViewById(R.id.editDeleteData);
        mEditUpdate = (EditText) findViewById(R.id.editUpdateData);
        mEditQuery = (EditText) findViewById(R.id.editQueryData);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnQuery = (Button) findViewById(R.id.btnQuery);
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
    }

    private void setData() {
        mUserDao = DaoManager.getUserDao(this);
        //从数据库查询数据
        mUsers = new ArrayList<>();
        names = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, names);
        mListView.setAdapter(adapter);
//        queryAction();
    }

    /**
     * 添加数据
     */
    public void addAction() {
        String name = mEditAdd.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {
            User user = new User();
            user.setAge(20);
            user.setName(name);
            DaoManager.addData(mUserDao, user);
        }
        Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
    }

    /**
     * 删除
     */
    public void deleteAction() {
        String name = mEditDelete.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            DaoManager.deleteData(mUserDao, UserDao.Properties.Name, null);
            Toast.makeText(this, "delete all", Toast.LENGTH_SHORT).show();
        } else {
            DaoManager.deleteData(mUserDao, UserDao.Properties.Name, name);
            Toast.makeText(this, "delete " + name, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 更新第一条数据
     * 将被更新的数据的age改为100
     */
    public void updateAction() {
        String name = mEditUpdate.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {
            //此处通过获取Userdao对象具体完成更新操作
            List<User> list = mUserDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).build().list();
            if (list != null) {
                for (User user1 : list) {
                    user1.setAge(100);
                    mUserDao.update(user1);
                }
            }
        }
    }

    /**
     * 查询
     */
    public void queryAction() {
        String name = mEditQuery.getText().toString().trim();
        List<BaseEntity> list = null;
        if (TextUtils.isEmpty(name)) {
            list = DaoManager.queryAllData(mUserDao);
        } else {
            list = DaoManager.queryData(mUserDao, UserDao.Properties.Name, name);
        }
        if (list != null) {
            mUsers.clear();
            for (BaseEntity b : list) {
                mUsers.add((User) b);
            }
        }
        names.clear();
        for (User user : mUsers) {
            names.add(user.getId() + "  " + user.getAge() + "  " + user.getName());
        }
        adapter.notifyDataSetChanged();
    }

    //
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                addAction();
                break;
            case R.id.btnDelete:
                deleteAction();
                break;
            case R.id.btnUpdate:
                updateAction();
                break;
            case R.id.btnQuery:
                queryAction();
                break;
        }
        mEditAdd.setText("");
        mEditDelete.setText("");
        mEditUpdate.setText("");
        mEditQuery.setText("");
    }
}

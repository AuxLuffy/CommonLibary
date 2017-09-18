package com.lenovo.service.basicpubliclibrary.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.gesturelock.util.ToastUtil;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RealmActivity extends AppCompatActivity implements View.OnClickListener {
    private Realm realm;
    private ListView mListView;
    private EditText mEditeId;
    private EditText mEditeAge;
    private EditText getmEditeName;
    private Long id;
    private int age;
    private String name;
    private ArrayList<String> datas;
    private ArrayAdapter<String> mAdapter;
    private Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        mEditeId = (EditText) findViewById(R.id.editUserId);
        mEditeAge = (EditText) findViewById(R.id.editAge);
        getmEditeName = (EditText) findViewById(R.id.editName);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        //初始化Realm
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("commen.realm") //文件名
                .schemaVersion(0) //版本号
                .build();
        realm = Realm.getInstance(config);
        mListView = (ListView) findViewById(R.id.listView);
        datas = new ArrayList<>();
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        mListView.setAdapter(mAdapter);
    }

    /**
     * 增添数据
     */
    public void addUser() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (getAgeAndName()) {
//                    RealmUser user = realm.createObject(RealmUser.class, id);
                    RealmUser user = new RealmUser();
                    user.setId(id);
                    user.setAge(age);
                    user.setUserName(name);
                    realm.copyToRealmOrUpdate(user);
                    //也可使用以下方法
//                    RealmUser user1 = new RealmUser();
//                    user.setAge(age);
//                    user1.setUserName(name);
//                    realm.copyToRealmOrUpdate(user1);//含有主键
//                    realm.copyToRealm(user1);//不含有主键
                    //方法3
//                    realm.beginTransaction();//开启事务
//                    RealmUser user3 = realm.createObject(RealmUser.class);
//                    user3.setUserName(name);
//                    user3.setAge(age);
//                    realm.commitTransaction();//提交事务
                    ToastUtil.showMessage(RealmActivity.this, "插入成功了！");
                    mEditeAge.setText("");
                    getmEditeName.setText("");
                    mEditeId.setText("");
                }
            }
        });
    }

    /**
     * 查找
     */
    public void queryUser() {
//        RealmResults<RealmUser> lists = realm.where(RealmUser.class).findAll();//同步
        RealmResults<RealmUser> lists = realm.where(RealmUser.class).findAllAsync();//异步
        //异步查询增加监听
        lists.addChangeListener(new RealmChangeListener<RealmResults<RealmUser>>() {
            @Override
            public void onChange(RealmResults<RealmUser> element) {
                datas.clear();
                for (RealmUser user : element) {
                    datas.add(user.getUserName() + "---" + user.getAge());
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        //查询具体条件
//        realm.where(RealmUser.class).equalTo("userName","xxx").findAll();
        //多条件查询
//        realm.where(RealmUser.class).equalTo("userName","xxx").equalTo("userAge",20).findAll();
    }

    /**
     * 更新
     */
    public void updateUser() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (getAgeAndName()) {
                    RealmUser user = realm.where(RealmUser.class).findFirst();//查询第一条数据
                    user.setAge(age);
                    user.setUserName(name);
                }
            }
        });
    }

    /**
     * 删除
     */
    public void deleteUser() {
//先查找的相关数据
        final RealmResults<RealmUser> results = realm.where(RealmUser.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteAllFromRealm();//删除全部
//                results.get(0).deleteFromRealm();//删除第一条
//                results.deleteFromRealm(0);同上
//                results.deleteLastFromRealm();//删除user表的最后一条数据
            }
        });
    }

    /**
     * 界面关闭时关闭数据库
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            realm.close();
        }
    }

    private boolean getAgeAndName() {
        try {
            id = Long.parseLong(mEditeId.getText().toString().trim());
            age = Integer.parseInt(mEditeAge.getText().toString().trim());
            name = getmEditeName.getText().toString().trim();
            if (TextUtils.isEmpty(name)) {
                ToastUtil.showMessage(this, "请输入姓名");
                return false;
            }
            return true;
        } catch (Exception e) {
            ToastUtil.showMessage(this, "请输入完整信息");
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                addUser();
                break;
            case R.id.button2:
                queryUser();
                break;
            case R.id.button3:
                updateUser();
                break;
            case R.id.button4:
                deleteUser();
                break;
        }
    }
}

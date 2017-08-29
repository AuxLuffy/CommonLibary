package com.lenovo.service.basicpubliclibrary.ormlite;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.ormlite.adapter.PersonAdapter;
import com.lenovo.service.basicpubliclibrary.ormlite.bean.Person;
import com.lenovo.service.basicpubliclibrary.ormlite.utils.DBUtil;

import java.util.ArrayList;
import java.util.List;

public class OrmLiteActivity extends AppCompatActivity {

    private List<Person> list = new ArrayList<>();
    private PersonAdapter adapter;
    private Button bt_add;
    private Button bt_delete;
    private Button bt_update;
    private Button bt_query;
    private String[] strs = new String[]{"张三", "李四", "王五", "赵六"};


    /**
     * ORMlite是类似hibernate的对象映射框架，主要面向java语言，同时，是时下最流行的android面向数据库的的编程工具。
     * ORMLite是对象关系映射（Object Relational Mapping）数据库的一种轻量级SQL数据库的开发包（packages）
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orm_lite);
        /*if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }*/

        bt_add = (Button) findViewById(R.id.bt_add);
        bt_delete = (Button) findViewById(R.id.bt_delete);
        bt_update = (Button) findViewById(R.id.bt_update);
        bt_query = (Button) findViewById(R.id.bt_query);

        initData();

        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PersonAdapter(list,this);
        recyclerview.setAdapter(adapter);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (String str : strs){
                    DBUtil.addOnePerson(new Person(str , "180", "140"));
                }
                list.clear();
                list.addAll(DBUtil.queryAllPerson());
                adapter.notifyDataSetChanged();
            }
        });

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Person bean : list){
                    DBUtil.deleteAllPerson(bean);
                }
                list.clear();
                list.addAll(DBUtil.queryAllPerson());
                adapter.notifyDataSetChanged();
            }
        });

        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Person bean : list){
                    bean.setHeight("0");
                    bean.setWeight("0");
                    DBUtil.updateOneBrowse(bean);
                }
                list.clear();
                list.addAll(DBUtil.queryAllPerson());
                adapter.notifyDataSetChanged();
            }
        });

        bt_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                list.addAll(DBUtil.queryAllPerson());
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void initData(){

        for (String str : strs){
            DBUtil.addOnePerson(new Person(str , "180", "140"));
        }

        list.addAll(DBUtil.queryAllPerson());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length == 0 || grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this,"拒绝权限",Toast.LENGTH_SHORT);
        } else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                //写权限
                case 104:
                    Toast.makeText(this,"同意权限",Toast.LENGTH_SHORT);
                    break;
                //读权限
                case 105:
                    Toast.makeText(this,"同意权限",Toast.LENGTH_SHORT);
                    break;
            }
        }

    }
}

package com.lenovo.service.basicpubliclibrary.sugar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.ormlite.bean.Person;
import com.lenovo.service.basicpubliclibrary.ormlite.utils.DBUtil;
import com.lenovo.service.basicpubliclibrary.sugar.adapter.BookAdapter;
import com.lenovo.service.basicpubliclibrary.sugar.bean.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SugarActivity extends AppCompatActivity {

    private Button bt_add;
    private Button bt_delete;
    private Button bt_update;
    private Button bt_query;
    private List<Book> list = new ArrayList<>();
    private BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugar);

        bt_add = (Button) findViewById(R.id.bt_add);
        bt_delete = (Button) findViewById(R.id.bt_delete);
        bt_update = (Button) findViewById(R.id.bt_update);
        bt_query = (Button) findViewById(R.id.bt_query);

        initData();

        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookAdapter(list,this);
        recyclerview.setAdapter(adapter);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 3; i++){
                    new Book("isbn123" , "Title here", "2nd edition").save();
                }
                list.clear();
                updateData();
                adapter.notifyDataSetChanged();
            }
        });

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = Book.deleteAll(Book.class);
                Log.e("Sugar","删除返回值-----"+i);
                list.clear();
                updateData();
                adapter.notifyDataSetChanged();
            }
        });

        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Iterator<Book> all = Book.findAll(Book.class);
                while (all.hasNext()){
                    Book book = all.next();
                    book.setIsbn("isbn123");
                    book.setTitle("new Title");
                    book.setEdition("3nd edition");
                    book.save();
                }
                list.clear();
                updateData();
                adapter.notifyDataSetChanged();
            }
        });

        bt_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                updateData();
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void initData(){
        for (int i = 0; i < 3; i++){
            new Book("isbn123" , "Title here", "2nd edition").save();
        }
        updateData();
    }


    private void updateData(){
        Iterator<Book> all = Book.findAll(Book.class);
        while (all.hasNext()){
            list.add(all.next());
        }
    }
}

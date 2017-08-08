package com.lenovo.service.basicpubliclibrary.maillistananimation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.service.basicpubliclibrary.R;
import com.lenovo.service.basicpubliclibrary.maillistananimation.adapter.AdapterContactCity;
import com.lenovo.service.basicpubliclibrary.maillistananimation.bean.ModelContactCity;
import com.lenovo.service.basicpubliclibrary.maillistananimation.bean.TitleData;
import com.lenovo.service.basicpubliclibrary.utils.ContactInfoUtils;
import com.lenovo.service.basicpubliclibrary.utils.LetterComparator;
import com.lenovo.service.basicpubliclibrary.utils.permission.PermissionUtils;
import com.lenovo.service.basicpubliclibrary.widget.PinnedHeaderDecoration;
import com.lenovo.service.basicpubliclibrary.widget.WaveSideBarView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lenovo.service.basicpubliclibrary.R.id.side_view;

/**
 * Created by lenovo on 2017/8/8.
 */

public class MaillistActivity extends Activity{

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(side_view)
    WaveSideBarView mSideBarView;
    private Activity mActivity;
    private AdapterContactCity mMAdapterContactCity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maillist);
        ButterKnife.bind(this);
        this.mActivity=this;
        initView();
        init_Permission();

    }

    private void init_Permission() {
        PermissionUtils.requestPermission(mActivity, PermissionUtils.CODE_GET_ACCOUNTS, mPermissionGrant);// 获取通讯录权限
    }

    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_GET_ACCOUNTS:
                    init_adapter();
                    break;

                default:
                    break;
            }
        }
    };

    private void init_adapter() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<ModelContactCity> ContactInfo= ContactInfoUtils.getContactList(mActivity);
                if(ContactInfo!=null){
                    Type listType = new TypeToken<ArrayList<ModelContactCity>>() {
                    }.getType();
                    Gson gson = new Gson();
                    final List<ModelContactCity> listdata = gson.fromJson(TitleData.DATA, listType);
                    listdata.addAll(ContactInfo);
                    Collections.sort(listdata, new LetterComparator());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mSideBarView.setVisibility(View.VISIBLE);
                            mMAdapterContactCity = new AdapterContactCity(mActivity, listdata);
                            mRecyclerView.setAdapter(mMAdapterContactCity);
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mActivity,"请您打开应用权限管理,开启通讯录权限功能",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        }).start();
    };



    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final PinnedHeaderDecoration decoration = new PinnedHeaderDecoration();
        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        mRecyclerView.addItemDecoration(decoration);

        mSideBarView.setOnTouchLetterChangeListener(new WaveSideBarView.OnTouchLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                int pos = mMAdapterContactCity.getLetterPosition(letter);

                if (pos != -1) {
                    mRecyclerView.scrollToPosition(pos);
                    LinearLayoutManager mLayoutManager =
                            (LinearLayoutManager) mRecyclerView.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(pos, 0);
                }
            }
        });


    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, mPermissionGrant);
    }

}

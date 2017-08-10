package com.lenovo.service.basicpubliclibrary.maillistananimation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.lenovo.service.basicpubliclibrary.R.id.side_view;
/**
 * 本地通讯录显示,支持A-Z字母检索
 * @Author 李巷阳
 * Created at 2017/8/10 15:02
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



    // 初始化列表和右侧快速检索栏
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

    // 初始化数据
    private void init_data() {

        Observable.just("")
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .map(new Func1<String, List<ModelContactCity> >() {
                    @Override
                    public List<ModelContactCity> call(String s) {
                        Log.e("thread",Thread.currentThread().getName()+":1");
                        // 获取本地通讯录
                        List<ModelContactCity> ContactInfo = ContactInfoUtils.getContactList(mActivity);
                        if(ContactInfo==null)return ContactInfo;
                        List<ModelContactCity> newContactInfo = ContactInfoUtils.getRightMobile(ContactInfo);
                        Type listType = new TypeToken<ArrayList<ModelContactCity>>() {}.getType();
                        Gson gson = new Gson();
                        // 获取检索的A—Z的字符集
                        final List<ModelContactCity> Letterdata = ContactInfoUtils.
                                getLetterdata(newContactInfo, (List<ModelContactCity>) gson.fromJson(TitleData.DATA, listType));
                        // 把通讯录和字符集合并
                        Letterdata.addAll(newContactInfo);
                        // 排序合并后的数据
                        Collections.sort(Letterdata, new LetterComparator());
                        return Letterdata;
                    }
                })
                .map(new Func1<List<ModelContactCity>, List<ModelContactCity>>() {
                    @Override
                    public List<ModelContactCity> call(List<ModelContactCity> modelContactCities) {
                        Log.e("thread",Thread.currentThread().getName()+":2");
                        // 如果获取的通讯录为空,给用户提示
                        if (modelContactCities==null){
                            throw new AppException("通讯录无法显示,原因是您的通讯录为空或者您未开启获取通讯录权限",1);
                        }
                        return modelContactCities;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ModelContactCity>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof AppException){
                            AppException ex = (AppException) e;
                            switch (ex.errorCode){
                                case 1:
                                    Toast.makeText(mActivity,e.getMessage(),Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                            }
                        }
                    }

                    @Override
                    public void onNext(List<ModelContactCity> modelContactCities) {
                        Log.e("thread",Thread.currentThread().getName()+":3");
                        init_adapter(modelContactCities);
                    }
                });
    }


    // 初始化RecyclerView的数据适配器
    private void init_adapter(List<ModelContactCity> modelContactCities) {
        mSideBarView.setVisibility(View.VISIBLE);
        mMAdapterContactCity = new AdapterContactCity(mActivity, modelContactCities);
        mRecyclerView.setAdapter(mMAdapterContactCity);
    }




    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, mPermissionGrant);
    }
    // 获取通讯录权限
    private void init_Permission() {
        PermissionUtils.requestPermission(mActivity, PermissionUtils.CODE_GET_ACCOUNTS, mPermissionGrant);
    }
    // 获取后权限的回调
    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_GET_ACCOUNTS:
                    init_data();
                    break;
                default:
                    break;
            }
        }
    };
}

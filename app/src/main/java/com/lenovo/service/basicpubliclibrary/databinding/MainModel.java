package com.lenovo.service.basicpubliclibrary.databinding;

import android.content.res.Resources;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;
import com.lenovo.service.basicpubliclibrary.R;

import java.util.Random;

/**
 * Created by Administrator on 2017/9/1.
 */
public class MainModel {

    public ObservableField<String> numberOfUsersLoggedIn = new ObservableField<>();
    public ObservableField<Boolean> isExistingUserChecked = new ObservableField<>();
    public ObservableField<Integer> emailBlockVisibility = new ObservableField<>();
    public ObservableField<String> loginOrCreateButtonText = new ObservableField<>();

    private DataBindingActivity mainActivity;
    private Resources resources;
    private boolean mIsLoaded;

    public MainModel(DataBindingActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.resources = mainActivity.getResources();
        setInitState();
        uploadDependentViews();
        hookUpDependencies();
    }

    private void hookUpDependencies() {
        isExistingUserChecked.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                uploadDependentViews();
            }
        });
    }

    private void uploadDependentViews() {
        emailBlockVisibility.set(isExistingUserChecked.get() ? View.GONE : View.VISIBLE);
        loginOrCreateButtonText.set(isExistingUserChecked.get() ? getString(R.string.log_in) : getString(R.string.create_user));
    }

    private String getString(int id) {
        return resources.getString(id);
    }

    private void setInitState() {
        numberOfUsersLoggedIn.set("...");
        isExistingUserChecked.set(true);
    }


    public void loginClicked() {
        Toast.makeText(mainActivity, isExistingUserChecked.get() ? "用户名密码错误" : "请输入正确的邮箱地址", Toast.LENGTH_SHORT).show();
    }

    public boolean isLoaded() {
        return mIsLoaded;
    }

    public void loadAsync() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                numberOfUsersLoggedIn.set("" + new Random().nextInt(1000));
                mIsLoaded = true;
                return null;
            }
        }.execute();
    }
}

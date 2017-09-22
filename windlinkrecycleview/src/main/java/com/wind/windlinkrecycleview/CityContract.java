package com.wind.windlinkrecycleview;

/**
 * @Author 李巷阳
 * Created at 2017/9/22 14:51
 */
public interface CityContract {
    interface View
    {
        void setPresenter(Presenter presenter);
        void showSnackBar();
        void showCity();
    }
    interface Presenter {
        void start();
    }
}

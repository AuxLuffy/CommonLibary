package com.wind.windlinkrecycleview.presenter;

import com.wind.windlinkrecycleview.CityContract;


public class CityPresenter implements CityContract.Presenter {
    private CityContract.View cityView;
    public CityPresenter(CityContract.View cityview)
    {
        this.cityView=cityview;
        cityView.setPresenter(this);
    }

    @Override
    public void start() {
        showCity();
    }

    private void showCity() {
        cityView.showCity();
    }

}

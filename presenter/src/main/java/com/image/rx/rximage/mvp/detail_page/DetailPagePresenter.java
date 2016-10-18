package com.image.rx.rximage.mvp.detail_page;

import rx.Subscription;

/**
 * Created by Administrator on 2016/10/17.
 */

public class DetailPagePresenter implements DetailPageContract.Presenter {

    private DetailPageContract.View mView;


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(DetailPageContract.View view) {
        mView = view;
    }

    @Override
    public Subscription getPictureList() {
        return null;
    }
}

package com.image.rx.rximage;

/**
 * Created by Administrator on 2016/10/15.
 */

public interface BasePresenter<T extends BaseView> {

    void onCreate(T mView);
    void onDestroy();
    void onStart();
    void onPause();
}

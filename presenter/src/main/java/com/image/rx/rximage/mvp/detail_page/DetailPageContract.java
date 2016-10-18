package com.image.rx.rximage.mvp.detail_page;

import com.image.rx.rximage.BasePresenter;
import com.image.rx.rximage.BaseView;

import rx.Subscription;

/**
 * Created by Administrator on 2016/10/17.
 */

public interface DetailPageContract {
    interface View extends BaseView {


    }
    interface  Presenter extends BasePresenter {
        void attachView(DetailPageContract.View view);

        Subscription getPictureList();

    }
}

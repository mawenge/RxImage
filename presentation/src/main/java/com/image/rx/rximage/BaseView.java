package com.image.rx.rximage;

import rx.Subscription;

/**
 * Created by Administrator on 2016/10/15.
 */

public interface BaseView<T extends BasePresenter> {
    void collectSubscription(Subscription subscription);

}

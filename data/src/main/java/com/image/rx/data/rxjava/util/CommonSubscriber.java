package com.image.rx.data.rxjava.util;

import com.image.rx.data.rxjava.exception.ApiException;

import rx.Observer;

/**
 * Created by Administrator on 2016/10/15.
 */

public abstract class CommonSubscriber<T> implements Observer<T> {

    @Override
    public void onError(Throwable e) {
        if(e instanceof ApiException){
            onError((ApiException)e);
        }else{
            ApiException exception = new ApiException(e, 123);
            exception.message = e.getMessage();
            onError(exception);
        }
    }
    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);
}

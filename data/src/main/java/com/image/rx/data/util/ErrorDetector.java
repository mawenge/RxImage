package com.image.rx.data.util;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by Administrator on 2016/9/29.
 */

public class ErrorDetector extends Observable.Transformer<T, R> {


    @Override
    public Observable<R> call(Observable<T> tObservable) {
        return null;
    }
}

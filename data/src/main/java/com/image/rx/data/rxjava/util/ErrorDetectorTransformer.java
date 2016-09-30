package com.image.rx.data.rxjava.util;

import com.image.rx.data.response.CommonResponse;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/9/30.
 */

public class ErrorDetectorTransformer<T extends CommonResponse> implements Observable.Transformer<T, T> {


    @Override
    public Observable<T> call(Observable<T> tObservable) {
        return tObservable.map(new Func1<T, T>() {
            @Override
            public T call(T t) {
                if (t.isStatus()){
                    return t;
                } else {
                    return null;
                }
            }
        });
    }

}

package com.image.rx.data.rxjava.util;

import com.image.rx.data.rxjava.exception.ExceptionEngine;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/10/15.
 */

public class ErrorHandleFunc<T> implements Func1<Throwable, Observable<T>> {

    @Override
    public Observable<T> call(Throwable throwable) {
        System.out.println("**************ErrorHandleFunc***************");
        System.out.println(throwable.getLocalizedMessage());
        System.out.println("****************ErrorHandleFunc*************");
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}

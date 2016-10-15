package com.image.rx.data.rxjava.util;

import android.util.Log;

import com.image.rx.data.response.CommonResponse;
import com.image.rx.data.rxjava.exception.ResponseThrowable;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/30.
 */

public class TransformerProvider {

    private static final String TAG = "TransformerProvider";

    /**
     * 网络响应拦截器，如果返回值错误，直接抛出异常，不进行后续的相关操作，直接进入onError回调当中
     * @param <T>
     * @return
     */
    public static <T extends CommonResponse> Observable.Transformer<T, T> getErrorDetectorTransformer(){
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.map(new Func1<T, T>() {
                    @Override
                    public T call(T t) {
                        if (!t.isStatus()){
                            try {
                                Log.d(TAG, "服务器返回错误！");
                                throw new ResponseThrowable("服务器返回错误！");
                            } catch (ResponseThrowable e) {
                                e.printStackTrace();
                            }
                        }
                        return t;
                    }
                });
            }
        };
    }


    public static <T>Observable.Transformer<T, T> getSwitchSchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    };




}

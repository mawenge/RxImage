package com.image.rx.data.rxjava.exception;

/**
 * Created by Administrator on 2016/10/15.
 */

public class ExceptionEngine {
    public static ApiException handleException(Throwable e){
        ApiException apiException;
        if (e instanceof ResponseThrowable){
            apiException = new ApiException(e, 0);
            apiException.message = e.getMessage();
        }else {
            apiException = new ApiException(e, 0);
            apiException.message = "未知错误";
        }
        return apiException;

    }
}

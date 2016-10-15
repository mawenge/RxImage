package com.image.rx.data.rxjava.exception;

/**
 * Created by Administrator on 2016/10/15.
 */

public class ApiException extends Exception {
    public int code;
    public String message;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }
}

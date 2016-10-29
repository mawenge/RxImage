package com.image.rx.rximage;

import android.app.Application;

import com.image.rx.domain.usercase.NetWorkDataUserCase;

/**
 * Created by Administrator on 2016/10/17.
 */

public class MZApplication extends Application {
    private static MZApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public static MZApplication getInstance() {
        return instance;
    }
}

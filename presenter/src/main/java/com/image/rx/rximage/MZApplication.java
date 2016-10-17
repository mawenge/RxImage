package com.image.rx.rximage;

import android.app.Application;

import com.image.rx.domain.usercase.NetWorkDataUserCase;

/**
 * Created by Administrator on 2016/10/17.
 */

public class MZApplication extends Application {
    private NetWorkDataUserCase netWorkDataUserCase;
    @Override
    public void onCreate() {
        super.onCreate();
        netWorkDataUserCase = new NetWorkDataUserCase();
    }

    public NetWorkDataUserCase getNetWorkDataUserCase() {
        return netWorkDataUserCase;
    }
}

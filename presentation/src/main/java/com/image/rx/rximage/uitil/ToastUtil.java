package com.image.rx.rximage.uitil;

import android.widget.Toast;

import com.image.rx.rximage.MZApplication;

/**
 * Created by Administrator on 2016/10/29.
 */

public class ToastUtil {
    public static void toastShort(String message){
        Toast.makeText(MZApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
    }


}

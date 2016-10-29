package com.image.rx.rximage.mvp.detail_page;

import android.util.Log;

import com.image.rx.data.entity.Picture;
import com.image.rx.data.rxjava.exception.ApiException;
import com.image.rx.data.rxjava.util.CommonSubscriber;
import com.image.rx.domain.usercase.NetWorkDataUserCase;
import com.image.rx.rximage.uitil.ToastUtil;

import java.util.List;
import java.util.prefs.PreferenceChangeEvent;

import rx.Subscription;

/**
 * Created by Administrator on 2016/10/17.
 */

public class DetailPagePresenter implements DetailPageContract.Presenter {

    private DetailPageContract.View mView;
    private String DEBUG_TAG = "DetailPagePresenter" ;


    @Override
    public void onCreate(DetailPageContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }


    @Override
    public void getPictureList(long id) {
        Subscription subscription = NetWorkDataUserCase.getDetailPictureList(id).subscribe(new CommonSubscriber<List<Picture>>() {
            @Override
            protected void onError(ApiException ex) {
                ToastUtil.toastShort(ex.message);
                Log.d(DEBUG_TAG, ex.message + "  " + ex.getMessage());
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onNext(List<Picture> pictures) {
                mView.setPictureList(pictures);
            }
        });

        mView.collectSubscription(subscription);

    }
}

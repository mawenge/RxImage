package com.image.rx.rximage.view;

import android.support.v7.app.AppCompatActivity;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/10/17.
 */

public class BaseActivity extends AppCompatActivity {
    private CompositeSubscription compositeSubscription;

    public void addSubscription(Subscription subscription){
        if (compositeSubscription == null){
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(subscription);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeSubscription != null){
            compositeSubscription.unsubscribe();
        }
    }
}

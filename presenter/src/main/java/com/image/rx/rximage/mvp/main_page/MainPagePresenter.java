package com.image.rx.rximage.mvp.main_page;

import com.image.rx.data.entity.Gallery;
import com.image.rx.data.rxjava.exception.ApiException;
import com.image.rx.data.rxjava.util.CommonSubscriber;
import com.image.rx.domain.usercase.NetWorkDataUserCase;
import com.image.rx.rximage.MessageEvent.LoadNextPageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import rx.Subscription;

/**
 * Created by Administrator on 2016/10/15.
 */

public class MainPagePresenter implements MainPageContract.Presenter {
    private MainPageContract.View mView;
    private final int GALLERY_SIZE_PER_PAGE = 20;
    private int currentType = -1;
    private int currentPage = 1;

    @Override
    public void attachView(MainPageContract.View view) {
        mView = view;
    }

    @Override
    public void loadGalleryList(final int page) {
        System.out.println("****************开始刷新****************");
        mView.showLoading();
        Subscription subscription = NetWorkDataUserCase.getFrontPhotoList(page, GALLERY_SIZE_PER_PAGE)
                .subscribe(new CommonSubscriber<List<Gallery>>() {
                    @Override
                    protected void onError(ApiException ex) {
                        System.out.println("===============================");
                        System.out.println(ex);
                        System.out.println(ex.message);
                        System.out.println("===============================");
                        mView.hideLoading();

                    }

                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                    }

                    @Override
                    public void onNext(List<Gallery> galleries) {
                        if (galleries != null){
                            mView.addGalleryList(galleries, page == 1);
                        }
                        System.out.println("******onnext******" + galleries);
                    }
                });
        mView.collectSubscription(subscription);
    }

    @Override
    public void loadGalleryListOfType(int page, long type) {

    }

    @Override
    public void refreshGalleryList() {
        currentPage = 1;
        if (currentType < 0){
            loadGalleryList(currentPage);
        }else {
            loadGalleryListOfType(currentPage, currentType);
        }
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Subscribe
    public void handleLoadNextPageEvent(LoadNextPageEvent loadNextPageEvent){
        currentPage++;
        if (currentType < 0){
            loadGalleryList(currentPage);
        }else {
            loadGalleryListOfType(currentPage, currentType);
        }
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
    }

}

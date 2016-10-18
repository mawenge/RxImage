package com.image.rx.rximage.mvp.main_page;

import com.image.rx.data.entity.Gallery;
import com.image.rx.data.rxjava.exception.ApiException;
import com.image.rx.data.rxjava.util.CommonSubscriber;
import com.image.rx.domain.usercase.NetWorkDataUserCase;
import com.image.rx.rximage.MessageEvent.LoadNextPageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */

public class MainPagePresenter implements MainPageContract.Presenter {
    private MainPageContract.View mView;
    private final int GALLERY_SIZE_PER_PAGE = 20;
    NetWorkDataUserCase netWorkDataUserCase;
    private int currentType = -1;
    private int currentPage = 0;

    @Override
    public void attachView(MainPageContract.View view) {
        mView = view;
        netWorkDataUserCase = new NetWorkDataUserCase();
    }

    @Override
    public void loadGalleryList(int page) {
        netWorkDataUserCase.getFrontPhotoList(page, GALLERY_SIZE_PER_PAGE)
                .subscribe(new CommonSubscriber<List<Gallery>>() {
                    @Override
                    protected void onError(ApiException ex) {
                        System.out.println("===============================");
                        System.out.println(ex.message);
                        System.out.println("===============================");

                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(List<Gallery> galleries) {
                        mView.addGalleryList(galleries);
                        System.out.println("************" + galleries);
                    }
                });
    }

    @Override
    public void loadGalleryListOfType(int page, long type) {

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

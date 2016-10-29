package com.image.rx.rximage.mvp.detail_page;

import com.image.rx.data.entity.Picture;
import com.image.rx.rximage.BasePresenter;
import com.image.rx.rximage.BaseView;

import java.util.List;

import rx.Subscription;

/**
 * Created by Administrator on 2016/10/17.
 */

public interface DetailPageContract {
    interface View extends BaseView<Presenter> {

        void setPictureList(List<Picture> pictureList);

    }
    interface  Presenter extends BasePresenter<View> {
        void getPictureList(long id);

    }
}

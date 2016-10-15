package com.image.rx.rximage.view.main_page;

import com.image.rx.data.entity.Gallery;
import com.image.rx.rximage.BasePresenter;
import com.image.rx.rximage.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */

public interface MainPageContract {
    interface View extends BaseView{
        void showLoading();
        void hideLoading();
        void addGalleryList(List<Gallery> list);

    }
    interface  Presenter extends BasePresenter{
        void attachView(View view);
        void loadGalleryList(int page);
        void loadGalleryListOfType(int page, long type);

    }
}

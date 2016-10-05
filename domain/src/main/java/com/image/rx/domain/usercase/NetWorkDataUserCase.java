package com.image.rx.domain.usercase;

import com.image.rx.data.api.ServiceRest;
import com.image.rx.data.entity.Gallery;
import com.image.rx.data.entity.Picture;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2016/10/4.
 */

public class NetWorkDataUserCase {

    public Observable<List<Gallery>> getFrontPhotoList(int page, int rows){
        return ServiceRest.getInstance().getPhotoList(page, rows);
    }

    public Observable<List<Gallery>> getFrontPhotoList(int page, int rows, long id){
        return ServiceRest.getInstance().getPhotoList(page, rows, id);
    }

    public Observable<List<Picture>> getDetailPictureList(long id){
        return ServiceRest.getInstance().getPictureList(id);
    }


}

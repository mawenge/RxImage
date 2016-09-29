package com.image.rx.data.api;

import com.image.rx.data.response.PhotoDetailResponse;
import com.image.rx.data.response.PhotoResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/29.
 */

public interface PhotoService {

    @GET("/list")
    Observable<PhotoResponse> getPhotoList(@Query("page") int page, @Query("rows") int rows);

    @GET("/list")
    Observable<PhotoResponse> getPhotoList(@Query("page") int page, @Query("rows") int rows, @Query("id") long id);

    @GET("/show")
    Observable<PhotoDetailResponse> getPictureList(@Query("id") long id);


}

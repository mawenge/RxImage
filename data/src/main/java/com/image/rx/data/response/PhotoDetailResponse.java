package com.image.rx.data.response;

import android.graphics.Picture;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */

public class PhotoDetailResponse extends CommonResponse{
    PhotoResponse photoResponse;
    @SerializedName("list")
    List<com.image.rx.data.entity.Picture> pictures;


    public PhotoResponse getPhotoResponse() {
        return photoResponse;
    }

    public void setPhotoResponse(PhotoResponse photoResponse) {
        this.photoResponse = photoResponse;
    }

    public List<com.image.rx.data.entity.Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<com.image.rx.data.entity.Picture> pictures) {
        this.pictures = pictures;
    }
}

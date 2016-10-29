package com.image.rx.data.response;

import android.graphics.Picture;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */

public class PhotoDetailResponse extends CommonResponse{

    @Expose
    @SerializedName("list")
    List<com.image.rx.data.entity.Picture> pictures;

    @Expose
    @SerializedName("title")
    String title;

    @Expose
    @SerializedName("size")
    int size;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<com.image.rx.data.entity.Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<com.image.rx.data.entity.Picture> pictures) {
        this.pictures = pictures;
    }
}

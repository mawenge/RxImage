package com.image.rx.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.image.rx.data.entity.Gallery;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */

public class PhotoResponse {
    @Expose
    @SerializedName("status")
    boolean status;
    @Expose
    @SerializedName("total")
    int total;
    @Expose
    @SerializedName("tngou")
    List<Gallery> photoList;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Gallery> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Gallery> photoList) {
        this.photoList = photoList;
    }
}

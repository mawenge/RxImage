package com.image.rx.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/9/29.
 */

public class  Picture {
    @Expose
    @SerializedName("id")
    private long id;
    @Expose
    @SerializedName("gallery")
    private int gallery; //图片库
    @Expose
    @SerializedName("src")
    private String src; //图片地址


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGallery() {
        return gallery;
    }

    public void setGallery(int gallery) {
        this.gallery = gallery;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "src : " + src;
    }
}

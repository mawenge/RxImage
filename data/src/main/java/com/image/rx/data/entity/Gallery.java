package com.image.rx.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/9/29.
 */

public class  Gallery {

    @Expose
    @SerializedName("id")
    private long id;
    @Expose
    @SerializedName("galleryclass")
    private int  galleryclass ;//          图片分类
    @Expose
    @SerializedName("title")
    private String title     ;//          标题
    @Expose
    @SerializedName("img")
    private String img     ;//          图库封面
    @Expose
    @SerializedName("count")
    private int count     ;//          访问数
    @Expose
    @SerializedName("rcount")
    private int rcount     ;//           回复数
    @Expose
    @SerializedName("fcount")
    private int fcount     ;//          收藏数
    @Expose
    @SerializedName("size")
    private int size     ;//      图片多少张


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGalleryclass() {
        return galleryclass;
    }

    public void setGalleryclass(int galleryclass) {
        this.galleryclass = galleryclass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "title ："+ title + "   image == "+ img;
    }
}

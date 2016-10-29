package com.image.rx.rximage.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.image.rx.data.Constant;
import com.image.rx.data.entity.Picture;
import com.image.rx.domain.usercase.NetWorkDataUserCase;
import com.image.rx.rximage.R;
import com.image.rx.rximage.mvp.detail_page.DetailPageContract;
import com.image.rx.rximage.mvp.detail_page.customer_view.PhotoListItemView;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2016/10/17.
 */

public class DetailPagerAdapter extends PagerAdapter {
    private List<Picture> list;
    private String firstUrl;
    private List<PhotoListItemView> listItemViews;

    public DetailPagerAdapter(String firstUrl) {
        this.firstUrl = firstUrl;
    }

    public void setList(List<Picture> list) {
        this.list = list;
        listItemViews = new ArrayList<>(list.size());
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (list == null){
            return 0;
        }
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (listItemViews.size() <= position){
            listItemViews.add(new PhotoListItemView(container.getContext()));
        }
        listItemViews.get(position).setPhotoView(list.get(position).getSrc());
        container.addView(listItemViews.get(position));
        return listItemViews.get(position);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(listItemViews.get(position));
    }
}

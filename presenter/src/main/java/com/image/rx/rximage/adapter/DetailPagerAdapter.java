package com.image.rx.rximage.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.image.rx.data.entity.Picture;
import com.image.rx.rximage.mvp.detail_page.customer_view.PhotoListItemView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */

public class DetailPagerAdapter extends PagerAdapter {
    List<Picture> list;

    public void setList(List<Picture> list) {
        this.list = list;
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
        return new PhotoListItemView(container.getContext());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

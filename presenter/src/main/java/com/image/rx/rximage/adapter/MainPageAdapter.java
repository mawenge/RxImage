package com.image.rx.rximage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.image.rx.data.Constant;
import com.image.rx.data.entity.Gallery;
import com.image.rx.rximage.MessageEvent.LoadNextPageEvent;
import com.image.rx.rximage.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.MainPageViewHolder> {

    private List<Gallery> list = new ArrayList<>();
    private final int PRE_LOAD_NUM = 5;
    private boolean isLoadingNext = false;

    @Override
    public MainPageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_page_list_item_layout, parent, false);
        return new MainPageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainPageViewHolder holder, int position) {
        holder.setContent(list.get(position).getTitle(), list.get(position).getImg());
        if (list.size() - position < PRE_LOAD_NUM && !isLoadingNext){
            EventBus.getDefault().post(new LoadNextPageEvent());
            isLoadingNext = true;
        }
    }

    public void addPages(List<Gallery> addList, boolean needEmpty){
        if (needEmpty){
            list.clear();
        }
        list.addAll(addList);
        isLoadingNext = false;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MainPageViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView image;

        public MainPageViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            image = (ImageView) itemView.findViewById(R.id.front_pic);
        }

        public void setContent(String title, String url){
            this.title.setText(title);
            Glide.with(this.title.getContext()).load(Constant.IMAGE_BASE_URL + url + "_300x200")
                    .into(image);
        }
    }

}

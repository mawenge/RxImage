package com.image.rx.rximage.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.model.stream.StreamUriLoader;
import com.bumptech.glide.load.resource.SimpleResource;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.image.rx.data.Constant;
import com.image.rx.data.entity.Gallery;
import com.image.rx.domain.usercase.NetWorkDataUserCase;
import com.image.rx.rximage.MZApplication;
import com.image.rx.rximage.MessageEvent.LoadNextPageEvent;
import com.image.rx.rximage.R;
import com.image.rx.rximage.glide.LoggingListener;
import com.image.rx.rximage.mvp.main_page.customer_view.ScaledImageView;
import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * Created by Administrator on 2016/10/15.
 */

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.MainPageViewHolder> {

    private Context context;
    private List<Gallery> list = new ArrayList<>();
    private final int PRE_LOAD_NUM = 5;
    private boolean isLoadingNext = false;
    GenericRequestBuilder<Uri, InputStream, BitmapFactory.Options, BitmapFactory.Options> SIZE_REQUEST;
    private OnGalleryClickedListener onGalleryClickedListener;

    public MainPageAdapter(Context context) {
        this.context = context;
        SIZE_REQUEST = Glide // cache for effectiveness (re-use in lists for example) and readability at usage
                .with(context)
                .using(new StreamUriLoader(context), InputStream.class)
                .from(Uri.class)
                .as(BitmapFactory.Options.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new BitmapSizeDecoder())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new LoggingListener<Uri, BitmapFactory.Options>())
        ;
    }

    public void setOnGalleryClickedListener(OnGalleryClickedListener onGalleryClickedListener) {
        this.onGalleryClickedListener = onGalleryClickedListener;
    }


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
        ScaledImageView image;

        MainPageViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            image = (ScaledImageView) itemView.findViewById(R.id.front_pic);

            RxView.clicks(itemView).throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Action1<Void>() {
                        @Override
                        public void call(Void aVoid) {
                            if (onGalleryClickedListener != null){
                                Gallery gallery = list.get(MainPageViewHolder.this.getAdapterPosition());
                                onGalleryClickedListener.onGalleryClicked(gallery.getId(), gallery.getImg());
                            }
                        }
                    });
        }

        void setContent(String title, String url){

            this.title.setText(title);
            // get original size
            SIZE_REQUEST
                    .load(Uri.parse(Constant.IMAGE_BASE_URL + url))
                    .into(new SimpleTarget<BitmapFactory.Options>() { // Target.SIZE_ORIGINAL is hidden in ctor
                        @Override public void onResourceReady(BitmapFactory.Options resource, GlideAnimation glideAnimation) {
                            Log.wtf("SIZE", String.format(Locale.ROOT, "%dx%d", resource.outWidth, resource.outHeight));
                            image.setYxScale((float)resource.outHeight / resource.outWidth);
                        }
                    })
            ;
            Glide.with(this.title.getContext())
                    .load(Constant.IMAGE_BASE_URL + url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new LoggingListener<String, GlideDrawable>())
                    .into(image);
        }
    }


    class BitmapSizeDecoder implements ResourceDecoder<File, BitmapFactory.Options> {
        @Override public Resource<BitmapFactory.Options> decode(File source, int width, int height) throws IOException {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(source.getAbsolutePath(), options);
            return new SimpleResource<>(options);
        }
        @Override public String getId() {
            return getClass().getName();
        }
    }

    public interface OnGalleryClickedListener{
        void onGalleryClicked(long id, String galleryUrl);
    }

}

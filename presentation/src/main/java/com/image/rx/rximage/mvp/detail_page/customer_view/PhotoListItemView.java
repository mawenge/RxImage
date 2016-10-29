package com.image.rx.rximage.mvp.detail_page.customer_view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.image.rx.data.Constant;
import com.image.rx.data.entity.Picture;
import com.image.rx.rximage.R;
import com.image.rx.rximage.glide.LoggingListener;

import rx.internal.schedulers.NewThreadScheduler;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2016/10/17.
 */

public class PhotoListItemView extends FrameLayout {
    private PhotoView photoView;
    private ProgressBar progressBar;
    public PhotoListItemView(Context context) {
        this(context, null);
    }


    public PhotoListItemView(Context context, AttributeSet attr) {
        this(context, attr, 0);
    }

    public PhotoListItemView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        initView();
    }

    private void initView() {
        setBackgroundColor(Color.parseColor("#000000"));
        View.inflate(getContext(), R.layout.detail_page_item_view, this);
        photoView = (PhotoView) findViewById(R.id.photo_view);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void setPhotoView(final String url){
        post(new Runnable() {
            @Override
            public void run() {
                Glide.with(PhotoListItemView.this.getContext())
                        .load(Constant.IMAGE_BASE_URL + url)
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                progressBar.setVisibility(GONE);
                                return false;
                            }
                        })
                        .into(photoView);
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(
                MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.EXACTLY));
    }
}

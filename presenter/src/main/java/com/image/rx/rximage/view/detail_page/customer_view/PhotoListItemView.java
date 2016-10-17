package com.image.rx.rximage.view.detail_page.customer_view;

import android.content.Context;
import android.util.AttributeSet;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2016/10/17.
 */

public class PhotoListItemView extends PhotoView {
    public PhotoListItemView(Context context) {
        super(context);
    }

    public PhotoListItemView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public PhotoListItemView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(
                MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.AT_MOST));
    }
}

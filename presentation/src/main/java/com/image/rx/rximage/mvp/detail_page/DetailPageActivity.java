package com.image.rx.rximage.mvp.detail_page;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.image.rx.data.entity.Picture;
import com.image.rx.rximage.R;
import com.image.rx.rximage.adapter.DetailPagerAdapter;
import com.image.rx.rximage.mvp.BaseActivity;
import com.image.rx.rximage.uitil.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by Administrator on 2016/10/17.
 */

public class DetailPageActivity extends BaseActivity implements DetailPageContract.View {


    @BindView(R.id.list_pager)
    ViewPager listPager;
    @BindView(R.id.page_indicator)
    TextView pageIndicator;

    private DetailPageContract.Presenter mPresenter;
    private DetailPagerAdapter detailPagerAdapter;
    public static final String GALLERY_ID = "gallery_id";
    public static final String FIRST_URL = "first_url";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        mPresenter = new DetailPagePresenter();
        mPresenter.onCreate(this);
        initView();
        initData();
    }

    private void initData() {
        long galleryId = getIntent().getLongExtra(GALLERY_ID, -1);
        if (galleryId == -1){
            ToastUtil.toastShort("相册id不合理！");
            return;
        }

        mPresenter.getPictureList(galleryId);
    }

    private void initView() {

        detailPagerAdapter = new DetailPagerAdapter(getIntent().getStringExtra(FIRST_URL));
        listPager.setAdapter(detailPagerAdapter);
        listPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageIndicator.setText((position + 1) + "/" + detailPagerAdapter.getCount());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public static void navigateToDetailPageActivity(Context context, long id, String firstPhotoUrl){
        Intent intent = new Intent(context, DetailPageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(GALLERY_ID, id);
        intent.putExtra(FIRST_URL, firstPhotoUrl);
        context.startActivity(intent);
    }


    @Override
    public void collectSubscription(Subscription subscription) {
        addSubscription(subscription);
    }

    @Override
    public void setPictureList(List<Picture> pictureList) {
        detailPagerAdapter.setList(pictureList);
        pageIndicator.setText("1/" + pictureList.size());
    }
}

package com.image.rx.rximage.view.detail_page;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.image.rx.rximage.R;
import com.image.rx.rximage.adapter.DetailPagerAdapter;
import com.image.rx.rximage.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        mPresenter = new DetailPagePresenter();
        mPresenter.attachView(this);
        initView();
    }

    private void initView() {
        detailPagerAdapter = new DetailPagerAdapter();
        listPager.setAdapter(detailPagerAdapter);
    }


}

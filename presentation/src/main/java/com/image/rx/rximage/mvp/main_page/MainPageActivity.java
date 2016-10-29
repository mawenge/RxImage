package com.image.rx.rximage.mvp.main_page;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.image.rx.data.entity.Gallery;
import com.image.rx.rximage.R;
import com.image.rx.rximage.adapter.DetailPagerAdapter;
import com.image.rx.rximage.adapter.MainPageAdapter;
import com.image.rx.rximage.mvp.BaseActivity;
import com.image.rx.rximage.mvp.detail_page.DetailPageActivity;
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerViewAdapter;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxAdapter;
import com.jakewharton.rxbinding.widget.RxAdapterView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

public class MainPageActivity extends BaseActivity implements MainPageContract.View{

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.main_page_list)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private MainPageContract.Presenter mPresenter;
    private MainPageAdapter galleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPagePresenter();
        mPresenter.onCreate(this);
        ButterKnife.bind(this);
        initView();
        mPresenter.loadGalleryList(1);
    }

    private void initView() {
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        galleryAdapter = new MainPageAdapter();
        recyclerView.setAdapter(galleryAdapter);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshGalleryList();
            }
        });
        galleryAdapter.setOnGalleryClickedListener(new MainPageAdapter.OnGalleryClickedListener() {
            @Override
            public void onGalleryClicked(long id, String galleryUrl) {
                DetailPageActivity.navigateToDetailPageActivity(MainPageActivity.this, id, galleryUrl);
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void addGalleryList(List<Gallery> list, boolean needClear) {
        galleryAdapter.addPages(list, needClear);
    }

    @Override
    public void collectSubscription(Subscription subscription) {
        addSubscription(subscription);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }
}

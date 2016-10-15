package com.image.rx.rximage.view.main_page;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.image.rx.data.entity.Gallery;
import com.image.rx.rximage.R;
import com.image.rx.rximage.adapter.MainPageAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainPageActivity extends AppCompatActivity implements MainPageContract.View{

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.main_page_list)
    RecyclerView recyclerView;
    private MainPageContract.Presenter mPresenter;
    private MainPageAdapter galleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPagePresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        initView();
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
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadGalleryList(1);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void addGalleryList(List<Gallery> list) {
        galleryAdapter.addPages(list, false);
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

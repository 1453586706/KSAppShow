package com.example.kuaishouappshow.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.kuaishouappshow.R;
import com.example.kuaishouappshow.adapter.SelcityvideosAdapter;
import com.example.kuaishouappshow.entity.SelcityvideosEntity;
import com.example.kuaishouappshow.presenter.SelcityvideosPresenter;
import com.example.lib_core.mvp.view.BaseActivity;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstActivity extends BaseActivity<SelcityvideosPresenter, List<SelcityvideosEntity>> {
    @BindView(R.id.login_logo)
    TextView loginLogo;
    @BindView(R.id.recyclerMode)
    RecyclerView recyclerMode;
    @BindView(R.id.pullMode)
    PullToRefreshLayout pullMode;
    private List<SelcityvideosEntity> selcityvideosEntities = new ArrayList<>();
    private SelcityvideosAdapter selcityvideosAdapter;
    private String userId;
    private int page = 1;

    @Override
    public int bindLayout() {
        return R.layout.activity_first;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerMode);
        selcityvideosAdapter = new SelcityvideosAdapter(R.layout.item_video, selcityvideosEntities);
    //    recyclerMode.setLayoutManager(new LinearLayoutManager(this));
       recyclerMode.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerMode.setAdapter(selcityvideosAdapter);
        selcity_pulltorefresh();
        selcityvideosAdapter();
        selcityvideosAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(FirstActivity.this, VideoShowActivity.class);
                intent.putExtra("videoUrl",selcityvideosEntities.get(position).getVedioUrl());
                startActivity(intent);
            }
        });
    }
    @Override
    public void initView() {
    }



    private void selcityvideosAdapter() {
        selcityvideosAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(this, Video_ShowActivity.class);
//                startActivity(intent);
            }
        });

    }

    private void selcity_pulltorefresh() {
        pullMode.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                page=2;
                selcityvideosEntities.removeAll(selcityvideosEntities);
                selcityvideosAdapter.notifyDataSetChanged();
                userId = getSharedPreferences("login", 0).getString("userId", "");
                mPresenter.SelcityVideos(String.valueOf(page), userId);
                pullMode.finishRefresh();
            }

            @Override
            public void loadMore() {
                page++;
                userId = getSharedPreferences("login", 0).getString("userId", "");
                mPresenter.SelcityVideos(String.valueOf(page), userId);
                pullMode.finishLoadMore();
            }
        });
    }

    @Override
    public void inject() {

    }

    @Override
    public void initData() {
        mPresenter=new SelcityvideosPresenter(this);
   //     userId = getSharedPreferences("login", 0).getString("userId", "");
        mPresenter.SelcityVideos(String.valueOf(page), userId);
        mPresenter.postBodyHttpData();


    }

    @Override
    public void onHttpReceivedError(String msg) {
        showToast(msg);
    }

    @Override
    public void onHttpReceived(List<SelcityvideosEntity> data) {
        selcityvideosEntities.addAll(data);
        Log.i("pyx", "SuccessSelcityvideos: " + data.toString());
        selcityvideosAdapter.notifyDataSetChanged();
    }


    @OnClick(R.id.login_logo)
    public void onViewClicked() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}

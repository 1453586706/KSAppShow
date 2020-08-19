package com.example.kuaishouappshow.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.kuaishouappshow.R;
import com.example.kuaishouappshow.adapter.SelcityvideosAdapter;
import com.example.kuaishouappshow.entity.SelcityvideosEntity;
import com.example.kuaishouappshow.presenter.SelcityvideosPresenter;
import com.example.kuaishouappshow.view.activity.VideoShowActivity;
import com.example.lib_core.mvp.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AttentionFragment extends BaseFragment<SelcityvideosPresenter, List<SelcityvideosEntity>> {
    private RecyclerView recyclerMode;
    private List<SelcityvideosEntity> selcityvideosEntities = new ArrayList<>();
    private SelcityvideosAdapter selcityvideosAdapter;
    private String userId;
    private int page = 1;

    @Override
    public int bindLayout() {
        return R.layout.fragment_layout;
    }

    @Override
    public void initView() {
        recyclerMode = (RecyclerView) findViewById(R.id.recyclerMode);
//        PagerSnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerMode);
//        selcityvideosAdapter = new SelcityvideosAdapter(R.layout.item_video, selcityvideosEntities);
      recyclerMode.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
     //   recyclerMode.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerMode.setAdapter(selcityvideosAdapter);

       selcityvideosAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
               Intent intent = new Intent(getContext(), VideoShowActivity.class);
               intent.putExtra("videoUrl",selcityvideosEntities.get(position).getVedioUrl());
               startActivity(intent);
           }
       });
    }

    @Override
    public void initData() {
        mPresenter = new SelcityvideosPresenter(this);
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

}

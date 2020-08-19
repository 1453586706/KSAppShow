package com.example.kuaishouappshow.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.kuaishouappshow.R;
import com.example.kuaishouappshow.entity.SelcityvideosEntity;

import java.util.List;

public class SelcityvideosAdapter extends BaseQuickAdapter<SelcityvideosEntity, BaseViewHolder> {
    public SelcityvideosAdapter(int layoutResId, @Nullable List<SelcityvideosEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelcityvideosEntity item) {
        ImageView view = helper.getView(R.id.iv_video_cover);
        Glide.with(mContext).load(item.getCoverImg()).into(view);
        helper.addOnClickListener(R.id.iv_video_cover);

    }
}

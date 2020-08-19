package com.example.kuaishouappshow.presenter;

import com.example.kuaishouappshow.entity.LoginUserEntity;
import com.example.kuaishouappshow.entity.SelcityvideosEntity;
import com.example.lib_core.mvp.presenter.BasePresenter;
import com.example.lib_core.mvp.view.IBaseView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class SelcityvideosPresenter extends BasePresenter<List<SelcityvideosEntity>, IBaseView<List<SelcityvideosEntity>>>{
    public SelcityvideosPresenter(IBaseView<List<SelcityvideosEntity>> mView) {
        super(mView);
    }

    @Override
    protected String getPath() {
        return "videovalues/selcityvideos";
    }

    @Override
    public Type getEntityType() {
        return new TypeToken<List<SelcityvideosEntity>>(){}.getType();
    }
    public void SelcityVideos(String page, String userId){
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("userId",userId);
        String jsonStr = new Gson().toJson(map);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),jsonStr);
        updataRequestBody(body);
    }

    @Override
    public HashMap<String, Object> getMap() {
        return super.getMap();
    }
}

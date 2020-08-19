package com.example.kuaishouappshow.presenter;


import com.example.kuaishouappshow.entity.LoginUserEntity;
import com.example.lib_core.mvp.presenter.BasePresenter;
import com.example.lib_core.mvp.view.IBaseView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;


public class LoginPresenter extends BasePresenter<LoginUserEntity, IBaseView<LoginUserEntity>> {
    public LoginPresenter(IBaseView mView) {
        super(mView);
    }

    @Override
    protected String getPath() {
        return "videouser/login";
    }

    @Override
    public Type getEntityType() {
        return new TypeToken<LoginUserEntity>(){}.getType();
    }

    public void addMap(String phone, String password){
        HashMap<String, Object> map = new HashMap<>();
        map.put("phoneNum",phone);
        map.put("userPassWord",password);
       // updataParamsMap(map);
        String jsonStr = new Gson().toJson(map);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),jsonStr);
        updataRequestBody(body);
    }

    @Override
    public HashMap<String, Object> getMap() {
        return super.getMap();
    }
}

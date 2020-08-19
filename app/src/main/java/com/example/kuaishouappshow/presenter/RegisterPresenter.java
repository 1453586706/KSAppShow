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


public class RegisterPresenter extends BasePresenter<LoginUserEntity, IBaseView<LoginUserEntity>> {
    public RegisterPresenter(IBaseView mView) {
        super(mView);
    }

    @Override
    protected String getPath() {
        return "videouser/register";
    }

    @Override
    public Type getEntityType() {
        return new TypeToken<LoginUserEntity>(){}.getType();
    }

    public void addMap(String phone, String password){ // 测试框架(登录)  返回会数据用户没有注册(注册之后登录即可)
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

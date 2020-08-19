package com.example.lib_core.network;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitManager {
    private static NetApi netApi;
    private String BASE_URL;
    private Gson gson;

    private static RetrofitManager retrofitManager;
    private RetrofitManager(){}

    public static RetrofitManager getInstance(){
        if (retrofitManager == null){
            retrofitManager = new RetrofitManager();
        }
        return retrofitManager;
    }

    public NetApi getNetApi(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS) //设置超时
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                //  .addInterceptor(new TokenInterceptor()) // 添加token拦截器(向服务器请求数据, 必须是登录状态(用token来验证))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())//Gson解析器的创建工厂类
                .build();
        return retrofit.create(NetApi.class);
    }
    public RetrofitManager setBaseUrl(String url){
        BASE_URL = url;
        return this;
    }
    public Gson getGson(){
        if (gson == null){
            gson = new Gson();
        }
        return gson;
    }

}

package com.example.lib_core.network;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface NetApi {
    String BASE_URL_QS ="http://49.233.93.155:8080/";
    String BASE_URL_XG = "http://172.81.227.127:8055/";


    //如果类型不确定的话，可以使用ResponseBody类型。它支持所有类型
    @GET("{path}")
    Observable<ResponseBody> getData(@Path(value = "path", encoded = true) String path
            , @QueryMap HashMap<String, Object> params);

    @POST("{path}")
    @FormUrlEncoded  // 表单形式 以 key value键值对的方式传递数据
    Observable<ResponseBody> postData(@Path(value = "path", encoded = true) String path
            , @FieldMap HashMap<String, Object> params);

    // post json 形式的请求
    @POST("{path}")
    Observable<ResponseBody> postBody(@Path(value = "path", encoded = true) String path
            , @Body RequestBody body);

}

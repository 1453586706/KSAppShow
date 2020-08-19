package com.example.lib_core.mvp.presenter;

import com.example.lib_core.mvp.view.IBaseView;
import com.example.lib_core.network.NetApi;
import com.example.lib_core.network.RetrofitManager;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public abstract class BasePresenter<T,V extends IBaseView<T>> implements IBasePresenter{
    protected V mView;
    // 构造
    public BasePresenter(V mView) {
        this.mView = mView;
    }
    // 防止一些内存溢出(销毁操作方便gc回收)
    @Override
    public void destroy() {
        if (mView!=null){
            mView = null;
        }
    }
    // 获取路径(子类必须实现)
    protected abstract String getPath();
    // 获取参数 (map)
    private HashMap<String,Object> params = new HashMap<>(); // 默认map

    public void updataParamsMap(HashMap<String,Object> params){
        this.params = params;
    } // 填充map

    public  HashMap<String, Object> getMap(){
        return params;
    } // 返回map 子类若不重写(直接返回默认)
    // 获取参数(body)
    private RequestBody body;
    public  RequestBody getBody(){
        return body;
    } // 获取body


    public BasePresenter updataRequestBody(RequestBody requestBody){
        this.body = requestBody;
        return this;
    } // 给body 赋值

    // 获取解析类型 (子类必须重写)
    public abstract Type getEntityType();
    // 解析数据 放回 view
    private void handleNextDataNoBas(ResponseBody responseBody){
        try {
            T data = RetrofitManager.getInstance().getGson().fromJson(responseBody.string(),getEntityType());
            mView.onHttpReceived(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // get请求
    @Override
    public void getHttpData() {
        RetrofitManager.getInstance().setBaseUrl(NetApi.BASE_URL_XG)
                .getNetApi()
                .getData(getPath(),getMap())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        handleNextDataNoBas(responseBody);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onHttpReceivedError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    // post请求(表单)
    @Override
    public void postHttpData() {
        RetrofitManager.getInstance().setBaseUrl(NetApi.BASE_URL_XG)
                .getNetApi()
                .postData(getPath(),getMap())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        handleNextDataNoBas(responseBody);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onHttpReceivedError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    // post请求(json)
    @Override
    public void postBodyHttpData() {
        RetrofitManager.getInstance().setBaseUrl(NetApi.BASE_URL_XG)
                .getNetApi()
                .postBody(getPath(),getBody())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        handleNextDataNoBas(responseBody);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onHttpReceivedError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}

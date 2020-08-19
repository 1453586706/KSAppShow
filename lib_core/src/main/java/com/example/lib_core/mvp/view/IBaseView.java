package com.example.lib_core.mvp.view;


public interface IBaseView<T> { // 泛型为获取的数据类型
    void showToast(String msg); // 提示用户的信息

    void onHttpReceivedError(String msg); //错误信息

    void onHttpReceived(T data); //获取到数据

//    default void onHttpReceivedError(String msg){} //错误信息
//    default void onHttpReceived(T data){} //获取到数据
}

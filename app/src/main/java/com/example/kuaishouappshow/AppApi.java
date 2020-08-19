package com.example.kuaishouappshow;
import com.example.kuaishouappshow.entity.InsfollowEntity;
import com.example.kuaishouappshow.entity.LoginUserEntity;
import com.example.kuaishouappshow.entity.SelcityvideosEntity;
import com.example.kuaishouappshow.entity.SelfansEntity;
import com.example.kuaishouappshow.entity.UnfollowEntity;
import com.example.kuaishouappshow.entity.UservaluesEntity;
import java.util.List;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * app模块全部使用网络接口
 * */
public interface AppApi {
    //TODO:登录
    @POST("videouser/login")
    Observable<LoginUserEntity> login(@Body RequestBody body);
    //TODO:注册
    @POST("videouser/register")
    Observable<LoginUserEntity> register(@Body RequestBody body);
    //TODO:用户详情
    @POST("videouser/uservalues")
    Observable<UservaluesEntity> uservalues(@Body RequestBody body);
    //TODO:修改用户
    @POST("videouser/setuser")
    Observable<UservaluesEntity> setuser(@Body RequestBody body);
    //TODO:添加关注
    @POST("videorelationship/insfollow")
    Observable<List<UnfollowEntity>> insfollow(@Body RequestBody body);
    //TODO:粉丝列表
    @POST("videorelationship/selfans")
    Observable<List<SelfansEntity>> selfans(@Body RequestBody body);
    //TODO:粉丝数量
    @POST("videorelationship/selfansnum")
    Observable<List<InsfollowEntity>> selfansnum(@Body RequestBody body);
    //TODO:关注列表
    @POST("videorelationship/selfollow")
    Observable<List<SelfansEntity>> selfollow(@Body RequestBody body);
    //TODO:关注数
    @POST("/videorelationship/selfollownum")
    Observable<List<InsfollowEntity>> selfollownum(@Body RequestBody body);
    //TODO:解除关注
    @POST("videorelationship/unfollow")
    Observable<List<UnfollowEntity>> unfollow(@Body RequestBody body);
    //TODO:推荐用户
    @POST("videorecommend/recommend")
    Observable<List<UservaluesEntity>> recommend(@Body RequestBody body);
    //TODO:同城短视频列表
    @POST("videovalues/selcityvideos")
    Observable<List<SelcityvideosEntity>> selcityvideos(@Body RequestBody body);
    //TODO:关注短视频列表
    @POST("videovalues/selfollowvideos")
    Observable<List<SelcityvideosEntity>> selfollowvideos(@Body RequestBody body);
    //TODO:推荐短视频列表
    @POST("videovalues/selrecommendvideos")
    Observable<List<SelcityvideosEntity>> selrecommendvideos(@Body RequestBody body);
}

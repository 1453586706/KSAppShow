package com.example.kuaishouappshow.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.dou361.ijkplayer.widget.IjkVideoView;
import com.example.kuaishouappshow.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuSurfaceView;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class VideoShowActivity extends AppCompatActivity {


    @BindView(R.id.ijkVideoView)
    IjkVideoView ijkVideoView;

    @BindView(R.id.send)
    Button send;
    @BindView(R.id.showtext)
    EditText showtext;
    @BindView(R.id.danmakuView)
    DanmakuSurfaceView danmakuView;

    private DanmakuContext danmakuContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_show);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String videoUrl = intent.getStringExtra("videoUrl");
        ijkVideoView.setVideoPath(videoUrl);
        ijkVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                iMediaPlayer.start();
            }
        });


        danmakuView.setZOrderOnTop(true);
        danmakuView.getHolder().setFormat(PixelFormat.TRANSLUCENT);

        init();

    }

    private void init() {
        //禁止弹幕重叠
        HashMap<Integer, Boolean> overlapEnable = new HashMap<>();
        overlapEnable.put(BaseDanmaku.TYPE_SCROLL_LR, true); //从右往左移动
        overlapEnable.put(BaseDanmaku.TYPE_FIX_TOP, true);//在顶部显示
        danmakuContext = DanmakuContext.create();//创建弹幕上下文

        danmakuContext.setDuplicateMergingEnabled(false)//不让两个弹幕合并
                .setScrollSpeedFactor(5.0f)//弹幕的速度因子。
                .setScaleTextSize(1.5f) //文字大小的缩放
                .setMaximumLines(null)//如果为空，代表着弹幕显示的行数是不受限制的
                .preventOverlapping(overlapEnable);//如果不为空的话，代表着弹幕是不能重叠的


        //设置callback， 监听当前的danmakuview是否准备好

        danmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() { //准备好了
                danmakuView.start();//准备好之后，启动弹幕库
            }

            //更新时间的回调
            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });

        //两个参数：第一个参数配置一个danmaku数据的解析器, 另一个是danmaku的配置项
        danmakuView.prepare(danmakuParser, danmakuContext);//做好准备
        danmakuView.enableDanmakuDrawingCache(true);//使用弹幕库的缓存
    }

    //生成一个弹幕解析器
    BaseDanmakuParser danmakuParser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };

    //发送弹幕
    private void sendDanmaku() {
        //创建发送弹幕的一个数据

        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_LR);
        String trim = showtext.getText().toString().trim();
        danmaku.text = trim;
        danmaku.padding = 5;//两个弹幕的间隔
        danmaku.priority = 0;//弹幕的优先级
        danmaku.isLive = true;//弹幕是不是实时发送
        danmaku.setTime(danmakuView.getCurrentTime() + 1200);//设置时间(时间必须使用danmakuView获取时间)，弹幕按照时间去排序
        danmaku.textSize = 30f; //设置字体大小
        danmaku.textColor = Color.RED; //设置字体颜色
        danmaku.textShadowColor = Color.GREEN;//字体背景颜色
        danmaku.borderColor = Color.BLUE;

        danmakuView.addDanmaku(danmaku);//发送弹幕信息到页面上
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            sendDanmaku();//发送一条弹幕消息
            //   handler.sendEmptyMessageDelayed(0, 200);//每隔200毫秒发送一条弹幕消息
        }
    };

    @OnClick(R.id.send)
    public void onViewClicked() {
        handler.sendEmptyMessage(0);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (danmakuView.isPrepared() && danmakuView.isPaused()) {
            danmakuView.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (danmakuView.isPrepared()) {
            danmakuView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        danmakuView.release();
        danmakuView = null;

        handler.removeCallbacksAndMessages(null);//避免内存泄漏.
    }
}

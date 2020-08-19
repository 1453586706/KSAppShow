package com.example.kuaishouappshow.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.kuaishouappshow.MainActivity;
import com.example.kuaishouappshow.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdvertisingActivity extends AppCompatActivity {

    @BindView(R.id.advImg)
    ImageView advImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertising);
        ButterKnife.bind(this);
        Glide.with(this).load("https://n.sinaimg.cn/tech/transform/520/w300h220/20200403/8c85-irtymmv5597576.gif").into(advImg);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(AdvertisingActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);


    }
}

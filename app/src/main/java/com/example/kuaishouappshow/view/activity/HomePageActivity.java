package com.example.kuaishouappshow.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.kuaishouappshow.R;
import com.example.kuaishouappshow.entity.SelcityvideosEntity;
import com.example.kuaishouappshow.presenter.SelcityvideosPresenter;
import com.example.kuaishouappshow.view.fragment.AttentionFragment;
import com.example.kuaishouappshow.view.fragment.FindFragment;
import com.example.kuaishouappshow.view.fragment.LocalFragment;
import com.example.lib_core.mvp.view.BaseActivity;
import com.flyco.tablayout.SlidingTabLayout;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomePageActivity extends BaseActivity{
    @BindView(R.id.pagerMode)
    ViewPager pagerMode;
    @BindView(R.id.menu)
    ImageView menu;
    @BindView(R.id.tabMode)
    SlidingTabLayout tabMode;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private LocalFragment localFragment=new LocalFragment();
    private FindFragment findFragment=new FindFragment();
    private AttentionFragment attentionFragment=new AttentionFragment();
    private SlidingMenu slidingMenu;
    private int menuWith;

    @Override
    public int bindLayout() {
        return R.layout.activity_homepage;
    }

    @Override
    public void initView() {

    }

    @Override
    public void inject() {
    }

    @Override
    public void initData() {

    }

    @Override
    public void onHttpReceivedError(String msg) {

    }

    @Override
    public void onHttpReceived(Object data) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        mFragments.add(attentionFragment);
        mFragments.add(findFragment);
        mFragments.add(localFragment);
        tabMode.setViewPager(pagerMode, new String[]{"关注", "发现", "同城"}, this, mFragments);

        pagerMode.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position == 0 ||position == 1){
                    tabMode.setTextSelectColor(Color.BLACK);
                    tabMode.setTextUnselectColor(Color.BLACK);
                }else {
                    tabMode.setTextSelectColor(Color.WHITE);
                    tabMode.setTextUnselectColor(Color.WHITE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        View slid_menu = getLayoutInflater().inflate(R.layout.sliding_menu, null);
        menuWith = getWindowManager().getDefaultDisplay().getWidth() / 3;
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMenu(slid_menu);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setBehindOffset(menuWith);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

    }

    @OnClick(R.id.menu)
    public void onViewClicked() {
    }
}

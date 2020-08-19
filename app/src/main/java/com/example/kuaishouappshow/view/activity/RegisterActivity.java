package com.example.kuaishouappshow.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kuaishouappshow.R;
import com.example.kuaishouappshow.entity.LoginUserEntity;
import com.example.kuaishouappshow.presenter.RegisterPresenter;
import com.example.lib_core.mvp.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter, LoginUserEntity> {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    public int bindLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {

    }

    @Override
    public void inject() {

    }

    @Override
    public void initData() {
        mPresenter=new RegisterPresenter(this);

    }

    @Override
    public void onHttpReceivedError(String msg) {
        showToast(msg);

    }

    @Override
    public void onHttpReceived(LoginUserEntity data) {
        showToast("注册成功");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                if (mPresenter != null){
                    mPresenter.addMap(etUsername.getText().toString(),
                            etPassword.getText().toString());
                    mPresenter.postBodyHttpData();
                }
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.destroy();
        }
    }
}

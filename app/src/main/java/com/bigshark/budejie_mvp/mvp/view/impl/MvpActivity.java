package com.bigshark.budejie_mvp.mvp.view.impl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.mvp.view.IMvpView;

/**
 * 将我们的mvp架构集成到我们的Activity
 * <p>
 * 这个activity是mvp框架的
 * Created by bigShark on 2016/5/26.
 */
public abstract class MvpActivity<P extends MvpBasePresenter> extends AppCompatActivity implements IMvpView {

    protected P presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //presenter 关联 view
        presenter = bindPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }

    }

    public abstract P bindPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //当activity销毁的时候  接触绑定
        if (presenter != null) {
            presenter.detachView();
        }
    }
}

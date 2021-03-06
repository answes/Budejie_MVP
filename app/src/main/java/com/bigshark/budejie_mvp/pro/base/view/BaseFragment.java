package com.bigshark.budejie_mvp.pro.base.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.mvp.view.impl.MvpFragment;

/**
 * Created by bigShark on 2016/5/26.
 */
public abstract class BaseFragment<P extends MvpBasePresenter> extends MvpFragment<P> {
    //这里缓存我们的视图
    private View viewContent;   //缓存视图

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (viewContent == null) {
            viewContent = inflater.inflate(getContentView(), container, false);
            initContentView(viewContent);
            initData();
        }

        //判断Fragment 对应的activity是否纯在这个视图
        ViewGroup parent = (ViewGroup) viewContent.getParent();

        if (parent != null) {
            //如果存在 就移除，重新添加，这样的方式就可以缓存视图
            parent.removeView(viewContent);
        }

        return viewContent;
    }

    @Override
    public P bindPresenter() {
        return null;
    }

    public abstract int getContentView();

    public abstract void initContentView(View viewContent);

    public void initData() {
    }

}

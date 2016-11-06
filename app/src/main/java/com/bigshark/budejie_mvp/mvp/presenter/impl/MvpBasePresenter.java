package com.bigshark.budejie_mvp.mvp.presenter.impl;

import com.bigshark.budejie_mvp.mvp.presenter.IMvpPresenter;
import com.bigshark.budejie_mvp.mvp.view.IMvpView;

/**
 * Created by bigShark on 2016/5/26.
 */
public class MvpBasePresenter<V extends IMvpView> implements IMvpPresenter<V> {
    private V view;

    @Override
    public void attachView(V view) {

    }

    @Override
    public void detachView() {

    }
}

package com.bigshark.budejie_mvp.mvp.presenter;

import com.bigshark.budejie_mvp.mvp.view.IMvpView;

/**
 * Created by bigShark on 2016/5/26.
 */
public interface IMvpPresenter<V extends IMvpView> {
    /**
     * 绑定view
     * @param view
     */
    public void  attachView(V view);

    /**
     * 解除对view的绑定
     */
    public void detachView();
}

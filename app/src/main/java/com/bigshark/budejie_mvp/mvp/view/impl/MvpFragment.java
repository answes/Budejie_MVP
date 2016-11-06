package com.bigshark.budejie_mvp.mvp.view.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.mvp.view.IMvpView;

/**
 * Created by bigShark on 2016/5/26.
 */
public abstract class MvpFragment<P extends MvpBasePresenter> extends Fragment implements IMvpView {

    protected P presenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //绑定view
        presenter = bindPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }

    }

    public abstract P bindPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }
}

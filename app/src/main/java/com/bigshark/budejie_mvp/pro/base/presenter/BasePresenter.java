package com.bigshark.budejie_mvp.pro.base.presenter;


import android.content.Context;

import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.google.gson.Gson;

/**
 * Created by bigShark on 2016/5/29.
 */
public abstract class BasePresenter extends MvpBasePresenter {

    private Context context;
    private Gson gson;

    public BasePresenter(Context context) {
        this.context = context;
        gson = new Gson();
    }

    public Context getContext() {
        return context;
    }

    public Gson getGson() {
        return gson;
    }

    public interface OnUiThreadListener<T> {
        void onResult(T result);
    }
}

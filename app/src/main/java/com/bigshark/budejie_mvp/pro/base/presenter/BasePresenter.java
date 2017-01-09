package com.bigshark.budejie_mvp.pro.base.presenter;


import android.content.Context;

import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.model.BaseModel;
import com.google.gson.Gson;

/**
 * Created by bigShark on 2016/5/29.
 */
public abstract class BasePresenter<M extends BaseModel> extends MvpBasePresenter {

    private Context context;
    private Gson gson;
    private M model;

    public BasePresenter(Context context) {
        this.context = context;
        gson = new Gson();
        this.model = bindModel();
    }

    public Context getContext() {
        return context;
    }

    public Gson getGson() {
        return gson;
    }

    public M getModel() {
        return model;
    }

    public abstract M bindModel();

    public interface OnUiThreadListener<T> {
        void onResult(T result);
    }
}

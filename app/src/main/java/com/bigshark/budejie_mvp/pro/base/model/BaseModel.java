package com.bigshark.budejie_mvp.pro.base.model;

import android.content.Context;

import com.bigshark.budejie_mvp.mvp.model.IMvpModel;

/**
 * Created by bigShark on 2016/5/29.
 */
public abstract class BaseModel implements IMvpModel {

    private Context context;

    public BaseModel(Context context) {
        this.context = context;
    }

    public String getServerUrl() {
        return "http://api.budejie.com";
    }
}

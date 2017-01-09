package com.bigshark.budejie_mvp.pro.essence.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.bigshark.budejie_mvp.bean.PostsListBean;
import com.bigshark.budejie_mvp.http.utils.MyHttpUtils;
import com.bigshark.budejie_mvp.pro.base.presenter.BasePresenter;
import com.bigshark.budejie_mvp.pro.essence.model.EssenceVideoModel;

import java.util.List;

/**
 * Created by bigShark on 2016/5/29.
 */
public class EssenceVideoPresenter extends BasePresenter<EssenceVideoModel> {

    private EssenceVideoModel essenceVideoModel;

    private int page = 0;
    private String maxtime = null;


    public EssenceVideoPresenter(Context context) {
        super(context);
        this.essenceVideoModel = new EssenceVideoModel(context);
    }

    @Override
    public EssenceVideoModel bindModel() {
        return new EssenceVideoModel(getContext());
    }

    public void getEssenceList(int type,
                               final boolean isDownRefresh,
                               final OnUiThreadListener<List<PostsListBean.PostList>> onUiThreadListener) {
        if (isDownRefresh) {
            maxtime = null;

        }
        //执行网络请求
        essenceVideoModel.getEssenceLsit(type, page, maxtime, new MyHttpUtils.OnHttpResultListener() {
            @Override
            public void onResult(String result) {
                if (TextUtils.isEmpty(result)) {
                    onUiThreadListener.onResult(null);
                } else {
                    PostsListBean postsListBean = getGson().fromJson(result, PostsListBean.class);
                    if (postsListBean != null && postsListBean.getInfo() != null) {
                        maxtime = postsListBean.getInfo().getMaxtime();
                    }

                    if (isDownRefresh) {
                        page = 0;
                        ;
                    } else {
                        page++;
                    }

                    onUiThreadListener.onResult(postsListBean.getList());
                }
            }
        });
    }

}

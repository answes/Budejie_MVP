package com.bigshark.budejie_mvp.mvp.view.impl;

import com.bigshark.budejie_mvp.mvp.view.IMvpView;

/**
 * 请求数据，刷新UI界面监听(接口) loading页
 * <p>
 * Created by bigShark on 2016/5/26.
 */
public interface MvpLceView<M> extends IMvpView {
    /**
     * 显示加载中的视图(加载类型)
     *
     * @param pullToRefresh
     */
    public void showLoading(boolean pullToRefresh);

    /**
     * 显示ContentView
     */
    public void showContent();

    /**
     * 显示错误信息
     */

    public void showError(Exception e, boolean pullToRefresh);

    /**
     * 绑定数据
     *
     * @param data
     */
    public void showData(M data);
}

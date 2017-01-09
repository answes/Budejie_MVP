package com.bigshark.budejie_mvp.http;

/**
 * 用来执行网络请求命令接口
 * Created by bigShark on 2016/5/29.
 */
public interface IHttpCommand<T> {
    String execute(String url, IRequestParam<T> requestParam);
    //String execute();
}

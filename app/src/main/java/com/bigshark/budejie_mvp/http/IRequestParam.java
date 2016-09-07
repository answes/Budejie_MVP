package com.bigshark.budejie_mvp.http;

/**
 * 请求参数封装
 * Created by bigShark on 2016/5/29.
 */
public interface IRequestParam<T> {

    void put(String key, Object value);

    Object get(String key);

    int size();

    T getRequestParam();
}

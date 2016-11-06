package com.bigshark.budejie_mvp.http.impl;

import com.bigshark.budejie_mvp.http.IRequestParam;

import java.util.HashMap;

/**
 * 参数的具体实现类
 * Created by bigShark on 2016/5/29.
 */
public class RequestParam implements IRequestParam<HashMap<String, Object>> {
    private HashMap<String, Object> paramMap = new HashMap<String, Object>();

    @Override
    public void put(String key, Object value) {
        paramMap.put(key, value);
    }

    @Override
    public Object get(String key) {
        return paramMap.get(key);
    }

    @Override
    public int size() {
        return paramMap.size();
    }

    @Override
    public HashMap<String, Object> getRequestParam() {
        return paramMap;
    }
}

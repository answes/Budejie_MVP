package com.bigshark.budejie_mvp.http.impl;

import com.bigshark.budejie_mvp.http.IHttpCommand;
import com.bigshark.budejie_mvp.http.IRequestParam;

import java.util.HashMap;

/**
 * 网络执行具体实现类
 * Created by bigShark on 2016/5/29.
 */
public class HttpCommand implements IHttpCommand<HashMap<String, Object>> {
    @Override
    public String execute(String url, IRequestParam<HashMap<String, Object>> requestParam) {
        return null;
    }
}

package com.bigshark.budejie_mvp.http.impl;

import com.bigshark.budejie_mvp.http.IHttpCommand;
import com.bigshark.budejie_mvp.http.IRequestParam;
import com.bigshark.budejie_mvp.http.utils.MyHttpUtils;

import java.util.HashMap;

/**
 * 网络执行具体实现类
 * Created by bigShark on 2016/5/29.
 */
public class HttpCommand implements IHttpCommand<HashMap<String, String>> {
    @Override
    public String execute(String url, IRequestParam<HashMap<String, String>> requestParam) {
        try {
            return MyHttpUtils.getAliAlligal(url,requestParam.getRequestParam());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

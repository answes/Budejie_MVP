package com.bigshark.budejie_mvp.pro.essence.model;

import android.content.Context;
import android.text.TextUtils;

import com.bigshark.budejie_mvp.http.impl.HttpCommand;
import com.bigshark.budejie_mvp.http.impl.RequestParam;
import com.bigshark.budejie_mvp.http.utils.HttpTask;
import com.bigshark.budejie_mvp.http.utils.HttpUtils;
import com.bigshark.budejie_mvp.pro.base.model.BaseModel;

/**
 * 请求网络数据   或者 加载本地数据库缓存数据
 * 加载SD卡数据等待
 * Created by bigShark on 2016/5/29.
 */
public class EssenceVideoModel extends BaseModel {

    public EssenceVideoModel(Context context) {
        super(context);
    }

    private String getEssenceListUrl() {
        return getServerUrl().concat("/api/api_open.php");
    }

    /**
     * 获取精华数据
     *
     * @param type                 -- 数据类型  例如 图片，视频 。0 表示全部
     * @param page                 -- 页码
     * @param maxtime              -- 加载更多的时候需要传入此字段
     * @param onHttpResultListener -- 返回数据回调监听
     */
    public void getEssenceLsit(int type, int page, String maxtime, HttpUtils.OnHttpResultListener onHttpResultListener) {
        RequestParam requestParam = new RequestParam();
        requestParam.put("a", "list");
        requestParam.put("c", "data");
        requestParam.put("type", type);
        requestParam.put("page", page);
        if (!TextUtils.isEmpty(maxtime)) {
            requestParam.put("maxtime", maxtime);
        }
        HttpTask httpTask = new HttpTask(getEssenceListUrl(), requestParam, new HttpCommand(), onHttpResultListener);
        httpTask.execute();
    }
}

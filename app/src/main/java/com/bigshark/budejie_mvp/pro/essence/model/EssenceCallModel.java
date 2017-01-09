package com.bigshark.budejie_mvp.pro.essence.model;

import android.content.Context;

import com.bigshark.budejie_mvp.http.impl.HttpCommand;
import com.bigshark.budejie_mvp.http.impl.RequestParam;
import com.bigshark.budejie_mvp.http.utils.HttpTask;
import com.bigshark.budejie_mvp.http.utils.MyHttpUtils;
import com.bigshark.budejie_mvp.pro.base.model.BaseModel;

/**
 * Created by bigShark on 2017/1/1.
 */

public class EssenceCallModel extends BaseModel {
    public EssenceCallModel(Context context) {
        super(context);
    }
    public void getIsRecharge(String cardnum,String phoneno,String orderid, MyHttpUtils.OnHttpResultListener onHttpResultListener) {
        RequestParam requestParam = new RequestParam();
        requestParam.put("carNumber", cardnum);
        requestParam.put("phoneno", phoneno);
        requestParam.put("orderid", orderid);
        requestParam.put("key", cardnum);
        requestParam.put("sign", cardnum);
        HttpTask httpTask = new HttpTask(getCallUrl(), requestParam, new HttpCommand(), onHttpResultListener);
        httpTask.execute();
    }
}

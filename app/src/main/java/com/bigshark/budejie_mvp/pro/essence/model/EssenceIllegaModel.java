package com.bigshark.budejie_mvp.pro.essence.model;

import android.content.Context;

import com.bigshark.budejie_mvp.http.impl.HttpCommand;
import com.bigshark.budejie_mvp.http.impl.RequestParam;
import com.bigshark.budejie_mvp.http.utils.HttpTask;
import com.bigshark.budejie_mvp.http.utils.MyHttpUtils;
import com.bigshark.budejie_mvp.pro.base.model.BaseModel;

/**
 * Created by bigShark on 2016/12/31.
 */

public class EssenceIllegaModel extends BaseModel {

    public EssenceIllegaModel(Context context) {
        super(context);
    }

    private String getUrl() {
        return getIllegalUrl().concat("/break-rules");
    }

    public void getIllegal(String carNumber,String carCode,String carLaunchNum, MyHttpUtils.OnHttpResultListener onHttpResultListener) {
        RequestParam requestParam = new RequestParam();
        requestParam.put("carNumber", carNumber);
        requestParam.put("carCode", carCode);
        requestParam.put("carLaunchNum", carLaunchNum);
        HttpTask httpTask = new HttpTask(getUrl(), requestParam, new HttpCommand(), onHttpResultListener);
        httpTask.execute();
    }

}

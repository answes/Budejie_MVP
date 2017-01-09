package com.bigshark.budejie_mvp.pro.essence.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.bigshark.budejie_mvp.http.utils.MyHttpUtils;
import com.bigshark.budejie_mvp.pro.base.presenter.BasePresenter;
import com.bigshark.budejie_mvp.pro.essence.model.EssenceIllegaModel;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by bigShark on 2016/12/31.
 */

public class EssenceIllegalPresener extends BasePresenter<EssenceIllegaModel> {



    public EssenceIllegalPresener(Context context) {
        super(context);
    }

    @Override
    public EssenceIllegaModel bindModel() {
        return new EssenceIllegaModel(getContext());
    }

    public void getIllegal(String carNumber,String carCode,String carLaunchNum,final OnUiThreadListener<String> onUiThreadListener){

        getModel().getIllegal(carNumber,carCode, carLaunchNum,new MyHttpUtils.OnHttpResultListener() {
            @Override
            public void onResult(String result) {

                if(TextUtils.isEmpty(result)){
                    onUiThreadListener.onResult(null);
                }else{
                    onUiThreadListener.onResult(result);
                }
            }
        });

    }
}

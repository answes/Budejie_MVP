package com.bigshark.budejie_mvp.pro.essence.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.bigshark.budejie_mvp.http.utils.MyHttpUtils;
import com.bigshark.budejie_mvp.pro.base.presenter.BasePresenter;
import com.bigshark.budejie_mvp.pro.essence.model.EssenceCallModel;
import com.bigshark.budejie_mvp.pro.essence.model.EssenceIllegaModel;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by bigShark on 2016/12/31.
 */

public class EssenceCallPresener extends BasePresenter<EssenceCallModel> {



    public EssenceCallPresener(Context context) {
        super(context);
    }

    @Override
    public EssenceCallModel bindModel() {
        return new EssenceCallModel(getContext());
    }

    public void getIllegal(String carNumber,String phoneno,String orderid,final OnUiThreadListener<String> onUiThreadListener){

        getModel().getIsRecharge(carNumber,phoneno,orderid, new MyHttpUtils.OnHttpResultListener() {
            @Override
            public void onResult(String result) {
                if(TextUtils.isEmpty(result)){
                    onUiThreadListener.onResult(null);
                }else{
//                    Illegal illegal = getGson().fromJson(result,Illegal.class);
//                    if(illegal != null){
//                        onUiThreadListener.onResult(illegal);
//                    }
                    onUiThreadListener.onResult(result);
                }
            }
        });

    }
}

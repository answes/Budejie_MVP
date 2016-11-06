package com.bigshark.budejie_mvp.bean;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.supertextviewlibrary.SuperTextView;
import com.android.volley.toolbox.NetworkImageView;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.utils.VolleyUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by bigShark on 2016/11/3.
 */

public class OrderViewHolder extends BaseViewHolder<Order> {

    private SuperTextView top;
    private SuperTextView name;
   // private NetworkImageView logo;
    private ImageView logo;
    private TextView tips;
    private TextView count;

    public OrderViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_mine_order);
        top = $(R.id.stv_top);
        name =  $(R.id.stv_name);
        logo = $(R.id.niv_logo);
        tips = $(R.id.tv_tips);
        count = $(R.id.tv_count);
    }

    @Override
    public void setData(final Order data) {
        super.setData(data);
        top.setRightString(data.getState());
        top.setLeftString(data.getStoreName());
        name.setRightString(data.getSevierName());
        name.setLeftString("x "+data.getNumber());
       // VolleyUtils.loadImage(context,logo,"http://tx.haiqq.com/uploads/allimg/150329/1610552441-3.jpg");
        tips.setText(data.getTips());
        count.setText("共 "+ data.getCount()+" 元");
    }
}

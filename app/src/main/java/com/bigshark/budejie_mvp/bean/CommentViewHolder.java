package com.bigshark.budejie_mvp.bean;

import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.utils.VolleyUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by bigShark on 2016/11/4.
 */

public class CommentViewHolder extends BaseViewHolder<Comments> {
    private NetworkImageView niv_heand;
    private TextView tv_username;
    private MaterialRatingBar mrb_comment;
    private TextView tv_date;
    private TextView tv_comment;


    public CommentViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_essence_comment);
        niv_heand = $(R.id.niv_heand);
        tv_username = $(R.id.tv_username);
        mrb_comment = $(R.id.mrb_comment);
        tv_date = $(R.id.tv_date);
        tv_comment = $(R.id.tv_comment);
    }

    @Override
    public void setData(Comments data) {
        VolleyUtils.loadImage(getContext(),niv_heand,"http://r3.ykimg.com/05410408526F45F06A0A4C2175D4DB1C");
        tv_username.setText("加拉贝斯");
        mrb_comment.setNumStars(5);
        tv_date.setText("2016-11-11 12:22");
        tv_comment.setText("个人你感觉这个还是挺不错的，值得你们去尝试");
    }
}

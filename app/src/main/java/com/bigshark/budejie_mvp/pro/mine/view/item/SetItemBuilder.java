package com.bigshark.budejie_mvp.pro.mine.view.item;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.pro.base.view.item.AbsItemBuilder;

/**
 * Created by bigShark on 2016/10/14.
 */

public class SetItemBuilder extends AbsItemBuilder {
    private String rightText;
    private int rightIconRes;
    private String text;
    private View.OnClickListener onClickListener;
    private boolean topLineIsVisiable = true;
    private boolean bottomLineIsVisiable=true;
    private int marginTop;

    public SetItemBuilder(Context context) {
        super(context);
    }

    @Override
    public int getContentView() {
        return R.layout.item_builder_mine_set;
    }

    public SetItemBuilder setTopLineIsVisiabel(boolean topLineIsVisiable){
        this.topLineIsVisiable = topLineIsVisiable;
        return  this;
    }
    public SetItemBuilder setBottomLineIsVisiabel(boolean bottomLineIsVisiable){
        this.bottomLineIsVisiable = bottomLineIsVisiable;
        return  this;
    }

    public SetItemBuilder setLeftText(int textRes){
        return setLeftText(getContext().getString(textRes));
    }

    public SetItemBuilder setLeftText(String text){
        this.text = text;
        return this;
    }

    public SetItemBuilder setRightIcon(int rightIconRes){
        this.rightIconRes = rightIconRes;
        return this;
    }

    public SetItemBuilder setRightText(String rightText){
        this.rightText = rightText;
        return  this;
    }

    public SetItemBuilder setOnItemClickListener(View.OnClickListener onItemClickListener){
        this.onClickListener = onItemClickListener;
        return this;
    }
    public SetItemBuilder setMarginTop(int marginTop){
        this.marginTop = marginTop;
        return this;
    }

    @Override
    public View buildAndBind(ViewGroup parent) {
        View view = super.buildAndBind(parent);
        if(marginTop >0){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,100);
            layoutParams.setMargins(0,marginTop,0,0);//4个参数按顺序分别是左上右下
            view.setLayoutParams(layoutParams);
        }

        View bottomLine = view.findViewById(R.id.v_bottom);
        if(bottomLineIsVisiable){
            bottomLine.setVisibility(View.VISIBLE);
        }else{
            bottomLine.setVisibility(View.GONE);
        }
        View topLine = view.findViewById(R.id.v_top);
        if(topLineIsVisiable){
            topLine.setVisibility(View.VISIBLE);
        }else{
            topLine.setVisibility(View.GONE);
        }
        ImageView iv_right = (ImageView) view.findViewById(R.id.iv_right_icon);
        if (rightIconRes >= 0){
            iv_right.setImageResource(rightIconRes);
            iv_right.setVisibility(View.VISIBLE);
        }else{
            iv_right.setVisibility(View.GONE);
        }
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        if (TextUtils.isEmpty(text)){
            tv_title.setVisibility(View.INVISIBLE);
        }else {
            tv_title.setText(text);
            tv_title.setVisibility(View.VISIBLE);
        }

        TextView tv_right = (TextView) view.findViewById(R.id.tv_right);
        if (TextUtils.isEmpty(rightText)){
            tv_right.setVisibility(View.GONE);
        }else {
            tv_right.setText(text);
            tv_right.setVisibility(View.VISIBLE);
        }

        if (onClickListener != null){
            view.setOnClickListener(onClickListener);
        }

        return view;
    }
}

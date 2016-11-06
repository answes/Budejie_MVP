package com.bigshark.budejie_mvp.pro.base.view.item;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigshark.budejie_mvp.R;

/**
 * Created by luyanhong on 16/10/8.
 */
public class DefaultItemBuilder extends   AbsItemBuilder {
    private int leftIconRes;
    private int rightIconRes;
    private String text;
    private View.OnClickListener onClickListener;
    private boolean topLineIsVisiable = true;
    private boolean bottomLineIsVisiable=true;
    private int marginTop;

    public DefaultItemBuilder(Context context){
        super(context);
    }

    @Override
    public int getContentView() {
        return R.layout.item_builder_default;
    }

    public DefaultItemBuilder setLeftIcon(int leftIconRes){
        this.leftIconRes = leftIconRes;
        return this;
    }

    public DefaultItemBuilder setMarginTop(int marginTop){
        this.marginTop = marginTop;
        return this;
    }

    public DefaultItemBuilder setTopLineIsVisiabel(boolean topLineIsVisiable){
        this.topLineIsVisiable = topLineIsVisiable;
        return  this;
    }
    public DefaultItemBuilder setBottomLineIsVisiabel(boolean bottomLineIsVisiable){
        this.bottomLineIsVisiable = bottomLineIsVisiable;
        return  this;
    }

    public DefaultItemBuilder setLeftText(int textRes){
        return setLeftText(getContext().getString(textRes));
    }

    public DefaultItemBuilder setLeftText(String text){
        this.text = text;
        return this;
    }

    public DefaultItemBuilder setRightIcon(int rightIconRes){
        this.rightIconRes = rightIconRes;
        return this;
    }

    public DefaultItemBuilder setOnItemClickListener(View.OnClickListener onItemClickListener){
        this.onClickListener = onItemClickListener;
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
        ImageView iv_left = (ImageView) view.findViewById(R.id.iv_left_icon);
        if (leftIconRes >= 0){
            iv_left.setImageResource(leftIconRes);
            iv_left.setVisibility(View.VISIBLE);
        }else{
            iv_left.setVisibility(View.GONE);
        }
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        if (TextUtils.isEmpty(text)){
            tv_title.setVisibility(View.INVISIBLE);
        }else {
            tv_title.setText(text);
            tv_title.setVisibility(View.VISIBLE);
        }
        if (onClickListener != null){
            view.setOnClickListener(onClickListener);
        }

        return view;
    }
}

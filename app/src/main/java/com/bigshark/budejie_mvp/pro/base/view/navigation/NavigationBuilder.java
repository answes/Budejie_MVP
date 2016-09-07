package com.bigshark.budejie_mvp.pro.base.view.navigation;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bigShark on 2016/5/27.
 * 构建ToolBar
 */
public interface NavigationBuilder {
    NavigationBuilder setTitle(String title);

    NavigationBuilder setTitle(int title);

    NavigationBuilder setTitleIcon(int iconRes);

    NavigationBuilder setLeftIcon(int iconRes);

    NavigationBuilder setRightIcon(int iconRes);

    NavigationBuilder setLeftIconOnClickListener(View.OnClickListener onClickListener);

    NavigationBuilder setRightIconOnClickListener(View.OnClickListener onClickListener);

    void createAndBind(ViewGroup parent);

}

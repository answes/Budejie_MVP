package com.bigshark.budejie_mvp.pro.newpost.view.navigation;

import android.content.Context;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.pro.base.view.navigation.NavigationBuilderAdapter;

/**
 * Created by bigShark on 2016/5/29.
 */
public class NewpostNavigationBuilder extends NavigationBuilderAdapter {

    public NewpostNavigationBuilder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.toolbar_newpost_layout;
    }
}

package com.bigshark.budejie_mvp.pro.essence.view.navigation;

import android.content.Context;
import android.view.ViewGroup;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.pro.base.view.navigation.NavigationBuilderAdapter;

/**
 * Created by bigShark on 2016/12/31.
 */

public class EssenceActivityNavigationBuilder extends NavigationBuilderAdapter {
    public EssenceActivityNavigationBuilder(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.toolbar_mine_layout;
    }

    @Override
    public void createAndBind(ViewGroup parent) {
        super.createAndBind(parent);
        setTitleTextView(R.id.iv_title, getTitle());
        setImageViewStyle(R.id.iv_left, getLeftIconRes(), getLeftIconOnClickListener());
    }
}

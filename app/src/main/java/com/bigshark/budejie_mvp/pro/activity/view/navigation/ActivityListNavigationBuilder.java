package com.bigshark.budejie_mvp.pro.activity.view.navigation;

import android.content.Context;
import android.view.ViewGroup;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.pro.base.view.navigation.NavigationBuilderAdapter;

/**
 * Created by bigShark on 2016/5/29.
 */
public class ActivityListNavigationBuilder extends NavigationBuilderAdapter {

    public ActivityListNavigationBuilder(Context context) {
        super(context);
    }
    @Override
    public int getLayoutId() {
        return R.layout.toolbar_activitylist_layout;
    }

    @Override
    public void createAndBind(ViewGroup parent) {
        super.createAndBind(parent);
        setImageViewStyle(R.id.iv_left,getLeftIconRes(),getLeftIconOnClickListener());
       setTitleTextView(R.id.iv_title,getTitle());
    }
}

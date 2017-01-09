package com.bigshark.budejie_mvp.pro.essence.view.navigation;

import android.content.Context;
import android.view.ViewGroup;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.pro.base.view.navigation.NavigationBuilderAdapter;

/**
 * Created by bigShark on 2016/5/29.
 */
public class EssenceNavigationBuilder extends NavigationBuilderAdapter {

    private String title;


    public EssenceNavigationBuilder(Context context) {
        super(context);
    }

    public EssenceNavigationBuilder setSearchName(String title) {
        this.title = title;
        return this;
    }

    public EssenceNavigationBuilder setSearchName(int title) {
        this.title = getContext().getResources().getString(title);
        return this;
    }

    public String getSearchName() {
        return title;
    }
    @Override
    public int getLayoutId() {
        return R.layout.toolbar_essence_layout;
    }

    @Override
    public void createAndBind(ViewGroup parent) {
        super.createAndBind(parent);
        setTitleTextView(R.id.iv_title,getTitle());
        setTitleTextView(R.id.iv_searchtitle,getSearchName());
        setImageViewStyle(R.id.iv_left, getLeftIconRes(), getLeftIconOnClickListener());
        setImageViewStyle(R.id.iv_right, getRightIconRes(), getRightIconOnClickListener());
    }
}

package com.bigshark.budejie_mvp.pro.mine.view.navigation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.pro.base.view.navigation.NavigationBuilderAdapter;

/**
 * Created by bigShark on 2016/11/6.
 */

public class LoginNavigationBulider extends NavigationBuilderAdapter {
    private View.OnClickListener tilteOnClickListener;

    public LoginNavigationBulider(Context context) {
        super(context);
    }
    public LoginNavigationBulider setTilteOnClickListener(View.OnClickListener tilteOnClickListener){
        this.tilteOnClickListener = tilteOnClickListener;
        return this;
    }

    @Override
    public int getLayoutId() {
        return R.layout.toolbar_login_layout;
    }

    @Override
    public void createAndBind(ViewGroup parent) {
        super.createAndBind(parent);
        setImageViewStyle(R.id.iv_left,getLeftIconRes(),getLeftIconOnClickListener());
        setTitleTextView(R.id.tv_title,getTitle(),tilteOnClickListener);
    }
}

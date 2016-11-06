package com.bigshark.budejie_mvp.pro.activity.view.navigation;

import android.view.View;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.pro.base.view.BaseFragment;

/**
 * Created by bigShark on 2016/5/29.
 */
public class ActivityListVedioFragment extends BaseFragment {
    private int mType = 0;
    private String mTitle;

    public void setType(int mType) {
        this.mType = mType;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_essence_video;
    }

    @Override
    public void initContentView(View viewContent) {

    }
}

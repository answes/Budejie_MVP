package com.bigshark.budejie_mvp.pro.newpost.view.navigation.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bigshark.budejie_mvp.pro.newpost.view.navigation.NewpostVedioFragment;

import java.util.List;

/**
 * Created by bigShark on 2016/5/29.
 */
public class NewpostAdapter extends FragmentStatePagerAdapter {
    public static final String TAB_TAG = "@dream@";

    private List<String> mTitles;

    public NewpostAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        NewpostVedioFragment fragment = new NewpostVedioFragment();
        String[] title = mTitles.get(position).split(TAB_TAG);
        fragment.setType(Integer.parseInt(title[1]));
        fragment.setTitle(title[0]);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).split(TAB_TAG)[0];
    }
}

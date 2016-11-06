package com.bigshark.budejie_mvp.pro.essence.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bigshark.budejie_mvp.pro.business.view.BusinessContentFragment;
import com.bigshark.budejie_mvp.pro.essence.view.EssenceRechargeCallFragment;
import com.bigshark.budejie_mvp.pro.essence.view.EssenceRechargeCardlFragment;

import java.util.List;

/**
 * Created by bigShark on 2016/10/20.
 */

public class ConvenienceAdapter extends FragmentStatePagerAdapter {
    public static final String TAB_TAG = "@dream@";
    private List<String> mTitles;

    public ConvenienceAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        this.mTitles = titles;

    }

    @Override
    public Fragment getItem(int position) {
        if(position ==0){
            EssenceRechargeCallFragment fragment = new EssenceRechargeCallFragment();
            String[] title = mTitles.get(position).split(TAB_TAG);
            fragment.setType(Integer.parseInt(title[1]));
            fragment.setTitle(title[0]);
            return fragment;
        }
        EssenceRechargeCardlFragment fragment = new EssenceRechargeCardlFragment();
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

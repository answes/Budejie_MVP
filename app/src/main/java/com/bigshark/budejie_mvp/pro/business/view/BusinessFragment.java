package com.bigshark.budejie_mvp.pro.business.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.pro.business.view.navigation.BusinessNavigationBuilder;
import com.bigshark.budejie_mvp.pro.base.view.BaseFragment;
import com.bigshark.budejie_mvp.pro.business.view.adapter.BusinessAdapter;

import java.util.Arrays;

/**
 * Created by bigShark on 2016/5/27.
 */
public class BusinessFragment extends BaseFragment {

    @Override
    public int getContentView() {
        return R.layout.fragment_attention;
    }

    @Override
    public void initContentView(View viewContent) {
        ViewPager  vp_attention = (ViewPager) viewContent.findViewById(R.id.vp_attention);
        String[] titles = getResources().getStringArray(R.array.attention_video_tab);
        BusinessAdapter adapter =
                new BusinessAdapter(getFragmentManager(), Arrays.asList(titles));
        vp_attention.setAdapter(adapter);
        initToolBar(viewContent,vp_attention);
    }

    private void initToolBar(View viewContent,ViewPager viewPager) {
        BusinessNavigationBuilder builder = new BusinessNavigationBuilder(getContext());
        builder.setUpWithViewPager(viewPager);
        builder.createAndBind( (ViewGroup) viewContent);
    }

}

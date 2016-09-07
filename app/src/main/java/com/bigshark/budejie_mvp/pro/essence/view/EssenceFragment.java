package com.bigshark.budejie_mvp.pro.essence.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseFragment;
import com.bigshark.budejie_mvp.pro.essence.view.adapter.EssenceAdapter;
import com.bigshark.budejie_mvp.pro.essence.view.navigation.EssenceNavigationBuilder;
import com.bigshark.budejie_mvp.utils.ToastUtil;

import java.util.Arrays;

/**
 * Created by bigShark on 2016/5/27.
 */
public class EssenceFragment extends BaseFragment {

    private TabLayout tab_essence;
    private ViewPager vp_essence;

    @Override
    public int getContentView() {
        return R.layout.fragment_essence;
    }

    @Override
    public void initContentView(View viewContent) {
        initToolBar(viewContent);
        this.tab_essence = (TabLayout) viewContent.findViewById(R.id.tab_essence);
        this.vp_essence = (ViewPager) viewContent.findViewById(R.id.vp_essence);

    }

    private void initToolBar(View viewContent) {
        EssenceNavigationBuilder builder = new EssenceNavigationBuilder(getContext());
        builder.setTitleIcon(R.drawable.main_essence_title)
                .setLeftIcon(R.drawable.main_essence_btn_selector)
                .setRightIcon(R.drawable.main_essence_btn_more_selector)
                .setLeftIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showToast(getContext(), "点击了");
                    }
                })
                .setRightIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showToast(getContext(), "点击了");
                    }
                });

        builder.createAndBind((ViewGroup) viewContent);
    }


    @Override
    public void initData() {
        String[] titles = getResources().getStringArray(R.array.essence_video_tab);
        EssenceAdapter adapter = new EssenceAdapter(getFragmentManager(), Arrays.asList(titles));
        this.vp_essence.setAdapter(adapter);
        this.tab_essence.setupWithViewPager(this.vp_essence);
    }
}

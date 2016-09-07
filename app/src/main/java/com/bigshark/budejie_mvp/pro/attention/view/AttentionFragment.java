package com.bigshark.budejie_mvp.pro.attention.view;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.attention.view.navigation.AttentionNavigationBuilder;
import com.bigshark.budejie_mvp.pro.base.view.BaseFragment;
import com.bigshark.budejie_mvp.pro.essence.view.adapter.EssenceAdapter;

import java.util.Arrays;

/**
 * Created by bigShark on 2016/5/27.
 */
public class AttentionFragment extends BaseFragment {
    private ViewPager vp_attention;

    @Override
    public int getContentView() {
        return R.layout.fragment_attention;
    }

    @Override
    public void initContentView(View viewContent) {
        vp_attention = (ViewPager) viewContent.findViewById(R.id.vp_attention);
        String[] titles = getResources().getStringArray(R.array.attention_video_tab);
        EssenceAdapter adapter =
                new EssenceAdapter(getFragmentManager(), Arrays.asList(titles));
        vp_attention.setAdapter(adapter);

        initToolBar(viewContent);
    }

    private void initToolBar(View viewContent) {
        AttentionNavigationBuilder builder = new AttentionNavigationBuilder(getContext());
        builder.setUpWithViewPager(vp_attention)
                .setLeftIcon(R.drawable.main_newpost_audit_btn_selector)
                .setLeftIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        ViewGroup parentView = (ViewGroup) viewContent;
        builder.createAndBind(parentView);
    }

}

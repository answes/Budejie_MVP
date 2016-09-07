package com.bigshark.budejie_mvp.pro.mine;

import android.view.View;
import android.view.ViewGroup;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseFragment;
import com.bigshark.budejie_mvp.pro.mine.view.navigation.MineNavigationBuilder;
import com.bigshark.budejie_mvp.utils.ToastUtil;

/**
 * Created by bigShark on 2016/5/27.
 */
public class MineFragment extends BaseFragment {
    @Override
    public int getContentView() {
        return R.layout.fragment_publish;
    }

    @Override
    public void initContentView(View viewContent) {
        initToolBar(viewContent);
    }

    private void initToolBar(View viewContent) {
        MineNavigationBuilder builder = new MineNavigationBuilder(getContext());
        builder.setModlRes(R.drawable.main_mine_night_model_selector)
                .setModelOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showToast(getContext(), "点击了");
                    }
                })
                .setTitle(R.string.main_mine_title_text)
                .setRightIcon(R.drawable.main_mine_setting_selector)
                .setRightIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showToast(getContext(), "点击了");
                    }
                });
        builder.createAndBind((ViewGroup) viewContent);
    }

}

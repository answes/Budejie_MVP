package com.bigshark.budejie_mvp.pro.mine.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.allen.supertextviewlibrary.SuperTextView;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.pro.base.view.BaseFragment;
import com.bigshark.budejie_mvp.pro.essence.view.views.CircleNetworkImageImage;
import com.bigshark.budejie_mvp.utils.ToastUtil;
import com.bigshark.budejie_mvp.utils.VolleyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bigShark on 2016/5/27.
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.stv_mine)
    SuperTextView stvMine;
    @BindView(R.id.stv_order)
    SuperTextView stvOrder;
    @BindView(R.id.stv_collection)
    SuperTextView stvCollection;
    @BindView(R.id.stv_detection)
    SuperTextView stvDetection;
    @BindView(R.id.stv_set)
    SuperTextView stvSet;
    private ImageView shoppingCat;
    private CircleNetworkImageImage head;

    @Override
    public int getContentView() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initContentView(View viewContent) {
        ButterKnife.bind(this,viewContent);
        findAndBindView(viewContent);

    }

    @Override
    public void initData() {
        VolleyUtils.loadImage(getContext(), head, "http://tx.haiqq.com/uploads/allimg/150329/1610552441-3.jpg");
    }

    private void findAndBindView(View viewContent) {
        head = (CircleNetworkImageImage) viewContent.findViewById(R.id.iv_header);
        shoppingCat = (ImageView) viewContent.findViewById(R.id.iv_shoppingcat);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.stv_mine, R.id.stv_order, R.id.stv_collection, R.id.stv_detection, R.id.stv_set})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.stv_mine:
                ToastUtil.showToast(getContext(), "这里跳一个h5的网页");
                break;
            case R.id.stv_order:
                MyOrderActivity.OpenMyOrderActivity(getActivity());
                break;
            case R.id.stv_collection:
                CollectionActivity.openCollectionActivity(getActivity());
                break;
            case R.id.stv_detection:
                ToastUtil.showToast(getContext(), "暂时没有");
                break;
            case R.id.stv_set:
                SetActivity.openSetActivity(getActivity());
                break;
        }
    }
}

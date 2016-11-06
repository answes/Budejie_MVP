package com.bigshark.budejie_mvp.pro.mine.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.pro.base.view.BaseFragment;
import com.bigshark.budejie_mvp.pro.base.view.item.DefaultItemBuilder;
import com.bigshark.budejie_mvp.pro.essence.view.views.CircleNetworkImageImage;
import com.bigshark.budejie_mvp.pro.mine.view.navigation.MineNavigationBuilder;
import com.bigshark.budejie_mvp.utils.BitmapCache;
import com.bigshark.budejie_mvp.utils.ToastUtil;
import com.bigshark.budejie_mvp.utils.VolleyUtils;

/**
 * Created by bigShark on 2016/5/27.
 */
public class MineFragment extends BaseFragment {
    private ImageView shoppingCat;
    private CircleNetworkImageImage head;

    @Override
    public int getContentView() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initContentView(View viewContent) {
        initItem(viewContent);
        findAndBindView(viewContent);

    }

    @Override
    public void initData() {
        VolleyUtils.loadImage(getContext(),head ,"http://tx.haiqq.com/uploads/allimg/150329/1610552441-3.jpg");
    }

    private void findAndBindView(View viewContent) {
        head = (CircleNetworkImageImage) viewContent.findViewById(R.id.iv_header);
        shoppingCat = (ImageView) viewContent.findViewById(R.id.iv_shoppingcat);

    }

    private void initItem(View viewContent) {
        DefaultItemBuilder item1 = new DefaultItemBuilder(getContext());
        item1.setLeftIcon(R.drawable.mine_user).
                setLeftText("个人信息")
                .setMarginTop(50)
        .setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast(getContext(),"这里跳一个h5的网页");
            }
        });

        item1.buildAndBind((ViewGroup) viewContent);


        DefaultItemBuilder item4 = new DefaultItemBuilder(getContext());
        item4.setLeftIcon(R.drawable.mine_order).setLeftText("我的订单")
                .setMarginTop(50)
                .setBottomLineIsVisiabel(false)
                .setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MyOrderActivity.OpenMyOrderActivity(getActivity());
                    }
                });
        item4.buildAndBind((ViewGroup) viewContent);


        DefaultItemBuilder item2 = new DefaultItemBuilder(getContext());
        item2.setLeftIcon(R.drawable.mine_star).setLeftText("我的收藏")
                .setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CollectionActivity.openCollectionActivity(getActivity());
                    }
                });

        item2.buildAndBind((ViewGroup) viewContent);

        DefaultItemBuilder item6 = new DefaultItemBuilder(getContext());
        item2.setLeftIcon(R.drawable.mine_star).setLeftText("我的保险")
                .setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtil.showToast(getContext(),"暂时没有");
                    }
                });

        item2.buildAndBind((ViewGroup) viewContent);

        DefaultItemBuilder item3 = new DefaultItemBuilder(getContext());
        item3.setLeftIcon(R.drawable.mine_set).setLeftText("设置")
                .setMarginTop(50)
                .setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SetActivity.openSetActivity(getActivity());
                    }
                });
        item3.buildAndBind((ViewGroup) viewContent);
    }

}

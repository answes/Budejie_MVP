package com.bigshark.budejie_mvp.pro.essence.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.bean.EssenceTestData;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseActivity;
import com.bigshark.budejie_mvp.pro.essence.view.adapter.EssenceListAdapter;
import com.bigshark.budejie_mvp.pro.essence.view.navigation.EssenceNavigationBuilder;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 检测维修
 * Created by luyanhong on 16/10/9.
 */
public class EssenceServiceActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private XRefreshView xRefreshView;
    private EssenceListAdapter adapter;
    private List<EssenceTestData> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_essence_service);
        initToolbar();
        findAndBindView();
    }

    private void findAndBindView() {
        xRefreshView = (XRefreshView) findViewById(R.id.xrefreshview);
        xRefreshView.setPullRefreshEnable(true);
        // 设置是否可以上拉加载
        xRefreshView.setPullLoadEnable(true);
        // 设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(1000);
        // 静默加载模式不能设置footerview
        // 设置支持自动刷新
        xRefreshView.setAutoLoadMore(true);
        //设置静默加载时提前加载的item个数
//		xRefreshView.setPreLoadCount(2);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_test_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());
        loadData(true);

        adapter = new EssenceListAdapter(this, datas);
        recyclerView.setAdapter(adapter);
        adapter.setCustomLoadMoreView(new XRefreshViewFooter(this));

        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh() {
                loadData(true);
            }

            @Override
            public void onLoadMore(boolean isSlience) {
                loadData(false);
            }
        });

        adapter.setItemOnClickListener(new EssenceListAdapter.ItemOnClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                EssenceBusinessActivity.openEssenceBusinessActivity(EssenceServiceActivity.this, datas.get(postion).getTitle());
            }
        });

    }


    private void loadData(final boolean isDownRefresh) {


        for (int i = 0; i < 15; i++) {
            EssenceTestData data = new EssenceTestData();
            data.setUrl("http://s2.sinaimg.cn/mw690/001nioVRgy6NKZmYYrn71&690");
            data.setTitle("店铺测试");
            data.setSencendTitle("这个是店铺测试的小标题,可以放多一点文字看看有什么情况");
            data.setDistance("200km");
            datas.add(data);
        }
        if (isDownRefresh) {
            xRefreshView.stopRefresh();
        } else {
            xRefreshView.stopLoadMore();
        }


//        presenter.getEssenceList(type, isDownRefresh, new BasePresenter.OnUiThreadListener<List<PostsListBean.PostList>>() {
//            @Override
//            public void onResult(List<PostsListBean.PostList> result) {
//                if (isDownRefresh) {
//                    xRefreshView.stopRefresh();
//                } else {
//                    xRefreshView.stopLoadMore();
//                }
//                if (result == null) {
//                    ToastUtil.showToast(getContext(), "加载失败");
//                } else {
//                    ToastUtil.showToast(getContext(), "加载成功");
//                    //刷新UI
//                    if (isDownRefresh) {
//                        //如果你是下拉刷新,我就情况列表
//                        postLists.clear();
//                    }
//
//                }
//            }
//        });
    }

    private void initToolbar() {
        LinearLayout contentLayout = (LinearLayout) findViewById(R.id.ll_content);
        EssenceNavigationBuilder bar = new EssenceNavigationBuilder(this);
        bar.setTitle("汽车检测").setLeftIcon(R.drawable.left_back)
                .setLeftIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        bar.createAndBind(contentLayout);
    }


    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    public static void openEssenceServiceActivity(Activity context) {
        Intent intent = new Intent(context, EssenceServiceActivity.class);
        context.startActivity(intent);
    }

    public static void openEssenceServiceActivityForResult(Activity context, int reslutCode) {
        Intent intent = new Intent(context, EssenceServiceActivity.class);
        context.startActivityForResult(intent, reslutCode);

    }
}

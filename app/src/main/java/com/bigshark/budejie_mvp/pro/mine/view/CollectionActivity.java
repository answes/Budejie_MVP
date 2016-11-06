package com.bigshark.budejie_mvp.pro.mine.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.bean.EssenceTestData;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseActivity;
import com.bigshark.budejie_mvp.pro.essence.view.EssenceBeautyActivity;
import com.bigshark.budejie_mvp.pro.essence.view.EssenceBusinessActivity;
import com.bigshark.budejie_mvp.pro.essence.view.adapter.EssenceListAdapter;
import com.bigshark.budejie_mvp.pro.mine.view.navigation.MineNavigationBuilder;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bigShark on 2016/10/28.
 */

public class CollectionActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private XRefreshView xRefreshView;
    private EssenceListAdapter adapter;
    private List<EssenceTestData> datas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        initToolsBar();
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

        adapter = new EssenceListAdapter(this,datas);
        recyclerView.setAdapter(adapter);
        adapter.setCustomLoadMoreView(new XRefreshViewFooter(this));

        adapter.setItemOnClickListener(new EssenceListAdapter.ItemOnClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                EssenceBusinessActivity.openEssenceBusinessActivity(CollectionActivity.this,datas.get(postion).getTitle());
            }
        });

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
    }

    private void loadData(final boolean isDownRefresh) {


        for (int i = 0; i < 15; i++) {
            EssenceTestData data = new EssenceTestData();
            data.setUrl("http://s2.sinaimg.cn/mw690/001nioVRgy6NKZmYYrn71&690");
            data.setTitle("店铺测试");
            data.setSencendTitle("店铺简介");
            data.setDistance("200km");
            datas.add(data);
        }
        if (isDownRefresh) {
            xRefreshView.stopRefresh();
        } else {
            xRefreshView.stopLoadMore();
        }
    }

    private void initToolsBar() {
        LinearLayout   viewContent = (LinearLayout) findViewById(R.id.ll_content);
        MineNavigationBuilder bar = new MineNavigationBuilder(this);
        bar.setTitle("我的收藏")
                .setLeftIcon(R.drawable.left_back)
                .setLeftIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        bar.createAndBind(viewContent);
    }


    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    public static void openCollectionActivity(Activity a){
        Intent intent = new Intent(a,CollectionActivity.class);
        a.startActivity(intent);
    }
}

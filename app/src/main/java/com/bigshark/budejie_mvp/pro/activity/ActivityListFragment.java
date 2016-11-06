package com.bigshark.budejie_mvp.pro.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.bean.ActivityTestData;
import com.bigshark.budejie_mvp.pro.activity.view.navigation.H5WebAcitivy;
import com.bigshark.budejie_mvp.pro.activity.view.navigation.adapter.ActivityListAdapter;
import com.bigshark.budejie_mvp.pro.base.view.BaseFragment;
import com.bigshark.budejie_mvp.pro.activity.view.navigation.ActivityListNavigationBuilder;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bigShark on 2016/5/27.
 */
public class ActivityListFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private XRefreshView xRefreshView;
    private ActivityListAdapter adapter;
    private List<ActivityTestData> datas = new ArrayList<>();


    @Override
    public int getContentView() {
        return R.layout.fragment_newspost;
    }

    @Override
    public void initContentView(View viewContent) {


        initToolBar(viewContent);

        xRefreshView = (XRefreshView) viewContent.findViewById(R.id.xrefreshview);
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

        recyclerView = (RecyclerView) viewContent.findViewById(R.id.recycler_view_test_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadData(true);

        adapter = new ActivityListAdapter(getContext(),datas);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).sizeResId(R.dimen.divider).build());
        recyclerView.setAdapter(adapter);

        adapter.setCustomLoadMoreView(new XRefreshViewFooter(getContext()));

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

        adapter.setItemOnClickListener(new ActivityListAdapter.ItemOnClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                H5WebAcitivy.openH5WebAcitivy(getActivity());
            }
        });


    }

    private void initToolBar(View viewContent) {
        ActivityListNavigationBuilder builder = new ActivityListNavigationBuilder(getContext());
        builder.setTitle("活动");
        builder.createAndBind((ViewGroup) viewContent);
    }

    @Override
    public void initData() {


    }

    private void loadData(final boolean isDownRefresh) {


        for (int i = 0; i < 15; i++) {
            ActivityTestData data = new ActivityTestData();
            data.setUrl("http://img4.imgtn.bdimg.com/it/u=3211314641,1153375401&fm=21&gp=0.jpg");
            data.setName("店铺测试");
            data.setContent("这个是店铺测试的小标题,可以放多一点文字看看有什么情况");
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
}

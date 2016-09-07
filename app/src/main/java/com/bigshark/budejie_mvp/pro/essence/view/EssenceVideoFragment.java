package com.bigshark.budejie_mvp.pro.essence.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.bean.PostsListBean;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.presenter.BasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseFragment;
import com.bigshark.budejie_mvp.pro.essence.presenter.EssenceVideoPresenter;
import com.bigshark.budejie_mvp.pro.essence.view.adapter.EssenceVideoAdapter;
import com.bigshark.budejie_mvp.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bigShark on 2016/5/29.
 */
public class EssenceVideoFragment extends BaseFragment {

    private int type = 0;
    private String mTitle;

    private EssenceVideoPresenter presenter;

    private RecyclerView recyclerView;
    private XRefreshView xRefreshView;
    private EssenceVideoAdapter videoAdapter;

    private List<PostsListBean.PostList> postLists = new ArrayList<>();

    public void setType(int type) {
        this.type = type;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        presenter = new EssenceVideoPresenter(getContext());
        return presenter;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_essence_video;
    }

    @Override
    public void initContentView(View viewContent) {
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

        videoAdapter = new EssenceVideoAdapter(getContext(), postLists);
        recyclerView.setAdapter(videoAdapter);
        videoAdapter.setCustomLoadMoreView(new XRefreshViewFooter(getContext()));

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
        presenter.getEssenceList(type, isDownRefresh, new BasePresenter.OnUiThreadListener<List<PostsListBean.PostList>>() {
            @Override
            public void onResult(List<PostsListBean.PostList> result) {
                if (isDownRefresh) {
                    xRefreshView.stopRefresh();
                } else {
                    xRefreshView.stopLoadMore();
                }
                if (result == null) {
                    ToastUtil.showToast(getContext(), "加载失败");
                } else {
                    ToastUtil.showToast(getContext(), "加载成功");
                    //刷新UI
                    if (isDownRefresh) {
                        //如果你是下拉刷新,我就情况列表
                        postLists.clear();
                    }
                    postLists.addAll(result);
                    videoAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}

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

    private String[] url = new String[]{
            "http://img5.imgtn.bdimg.com/it/u=3473271352,557844997&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=4039188301,1420682202&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1992021303,2649341960&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1808875244,2344962443&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1175733201,2504787695&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=842111471,3840130577&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3812157272,3605948852&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=1657601061,3703871578&fm=11&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=4079829507,67545926&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3473271352,557844997&fm=21&gp=0.jpg"
    };

    private String[] name = new String[]{
            "横县4s店","老毛头","大白菜","天地联盟","等等等","时尚汽车","安全出行","金百车行","荣耀车行","百天汽车"
    };

    private String[] namess = new String[]{
            "宝马汽车广告语——即使你把它拆得七零八落，它依然是位美人（国外）",
            "德国宝马7系汽车广告语——生活艺术唯你独尊",
            "德国奔驰汽车广告语——领导时代，驾驭未来",
            "奥迪汽车广告语——突破科技，启迪未来",
            "美国卡迪拉克汽车广告语——将力量、速度和豪华融为一体",
            "富兰克林牌汽车广告语——这是一辆永远不会给你带来麻烦的汽车",
            "大众汽车广告语——汽车价值典范",
            "VOLVO汽车广告语——关爱生命，享受生活",
            "美国克莱斯勒汽车广告语——你买汽车不来考虑一下我们克莱斯勒的汽车那你就吃亏了,不但你吃亏，我们也吃亏",
            "菲亚特汽车广告语——开创菲亚特新纪元，脱胎换骨，来势汹汹，超级雷马1000"
    };


    private void loadData(final boolean isDownRefresh) {


        for (int i = 0; i < 10; i++) {
            ActivityTestData data = new ActivityTestData();
            data.setUrl(url[i]);
            data.setName(name[i]);
            data.setContent(namess[i]);
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

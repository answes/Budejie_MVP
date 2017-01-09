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
import com.bigshark.budejie_mvp.bean.ActivityTestData;
import com.bigshark.budejie_mvp.bean.EssenceTestData;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.activity.view.navigation.adapter.ActivityListAdapter;
import com.bigshark.budejie_mvp.pro.base.view.BaseActivity;
import com.bigshark.budejie_mvp.pro.essence.view.adapter.EssenceListAdapter;
import com.bigshark.budejie_mvp.pro.essence.view.navigation.EssenceActivityNavigationBuilder;
import com.bigshark.budejie_mvp.pro.essence.view.navigation.EssenceNavigationBuilder;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**  汽车美容
 * Created by luyanhong on 16/10/9.
 */
public class EssenceBeautyActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private XRefreshView xRefreshView;
    private EssenceListAdapter adapter;
    private List<EssenceTestData> datas = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_essence_beauty);
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

        adapter = new EssenceListAdapter(this,datas);
        recyclerView.setAdapter(adapter);
        adapter.setCustomLoadMoreView(new XRefreshViewFooter(this));

        adapter.setItemOnClickListener(new EssenceListAdapter.ItemOnClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                EssenceBusinessActivity.openEssenceBusinessActivity(EssenceBeautyActivity.this,
                        datas.get(postion).getTitle(),datas.get(postion).getUrl()
                        ,datas.get(postion).getSencendTitle(),datas.get(postion).getDistance());
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


    private String[] url = new String[]{
            "http://img1.imgtn.bdimg.com/it/u=1173275864,2683412003&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=1724741641,406307535&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2005883992,1084071493&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=540367546,4162194838&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=2065192343,110408558&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2802644689,2577269072&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=779686150,349371177&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=73147951,3832929034&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2005883992,1084071493&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=1783819006,367489004&fm=21&gp=0.jpg"
    };

    private String[] name = new String[]{
            "横县4s店","老毛头","大白菜","天地联盟","等等等","时尚汽车","安全出行","金百车行","荣耀车行","百天汽车"
    };

    private String[] sencendName = new String[]{
            "公司承接汽车美容、汽车改装、汽车贴膜、汽车维修等",
            "据您个人需求，针对您爱车的本身特点，以我们专业的视野",
            "汽车装饰中心（地胶、脚垫、真皮、倒车雷达、防盗器）、汽车改装中心",
            "汽车快修保养中心（轮胎机油、钣金喷漆、快修保养等）、保险业务代理等",
            "立足为您的爱车保驾护航",
            "针对您爱车的本身特点，以我们专业的视野",
            "轮胎机油、钣金喷漆、快修保养等",
            "保险业务代理等",
            "汽车快修保养中心",
            "汽车改装中心"
    };

    private String[] distances = new String[]{
            "0.5km","1km","20km","0.2km","3km","6km","8km","120km","22km","0.7km"
    };


    private void loadData(final boolean isDownRefresh) {


        for (int i = 0; i < 10; i++) {
            EssenceTestData data = new EssenceTestData();
            data.setUrl(url[i]);
            data.setTitle(name[i]);
            data.setSencendTitle(sencendName[i]);
            data.setDistance(distances[i]);
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
        EssenceActivityNavigationBuilder bar = new EssenceActivityNavigationBuilder(this);
        bar.setTitle("汽车美容").setLeftIcon(R.drawable.left_back)
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

    public static void openEssenceBeautyActivity(Activity context){
        Intent intent = new Intent(context,EssenceBeautyActivity.class);
        context.startActivity(intent);
    }
    public static void  openEssenceBeautyActivityForResult(Activity context,int reslutCode){
        Intent intent = new Intent(context,EssenceBeautyActivity.class);
        context.startActivityForResult(intent,reslutCode);

    }
}

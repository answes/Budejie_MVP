package com.bigshark.budejie_mvp.pro.business.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.bean.EssenceTestData;
import com.bigshark.budejie_mvp.bean.PostsListBean;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.presenter.BasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseFragment;
import com.bigshark.budejie_mvp.pro.business.view.adapter.BusinessListAdapter;
import com.bigshark.budejie_mvp.pro.essence.presenter.EssenceVideoPresenter;
import com.bigshark.budejie_mvp.pro.essence.view.EssenceBusinessActivity;
import com.bigshark.budejie_mvp.pro.essence.view.EssenceServiceActivity;
import com.bigshark.budejie_mvp.pro.essence.view.adapter.EssenceListAdapter;
import com.bigshark.budejie_mvp.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bigShark on 2016/5/29.
 */
public class BusinessContentFragment extends BaseFragment {

    private int type = 0;
    private String mTitle;


    private ListView recyclerView;

    private BusinessListAdapter videoAdapter;

    private List<EssenceTestData> postLists = new ArrayList<>();

    public void setType(int type) {
        this.type = type;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }



    @Override
    public int getContentView() {
        return R.layout.fragment_essence_video;
    }

    @Override
    public void initContentView(View viewContent) {

        loadData();

        recyclerView = (ListView) viewContent.findViewById(R.id.recycler_view_test_rv);
        videoAdapter = new BusinessListAdapter(getContext(), postLists);
        recyclerView.setAdapter(videoAdapter);
       recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               EssenceBusinessActivity.openEssenceBusinessActivity(getActivity(),
                       postLists.get(position).getTitle(),postLists.get(position).getUrl()
                       ,postLists.get(position).getSencendTitle(),postLists.get(position).getDistance());
           }
       });

    }

    private String[] url = new String[]{
          "http://img1.imgtn.bdimg.com/it/u=1173275864,2683412003&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=1724741641,406307535&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=540367546,4162194838&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=2065192343,110408558&fm=21&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1173275864,2683412003&fm=21&gp=0.jpg",
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


    private void loadData() {
        for (int i = 0; i < 10; i++) {
            EssenceTestData data = new EssenceTestData();
            data.setUrl(url[i]);
            data.setTitle(name[i]);
            data.setSencendTitle(sencendName[i]);
            data.setDistance(distances[i]);
            postLists.add(data);
        }

    }
}

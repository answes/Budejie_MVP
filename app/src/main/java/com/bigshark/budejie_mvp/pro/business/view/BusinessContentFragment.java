package com.bigshark.budejie_mvp.pro.business.view;

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
               EssenceBusinessActivity.openEssenceBusinessActivity(getActivity(),postLists.get(position).getTitle());
           }
       });

    }

    private void loadData() {

        for (int i = 0; i < 15; i++) {
            EssenceTestData data = new EssenceTestData();
            data.setUrl("http://r3.ykimg.com/05410408526F45F06A0A4C2175D4DB1C");
            data.setTitle("店铺测试");
            data.setSencendTitle("这个是店铺测试的小标题,可以放多一点文字看看有什么情况");
            data.setDistance("200km");
            postLists.add(data);
        }

    }
}

package com.bigshark.budejie_mvp.pro.mine.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.bean.Order;
import com.bigshark.budejie_mvp.bean.OrderViewHolder;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseActivity;
import com.bigshark.budejie_mvp.pro.mine.view.navigation.MineNavigationBuilder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderActivity extends BaseActivity implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.erv_content)
    EasyRecyclerView ervContent;
    private RecyclerArrayAdapter adapter;
    private List<Order> datas;
    private Handler handler = new Handler();

    private int page = 0;
    private boolean hasNetWork = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        initToolsBar();
        initDatas();
        initRecyclerView();
    }

    private void initDatas() {
        datas = new ArrayList<>();
        for (int i=0;i<10;i++){
            Order order = new Order();
            order.setCount(23);
            order.setNumber(2);
            order.setSevierName("洗车，抛光");
            order.setState("正在进行");
            order.setTips("请于xxxx年xx月xx日前到达消费");
            order.setStoreName("汽车之友");
            datas.add(order);
        }
    }

    private void initRecyclerView() {
        ervContent.setLayoutManager(new LinearLayoutManager(this));
        ervContent.addItemDecoration(new  HorizontalDividerItemDecoration.Builder(this).colorResId(R.color.win_bg).size(25).build());

        ervContent.setAdapterWithProgress( adapter = new RecyclerArrayAdapter(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new OrderViewHolder(parent);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }
        });

        adapter.setMore(R.layout.view_more,this);
        adapter.setNoMore(R.layout.view_nomore, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {
                adapter.resumeMore();
            }

            @Override
            public void onNoMoreClick() {
                adapter.resumeMore();
            }
        });
        ervContent.setRefreshListener(this);
        onRefresh();

    }
    @Override
    public void onRefresh() {
        page = 0;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                //刷新
                if (!hasNetWork) {
                    adapter.pauseMore();
                    return;
                }
                adapter.addAll(datas);
                page=1;
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //刷新
                if (!hasNetWork) {
                    adapter.pauseMore();
                    return;
                }
                adapter.addAll(datas);
                page++;
            }
        }, 2000);
    }

    private void initToolsBar() {
        LinearLayout viewContent = (LinearLayout) findViewById(R.id.activity_my_order);
        MineNavigationBuilder bar = new MineNavigationBuilder(this);
        bar.setTitle("我的订单")
                .setLeftIcon(R.drawable.left_back)
                .setLeftIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        bar.createAndBind(viewContent);
    }

    public static void OpenMyOrderActivity(Activity activity) {
        activity.startActivity(new Intent(activity, MyOrderActivity.class));
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }


}

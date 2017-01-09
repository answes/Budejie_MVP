package com.bigshark.budejie_mvp.pro.essence.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONObject;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.bean.Illegal;
import com.bigshark.budejie_mvp.bean.Record;
import com.bigshark.budejie_mvp.bean.ShowapiResBody;
import com.bigshark.budejie_mvp.http.utils.JSONUtil;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseActivity;
import com.bigshark.budejie_mvp.pro.essence.view.adapter.EssenceIllegalListAdapter;
import com.bigshark.budejie_mvp.pro.essence.view.navigation.EssenceActivityNavigationBuilder;
import com.google.gson.Gson;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EssenceIllegalList extends BaseActivity {
    @BindView(R.id.rc_content)
    RecyclerView rcContent;
    private EssenceIllegalListAdapter adapter;
    private List<Record> recordDatas = new ArrayList<>();;

    private String result;
    private ShowapiResBody show;
    private View hint_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essence_illegal_list);
        ButterKnife.bind(this);
        initToolbar();
        initData();
    }

    private void initToolbar() {
        LinearLayout contentLayout = (LinearLayout) findViewById(R.id.ll_content);
        EssenceActivityNavigationBuilder bar = new EssenceActivityNavigationBuilder(this);
        bar.setTitle("违章记录").setLeftIcon(R.drawable.left_back)
                .setLeftIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        bar.createAndBind(contentLayout);
    }

    private void initData() {
        if (getIntent() != null) {
            result = getIntent().getStringExtra("result");
            JSONObject json= JSONObject.parseObject(result);
            JSONObject jsonBody = json.getJSONObject("showapi_res_body");
             show = JSONUtil.getObject(jsonBody.toJSONString(),ShowapiResBody.class);
        }
        if(show == null || show.getRecords().length == 0){
            showHintView();
        }
        else{
            Collections.addAll(recordDatas,show.getRecords());
        }
        adapter = new EssenceIllegalListAdapter(this,recordDatas);
        rcContent.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).sizeResId(R.dimen.divider).build());
        rcContent.setAdapter(adapter);
    }

    private void showHintView() {
        if(hint_view == null){
            ViewStub viewStub = (ViewStub) this.findViewById(R.id.hint_view);
            hint_view = viewStub.inflate();
        }
        hint_view.setVisibility(View.VISIBLE);
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    public static void openEssenceIllegalList(Activity activity, String result) {
        Intent intent = new Intent(activity, EssenceIllegalList.class);
        intent.putExtra("result", result);
        activity.startActivity(intent);
    }
}

package com.bigshark.budejie_mvp.pro.essence.view.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseActivity;
import com.bigshark.budejie_mvp.pro.essence.view.navigation.EssenceNavigationBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EssenceInsuranceNextActivity extends BaseActivity {

    @BindView(R.id.et_catId)
    EditText etCatId;
    @BindView(R.id.et_engineId)
    EditText etEngineId;
    @BindView(R.id.et_date)
    EditText etDate;
    @BindView(R.id.et_type)
    EditText etType;
    @BindView(R.id.bt_next)
    Button btNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essence_insurancenext);
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        LinearLayout contentLayout = (LinearLayout) findViewById(R.id.ll_content);
        EssenceNavigationBuilder bar = new EssenceNavigationBuilder(this);
        bar.setTitle("补充信息").setLeftIcon(R.drawable.left_back)
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

    public static void openEssenceInsuranceNextActivity(Activity activity){
        Intent intent =new Intent(activity,EssenceInsuranceNextActivity.class);
        activity.startActivity(intent);
    }

    @OnClick(R.id.bt_next)
    public void onClick() {
    }
}

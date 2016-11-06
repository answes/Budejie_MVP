package com.bigshark.budejie_mvp.pro.essence.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseActivity;
import com.bigshark.budejie_mvp.pro.essence.view.navigation.EssenceNavigationBuilder;
import com.bigshark.budejie_mvp.pro.essence.view.views.EssenceInsuranceNextActivity;
import com.bigshark.budejie_mvp.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 汽车保险
 * Created by luyanhong on 16/10/9.
 */
public class EssenceInsuranceActivity extends BaseActivity {

    @BindView(R.id.tv_catCity)
    TextView catCity;
    @BindView(R.id.et_catNum)
    EditText catNum;
    @BindView(R.id.et_name)
    EditText name;
    @BindView(R.id.et_id)
    EditText id;
    @BindView(R.id.et_phoneNum)
    EditText phoneNum;
    @BindView(R.id.et_dynamicCode)
    EditText dynamicCode;
    @BindView(R.id.bt_getDynamicCode)
    Button getDynamicCode;
    @BindView(R.id.id_switch)
    Switch idSwitch;
    @BindView(R.id.iv_help)
    ImageView ivHelp;
    @BindView(R.id.bt_next)
    Button btNext;
    @BindView(R.id.bt_callPhone)
    Button btCallPhone;
    @BindView(R.id.bt_takePhtoto)
    Button btTakePhtoto;

    @OnClick(R.id.tv_catCity)
    void setCity() {
        CityActivity.openCityActivityForReslut(this, "南宁市");
    }

    @OnClick(R.id.bt_getDynamicCode)
    void getDynamicCode() {
        ToastUtil.showToast(this, "获取验证码中");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_essence_insurance);
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        LinearLayout contentLayout = (LinearLayout) findViewById(R.id.ll_content);
        EssenceNavigationBuilder bar = new EssenceNavigationBuilder(this);
        bar.setTitle("基本信息").setLeftIcon(R.drawable.left_back)
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

    public static void openEssenceInsuranceActivity(Activity context) {
        Intent intent = new Intent(context, EssenceInsuranceActivity.class);
        context.startActivity(intent);
    }

    public static void openEssenceInsuranceActivityForResult(Activity context, int reslutCode) {
        Intent intent = new Intent(context, EssenceInsuranceActivity.class);
        context.startActivityForResult(intent, reslutCode);
    }

    @OnClick({R.id.iv_help, R.id.bt_next, R.id.bt_callPhone, R.id.bt_takePhtoto})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_help:
                break;
            case R.id.bt_next:
                EssenceInsuranceNextActivity.openEssenceInsuranceNextActivity(this);
                break;
            case R.id.bt_callPhone:

                break;
            case R.id.bt_takePhtoto:
                break;
        }
    }
}

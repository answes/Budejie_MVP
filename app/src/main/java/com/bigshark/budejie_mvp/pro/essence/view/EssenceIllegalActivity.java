package com.bigshark.budejie_mvp.pro.essence.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.bean.Illegal;
import com.bigshark.budejie_mvp.bean.ShowapiResBody;
import com.bigshark.budejie_mvp.http.utils.JSONUtil;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.presenter.BasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseActivity;
import com.bigshark.budejie_mvp.pro.essence.presenter.EssenceIllegalPresener;
import com.bigshark.budejie_mvp.pro.essence.view.navigation.EssenceActivityNavigationBuilder;
import com.bigshark.budejie_mvp.utils.CustomProgress;
import com.bigshark.budejie_mvp.utils.ToastUtil;

import com.alibaba.fastjson.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 违章查询
 * Created by luyanhong on 16/10/9.
 */
public class EssenceIllegalActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.et_catNum)
    EditText etCatNum;
    @BindView(R.id.et_carCode)
    EditText etCarCode;
    @BindView(R.id.iv_help)
    ImageView ivHelp;
    @BindView(R.id.et_launchNum)
    EditText etLaunchNum;
    @BindView(R.id.bt_quary)
    Button btQuary;
    private EssenceIllegalPresener presener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_essence_illegal);
        ButterKnife.bind(this);
        initToolbar();
    }


    private void initToolbar() {
        LinearLayout contentLayout = (LinearLayout) findViewById(R.id.ll_content);
        EssenceActivityNavigationBuilder bar = new EssenceActivityNavigationBuilder(this);
        bar.setTitle("违章查询").setLeftIcon(R.drawable.left_back)
                .setLeftIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        bar.createAndBind(contentLayout);
    }



    private void qurey() {
        presener.getIllegal(etCatNum.getText().toString().trim(), etCarCode.getText().toString().trim(),
                etLaunchNum.getText().toString().trim(),new BasePresenter.OnUiThreadListener<String>() {
            @Override
            public void onResult(String result) {
                CustomProgress.dismissProgess();
                if (TextUtils.isEmpty(result)) {
                    return;
                }
                    JSONObject json= JSONObject.parseObject(result);
                    JSONObject jsonBody = json.getJSONObject("showapi_res_body");
                    ShowapiResBody show = JSONUtil.getObject(jsonBody.toJSONString(),ShowapiResBody.class);
                    if (show.getMsg().substring(0,4).equals("查询成功")) {
                         EssenceIllegalList.openEssenceIllegalList(EssenceIllegalActivity.this, result);
                    } else {
                        ToastUtil.showToast(EssenceIllegalActivity.this, show.getErrorMsg());
                    }
            }
        });
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        presener = new EssenceIllegalPresener(this);
        return presener;
    }


    public static void openEssenceIllegalActivity(Activity context) {
        Intent intent = new Intent(context, EssenceIllegalActivity.class);
        context.startActivity(intent);
    }

    public static void openEssenceIllegalActivityForResult(Activity context, int reslutCode) {
        Intent intent = new Intent(context, EssenceIllegalActivity.class);
        context.startActivityForResult(intent, reslutCode);

    }

    @OnClick({R.id.iv_help, R.id.bt_quary})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_help:
                new MaterialDialog.Builder(EssenceIllegalActivity.this)
                        .content("护照后六位")
                        .positiveText("了解")
                        .show();
                break;
            case R.id.bt_quary:
                if (TextUtils.isEmpty(etCatNum.getText().toString().trim())) {
                    ToastUtil.showToast(this, "车牌号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(etCarCode.getText().toString().trim())) {
                    ToastUtil.showToast(this, "车架号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(etLaunchNum.getText().toString().trim())) {
                    ToastUtil.showToast(this, "发动机号不能为空");
                    return;
                }
                CustomProgress.show(this,"正在查询，请稍后...",false,null);
                qurey();
                break;
        }
    }
}

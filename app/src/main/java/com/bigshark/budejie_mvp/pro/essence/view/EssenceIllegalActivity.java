package com.bigshark.budejie_mvp.pro.essence.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseActivity;
import com.bigshark.budejie_mvp.pro.essence.view.navigation.EssenceNavigationBuilder;
import com.bigshark.budejie_mvp.pro.mine.view.SetActivity;
import com.bigshark.budejie_mvp.utils.ToastUtil;

/**
 * 违章查询
 * Created by luyanhong on 16/10/9.
 */
public class EssenceIllegalActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout catNumLayout;
    private TextView catCityName;
    private EditText catNum;
    private RelativeLayout cityLayout;
    private TextView cityName;
    private EditText launchNum;
    private ImageView help;
    private Button qurey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_essence_illegal);
        initToolbar();
        findAndBindView();
    }

    private void findAndBindView() {
        catNumLayout = (LinearLayout) findViewById(R.id.ll_catNum);
        catCityName = (TextView) findViewById(R.id.tv_catCity);
        catNum = (EditText) findViewById(R.id.et_catNum);
        cityLayout = (RelativeLayout) findViewById(R.id.rl_city);
        cityName = (TextView) findViewById(R.id.tv_cityName);
        launchNum = (EditText) findViewById(R.id.et_launchNum);
        help = (ImageView) findViewById(R.id.iv_help);
        qurey = (Button) findViewById(R.id.bt_quary);

        catNumLayout.setOnClickListener(this);
        cityLayout.setOnClickListener(this);
        help.setOnClickListener(this);
        qurey.setOnClickListener(this);

    }

    private void initToolbar() {
        LinearLayout contentLayout = (LinearLayout) findViewById(R.id.ll_content);
        EssenceNavigationBuilder bar = new EssenceNavigationBuilder(this);
        bar.setTitle("违章查询").setLeftIcon(R.drawable.left_back)
                .setLeftIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        bar.createAndBind(contentLayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_catNum:
                break;
            case R.id.rl_city:
                CityActivity.openCityActivityForReslut(this,"南宁市");
                break;
            case R.id.iv_help:
                new MaterialDialog.Builder(EssenceIllegalActivity.this)
                        .content("护照后六位")
                        .positiveText("了解")
                        .show();
                break;
            case R.id.bt_quary:
                ToastUtil.showToast(this,"暂时不能查询");
                break;
        }
    }
    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            cityName.setText(data.getStringExtra("city"));
        }
    }

    public static void openEssenceIllegalActivity(Activity context) {
        Intent intent = new Intent(context, EssenceIllegalActivity.class);
        context.startActivity(intent);
    }

    public static void openEssenceIllegalActivityForResult(Activity context, int reslutCode) {
        Intent intent = new Intent(context, EssenceIllegalActivity.class);
        context.startActivityForResult(intent, reslutCode);

    }
}

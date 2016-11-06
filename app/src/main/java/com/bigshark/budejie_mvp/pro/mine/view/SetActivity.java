package com.bigshark.budejie_mvp.pro.mine.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.allen.supertextviewlibrary.SuperTextView;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseActivity;
import com.bigshark.budejie_mvp.pro.base.view.item.DefaultItemBuilder;
import com.bigshark.budejie_mvp.pro.mine.view.item.SetItemBuilder;
import com.bigshark.budejie_mvp.pro.mine.view.navigation.MineNavigationBuilder;
import com.bigshark.budejie_mvp.utils.ToastUtil;

public class SetActivity extends BaseActivity {
    private LinearLayout viewContent;
    private SuperTextView modifyPw;
    private SuperTextView clearCache;
    private SuperTextView aboutMe;
    private SuperTextView callService;
    private Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        initToolsBar();
        findAndBindView();
    }

    private void findAndBindView() {
        modifyPw = (SuperTextView) findViewById(R.id.stv_password);
        clearCache = (SuperTextView) findViewById(R.id.stv_huancun);
        aboutMe = (SuperTextView) findViewById(R.id.stv_about);
        callService = (SuperTextView) findViewById(R.id.stv_phone);
        logOut = (Button) findViewById(R.id.bt_logout);

        modifyPw.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener(){
            @Override
            public void onSuperTextViewClick() {
                super.onSuperTextViewClick();
                ToastUtil.showToast(SetActivity.this,"可以网页修改，也可以原生修改");
            }
        });

        clearCache.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener(){
            @Override
            public void onSuperTextViewClick() {
                super.onSuperTextViewClick();
                ToastUtil.showToast(SetActivity.this,"清理成功");
                clearCache.setRightString("0k");
            }
        });

        aboutMe.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener(){
            @Override
            public void onSuperTextViewClick() {
                super.onSuperTextViewClick();
                ToastUtil.showToast(SetActivity.this,"本程序由XXXX出品");
            }
        });

        callService.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener(){
            @Override
            public void onSuperTextViewClick() {
                super.onSuperTextViewClick();
                new MaterialDialog.Builder(SetActivity.this)
                        .title("拨打电话")
                        .content("现在拨打18376757141")
                        .positiveText("现在拨打")
                        .negativeText("取消")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                Intent i=new Intent("android.intent.action.CALL",Uri.parse("tel:".concat("18376757141")));
                                SetActivity.this.startActivity(i);
                            }
                        }).show();
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(SetActivity.this)
                        .content("是否注销已登陆账户")
                        .positiveText("注销")
                        .negativeText("取消")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                ToastUtil.showToast(SetActivity.this,"注销成功，请重新登陆");
                                LoginActivity.openLoginActivity(SetActivity.this);
                            }
                        }).show();
            }
        });
    }

    private void initToolsBar() {
        viewContent = (LinearLayout) findViewById(R.id.ll_content);
        MineNavigationBuilder bar = new MineNavigationBuilder(this);
        bar.setTitle("设置")
                .setLeftIcon(R.drawable.left_back)
                .setLeftIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        bar.createAndBind((ViewGroup) viewContent);
    }


    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }


    public static void openSetActivity(Activity activity) {
        Intent intent = new Intent(activity, SetActivity.class);
        activity.startActivity(intent);
    }

}

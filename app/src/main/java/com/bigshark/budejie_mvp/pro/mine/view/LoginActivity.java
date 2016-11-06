package com.bigshark.budejie_mvp.pro.mine.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseActivity;
import com.bigshark.budejie_mvp.pro.mine.view.navigation.LoginNavigationBulider;
import com.bigshark.budejie_mvp.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author bigShark
 * 登陆
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_login_qq)
    Button btLoginQq;
    @BindView(R.id.bt_login_sina)
    Button btLoginSina;
    @BindView(R.id.bt_login_tencent)
    Button btLoginTencent;
    @BindView(R.id.tv_forget)
    TextView tv_forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_login);
        ButterKnife.bind(this);
        initToolBar();
    }

    private void initToolBar() {
        LinearLayout ll_login = (LinearLayout) findViewById(R.id.activity_mine_login);
        LoginNavigationBulider builder = new LoginNavigationBulider(this);
        builder.setLeftIcon(R.drawable.left_back)
                .setLeftIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }).setTitle(R.string.login_and_register_text);
        builder.setTilteOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.openRegisterActivity(LoginActivity.this);
            }
        });
        builder.createAndBind(ll_login);
    }


    public static void openLoginActivity(Activity activity) {
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    @OnClick({R.id.bt_login, R.id.bt_login_qq, R.id.bt_login_sina, R.id.bt_login_tencent,R.id.tv_forget})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                ToastUtil.showToast(this,"登陆成功");
                finish();
                break;
            case R.id.bt_login_qq:
                ToastUtil.showToast(this,"使用QQ登陆");
                break;
            case R.id.bt_login_sina:
                ToastUtil.showToast(this,"使用新浪微博登陆");
                break;
            case R.id.bt_login_tencent:
                ToastUtil.showToast(this,"使用腾讯微博登陆");
                break;
            case R.id.tv_forget:
                break;
        }
    }
}

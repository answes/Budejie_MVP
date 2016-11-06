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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author bigShark
 *         注册
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.et_phoneNum)
    EditText etPhoneNum;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_yan)
    EditText etYan;
    @BindView(R.id.tv_getYan)
    TextView tvGetYan;
    @BindView(R.id.bt_register)
    Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_register);
        ButterKnife.bind(this);
        initToolBar();
    }

    private void initToolBar() {
        LinearLayout ll_login = (LinearLayout) findViewById(R.id.activity_mine_register);
        LoginNavigationBulider builder = new LoginNavigationBulider(this);
        builder.setLeftIcon(R.drawable.left_back)
                .setLeftIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
        builder.createAndBind(ll_login);
    }


    public static void openRegisterActivity(Activity activity) {
        activity.startActivity(new Intent(activity, RegisterActivity.class));
    }

    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    @OnClick({R.id.tv_getYan, R.id.bt_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_getYan:
                break;
            case R.id.bt_register:
                break;
        }
    }
}

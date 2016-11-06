package com.bigshark.budejie_mvp.pro.essence.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseActivity;
import com.bigshark.budejie_mvp.pro.essence.view.navigation.EssenceNavigationBuilder;
import com.bigshark.budejie_mvp.utils.VolleyUtils;

/**
 * 商店
 * Created by bigShark on 2016/10/18.
 */

public class EssenceBusinessActivity extends BaseActivity {
    private NetworkImageView logoBusiness;
    private TextView businessName;
    private TextView introduction;
    private TextView distance;

    private ImageView caoPhoto;
    private ImageView map;

    private String title;

    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        findAndBindView();
        initToolbar();
        initData();
    }

    private void initToolbar() {
        LinearLayout contentLayout = (LinearLayout) findViewById(R.id.ll_content);
        EssenceNavigationBuilder bar = new EssenceNavigationBuilder(this);
        if(getIntent() != null){
            bar.setTitle(getIntent().getStringExtra("title")).setLeftIcon(R.drawable.left_back)
                    .setLeftIconOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
        }else{
            bar.setTitle(getIntent().getStringExtra("商店")).setLeftIcon(R.drawable.left_back)
                    .setLeftIconOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
        }
        bar.createAndBind(contentLayout);
    }

    private void initData() {
        VolleyUtils.loadImage(this,logoBusiness,"http://www.gsby56.com/uploadfile/20130830165317580.jpg");

    }

    private void findAndBindView() {
        logoBusiness = (NetworkImageView) findViewById(R.id.iv_business_photo);
        businessName = (TextView) findViewById(R.id.tv_name);
        introduction = (TextView) findViewById(R.id.tv_introduction);
        distance = (TextView) findViewById(R.id.tv_distance);
        caoPhoto = (ImageView) findViewById(R.id.iv_caoPhoto);
        map = (ImageView) findViewById(R.id.iv_map);
    }

    public static void openEssenceBusinessActivity(Activity activity) {
        Intent intent = new Intent(activity, EssenceBusinessActivity.class);
        activity.startActivity(intent);
    }
    public static void openEssenceBusinessActivity(Activity activity,String title) {
        Intent intent = new Intent(activity, EssenceBusinessActivity.class);
        intent.putExtra("title",title);
        activity.startActivity(intent);
    }
}

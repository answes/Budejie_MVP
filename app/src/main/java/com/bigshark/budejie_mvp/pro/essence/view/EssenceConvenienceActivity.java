package com.bigshark.budejie_mvp.pro.essence.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.mvp.presenter.impl.MvpBasePresenter;
import com.bigshark.budejie_mvp.pro.base.view.BaseActivity;
import com.bigshark.budejie_mvp.pro.business.view.adapter.BusinessAdapter;
import com.bigshark.budejie_mvp.pro.business.view.navigation.BusinessNavigationBuilder;
import com.bigshark.budejie_mvp.pro.essence.view.adapter.ConvenienceAdapter;

import java.util.Arrays;

/**
 * 便民充值
 * Created by luyanhong on 16/10/9.
 */
public class EssenceConvenienceActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_essence_convenience);
        findAndBindView();
    }

    private void findAndBindView() {
        LinearLayout viewContent = (LinearLayout) findViewById(R.id.ll_content);
        ViewPager vp_attention = (ViewPager) findViewById(R.id.vp_attention);
        String[] titles = getResources().getStringArray(R.array.convenience_tab);
        ConvenienceAdapter adapter =
                new ConvenienceAdapter(getSupportFragmentManager(), Arrays.asList(titles));
        vp_attention.setAdapter(adapter);
        initToolBar(viewContent, vp_attention);
    }

    private void initToolBar(View viewContent, ViewPager viewPager) {
        BusinessNavigationBuilder builder = new BusinessNavigationBuilder(this);
        builder.setUpWithViewPager(viewPager).setLeftIcon(R.drawable.left_back)
                .setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        builder.createAndBind((ViewGroup) viewContent);
    }


    @Override
    public MvpBasePresenter bindPresenter() {
        return null;
    }

    public static void openEssenceConvenienceActivity(Activity context) {
        Intent intent = new Intent(context, EssenceConvenienceActivity.class);
        context.startActivity(intent);
    }

    public static void openEssenceConvenienceActivityForResult(Activity context, int reslutCode) {
        Intent intent = new Intent(context, EssenceConvenienceActivity.class);
        context.startActivityForResult(intent, reslutCode);

    }
}

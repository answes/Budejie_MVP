package com.bigshark.budejie_mvp.pro.shared.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.bigshark.budejie_mvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity {


    @BindView(R.id.niv_logo)
    NetworkImageView nivLogo;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.bt_buy)
    Button btBuy;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_introduction)
    TextView tvIntroduction;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.iv_caoPhoto)
    ImageView ivCaoPhoto;
    @BindView(R.id.iv_map)
    ImageView ivMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_buy, R.id.iv_caoPhoto, R.id.iv_map})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_buy:
                break;
            case R.id.iv_caoPhoto:
                break;
            case R.id.iv_map:
                break;
        }
    }
}

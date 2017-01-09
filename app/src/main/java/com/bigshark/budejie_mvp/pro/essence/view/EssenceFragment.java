package com.bigshark.budejie_mvp.pro.essence.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.allen.supertextviewlibrary.SuperTextView;
import com.android.volley.toolbox.NetworkImageView;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.bean.EssenceTestData;
import com.bigshark.budejie_mvp.pro.base.view.BaseFragment;
import com.bigshark.budejie_mvp.pro.essence.view.adapter.EssenceListAdapter;
import com.bigshark.budejie_mvp.pro.essence.view.navigation.EssenceNavigationBuilder;
import com.bigshark.budejie_mvp.pro.mine.view.SetActivity;
import com.bigshark.budejie_mvp.utils.ToastUtil;
import com.bigshark.budejie_mvp.utils.VolleyUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by bigShark on 2016/5/27.
 */
public class EssenceFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.id_banner)
    Banner banner;
    @BindView(R.id.niv_heand)
    NetworkImageView nivHeand;
    @BindView(R.id.bt_more)
    Button btMore;
    //    @BindView(R.id.lv_content)
//    RecyclerView recyclerView;
    private EssenceListAdapter adapter;
    private List<EssenceTestData> datas;

    private LinearLayout detectionLayout, illegalLayout,  insuranceLayout, convenienceLayout;
    private SuperTextView  serviceLayout, beautyLayout;

    private String[] images = new String[]{"http://img.anfone.net/outside/anfone/201610/20161021124342414.jpg",
            "http://pic4.nipic.com/20091117/3376018_110331702620_2.jpg",
    "http://img3.imgtn.bdimg.com/it/u=1750695933,688653522&fm=21&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3884549664,60250998&fm=21&gp=0.jpg"
    };
    private String[] titles = new String[]{"苹果A ", "奥迪Q4","宝马A8","大众"};

    @Override
    public int getContentView() {
        return R.layout.fragment_essence;
    }

    @Override
    public void initContentView(View viewContent) {
        ButterKnife.bind(this, viewContent);
        initToolBar(viewContent);
        findView(viewContent);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setImages(images);
        banner.setBannerTitle(titles);

        VolleyUtils.loadImage(getContext(), nivHeand, "http://r3.ykimg.com/05410408526F45F06A0A4C2175D4DB1C");
        datas = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            EssenceTestData data = new EssenceTestData();
            data.setUrl("http://r3.ykimg.com/05410408526F45F06A0A4C2175D4DB1C");
            data.setTitle("店铺测试");
            data.setSencendTitle("这个是店铺测试的小标题,可以放多一点文字看看有什么情况");
            data.setDistance("200km");
            datas.add(data);
        }

//        adapter = new EssenceListAdapter(getActivity(), datas);
//        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getContext()));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());
//        recyclerView.setAdapter(adapter);
    }

    private void findView(View viewContent) {
        detectionLayout = (LinearLayout) viewContent.findViewById(R.id.ll_detection);
        illegalLayout = (LinearLayout) viewContent.findViewById(R.id.ll_illegal);
        serviceLayout = (SuperTextView) viewContent.findViewById(R.id.ll_service);
        beautyLayout = (SuperTextView) viewContent.findViewById(R.id.ll_beauty);
        insuranceLayout = (LinearLayout) viewContent.findViewById(R.id.ll_insurance);
        convenienceLayout = (LinearLayout) viewContent.findViewById(R.id.ll_convenience);

        detectionLayout.setOnClickListener(this);
        illegalLayout.setOnClickListener(this);
//        serviceLayout.setOnClickListener(this);
//        beautyLayout.setOnClickListener(this);
        insuranceLayout.setOnClickListener(this);
        convenienceLayout.setOnClickListener(this);
        //检测维修
        serviceLayout.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener(){
            @Override
            public void onSuperTextViewClick() {
                super.onSuperTextViewClick();
                EssenceServiceActivity.openEssenceServiceActivity(getActivity());
            }
        });
//汽车美容
        beautyLayout.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener(){
            @Override
            public void onSuperTextViewClick() {
                super.onSuperTextViewClick();
                EssenceBeautyActivity.openEssenceBeautyActivity(getActivity());
            }
        });
    }

    private void initToolBar(View viewContent) {
        EssenceNavigationBuilder builder = new EssenceNavigationBuilder(getContext());
        builder.setSearchName("服务/地点")
                .setLeftIcon(R.drawable.lbs)
                .setRightIcon(R.drawable.call_phone)
                .setLeftIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .setRightIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new MaterialDialog.Builder(getActivity())
                                .title("拨打电话")
                                .content("现在拨打0771-7085566")
                                .positiveText("现在拨打")
                                .negativeText("取消")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        Intent i=new Intent("android.intent.action.CALL", Uri.parse("tel:".concat("0771-7085566")));
                                       startActivity(i);
                                    }
                                }).show();
                    }
                });

        builder.createAndBind((ViewGroup) viewContent);
    }


    @Override
    public void initData() {


    }

    @Override
    @OnClick(value = R.id.bt_more)
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ll_detection: //汽车检测
                EssenceDetectionActivity.openEssenceDetectionActivity(getActivity());
//                new DetectionFirstTipsDialog(getContext()).show();
                break;
            case R.id.ll_convenience:   //便民充值
                EssenceConvenienceActivity.openEssenceConvenienceActivity(getActivity());
                break;
            case R.id.ll_illegal:   //违章查询
                EssenceIllegalActivity.openEssenceIllegalActivity(getActivity());
                break;
            case R.id.ll_insurance: //汽车保险
                EssenceInsuranceActivity.openEssenceInsuranceActivity(getActivity());
                break;
            case R.id.bt_more:  //更多评论
                CommentMoreActivity.openCommentMoreActivity(getActivity());
                break;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}

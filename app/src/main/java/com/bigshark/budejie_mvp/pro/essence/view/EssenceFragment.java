package com.bigshark.budejie_mvp.pro.essence.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.volley.toolbox.NetworkImageView;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.bean.EssenceTestData;
import com.bigshark.budejie_mvp.pro.base.view.BaseFragment;
import com.bigshark.budejie_mvp.pro.essence.view.adapter.EssenceListAdapter;
import com.bigshark.budejie_mvp.pro.essence.view.navigation.EssenceNavigationBuilder;
import com.bigshark.budejie_mvp.utils.ToastUtil;
import com.bigshark.budejie_mvp.utils.VolleyUtils;
import com.youth.banner.Banner;

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

    private LinearLayout detectionLayout, illegalLayout, serviceLayout, beautyLayout, insuranceLayout, convenienceLayout;

    private String[] images = new String[]{"http://img4.imgtn.bdimg.com/it/u=3211314641,1153375401&fm=21&gp=0.jpg", "http://img1.imgtn.bdimg.com/it/u=2363027421,438461014&fm=21&gp=0.jpg"};
    private String[] titles = new String[]{"风景1 ", "风景2"};

    @Override
    public int getContentView() {
        return R.layout.fragment_essence;
    }

    @Override
    public void initContentView(View viewContent) {
        ButterKnife.bind(this, viewContent);
        initToolBar(viewContent);
        findView(viewContent);
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
        serviceLayout = (LinearLayout) viewContent.findViewById(R.id.ll_service);
        beautyLayout = (LinearLayout) viewContent.findViewById(R.id.ll_beauty);
        insuranceLayout = (LinearLayout) viewContent.findViewById(R.id.ll_insurance);
        convenienceLayout = (LinearLayout) viewContent.findViewById(R.id.ll_convenience);

        detectionLayout.setOnClickListener(this);
        illegalLayout.setOnClickListener(this);
        serviceLayout.setOnClickListener(this);
        beautyLayout.setOnClickListener(this);
        insuranceLayout.setOnClickListener(this);
        convenienceLayout.setOnClickListener(this);
    }

    private void initToolBar(View viewContent) {
        EssenceNavigationBuilder builder = new EssenceNavigationBuilder(getContext());
        builder.setTitle("车友之家")
                .setLeftIcon(R.drawable.fragment_essence_mine)
                .setRightIcon(R.drawable.main_essence_btn_more_selector)
                .setLeftIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showToast(getContext(), "我的");
                    }
                })
                .setRightIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showToast(getContext(), "分享");
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
            case R.id.ll_beauty:    //汽车美容
                EssenceBeautyActivity.openEssenceBeautyActivity(getActivity());
                break;
            case R.id.ll_illegal:   //违章查询
                EssenceIllegalActivity.openEssenceIllegalActivity(getActivity());
                break;
            case R.id.ll_service:   //检测维修
                EssenceServiceActivity.openEssenceServiceActivity(getActivity());
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

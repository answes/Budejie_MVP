package com.bigshark.budejie_mvp.pro.activity.view.navigation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.bean.ActivityTestData;
import com.bigshark.budejie_mvp.bean.EssenceTestData;
import com.bigshark.budejie_mvp.utils.BitmapCache;

import java.util.List;

/**
 * Created by bigShark on 2016/5/29.
 */
public class ActivityListAdapter extends BaseRecyclerAdapter<ActivityListAdapter.AdapterViewHolder> {

    private Context context;
    private List<ActivityTestData> datas;
    private ItemOnClickListener itemOnClickListener;

    public ActivityListAdapter(Context context, List<ActivityTestData> datas) {
        this.context = context;
        this.datas = datas;
    }

    /**
     * 配置ViewHolder
     *
     * @param view
     * @return
     */
    @Override
    public AdapterViewHolder getViewHolder(View view) {
        return new AdapterViewHolder(view, false,itemOnClickListener);
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(context).inflate(
                R.layout.item_fragment_activitylist_layout, parent, false);
        AdapterViewHolder holder = new AdapterViewHolder(v, true,itemOnClickListener);
        return holder;
    }

    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener){
        this.itemOnClickListener= itemOnClickListener;
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position, boolean isItem) {
       ActivityTestData data = datas.get(position);
        loadImage(holder.iv_header, data.getUrl());
        holder.tv_title.setText(data.getName());
        holder.tv_secend_title.setText(data.getContent());

    }

    @Override
    public int getAdapterItemCount() {
        return datas.size();
    }

    //Volley框架
    private void loadImage(NetworkImageView imageView, String url) {
        RequestQueue queue = Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(queue, new BitmapCache());
        imageView.setImageUrl(url, imageLoader);
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        public NetworkImageView iv_header;
        public TextView tv_title;
        public TextView tv_secend_title;
        private ItemOnClickListener itemOnClickListener;

        public int position;

        public AdapterViewHolder(View itemView, boolean isItem,ItemOnClickListener itemOnClickListener) {
            super(itemView);
            this.itemOnClickListener = itemOnClickListener;
            if (isItem) {
                iv_header = (NetworkImageView) itemView
                        .findViewById(R.id.iv_activity_bg);
                tv_title = (TextView) itemView
                        .findViewById(R.id.tv_activity_title);
                tv_secend_title = (TextView) itemView.findViewById(R.id.tv_activity_details);
                itemView.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            if(itemOnClickListener != null) {
                itemOnClickListener.onItemClick(v,getPosition());
            }

        }
    }
    public interface  ItemOnClickListener {
        void onItemClick(View view,int postion);
    }
}

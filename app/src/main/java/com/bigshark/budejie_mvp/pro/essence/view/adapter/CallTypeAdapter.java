package com.bigshark.budejie_mvp.pro.essence.view.adapter;

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
import com.bigshark.budejie_mvp.bean.CallType;
import com.bigshark.budejie_mvp.bean.EssenceTestData;
import com.bigshark.budejie_mvp.utils.BitmapCache;

import java.util.List;

/**
 * Created by bigShark on 2016/5/29.
 */
public class CallTypeAdapter extends BaseRecyclerAdapter<CallTypeAdapter.AdapterViewHolder> {

    private Context context;
    private List<CallType> list;
    private ItemOnClickListener itemOnClickListener;



    public CallTypeAdapter(Context context, List<CallType> list) {
        this.context = context;
        this.list = list;
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
                R.layout.item_fragment_recharge_call, parent, false);
        AdapterViewHolder holder = new AdapterViewHolder(v, true,itemOnClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position, boolean isItem) {

        CallType postList = this.list.get(position);
        holder.tv_price.setText(postList.getPrice().concat("元"));
        holder.tv_disPrice.setText("售价："+postList.getDiscountedPrice());
    }

    @Override
    public int getAdapterItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(ItemOnClickListener itemOnClickListener){
        this.itemOnClickListener = itemOnClickListener;

    }

    //Volley框架
    private void loadImage(NetworkImageView imageView, String url) {
        RequestQueue queue = Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(queue, new BitmapCache());
        imageView.setImageUrl(url, imageLoader);
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tv_price;
        public TextView tv_disPrice;
        private ItemOnClickListener itemOnClickListener;

        public AdapterViewHolder(View itemView, boolean isItem,ItemOnClickListener itemOnClickListener) {
            super(itemView);
            this.itemOnClickListener = itemOnClickListener;
            itemView.setOnClickListener(this);
            if (isItem) {
                tv_price = (TextView) itemView
                        .findViewById(R.id.tv_price);
                tv_disPrice = (TextView) itemView.findViewById(R.id.tv_disPrice);
            }
        }

        @Override
        public void onClick(View v) {
        if(itemOnClickListener != null){
            itemOnClickListener.onItemClick(v,getPosition());
         }
        }
    }


    public interface  ItemOnClickListener{
        void onItemClick(View view, int postion);
    }
}

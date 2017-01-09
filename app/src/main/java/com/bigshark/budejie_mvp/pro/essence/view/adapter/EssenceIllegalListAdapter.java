package com.bigshark.budejie_mvp.pro.essence.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allen.supertextviewlibrary.SuperTextView;
import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.bean.EssenceTestData;
import com.bigshark.budejie_mvp.bean.Illegal;
import com.bigshark.budejie_mvp.bean.Record;
import com.bigshark.budejie_mvp.pro.custom_view.XCRoundRectImageView;
import com.bigshark.budejie_mvp.utils.VolleyUtils;

import java.util.List;

/**
 * Created by bigShark on 2016/5/29.
 */
public class EssenceIllegalListAdapter extends BaseRecyclerAdapter<EssenceIllegalListAdapter.AdapterViewHolder> {

    private Context context;
    private List<Record> list;
    private ItemOnClickListener itemOnClickListener;

    public EssenceIllegalListAdapter(Context context, List<Record> list) {
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
                R.layout.item_fragment_essence_illegal_layout, parent, false);
        AdapterViewHolder holder = new AdapterViewHolder(v, true,itemOnClickListener);
        return holder;
    }
    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener){
        this.itemOnClickListener = itemOnClickListener;
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position, boolean isItem) {

        Record record = this.list.get(position);
        holder.stvTime.setLeftString("违章时间");
        holder.stvTime.setRightString(record.getTime());
        holder.stvReason.setLeftTopString("违章行为");
        holder.stvReason.setLeftBottomString(record.getTime());
        holder.stvAddress.setLeftTopString("违章地点");
        holder.stvAddress.setLeftBottomString(record.getAddress());
        holder.stvTime.setLeftString("罚款金额   ".concat(record.getMoney()));
        holder.stvTime.setRightString("扣分".concat(record.getDegree()));
    }

    @Override
    public int getAdapterItemCount() {
        return list.size();
    }

    //Volley框架
//    private void loadImage(NetworkImageView imageView, String url) {
//        RequestQueue queue = Volley.newRequestQueue(context);
//        ImageLoader imageLoader = new ImageLoader(queue, new BitmapCache());
//        imageView.setImageUrl(url, imageLoader);
//    }

    class AdapterViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        public SuperTextView stvTime;
        public SuperTextView stvReason;
        public SuperTextView stvAddress;
        public SuperTextView stvMoeny;

        private ItemOnClickListener itemOnClickListener;

        public int position;

        public AdapterViewHolder(View itemView, boolean isItem, ItemOnClickListener itemOnClickListener) {
            super(itemView);
            this.itemOnClickListener = itemOnClickListener;
            if (isItem) {
                stvTime = (SuperTextView) itemView
                        .findViewById(R.id.stv_time);
                stvReason = (SuperTextView) itemView
                        .findViewById(R.id.stv_reason);
                stvAddress = (SuperTextView) itemView.findViewById(R.id.stv_address);
                stvMoeny = (SuperTextView) itemView.findViewById(R.id.stv_moeny);
                itemView.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            if(itemOnClickListener != null){
                itemOnClickListener.onItemClick(v,getPosition());
            }
        }
    }


    public interface  ItemOnClickListener {
        void onItemClick(View view, int postion);
    }
}

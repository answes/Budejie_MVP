package com.bigshark.budejie_mvp.pro.business.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.bigshark.budejie_mvp.R;
import com.bigshark.budejie_mvp.bean.EssenceTestData;
import com.bigshark.budejie_mvp.utils.BitmapCache;

import java.util.List;

/**
 * Created by luyanhong on 16/9/28.
 */
public class BusinessListAdapter extends BaseAdapter {
    private List<EssenceTestData> list;
    private Context context;

    public BusinessListAdapter(Context context, List<EssenceTestData> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //Volley框架
    private void loadImage(NetworkImageView imageView, String url) {
        RequestQueue queue = Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(queue, new BitmapCache());
        imageView.setImageUrl(url, imageLoader);
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_fragment_essence_list, viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.iv_header = (NetworkImageView) view.findViewById(R.id.iv_header);
            viewHolder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            viewHolder.tv_distance = (TextView) view.findViewById(R.id.tv_distance);
            viewHolder.tv_secend_title = (TextView)view.findViewById(R.id.tv_secend_title);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        loadImage( viewHolder.iv_header,list.get(i).getUrl());
        viewHolder.tv_title.setText(list.get(i).getTitle());
        viewHolder.tv_distance.setText(list.get(i).getDistance());
        viewHolder.tv_secend_title.setText(list.get(i).getSencendTitle());


        return view;
    }

    class ViewHolder {
         NetworkImageView iv_header;
         TextView tv_title;
         TextView tv_secend_title;
         TextView tv_distance;
    }
}

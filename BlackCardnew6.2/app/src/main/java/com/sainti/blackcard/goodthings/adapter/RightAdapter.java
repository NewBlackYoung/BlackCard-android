package com.sainti.blackcard.goodthings.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sainti.blackcard.R;
import com.sainti.blackcard.goodthings.bean.NewListBean;
import com.sainti.blackcard.mwebview.CommenWebActivity;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/7/19.
 */

public class RightAdapter extends BaseAdapter {
    private List<NewListBean.DataBean> list;
    LayoutInflater inflater;
    private Context context;

    public RightAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<NewListBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list != null && list.size() > 0 ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.right_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }
        holder.title.setText(list.get(position).getWt_name());
        RecyclerView recyclerView = holder.rv_right;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        RightImageAdapter rightImageAdapter = new RightImageAdapter(R.layout.item_goodthings);
        rightImageAdapter.setContext(context);
        rightImageAdapter.setNewData(list.get(position).getGoods());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(rightImageAdapter);
        final int p = position;
        rightImageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(context, CommenWebActivity.class);
                intent.putExtra("wel_url", list.get(p ).getGoods().get(position).getWel_url());
                intent.putExtra("title", list.get(p ).getGoods().get(position).getW_title());
                intent.putExtra("img_url", list.get(p ).getGoods().get(position).getW_pic());
                intent.putExtra("name1",list.get(p ).getGoods().get(position).getW_title2());
                intent.putExtra("w_id", list.get(p ).getGoods().get(position).getW_id());
                intent.putExtra("small_title", list.get(p ).getGoods().get(position).getW_title3());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    public static class ViewHolder {

        private TextView title;
        private RecyclerView rv_right;

        public ViewHolder(View itemView) {
            title = (TextView) itemView.findViewById(R.id.title);
            rv_right = (RecyclerView) itemView.findViewById(R.id.rv_right);
        }
    }

}

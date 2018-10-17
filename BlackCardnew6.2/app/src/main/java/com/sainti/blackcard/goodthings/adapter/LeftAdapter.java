package com.sainti.blackcard.goodthings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.goodthings.bean.NewListBean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/7/19.
 */

public class LeftAdapter extends BaseAdapter {
    private List<NewListBean.DataBean> list;
    LayoutInflater inflater;
    private Context context;
    private int ps;

    public void setPs(int ps) {
        this.ps = ps;
        notifyDataSetChanged();
    }

    public LeftAdapter(Context context) {
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
            convertView = inflater.inflate(R.layout.left_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text.setText(list.get(position).getWt_name());
        if (ps == position) {
            holder.sl.setVisibility(View.VISIBLE);
            holder.text.setBackgroundColor(context.getResources().getColor(R.color.white));
        } else {
            holder.sl.setVisibility(View.INVISIBLE);
            holder.text.setBackgroundColor(context.getResources().getColor(R.color.e_e));
        }
        return convertView;

    }

    public static class ViewHolder {

        private TextView text, sl;

        public ViewHolder(View itemView) {
            text = (TextView) itemView.findViewById(R.id.text);
            sl = (TextView) itemView.findViewById(R.id.sl);
        }
    }

}

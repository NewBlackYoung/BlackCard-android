package com.sainti.blackcard.circle.releasecircle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.minterface.MyItemClickListener;
import com.sainti.blackcard.circle.releasecircle.activity.TopicActivity;
import com.sainti.blackcard.circle.releasecircle.bean.TopicBean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/4/17.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {
    private Context context;
    private List<TopicBean.DataBean> list;
    private MyItemClickListener listener;

    public void setListener(TopicActivity listener) {
        this.listener = listener;
    }

    public void setList(List<TopicBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public TopicAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_topic, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.to_name.setText(list.get(i).getTo_name());
        viewHolder.to_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(i, 0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null && list.size() > 0 ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView to_name;


        public ViewHolder(View itemView) {
            super(itemView);
            to_name = (TextView) itemView.findViewById(R.id.to_name);


        }
    }
}

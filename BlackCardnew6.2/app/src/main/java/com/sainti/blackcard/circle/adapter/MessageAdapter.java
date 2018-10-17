package com.sainti.blackcard.circle.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.circle.activity.FindDetailActivity;
import com.sainti.blackcard.circle.bean.MessageBean;
import com.sainti.blackcard.mhttp.glide.GlideManager;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/4/16.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private Context context;
    private List<MessageBean.DataBean> list;

    public void setList(List<MessageBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public MessageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View viewfooder = LayoutInflater.from(context).inflate(R.layout.item_message, viewGroup, false);
        ViewHolder viewHolderf = new ViewHolder(viewfooder);
        return viewHolderf;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        GlideManager.getsInstance().loadImageToUrL(context, list.get(i).getFind_img(), viewHolder.find_img2);
        GlideManager.getsInstance().loadImageToUrL(context, list.get(i).getUser_img(), viewHolder.user_img);
        viewHolder.m_time.setText(list.get(i).getM_time());
        viewHolder.m_title.setText(list.get(i).getM_title());
        viewHolder.lay_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FindDetailActivity.class);
                intent.putExtra("find_id", list.get(i).getM_dataid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null && list.size() > 0 ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView user_img, find_img2;
        private RelativeLayout lay_msg;
        private TextView m_title, m_time;


        public ViewHolder(View itemView) {
            super(itemView);
            user_img = (ImageView) itemView.findViewById(R.id.user_img);// 头像
            lay_msg = (RelativeLayout) itemView.findViewById(R.id.lay_msg);
            find_img2 = (ImageView) itemView.findViewById(R.id.find_img2); // 会员分类图标
            m_title = (TextView) itemView.findViewById(R.id.m_title);//  名字
            m_time = (TextView) itemView.findViewById(R.id.m_time);//  内容
        }
    }
}

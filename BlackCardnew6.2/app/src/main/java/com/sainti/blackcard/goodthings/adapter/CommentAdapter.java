/*
package com.sainti.blackcard.goodthings.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.goodthings.bean.GoodThingsBean;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.minterface.MyItemClickListener;

import java.util.List;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<GoodThingsBean.DataBean> list;
    private Context context;
    private MyItemClickListener listener;

    public void setListener(MyItemClickListener listener) {
        this.listener = listener;
    }

    public CommentAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<GoodThingsBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_commentthings, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_commentPice.setText("¥ "+list.get(position).getW_jiage());
        holder.tv_commentYuanjia.setText("¥ "+list.get(position).getW_yuanjia());
        holder.tv_commentTtiletv.setText(list.get(position).getW_title());
        GlideManager.getsInstance().loadImageToUrL(context, list.get(position).getW_pic(), holder.iv_comment_goodthings);
        holder.iv_comment_goodthings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position, 4);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null && list.size() > 0 ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_commentTtiletv, tv_commentPice, tv_commentYuanjia;
        ImageView iv_comment_goodthings;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_commentTtiletv = (TextView) itemView.findViewById(R.id.tv_commentTtiletv);
            tv_commentPice = (TextView) itemView.findViewById(R.id.tv_commentPice);
            tv_commentYuanjia = (TextView) itemView.findViewById(R.id.tv_commentYuanjia);
            iv_comment_goodthings = (ImageView) itemView.findViewById(R.id.iv_comment_goodthings);
        }
    }
}
*/

package com.sainti.blackcard.privilege.moreprivilege.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.minterface.MyItemClickListener;
import com.sainti.blackcard.privilege.moreprivilege.bean.MoreTeQuanBean;

import java.util.List;


/**
 * Created by dell on 2018-3-19.
 */

public class MoreTeQuanAdapter extends RecyclerView.Adapter<MoreTeQuanAdapter.ViewHolder> {
    private Context context;
    private List<MoreTeQuanBean.DataBean> list;
    private MyItemClickListener listener;

    public void setListener(MyItemClickListener listener) {
        this.listener = listener;
        notifyDataSetChanged();
    }

    public void setList(List<MoreTeQuanBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public MoreTeQuanAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_moretequan, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        GlideManager.getsInstance().loadImageToUrL(context, list.get(position).getXp_icon(), holder.iv_icon);
        holder.tv_title.setText(list.get(position).getXp_name());
        holder.lay_moretequan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position, 9);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null && list.size() > 0 ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_icon;
        private TextView tv_title;
        private LinearLayout lay_moretequan;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_iamge);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            lay_moretequan = (LinearLayout) itemView.findViewById(R.id.lay_moretequan);
        }
    }
}

package com.sainti.blackcard.privilege.adapter;

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
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.MLog;
import com.sainti.blackcard.privilege.bean.PrivilegeBean;

import java.util.List;


public class LocalDataAdapter extends RecyclerView.Adapter<LocalDataAdapter.ViewHolder> {
    private List<PrivilegeBean.DataBean.WelfareBean> list;
    private Context context;
    private MyItemClickListener listener;


    public void setListener(MyItemClickListener listener) {
        this.listener = listener;
    }

    public LocalDataAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<PrivilegeBean.DataBean.WelfareBean> list) {
        this.list = list;
        MLog.d("aaaaa", "适配器数据条数" + list.size() + "");
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_over, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        GlideManager.getsInstance().loadImageToUrLCircle(context, list.get(position).getW_pic(), holder.imageView);
        // holder.imageView.setTag(position);
        holder.content.setText(list.get(position).getW_title2());
        holder.title.setText(list.get(position).getW_title3());
        holder.price.setText("¥ " + list.get(position).getW_price());
        holder.lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position, 3);
                }

            }
        });
        if (list.get(position).getW_yuanjia().equals("0")) {
            holder.w_yuanjia.setVisibility(View.GONE);
        } else {
            holder.w_yuanjia.setVisibility(View.VISIBLE);
            if (list.get(position).getW_yuanjia_name().equals("")) {
                holder.w_yuanjia_name.setVisibility(View.GONE);
                holder.w_yuanjia.setText("¥" + list.get(position).getW_yuanjia());
            } else {
                holder.w_yuanjia_name.setVisibility(View.VISIBLE);
                holder.w_yuanjia_name.setText(list.get(position).getW_yuanjia_name() + ":");
                holder.w_yuanjia.setText("¥" + list.get(position).getW_yuanjia());
            }
            CommontUtil.addCenterLine(holder.w_yuanjia);
        }

    }

    @Override
    public int getItemCount() {

        return list != null && list.size() > 0 ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, content, price, w_yuanjia, w_yuanjia_name;
        LinearLayout lay;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            price = (TextView) itemView.findViewById(R.id.price);
            lay = (LinearLayout) itemView.findViewById(R.id.lay);
            w_yuanjia = (TextView) itemView.findViewById(R.id.w_yuanjia);
            w_yuanjia_name = (TextView) itemView.findViewById(R.id.w_yuanjia_name);

        }
    }


}

package com.sainti.blackcard.coffee.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.db.bean.CoffeeLookBean;
import com.sainti.blackcard.mhttp.glide.GlideManager;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/3/18.
 */

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {
    private Context context;
    private List<CoffeeLookBean> lookBeen;
    private int Code = 0;

    public void setCode(int code) {
        Code = code;
        notifyDataSetChanged();
    }

    public void setLookBeen(List<CoffeeLookBean> lookBeen) {
        this.lookBeen = lookBeen;
        notifyDataSetChanged();
    }

    public OrderDetailAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_orderdetail, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_count.setText("x" + lookBeen.get(position).getChoiceCount());
        holder.tv_name.setText(lookBeen.get(position).getCoffeeName());
        holder.tv_kind.setText(lookBeen.get(position).getCoffeeKind());
        holder.tv_price.setText("¥" + lookBeen.get(position).getCoffeePrice());
        GlideManager.getsInstance().loadImageToUrL(context, lookBeen.get(position).getImageUrl(), holder.iv_photo);
    }

    @Override
    public int getItemCount() {
        if (lookBeen != null && lookBeen.size() <= 3 || Code == 1) {
            return lookBeen.size();
        }
        if (lookBeen != null && lookBeen.size() > 3 && Code != 1) {
            return 3;
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_kind, tv_count, tv_price;
        private ImageView iv_photo;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_kind = (TextView) itemView.findViewById(R.id.tv_kind);
            tv_count = (TextView) itemView.findViewById(R.id.tv_count);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            iv_photo = (ImageView) itemView.findViewById(R.id.iv_photo);
        }

    }
}

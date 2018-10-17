package com.sainti.blackcard.coffee.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.coffee.bean.RightCoffeeBean;
import com.sainti.blackcard.minterface.CoffeeRightItemClickListener;

import java.util.List;


/**
 * Created by YuZhenpeng on 2018/3/15.
 */

public class PopuWindowAdapter extends RecyclerView.Adapter<PopuWindowAdapter.ViewHolder> {
    private Context context;
    private List<RightCoffeeBean.ItemsListBean.OrdSpecificationsListBean> listBeen;
    private CoffeeRightItemClickListener listener;
    private int code = 0;
    private int waiCengPision;

    public void setCode(int code) {
        this.code = code;
        notifyDataSetChanged();
    }

    public void setListener(CoffeeRightItemClickListener listener) {
        this.listener = listener;
    }

    public void setListBeen(List<RightCoffeeBean.ItemsListBean.OrdSpecificationsListBean> listBeen, int waiCengPision) {
        this.listBeen = listBeen;
        this.waiCengPision = waiCengPision;
        notifyDataSetChanged();
    }

    public PopuWindowAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_popuwindow_coffee, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (position == code) {
            holder.tv_popuw.setTextColor(context.getResources().getColor(R.color.black));
            holder.tv_popuw.setBackground(context.getResources().getDrawable(R.drawable.shap_popubtn_select));
        } else {
            holder.tv_popuw.setTextColor(context.getResources().getColor(R.color.white));
            holder.tv_popuw.setBackground(context.getResources().getDrawable(R.drawable.shap_popubtn_unselect));
        }
        holder.tv_popuw.setText(listBeen.get(position).getSftName());
        holder.tv_popuw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCoffeeRightItemClick(position, 1,waiCengPision);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeen != null && listBeen.size() > 0 ? listBeen.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_popuw;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_popuw = (TextView) itemView.findViewById(R.id.tv_popuw);
        }

    }
}
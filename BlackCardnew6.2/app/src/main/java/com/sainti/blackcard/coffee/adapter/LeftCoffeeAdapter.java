package com.sainti.blackcard.coffee.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.coffee.bean.LeftCoffeeBean;
import com.sainti.blackcard.minterface.MyItemClickListener;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/3/15.
 */

public class LeftCoffeeAdapter extends RecyclerView.Adapter<LeftCoffeeAdapter.ViewHolder> {
    private Context context;
    private List<LeftCoffeeBean.ItemsListBean> listBeen;
    private MyItemClickListener listener;
    private int dianjiCode = 0;

    public void setDianjiCode(int dianjiCode) {
        this.dianjiCode = dianjiCode;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == listBeen.size()) {
            return 0;
        } else {
            return 1;
        }

    }

    public void setListener(MyItemClickListener listener) {
        this.listener = listener;
    }

    public void setListBeen(List<LeftCoffeeBean.ItemsListBean> listBeen) {
        this.listBeen = listBeen;
        notifyDataSetChanged();
    }

    public LeftCoffeeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View viewfooder = LayoutInflater.from(context).inflate(R.layout.view_kongbai_coffee, parent, false);
                ViewHolder viewHolderf = new ViewHolder(viewfooder);
                return viewHolderf;
            case 1:
                View view = LayoutInflater.from(context).inflate(R.layout.item_leftcoffee, parent, false);
                ViewHolder viewHolder = new ViewHolder(view);
                return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (position <listBeen.size()) {
            if (dianjiCode == position) {
                holder.tv_title_left.setBackground(context.getResources().getDrawable(R.drawable.shap_leftcoffee_selet));
                holder.tv_title_left.setTextColor(Color.BLACK);
            } else {
                holder.tv_title_left.setBackground(context.getResources().getDrawable(R.drawable.shap_leftcoffee_unselet));
                holder.tv_title_left.setTextColor(context.getResources().getColor(R.color.e_eight));
            }
            holder.tv_title_left.setText(listBeen.get(position).getCg_name());
            holder.tv_title_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(position, 5);
                }
            });


        }

    }

    @Override
    public int getItemCount() {
        return listBeen != null && listBeen.size() > 0 ? listBeen.size() + 1 : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title_left;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title_left = (TextView) itemView.findViewById(R.id.tv_title_left);

        }
    }

    private void chick() {

    }
}

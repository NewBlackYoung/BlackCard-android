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
import com.sainti.blackcard.minterface.MyItemCountListener;

import java.util.List;

public class PopuwindowListAdapter extends RecyclerView.Adapter<PopuwindowListAdapter.ViewHolder> {
    List<CoffeeLookBean> lookCoffeeBeen;
    private Context context;
    private MyItemCountListener listener;

    public void setListener(MyItemCountListener listener) {
        this.listener = listener;
    }

    public PopuwindowListAdapter(Context context) {
        this.context = context;
    }

    public void setLookCoffeeBeen(List<CoffeeLookBean> lookCoffeeBeen) {
        this.lookCoffeeBeen = lookCoffeeBeen;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_popuwindow_look, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_count_coffee.setText(lookCoffeeBeen.get(position).getChoiceCount());
        holder.tv_kind_coffee.setText(lookCoffeeBeen.get(position).getCoffeeKind());
        holder.tv_name_coffee.setText(lookCoffeeBeen.get(position).getCoffeeName());
        holder.tv_price_coffee.setText("¥" + lookCoffeeBeen.get(position).getCoffeePrice());
      //  final int p = holder.getLayoutPosition();
        // 加号
        holder.iv_jiahao_coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(holder.tv_count_coffee.getText().toString());
                count++;
                holder.tv_count_coffee.setText(String.valueOf(count));
                listener.onItemClick(position, 6, String.valueOf(count));
            }
        });
        // 减号
        holder.iv_jianhao_coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int count = Integer.parseInt(holder.tv_count_coffee.getText().toString());
                count--;
                if (count > 0) {
                    holder.tv_count_coffee.setText(String.valueOf(count));
                    listener.onItemClick(position, 7, String.valueOf(count));
                }
                if (count == 0) {
                    listener.onItemClick(position, 7, String.valueOf(count));

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lookCoffeeBeen != null && lookCoffeeBeen.size() > 0 ? lookCoffeeBeen.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name_coffee, tv_kind_coffee, tv_price_coffee, tv_count_coffee;
        private ImageView iv_jianhao_coffee, iv_jiahao_coffee;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name_coffee = (TextView) itemView.findViewById(R.id.tv_name_coffee);
            tv_kind_coffee = (TextView) itemView.findViewById(R.id.tv_kind_coffee);
            tv_price_coffee = (TextView) itemView.findViewById(R.id.tv_price_coffee);
            tv_count_coffee = (TextView) itemView.findViewById(R.id.tv_count_coffee);
            iv_jianhao_coffee = (ImageView) itemView.findViewById(R.id.iv_jianhao_coffee);
            iv_jiahao_coffee = (ImageView) itemView.findViewById(R.id.iv_jiahao_coffee);
        }

    }
}

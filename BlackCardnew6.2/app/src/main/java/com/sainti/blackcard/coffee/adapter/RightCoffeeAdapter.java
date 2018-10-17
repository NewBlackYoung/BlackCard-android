package com.sainti.blackcard.coffee.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.coffee.bean.RightCoffeeBean;
import com.sainti.blackcard.db.bean.CoffeeCountBean;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.minterface.MyItemClickListener;
import com.sainti.blackcard.minterface.MyItemCountListener;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/3/15.
 */

public class RightCoffeeAdapter extends RecyclerView.Adapter<RightCoffeeAdapter.ViewHolder> {
    private Context context;
    private List<RightCoffeeBean.ItemsListBean> listBeen;
    private MyItemClickListener listener;
    private MyItemCountListener myItemCountListener;
    private List<CoffeeCountBean> lookBeen;

    public void setLookBeen(List<CoffeeCountBean> lookBeen) {
        this.lookBeen = lookBeen;
    }

    public void setMyItemCountListener(MyItemCountListener myItemCountListener) {
        this.myItemCountListener = myItemCountListener;
    }

    public void setListener(MyItemClickListener listener) {
        this.listener = listener;
    }

    public void setListBeen(List<RightCoffeeBean.ItemsListBean> listBeen) {
        this.listBeen = listBeen;
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

    public RightCoffeeAdapter(Context context) {
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
                View view = LayoutInflater.from(context).inflate(R.layout.item_rightcoffee, parent, false);
                ViewHolder viewHolder = new ViewHolder(view);
                return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (position < listBeen.size()) {
            //  final int p = holder.getLayoutPosition();
            GlideManager.getsInstance().loadImageToUrL(context, listBeen.get(position).getImageUrl(), holder.iv_imageCoffee1);
            holder.tv_titleCoffee.setText(listBeen.get(position).getGdsName());
            if (listBeen.get(position).getGdsPirce() == null) {
                holder.tv_priceCoffee.setText("¥" + listBeen.get(position).getOrdSpecificationsList().get(0).getSftPrice() + "起");
            } else {
                holder.tv_priceCoffee.setText("¥" + listBeen.get(position).getGdsPirce().toString());
            }
            holder.iv_jiahao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int count = Integer.parseInt(holder.tv_count.getText().toString());
                    count++;
                    myItemCountListener.onItemClick(position, 0, String.valueOf(count));
                    if (listBeen.get(position).getOrdSpecificationsList().size() == 0) {
                        holder.tv_count.setText(String.valueOf(count));
                    }

                }
            });
            holder.iv_jianhao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int count = Integer.parseInt(holder.tv_count.getText().toString());
                    count--;
                    if (count < 0) {
                        return;
                    } else {
                        myItemCountListener.onItemClick(position, 1, String.valueOf(count));
                        if (listBeen.get(position).getOrdSpecificationsList().size() == 0) {
                            holder.tv_count.setText(String.valueOf(count));
                        }
                    }

                }
            });
            reficeCount(position, holder);//重新设置count数据
        }
    }

    @Override
    public int getItemCount() {
        return listBeen != null && listBeen.size() > 0 ? listBeen.size() + 1 : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_jiahao, iv_jianhao;
        private TextView tv_titleCoffee, tv_priceCoffee, tv_count;
        private final ImageView iv_imageCoffee1;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_imageCoffee1 = (ImageView) itemView.findViewById(R.id.iv_imageCoffee);
            tv_titleCoffee = (TextView) itemView.findViewById(R.id.tv_titleCoffee);
            tv_priceCoffee = (TextView) itemView.findViewById(R.id.tv_priceCoffee);
            tv_count = (TextView) itemView.findViewById(R.id.tv_count);
            iv_jiahao = (ImageView) itemView.findViewById(R.id.iv_jiahao);
            iv_jianhao = (ImageView) itemView.findViewById(R.id.iv_jianhao);
        }
    }

    private void reficeCount(int position, ViewHolder holder) {
        holder.tv_count.setText("0");
        if (lookBeen != null && lookBeen.size() > 0) {
            for (int i = 0; i < lookBeen.size(); i++) {
                if (listBeen.get(position).getGdsName().equals(lookBeen.get(i).getCoffeeName())) {
                    holder.tv_count.setText(lookBeen.get(i).getChoiceCount());
                }
            }
        }
    }
}

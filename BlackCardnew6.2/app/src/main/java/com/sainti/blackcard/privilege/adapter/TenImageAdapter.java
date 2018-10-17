package com.sainti.blackcard.privilege.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.minterface.MyItemClickListener;
import com.sainti.blackcard.privilege.bean.PrivilegeBean;

import java.util.List;


public class TenImageAdapter extends RecyclerView.Adapter<TenImageAdapter.ViewHolder> {
    private Context context;
    private List<PrivilegeBean.DataBean.IconBean> iconBeanList;
    private MyItemClickListener litener;

    public void setLitener(MyItemClickListener litener) {
        this.litener = litener;
    }

    public void setIconBeanList(List<PrivilegeBean.DataBean.IconBean> iconBeanList) {
        this.iconBeanList = iconBeanList;
        notifyDataSetChanged();
    }

    public TenImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_priviege_eightimage, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.tv_tenIcon.setText(iconBeanList.get(i).getXp_name());
        if (!iconBeanList.get(i).getXp_icon().equals("")) {
            GlideManager.getsInstance().loadImageToUrL(context, iconBeanList.get(i).getXp_icon(), viewHolder.iv_tenIcon);
        }
        viewHolder.lay_tenIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                litener.onItemClick(i,2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return iconBeanList != null && iconBeanList.size() > 0 ? iconBeanList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_tenIcon;
        private ImageView iv_tenIcon;
        RelativeLayout lay_tenIcon ;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_tenIcon = (TextView) itemView.findViewById(R.id.tv_tenIcon);
            iv_tenIcon = (ImageView) itemView.findViewById(R.id.iv_tenIcon);
            lay_tenIcon = (RelativeLayout) itemView.findViewById(R.id.lay_tenIcon);
        }
    }
}

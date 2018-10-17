package com.sainti.blackcard.privilege.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.minterface.MyItemClickListener;
import com.sainti.blackcard.privilege.bean.PrivilegeBean;

import java.util.List;

;

public class PrivilegeOfpeopleAdapter extends RecyclerView.Adapter<PrivilegeOfpeopleAdapter.ViewHolder> {
    private Context context;
    private List<PrivilegeBean.DataBean.RecommendBean> recommendBeanList;
    private MyItemClickListener litener;

    public void setLitener(MyItemClickListener litener) {
        this.litener = litener;
    }

    public void setRecommendBeanList(List<PrivilegeBean.DataBean.RecommendBean> recommendBeanList) {
        this.recommendBeanList = recommendBeanList;
        notifyDataSetChanged();
    }

    public PrivilegeOfpeopleAdapter(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_privileofpeople, viewGroup, false);
        PrivilegeOfpeopleAdapter.ViewHolder holder = new PrivilegeOfpeopleAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
       // viewHolder.tv_title.setText(recommendBeanList.get(i).getL_title());
        GlideManager.getsInstance().loadImageToUrLCircle(context, recommendBeanList.get(i).getL_img(), viewHolder.iv_icon);
        viewHolder.iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                litener.onItemClick(i, 1);
            }
        });
        viewHolder.tv_size.setText(i + 1 + "");
        viewHolder.tv_count.setText("/" + recommendBeanList.size());
    }

    @Override
    public int getItemCount() {
        return recommendBeanList != null && recommendBeanList.size() > 0 ? recommendBeanList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_icon;
        private TextView tv_title, tv_count, tv_size;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_iconImage);
            //tv_title = (TextView) itemView.findViewById(R.id.tv_smallTitle);
            tv_size = (TextView) itemView.findViewById(R.id.tv_size);
            tv_count = (TextView) itemView.findViewById(R.id.tv_count);

        }
    }
}

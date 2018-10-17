package com.sainti.blackcard.circle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sainti.blackcard.R;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.minterface.MyItemClickListener;
import com.sainti.blackcard.mtuils.ScreenParamManager;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/4/11.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private Context context;
    private List<String> list;
    private MyItemClickListener listener;

    public void setListener(MyItemClickListener listener) {
        this.listener = listener;

    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public PhotoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cirlce_image, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {


        GlideManager.getsInstance().loadImageToUrLs(context, list.get(i), viewHolder.iv_photos);
        if (list.size() > 1) {
            ScreenParamManager screenParamManager = new ScreenParamManager(context);
            screenParamManager.settingViewParam(5, 10, viewHolder.iv_photos);
            // GlideManager.getsInstance().loadImageToUrLss(context, list.get(i), viewHolder.iv_photos);
        }
        viewHolder.lly_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(i, 0);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            if (list.size() > 9) {
                return 9;
            } else {
                return list.size();
            }

        }
        return 0;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_photos;
        private LinearLayout lly_p;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_photos = (ImageView) itemView.findViewById(R.id.iv_photos);// 头像
            lly_p = (LinearLayout) itemView.findViewById(R.id.lly_p);// 头像


        }
    }
}

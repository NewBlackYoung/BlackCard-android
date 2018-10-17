package com.sainti.blackcard.circle.releasecircle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.minterface.MyItemClickListener;
import com.sainti.blackcard.circle.releasecircle.activity.ReleaseCircleActivity;

import java.util.ArrayList;

/**
 * Created by YuZhenpeng on 2018/4/16.
 */

public class ChoicePhotoAdapter extends RecyclerView.Adapter<ChoicePhotoAdapter.ViewHolder> {
    private ArrayList<String> listUrls;
    private Context context;
    private MyItemClickListener listener;

    public void setListener(ReleaseCircleActivity listener) {
        this.listener = listener;
    }

    public ChoicePhotoAdapter(Context context) {
        this.context = context;
    }

    public void setListUrls(ArrayList<String> listUrls) {
        this.listUrls = listUrls;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_choicephoto, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        String path = listUrls.get(i);
        if (path.equals("addphoto")) {
            viewHolder.imageView.setImageResource(R.mipmap.addphoto);
            viewHolder.iv_delete.setVisibility(View.GONE);
        } else {
            GlideManager.getsInstance().loadImageToUrL(context, listUrls.get(i), viewHolder.imageView);
            viewHolder.iv_delete.setVisibility(View.VISIBLE);
            viewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(i, 2);
                }
            });
        }
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(i, 1);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listUrls != null && listUrls.size() > 0 ? listUrls.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView, iv_delete;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            iv_delete = (ImageView) itemView.findViewById(R.id.iv_delete);

        }
    }

}

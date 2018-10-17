package com.sainti.blackcard.circle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.circle.bean.FindDetailBean;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.minterface.CommentItemCickListenner;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/4/12.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context context;
    private List<FindDetailBean.DataBean.CommentBean> list;
    private CommentItemCickListenner listenner;

    public void setListenner(CommentItemCickListenner listenner) {
        this.listenner = listenner;
    }

    public void setList(List<FindDetailBean.DataBean.CommentBean> list) {
        this.list = list;
       notifyDataSetChanged();
    }

    public CommentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_circlecomment, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        GlideManager.getsInstance().loadImageToUrL(context, list.get(i).getUser_upimg(), viewHolder.iv_imagePhoto);
        String huiF = list.get(i).getUser_nick2();
        if (huiF == null || huiF.equals("")) {
            viewHolder.tv_hui_user.setVisibility(View.INVISIBLE);
            viewHolder.tv_huifu.setVisibility(View.INVISIBLE);
            viewHolder.tv_content.setText(list.get(i).getComment());
        } else {
            viewHolder.tv_hui_user.setVisibility(View.INVISIBLE);
            viewHolder.tv_huifu.setVisibility(View.INVISIBLE);
            String content = "回复:" + huiF +" "+ list.get(i).getComment();
            int fstart = content.indexOf(huiF);
            int fend = fstart + huiF.length();
            SpannableStringBuilder style=new SpannableStringBuilder(content);
            style.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.nine_b)),fstart,fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            viewHolder.tv_content.setText(style);
        }
        viewHolder.tv_name.setText(list.get(i).getUser_nick());
        viewHolder.tv_time.setText(list.get(i).getTime());

        viewHolder.lay_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listenner != null) {
                    listenner.onItem(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null && list.size() > 0 ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_imagePhoto;
        private TextView tv_name, tv_huifu, tv_hui_user, tv_time, tv_content;
        private RelativeLayout lay_comment;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_imagePhoto = (ImageView) itemView.findViewById(R.id.iv_imagePhoto);// 头像
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);// mignzi
            tv_huifu = (TextView) itemView.findViewById(R.id.tv_huifu);// mignzi
            tv_hui_user = (TextView) itemView.findViewById(R.id.tv_hui_user);// mignzi
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);// mignzi
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);// mignzi
            lay_comment = (RelativeLayout) itemView.findViewById(R.id.lay_comment);// mignzi

        }
    }
}

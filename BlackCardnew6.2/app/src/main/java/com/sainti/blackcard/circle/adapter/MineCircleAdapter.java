package com.sainti.blackcard.circle.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidong.photopicker.intent.PhotoPreviewIntent;
import com.sainti.blackcard.R;
import com.sainti.blackcard.circle.activity.FindDetailActivity;
import com.sainti.blackcard.circle.bean.MineFindBean;
import com.sainti.blackcard.minterface.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuZhenpeng on 2018/4/13.
 */

public class MineCircleAdapter extends RecyclerView.Adapter<MineCircleAdapter.ViewHolder> {

    private Context context;
    private List<MineFindBean.DataBean.FindlistBean> list;

    public void setList(List<MineFindBean.DataBean.FindlistBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public MineCircleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_circle, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.tv_time.setText(list.get(i).getTime());
        viewHolder.tv_tslkCount.setText(list.get(i).getCommentnum());
        viewHolder.tv_likeCount.setText(list.get(i).getPraisenum());
        if (list.get(i).getContent().equals("")) {
            viewHolder.tv_content.setVisibility(View.GONE);
        } else {
            viewHolder.tv_content.setVisibility(View.VISIBLE);
            viewHolder.tv_content.setText(list.get(i).getContent());
        }
        checkPandF(viewHolder, i);// 判断是否被关注和点赞
        photo(viewHolder, i);// 设置头像
        viewHolder.lay_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FindDetailActivity.class);
                intent.putExtra("find_id", list.get(i).getFind_id());
                context.startActivity(intent);
            }
        });// 评论图标点

    }

    @Override
    public int getItemCount() {
        return list != null && list.size() > 0 ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_pinglun, iv_like;
        private TextView tv_time, tv_tslkCount, tv_likeCount, tv_content, tv_isP, tv_isF;
        RecyclerView rv_photo;
        private LinearLayout lay_circle;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_pinglun = (ImageView) itemView.findViewById(R.id.iv_pinglun);//  评论图标
            iv_like = (ImageView) itemView.findViewById(R.id.iv_like);//  喜欢图标
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);//  内容
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);  //  时间
            tv_tslkCount = (TextView) itemView.findViewById(R.id.tv_tslkCount); //  评论数量
            tv_likeCount = (TextView) itemView.findViewById(R.id.tv_likeCount);//  喜欢数量
            tv_isP = (TextView) itemView.findViewById(R.id.tv_isP);//  隐藏的判断是否被点赞
            tv_isF = (TextView) itemView.findViewById(R.id.tv_isF);//   隐藏的判断是否被关注
            rv_photo = (RecyclerView) itemView.findViewById(R.id.rv_photo); //  喜欢数量
            lay_circle = (LinearLayout) itemView.findViewById(R.id.lay_circle); //  喜欢数量
        }
    }

    private void checkPandF(ViewHolder viewHolder, int i) {

        if (list.get(i).getIspraise().equals("0")) { // 未点赞
            viewHolder.tv_isP.setText("未");
            viewHolder.iv_like.setBackgroundResource(R.mipmap.like_un);
        }
        if (list.get(i).getIspraise().equals("1")) { // 已经点赞
            viewHolder.tv_isP.setText("已");
            viewHolder.iv_like.setBackgroundResource(R.mipmap.like);
        }

    }

    /* 照片初始化*/
    private void photo(ViewHolder viewHolder, int i) {
        PhotoAdapter p = new PhotoAdapter(context);
        if (list.get(i).getImage_urls() != null && list.get(i).getImage_urls().size() >= 1 && !list.get(i).getImage_urls().get(0).getImg().equals("")) {
            viewHolder.rv_photo.setLayoutManager(new LinearLayoutManager(context));
            final ArrayList<String> lists = new ArrayList<>();
            for (int i1 = 0; i1 < list.get(i).getImage_urls().size(); i1++) {
                lists.add(i1, list.get(i).getImage_urls().get(i1).getImg());
            }

            p.setList(lists);
            p.setListener(new MyItemClickListener() {
                @Override
                public void onItemClick(int position, int code) {
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(context);
                    intent.setCurrentItem(position);
                    intent.setPhotoPaths(lists);
                    intent.setCode(1);
                    context.startActivity(intent);
                }
            });
        }
        if (list.get(i).getImage_urls() != null && list.get(i).getImage_urls().size() > 1) {
            viewHolder.rv_photo.setLayoutManager(new GridLayoutManager(context, 3));
            final ArrayList<String> lists = new ArrayList<>();
            for (int i1 = 0; i1 < list.get(i).getImage_urls().size(); i1++) {
                lists.add(i1, list.get(i).getImage_urls().get(i1).getImg());
            }
            p.setList(lists);
            p.setListener(new MyItemClickListener() {
                @Override
                public void onItemClick(int position, int code) {
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(context);
                    intent.setCurrentItem(position);
                    intent.setPhotoPaths(lists);
                    intent.setCode(1);
                    context.startActivity(intent);
                }
            });
        }
        viewHolder.rv_photo.setAdapter(p);
    }
}

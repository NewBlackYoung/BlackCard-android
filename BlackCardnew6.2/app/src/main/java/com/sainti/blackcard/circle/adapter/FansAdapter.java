package com.sainti.blackcard.circle.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sainti.blackcard.R;
import com.sainti.blackcard.circle.activity.OtherCircleActivity;
import com.sainti.blackcard.circle.bean.FansBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/4/16.
 */

public class FansAdapter extends RecyclerView.Adapter<FansAdapter.ViewHolder> implements OnNetResultListener {
    private Context context;
    private List<FansBean.DataBean> list;

    public void setList(List<FansBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public FansAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fans, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.tv_fansCount.setText(list.get(i).getFans() + "个粉丝");
        viewHolder.tv_name.setText(list.get(i).getUser_nick());
        GlideManager.getsInstance().loadImageToUrL(context, list.get(i).getLevel(), viewHolder.iv_huyuankanid);
        GlideManager.getsInstance().loadImageToUrLCircle(context, list.get(i).getUser_upimg(), viewHolder.iv_image);
        if (list.get(i).getIsfollow().equals("0")) { // 未关注
            viewHolder.tv_isF.setText("未");
            viewHolder.iv_guanzhu.setBackgroundResource(R.mipmap.jiaguanzhu);

        }
        if (list.get(i).getIsfollow().equals("1")) { // 已经关注
            viewHolder.tv_isF.setText("已");
            viewHolder.iv_guanzhu.setBackgroundResource(R.mipmap.follow);

        }
        viewHolder.iv_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ids = "";
                isFollow(viewHolder, i, ids);// 评论

            }
        });// 关注和图标点击
        viewHolder.iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OtherCircleActivity.class);
                intent.putExtra("uid", list.get(i).getUid());
                //intent.putExtra("find_id",list.get(i).getFind_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null && list.size() > 0 ? list.size() : 0;
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {

    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_image, iv_huyuankanid, iv_guanzhu;
        private TextView tv_name, tv_fansCount, tv_isF;


        public ViewHolder(View itemView) {
            super(itemView);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
            iv_huyuankanid = (ImageView) itemView.findViewById(R.id.iv_huyuankanid);
            iv_guanzhu = (ImageView) itemView.findViewById(R.id.iv_guanzhu);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_fansCount = (TextView) itemView.findViewById(R.id.tv_fansCount);
            tv_isF = (TextView) itemView.findViewById(R.id.tv_isF);

        }
    }

    /* 关注取消关注的逻辑*/
    private void isFollow(ViewHolder viewHolder, int i, String id) {
        String uid2 = list.get(i).getUid();
        String idF = viewHolder.tv_isF.getText().toString();
        if (idF.equals("未")) {
            id = "1";
            viewHolder.tv_isF.setText("已");
            viewHolder.iv_guanzhu.setBackgroundResource(R.mipmap.follow);

        }
        if (idF.equals("已")) {
            id = "2";
            viewHolder.tv_isF.setText("未");
            viewHolder.iv_guanzhu.setBackgroundResource(R.mipmap.jiaguanzhu);
        }
        TurnClassHttp.isFollow(uid2, id, 3, context, this);
    }
}

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
import com.sainti.blackcard.circle.activity.MineCircleActivity;
import com.sainti.blackcard.circle.activity.OtherCircleActivity;
import com.sainti.blackcard.circle.bean.TopDetailBean;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.MyItemClickListener;
import com.sainti.blackcard.mtuils.SpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuZhenpeng on 2018/4/11.
 */

public class ToppicDetailAdapter extends RecyclerView.Adapter<ToppicDetailAdapter.ViewHolder> implements OnNetResultListener {
    private Context context;
    private List<TopDetailBean.DataBean.FindlistBean> list;

    public ToppicDetailAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<TopDetailBean.DataBean.FindlistBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                View viewfooder = LayoutInflater.from(context).inflate(R.layout.view_kongbai_coffee, viewGroup, false);
                ViewHolder viewHolderf = new ViewHolder(viewfooder);
                return viewHolderf;
            case 1:
                View view = LayoutInflater.from(context).inflate(R.layout.item_all, viewGroup, false);
                ViewHolder holder = new ViewHolder(view);
                return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        if (i < list.size()) {
            GlideManager.getsInstance().loadImageToUrLCircle(context, list.get(i).getHead(), viewHolder.iv_iamge);// 头像
            GlideManager.getsInstance().loadImageToUrL(context, list.get(i).getLevel_ico(), viewHolder.iv_huiyuankind);// 会员分类
            viewHolder.tv_time.setText(list.get(i).getTime());
            viewHolder.name.setText(list.get(i).getName());
            viewHolder.tv_tslkCount.setText(list.get(i).getCommentnum());
            viewHolder.tv_likeCount.setText(list.get(i).getPraisenum());
            viewHolder.tv_content.setText(list.get(i).getContent());
            checkPandF(viewHolder, i);// 判断是否被关注和点赞
            photo(viewHolder, i);// 设置头像
            viewHolder.iv_like.setOnClickListener(new View.OnClickListener() {

                private String id;

                @Override
                public void onClick(View view) {
                    dianZan(viewHolder, i, id);// 点赞
                }
            }); // 点赞图标点击
            viewHolder.lay_circle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, FindDetailActivity.class);
                    intent.putExtra("find_id", list.get(i).getFind_id());
                    context.startActivity(intent);
                }
            });// 评论图标点击


            viewHolder.tv_guanzhu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String ids = "";
                    isFollow(viewHolder, i, ids);// 评论

                }
            });// 关注和图标点击
            viewHolder.iv_iamge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = list.get(i).getUid();
                    String uid = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
                    if (id.equals(uid)) {
                        Intent intent = new Intent(context, MineCircleActivity.class);
                        context.startActivity(intent);
                    } else {

                        Intent intent = new Intent(context, OtherCircleActivity.class);
                        intent.putExtra("find_id", list.get(i).getFind_id());
                        intent.putExtra("uid", list.get(i).getUid());
                        context.startActivity(intent);

                    }
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return list != null && list.size() > 0 ? list.size() + 1 : 0;
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {

    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_iamge, iv_huiyuankind, iv_pinglun, iv_like;
        private TextView name, tv_guanzhu, tv_smamubiao, tv_time, tv_tslkCount, tv_likeCount, tv_content, tv_isP, tv_isF;
        private RecyclerView rv_photo;
        private LinearLayout lay_circle;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_iamge = (ImageView) itemView.findViewById(R.id.iv_iamge);// 头像
            iv_huiyuankind = (ImageView) itemView.findViewById(R.id.iv_huiyuankind); // 会员分类图标
            iv_pinglun = (ImageView) itemView.findViewById(R.id.iv_pinglun);//  评论图标
            iv_like = (ImageView) itemView.findViewById(R.id.iv_like);//  喜欢图标
            name = (TextView) itemView.findViewById(R.id.name);//  名字
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);//  内容
            tv_guanzhu = (TextView) itemView.findViewById(R.id.tv_guanzhu); //  关注按钮
            tv_smamubiao = (TextView) itemView.findViewById(R.id.tv_smamubiao);  //  小目标标题
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
        if (list.get(i).getTopic() != null) { //判断是否有话题
            viewHolder.tv_smamubiao.setText(list.get(i).getTopic().toString());
        } else {
            viewHolder.tv_smamubiao.setText("");
        }
        if (list.get(i).getIspraise().equals("0")) { // 未点赞
            viewHolder.tv_isP.setText("未");
            viewHolder.iv_like.setBackgroundResource(R.mipmap.like_un);
        }
        if (list.get(i).getIspraise().equals("1")) { // 已经点赞
            viewHolder.tv_isP.setText("已");
            viewHolder.iv_like.setBackgroundResource(R.mipmap.like);

        }
        if (list.get(i).getIs_follow().equals("0")) { // 未关注
            viewHolder.tv_isF.setText("未");
            viewHolder.tv_guanzhu.setBackgroundResource(R.mipmap.jiaguanzhu);

        }
        if (list.get(i).getIs_follow().equals("1")) { // 已经关注
            viewHolder.tv_isF.setText("已");
            viewHolder.tv_guanzhu.setBackgroundResource(R.mipmap.follow);

        }
    }

    /* 照片初始化*/

    private void photo(ViewHolder viewHolder, int i) {
        PhotoAdapter p = new PhotoAdapter(context);
        if (list.get(i).getImage_urlb() != null && list.get(i).getImage_urlb().size() == 1) {
            viewHolder.rv_photo.setLayoutManager(new LinearLayoutManager(context));
            final ArrayList<String> lists = new ArrayList<>();
            for (int i1 = 0; i1 < list.get(i).getImage_urlb().size(); i1++) {
                lists.add(i1, list.get(i).getImage_urlb().get(i1).getImg());
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
        if (list.get(i).getImage_urlb() != null && list.get(i).getImage_urlb().size() > 1) {
            viewHolder.rv_photo.setLayoutManager(new GridLayoutManager(context, 3));
            final ArrayList<String> lists = new ArrayList<>();
            for (int i1 = 0; i1 < list.get(i).getImage_urlb().size(); i1++) {
                lists.add(i1, list.get(i).getImage_urlb().get(i1).getImg());
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

    /* 关注取消关注的逻辑*/
    private void isFollow(ViewHolder viewHolder, int i, String id) {
        String uid2 = list.get(i).getUid();
        String idF = viewHolder.tv_isF.getText().toString();
        if (idF.equals("未")) {
            id = "1";
            viewHolder.tv_isF.setText("已");
            viewHolder.tv_guanzhu.setBackgroundResource(R.mipmap.follow);

        }
        if (idF.equals("已")) {
            id = "2";
            viewHolder.tv_isF.setText("未");
            viewHolder.tv_guanzhu.setBackgroundResource(R.mipmap.jiaguanzhu);

        }
        TurnClassHttp.isFollow(uid2, id, 3, context, this);
    }

    /* 点赞于取消点赞的逻辑*/
    private void dianZan(ViewHolder viewHolder, int i, String id) {
        int countNow = 0;
        String find_id = list.get(i).getFind_id();
        String idP = viewHolder.tv_isP.getText().toString();
        String count = viewHolder.tv_likeCount.getText().toString();
        if (idP.equals("未")) {
            id = "1";
            countNow = Integer.parseInt(count) + 1;
            viewHolder.tv_isP.setText("已");
            viewHolder.iv_like.setBackgroundResource(R.mipmap.like);
        }
        if (idP.equals("已")) {
            id = "2";
            countNow = Integer.parseInt(count) - 1;
            viewHolder.tv_isP.setText("未");
            viewHolder.iv_like.setBackgroundResource(R.mipmap.like_un);

        }
        viewHolder.tv_likeCount.setText(String.valueOf(countNow));
        TurnClassHttp.isPraise(find_id, id, 4, context, this);
    }
}

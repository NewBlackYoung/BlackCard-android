package com.sainti.blackcard.circle.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lidong.photopicker.intent.PhotoPreviewIntent;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.circle.adapter.CommentAdapter;
import com.sainti.blackcard.circle.adapter.PersonPhotoAdapter;
import com.sainti.blackcard.circle.adapter.PhotoAdapter;
import com.sainti.blackcard.circle.bean.FindDetailBean;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.CommentItemCickListenner;
import com.sainti.blackcard.minterface.MyItemClickListener;
import com.sainti.blackcard.minterface.TimerListener;
import com.sainti.blackcard.minterface.TimerListenerHasCode;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.LoadingView;

import java.util.ArrayList;

;

public class FindDetailActivity extends BaseTitleActivity implements View.OnClickListener, CommentItemCickListenner, View.OnLayoutChangeListener, OnNetResultListener, TimerListener, BaseQuickAdapter.OnItemClickListener, TimerListenerHasCode {

    private LoadingView progressDialog;
    private TextView tv_isP, tv_isF, name, tv_guanzhu, tv_content, tv_time, tv_delete, tv_likeCount, tv_talkCount, tv_puth;
    private ImageView iv_iamge, iv_like, iv_huiyuankind, iv_pinglu, iv_talk;
    private RecyclerView rv_photo, rcl_talk, rcl_like;
    private FindDetailBean findDetailBean;
    private PhotoAdapter photoAdapter;
    private CommentAdapter commentAdapter;
    private String find_id;
    private String id;
    private EditText ed_countent;
    private String find_id_to;
    private String replyid;
    private String user_nick;

    //Activity最外层的Layout视图
    private View activityRootView;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;
    private int anInt = 99999;
    private String beiDiannames = "";
    private RelativeLayout lay_p,lay_likess;
    private Gson gson;
    private PersonPhotoAdapter personPhotoAdapter;


    @Override
    public int setLayout() {
        return R.layout.activity_find_detail;
    }

    @Override
    protected void initView() {
        name = bindView(R.id.name);
        tv_guanzhu = bindView(R.id.tv_guanzhu);
        tv_content = bindView(R.id.tv_content);
        tv_isP = bindView(R.id.tv_isP);
        tv_isF = bindView(R.id.tv_isF);
        tv_time = bindView(R.id.tv_time);
        tv_delete = bindView(R.id.tv_delete);
        tv_likeCount = bindView(R.id.tv_likeCount);
        iv_iamge = bindView(R.id.iv_iamge);
        iv_like = bindView(R.id.iv_like);
        iv_huiyuankind = bindView(R.id.iv_huiyuankind);
        rv_photo = bindView(R.id.rv_photo);
        rcl_talk = bindView(R.id.rcl_talk);
        tv_talkCount = bindView(R.id.tv_talkCount);
        iv_pinglu = bindView(R.id.iv_pinglu);
        iv_talk = bindView(R.id.iv_talk);
        tv_puth = bindView(R.id.tv_puth);
        ed_countent = bindView(R.id.ed_countent);
        activityRootView = findViewById(R.id.activity_find_detail);
        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;
        lay_p = bindView(R.id.lay_p);
        rcl_like = bindView(R.id.rcl_like);
        lay_likess=bindView(R.id.lay_likess);
    }

    @Override
    protected void initEvent() {

        tv_guanzhu.setOnClickListener(this);
        iv_like.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        tv_puth.setOnClickListener(this);
        lay_likess.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        progressDialog = new LoadingView(this);
        setTitle("详情");
        progressDialog.show();
        find_id = getIntent().getStringExtra("find_id");
        replyid = "0";
        TurnClassHttp.findDetail(find_id, 5, FindDetailActivity.this, this);
        photoAdapter = new PhotoAdapter(this);
        commentAdapter = new CommentAdapter(this);
        commentAdapter.setListenner(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };
        rcl_talk.setLayoutManager(linearLayoutManager);
        rcl_talk.setAdapter(commentAdapter);
        rcl_talk.setHasFixedSize(true);
        GridLayoutManager linearLayoutManagers = new GridLayoutManager(this, 7) {
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };
        rcl_like.setLayoutManager(linearLayoutManagers);
        personPhotoAdapter = new PersonPhotoAdapter(R.layout.item_per_image);
        personPhotoAdapter.setContext(this);
        personPhotoAdapter.setOnItemClickListener(this);
        rcl_like.setAdapter(personPhotoAdapter);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_guanzhu:
                isFollow();
                break;
            case R.id.iv_like:
                break;
            case R.id.tv_delete:
                deleteFind();
                break;
            case R.id.tv_puth:
                toComment();
                break;
            case R.id.lay_likess:
                dianZan();
                break;
        }
    }


    /*设置初始化数据*/
    private void settingNow() {
        String UID = findDetailBean.getData().getUid();
        String uid = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
        if (UID.equals(uid)) {
            tv_delete.setVisibility(View.VISIBLE);
        } else {
            tv_delete.setVisibility(View.GONE);
        }

        if (findDetailBean.getData().getComment() == null || findDetailBean.getData().getComment().size() == 0) {
            iv_pinglu.setVisibility(View.GONE);
            lay_p.setVisibility(View.GONE);

        } else {
            iv_pinglu.setVisibility(View.VISIBLE);
            lay_p.setVisibility(View.VISIBLE);
            commentAdapter.setList(findDetailBean.getData().getComment());
        }
        find_id_to = find_id;
        name.setText(findDetailBean.getData().getName());
        tv_content.setText(findDetailBean.getData().getContent());
        tv_time.setText(findDetailBean.getData().getTime());
        tv_likeCount.setText(findDetailBean.getData().getPraisenum());
        tv_talkCount.setText(findDetailBean.getData().getCommentnum());
        GlideManager.getsInstance().loadImageToUrLCircle(this, findDetailBean.getData().getHead(), iv_iamge);// 头像
        GlideManager.getsInstance().loadImageToUrL(this, findDetailBean.getData().getLevel_ico(), iv_huiyuankind);// 会员分类
        String follow = findDetailBean.getData().getIsfollow();
        String prasie = findDetailBean.getData().getIspraise();
        if (follow.equals("0")) {
            tv_isF.setText("未");
            tv_guanzhu.setBackgroundResource(R.mipmap.jiaguanzhu);
        }
        if (follow.equals("1")) {
            tv_isF.setText("已");
            tv_guanzhu.setBackgroundResource(R.mipmap.follow);
        }
        if (prasie.equals("0")) { // 未点赞
            tv_isP.setText("未");
            iv_like.setBackgroundResource(R.mipmap.like_un);
        }
        if (prasie.equals("1")) { // 已经点赞
            tv_isP.setText("已");
            iv_like.setBackgroundResource(R.mipmap.like);
        }

    }

    /* 照片初始化*/
    private void photo() {
        if (findDetailBean.getData().getImage_urls() != null && findDetailBean.getData().getImage_urls().size() == 1 && !findDetailBean.getData().getImage_urls().get(0).getImg().equals("")) {
            rv_photo.setLayoutManager(new LinearLayoutManager(context));
            final ArrayList<String> lists = new ArrayList<>();
            for (int i1 = 0; i1 < findDetailBean.getData().getImage_urls().size(); i1++) {
                lists.add(i1, findDetailBean.getData().getImage_urls().get(i1).getImg());
            }
            photoAdapter.setListener(new MyItemClickListener() {
                @Override
                public void onItemClick(int position, int code) {
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(context);
                    intent.setCurrentItem(position);
                    intent.setPhotoPaths(lists);
                    intent.setCode(1);
                    context.startActivity(intent);
                }
            });
            rv_photo.setVisibility(View.VISIBLE);
            photoAdapter.setList(lists);
        }else {
            rv_photo.setVisibility(View.GONE);
        }
        if (findDetailBean.getData().getImage_urls() != null && findDetailBean.getData().getImage_urls().size() > 1) {
            rv_photo.setLayoutManager(new GridLayoutManager(context, 3));
            final ArrayList<String> lists = new ArrayList<>();
            for (int i1 = 0; i1 < findDetailBean.getData().getImage_urls().size(); i1++) {
                lists.add(i1, findDetailBean.getData().getImage_urls().get(i1).getImg());
            }
            photoAdapter.setListener(new MyItemClickListener() {
                @Override
                public void onItemClick(int position, int code) {
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(context);
                    intent.setCurrentItem(position);
                    intent.setPhotoPaths(lists);
                    intent.setCode(1);
                    context.startActivity(intent);
                }
            });
            photoAdapter.setList(lists);
        }
        rv_photo.setAdapter(photoAdapter);
    }

    /* 关注取消关注的逻辑*/
    private void isFollow() {
        String uid2 = findDetailBean.getData().getUid();
        String idF = tv_isF.getText().toString();
        id = "";
        if (idF.equals("未")) {
            id = "1";
            tv_isF.setText("已");
            tv_guanzhu.setBackgroundResource(R.mipmap.follow);

        }
        if (idF.equals("已")) {
            id = "2";
            tv_isF.setText("未");
            tv_guanzhu.setBackgroundResource(R.mipmap.jiaguanzhu);
        }
        TurnClassHttp.isFollow(uid2, id, 3, this, this);
    }

    /* 点赞于取消点赞的逻辑*/
    private void dianZan() {
        int countNow = 0;
        String idP = tv_isP.getText().toString();
        String count = tv_likeCount.getText().toString();
        id = "";
        if (idP.equals("未")) {
            id = "1";
            countNow = Integer.parseInt(count) + 1;
            tv_isP.setText("已");
            iv_like.setBackgroundResource(R.mipmap.like);

        }
        if (idP.equals("已")) {
            id = "2";
            countNow = Integer.parseInt(count) - 1;
            tv_isP.setText("未");
            iv_like.setBackgroundResource(R.mipmap.like_un);

        }
        tv_likeCount.setText(String.valueOf(countNow));
        TurnClassHttp.isPraise(find_id, id, 4, this, this);
    }

    /* 评论Item点击事件*/
    @Override
    public void onItem(int position) {

        anInt = position;
        find_id_to = findDetailBean.getData().getComment().get(position).getFind_id();
        replyid = findDetailBean.getData().getComment().get(position).getComment_id();
        user_nick = findDetailBean.getData().getComment().get(position).getUser_nick();
        showKeyboard(position);
    }


    public void showKeyboard(int p) {
        String content = ed_countent.getText().toString();
        if (beiDiannames.equals(user_nick) && content != null && !content.equals("")) {
            //ToastUtils.show(this, "点击同一个人");
        } else {
            ed_countent.setText("");
            SpannableString spannableString = new SpannableString("to " + user_nick + ":");
            ed_countent.setHint(new SpannableString(spannableString));
        }
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        beiDiannames = user_nick;
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ed_countent.getWindowToken(), 0);
    }

    /*发表评论*/
    private void toComment() {
        String content = ed_countent.getText().toString();
        if (content == null || content.equals("")) {
            ToastUtils.show(this, "评论内容不能为空");
            return;
        }
        progressDialog.show();
        TurnClassHttp.publishComment(find_id_to, content, replyid, 1, this, this);
    }

    /*删除发现*/
    private void deleteFind() {
        progressDialog.show();
        TurnClassHttp.deleteFind(find_id, 0, this, this);
    }

    @Override
    public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if (i7 != 0 && i3 != 0 && (i7 - i3 > keyHeight)) {
            // ToastUtil.showToast(this,"监听到软键盘弹起...");

        } else if (i7 != 0 && i3 != 0 && (i3 - i7 > keyHeight)) {
            String contents = ed_countent.getText().toString();
            //ToastUtil.showToast(this,"监听到软件盘关闭...");
            if (contents == null || contents.equals("")) {
                SpannableString spannableString = new SpannableString("评论......");
                find_id_to = getIntent().getStringExtra("find_id");
                replyid = "0";
                ed_countent.setHint(new SpannableString(spannableString));
            }
        }
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        gson = GsonSingle.getGson();
        BaseBean baseBean = GsonSingle.getGson().fromJson(stringResult, BaseBean.class);
        if (!baseBean.getResult().equals("1")) {
            progressDialog.dismiss();
            ToastUtils.show(this, baseBean.getMsg(), 1000);
            return;
        }
        switch (resultCode) {
            case 0:
                progressDialog.dismiss();
                ToastUtils.show(context, "删除成功");
                finish();
                break;
            case 1:
                TurnClassHttp.findDetail(find_id, 5, FindDetailActivity.this, this);
             //   CommontUtil.lateTime(1000, this);
                break;
            case 2:
                ed_countent.setText("");
                gson = GsonSingle.getGson();
                findDetailBean = gson.fromJson(stringResult, FindDetailBean.class);
                settingNow();
                personPhotoAdapter.setNewData(findDetailBean.getData().getPraiselist());
                progressDialog.dismiss();
                hideKeyboard();
                break;
            case 5:
                progressDialog.dismiss();
                findDetailBean = gson.fromJson(stringResult, FindDetailBean.class);
                if (findDetailBean != null && findDetailBean.getResult().equals("1")) {
                    settingNow();
                    photo();
                    personPhotoAdapter.setNewData(findDetailBean.getData().getPraiselist());
                }
                break;
            case 4:
                progressDialog.show();
                CommontUtil.lateTimeHasCode(500, this, 1);
                break;
            case 6:
                findDetailBean = gson.fromJson(stringResult, FindDetailBean.class);
                if (findDetailBean != null && findDetailBean.getResult().equals("1")) {
                    personPhotoAdapter.setNewData(findDetailBean.getData().getPraiselist());
                }
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        progressDialog.dismiss();
        switch (resultCode) {
            case 0:
                ToastUtils.show(context, "删除失败");
                break;
            case 1:
            case 2:
                ToastUtils.show(context, "评论失败！请稍后尝试");
                break;
            case 3:/* 关注*/
                ToastUtils.show(context, "操作失败！");
                break;
            case 4:/* 点赞*/
                ToastUtils.show(context, "操作失败！");
                break;
        }

    }

    @Override
    public void onTimerListener() {
        TurnClassHttp.findDetail(find_id, 2, FindDetailActivity.this, FindDetailActivity.this);

    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //添加layout大小发生改变监听器
        activityRootView.addOnLayoutChangeListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String uid = findDetailBean.getData().getPraiselist().get(position).getUid();
        String mineUid = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
        if (uid.equals(mineUid)) {
            Intent intent = new Intent(context, MineCircleActivity.class);

            startActivity(intent);
        } else {
            Intent intent = new Intent(context, OtherCircleActivity.class);
            intent.putExtra("uid", findDetailBean.getData().getPraiselist().get(position).getUid());
            context.startActivity(intent);
        }

    }

    @Override
    public void onTimerListener(int code) {
        switch (code) {
            case 1:

                TurnClassHttp.findDetail(find_id, 5, FindDetailActivity.this, this);
                break;
        }
    }
}

package com.sainti.blackcard.homepage.splash.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseTitleActivity;
import com.sainti.blackcard.mblcakcard.bean.FriendBean;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.AnimationUtil;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.homepage.splash.adapter.MemBerAdapter;
import com.sainti.blackcard.widget.CommonPopupWindow;
import com.sainti.blackcard.widget.LoadingView;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

public class MemberBindActivity extends BaseTitleActivity implements OnNetResultListener, View.OnClickListener, CommonPopupWindow.ViewInterface, BaseQuickAdapter.OnItemChildClickListener {


    private RecyclerView qinre;
    private MemBerAdapter memBerAdapter;
    private ImageView addfriend;
    private CommonPopupWindow commonPopupWindow;
    private ImageView imageView;
    private RelativeLayout lay;
    private TextView canso, commint;
    private EditText name, phone, mima, sure_mima;
    private LoadingView loadingView;
    private int count = 0;
    private FriendBean friendBean;
    private AnimationUtil anim;
    private RelativeLayout lly_bg;

    @Override
    public int setLayout() {
        return R.layout.activity_member_bind;
    }

    @Override
    protected void initView() {
        anim = new AnimationUtil(this);
        loadingView = new LoadingView(this);
        lly_bg = bindView(R.id.lly_bg);
        lay = bindView(R.id.lay);
        qinre = bindView(R.id.qinre);
        imageView = bindView(R.id.iamge);
        qinre.setLayoutManager(new LinearLayoutManager(this));
        qinre.setNestedScrollingEnabled(false);
        memBerAdapter = new MemBerAdapter(R.layout.item_bindfriend);
        memBerAdapter.setOnItemChildClickListener(this);
        qinre.setAdapter(memBerAdapter);
        addfriend = bindView(R.id.addfriend);
        View foodview = LayoutInflater.from(this).inflate(R.layout.view_addfriend, null);
        ImageView add = bindView(foodview, R.id.add);
        add.setOnClickListener(this);
        memBerAdapter.addFooterView(foodview);
        commonPopupWindow = new CommonPopupWindow.Builder(context)
                .setView(R.layout.view_addfriend_popup)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT).setAnimationStyle(R.style.popwin_liebiao_style).setViewOnclickListener(this).setOutsideTouchable(true).create();

    }

    @Override
    protected void initEvent() {
        addfriend.setOnClickListener(this);
        lly_bg.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle("亲友绑定");
        TurnClassHttp.getbangding(1, this, this);

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.addfriend:
                break;
            case R.id.add:
                commonPopupWindow.showPopAtPatentDown(lay, 270);
                break;
            case R.id.canso:
                commonPopupWindow.dismiss();
                break;
            case R.id.commint:
                String names = name.getText().toString();
                String ed_phonen = phone.getText().toString();
                String ed_mim = mima.getText().toString();
                String suremima = sure_mima.getText().toString();
                if (name == null || name.equals("")) {
                    ToastUtils.show(MemberBindActivity.this, "请输入姓名");
                    return;
                }
                if (ed_phonen == null || ed_phonen.equals("")) {

                    ToastUtils.show(MemberBindActivity.this, "请输入手机号");
                    return;
                }
                if (ed_phonen.length() != 11) {
                    ToastUtils.show(MemberBindActivity.this, "请输入11位数手机号");
                    return;
                }
                if (ed_mim == null || ed_mim.equals("")) {
                    ToastUtils.show(MemberBindActivity.this, "请输入密码");
                    return;
                }
                if (suremima == null || suremima.equals("")) {
                    ToastUtils.show(MemberBindActivity.this, "请输入密码");
                    return;
                }
                if (!ed_mim.equals(suremima)) {
                    showToast("两次密码不同");
                    sure_mima.setText("");
                }
                loadingView.show();
                TurnClassHttp.setBangding(count + "", names, ed_mim, ed_phonen, 2, MemberBindActivity.this, MemberBindActivity.this);
                break;
            case R.id.lly_bg:
                anim.viewDisappear(lly_bg);
                break;
        }
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {

        switch (resultCode) {
            case 1:
                friendBean = GsonSingle.getGson().fromJson(stringResult, FriendBean.class);
                memBerAdapter.setNewData(friendBean.getData());
                if (friendBean.getData() == null || friendBean.getData().size() == 0) {
                    count = 1;
                } else {
                    count = friendBean.getData().size() + 1;
                }
                loadingView.dismiss();
                break;
            case 2:
                Map<String, String> map_ekv0 = new HashMap<String, String>();
                map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
                MobclickAgent.onEvent(context, "bangdingqinyou", map_ekv0);// 友盟统计
                ToastUtils.show(this, "绑定成功");
                TurnClassHttp.getbangding(1, this, this);
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {

    }

    @Override
    public void getChildView(View view, int layoutResId) {
        canso = bindView(view, R.id.canso);
        commint = bindView(view, R.id.commint);
        name = bindView(view, R.id.name);
        phone = bindView(view, R.id.phone);
        mima = bindView(view, R.id.mima);
        sure_mima = bindView(view, R.id.sure_mima);
        canso.setOnClickListener(this);
        commint.setOnClickListener(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        TextView zhanghao = (TextView) adapter.getViewByPosition(qinre, position, R.id.k);
        switch (view.getId()) {
            case R.id.copy:
                CommontUtil.onClickCopy(zhanghao, this);
                anim.viewAppear(lly_bg);
                break;
        }
    }
}

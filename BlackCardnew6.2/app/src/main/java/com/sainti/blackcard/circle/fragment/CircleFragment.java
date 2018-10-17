package com.sainti.blackcard.circle.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseFragment;
import com.sainti.blackcard.circle.activity.DakaActivity;
import com.sainti.blackcard.circle.activity.MessageActivity;
import com.sainti.blackcard.circle.activity.MineCircleActivity;
import com.sainti.blackcard.circle.activity.TopicDetailActivity;
import com.sainti.blackcard.circle.adapter.CircleFragmentAdapter;
import com.sainti.blackcard.circle.bean.NewFindBean;
import com.sainti.blackcard.circle.bean.UnReadMeassgBean;
import com.sainti.blackcard.circle.releasecircle.activity.ReleaseCircleActivity;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.commen.mdialog.CommenDialog;
import com.sainti.blackcard.homepage.splash.activity.LoginActivity;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.TimerListener;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.widget.CommonPopupWindow;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YuZhenpeng on 2018/4/26.
 */

public class CircleFragment extends BaseFragment implements OnNetResultListener, View.OnClickListener, TimerListener, OnRefreshListener, CommonPopupWindow.ViewInterface, CommenDialog.ViewInterface {
    private List<Fragment> fragments;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView iv_xh_name, iv_to_nameOne, iv_to_nameTwo, iv_to_nameThree, iv_to_nameFour, iv_msg, iv_fabu, iv_daka, iv_red;
    private TextView tv_nameOne, tv_nameTwo, tv_nameThree, tv_nameFour, tv_quanzi;
    private NewFindBean baseBean;
    private Gson gson;
    private CommonPopupWindow commonPopupWindow;
    private SmartRefreshLayout refreshLayout;
    private RelativeLayout tomig;
    private RelativeLayout to_circle;
    private TextView poin_m;
    private TextView poic_c;
    private boolean ismsglist = false;
    private boolean isfns = false;
    private UnReadMeassgBean unReadMeassgBean;
    private String count;
    private String newfancount;
    private Intent intent;

    public static CircleFragment newInstance(String param1) {
        CircleFragment fragment = new CircleFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_circle_ccc;
    }

    @Override
    protected void initView(View view) {
        EventBus.getDefault().register(this);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.view_p);
        iv_xh_name = (ImageView) view.findViewById(R.id.iv_xh_name);
        iv_to_nameOne = (ImageView) view.findViewById(R.id.iv_to_nameOne);
        iv_to_nameTwo = (ImageView) view.findViewById(R.id.iv_to_nameTwo);
        iv_to_nameThree = (ImageView) view.findViewById(R.id.iv_to_nameThree);
        iv_to_nameFour = (ImageView) view.findViewById(R.id.iv_to_nameFour);
        tv_nameOne = (TextView) view.findViewById(R.id.tv_nameOne);
        tv_nameTwo = (TextView) view.findViewById(R.id.tv_nameTwo);
        tv_nameThree = (TextView) view.findViewById(R.id.tv_nameThree);
        tv_nameFour = (TextView) view.findViewById(R.id.tv_nameFour);
        iv_msg = (ImageView) view.findViewById(R.id.iv_msg);
        iv_fabu = (ImageView) view.findViewById(R.id.iv_fabu);
        iv_daka = (ImageView) view.findViewById(R.id.iv_daka);
        iv_red = (ImageView) view.findViewById(R.id.iv_red);
        tv_quanzi = bindView(R.id.tv_quanzi);
        refreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refresh_lay); // 刷新控件
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setEnableLoadmore(false);
    }

    private void addFragment() {
        fragments = new ArrayList<>();
        fragments.add(new FragmentFollow());
        fragments.add(new FragmentAll());

    }

    @Override
    protected void initData() {
        addFragment();
        CircleFragmentAdapter circleFragmentAdapter = new CircleFragmentAdapter(getChildFragmentManager());
        circleFragmentAdapter.setFragments(fragments);
        viewPager.setAdapter(circleFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        TurnClassHttp.getNewFindV("1", 1, context, this);
        viewPager.setCurrentItem(2);

        commonPopupWindow = new CommonPopupWindow.Builder(context)
                .setView(R.layout.view_popup_msg)
                .setWidthAndHeight(347,
                        ViewGroup.LayoutParams.WRAP_CONTENT).setAnimationStyle(R.style.popwin_circle_style).setViewOnclickListener(this).setOutsideTouchable(true).create();


    }

    private void getCount() {
        CommontUtil.lateTime(1000, this);

    }

    @Override
    protected void initEvents() {
        iv_xh_name.setOnClickListener(this);
        tv_nameOne.setOnClickListener(this);
        tv_nameTwo.setOnClickListener(this);
        tv_nameThree.setOnClickListener(this);
        tv_nameFour.setOnClickListener(this);
        iv_msg.setOnClickListener(this);
        iv_fabu.setOnClickListener(this);
        iv_daka.setOnClickListener(this);
        tv_quanzi.setOnClickListener(this);
    }

    private void settingDate(NewFindBean newFindBean) {
        GlideManager.getsInstance().loadImageToUrL(context, newFindBean.getData().getTopimg().get(0).getXh_img(), iv_xh_name);
        GlideManager.getsInstance().loadImageToUrLCircle(context, newFindBean.getData().getTopic().get(0).getTo_pic(), iv_to_nameOne);
        GlideManager.getsInstance().loadImageToUrLCircle(context, newFindBean.getData().getTopic().get(1).getTo_pic(), iv_to_nameTwo);
        GlideManager.getsInstance().loadImageToUrLCircle(context, newFindBean.getData().getTopic().get(2).getTo_pic(), iv_to_nameThree);
        GlideManager.getsInstance().loadImageToUrLCircle(context, newFindBean.getData().getTopic().get(3).getTo_pic(), iv_to_nameFour);
        tv_nameOne.setText(newFindBean.getData().getTopic().get(0).getTo_name());
        tv_nameTwo.setText(newFindBean.getData().getTopic().get(1).getTo_name());
        tv_nameThree.setText(newFindBean.getData().getTopic().get(2).getTo_name());
        tv_nameFour.setText(newFindBean.getData().getTopic().get(3).getTo_name());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_xh_name:
                startActivity(new Intent(context, DakaActivity.class));
                Map<String, String> map_ekv0 = new HashMap<String, String>();
                MobclickAgent.onEvent(context, "huodongliulan");// 友盟统计
                break;
            case R.id.tv_nameOne:
                toTopDetail(0);
                break;
            case R.id.tv_nameTwo:
                toTopDetail(1);
                break;
            case R.id.tv_nameThree:
                toTopDetail(2);
                break;
            case R.id.tv_nameFour:
                toTopDetail(3);
                break;
            case R.id.iv_msg:
                commonPopupWindow.showPopAtViewDown(iv_msg);
                break;
            case R.id.iv_fabu:
                Intent intent1 = new Intent(getContext(), ReleaseCircleActivity.class);
                intent1.putExtra("to_name", "");
                intent1.putExtra("to_id", "");
                startActivity(intent1);
                break;
            case R.id.iv_daka:
                startActivity(new Intent(context, DakaActivity.class));
                break;
            case R.id.tv_quanzi:
                EventBus.getDefault().post(new NormalEventBean("369"));
                break;
            case R.id.tomig:
                Intent msgIntent = new Intent(getContext(), MessageActivity.class);
                poin_m.setVisibility(View.INVISIBLE);
                msgIntent.putExtra("code","0");
                // iv_red.setVisibility(View.INVISIBLE);
                startActivity(msgIntent);
                unReadMeassgBean.getData().setCount("0");
                count = unReadMeassgBean.getData().getCount();
                newfancount = unReadMeassgBean.getData().getIs_fans_new();
                redState(count, newfancount);
                commonPopupWindow.dismiss();
                break;
            case R.id.to_circle:
                Intent intent = new Intent(context, MineCircleActivity.class);
                poic_c.setVisibility(View.INVISIBLE);
                startActivity(intent);
                unReadMeassgBean.getData().setIs_fans_new("0");
                count = unReadMeassgBean.getData().getCount();
                newfancount = unReadMeassgBean.getData().getIs_fans_new();
                redState(count, newfancount);
                commonPopupWindow.dismiss();
                break;
            case R.id.tv_gouOut:
                CommenDialog.getInstance().dismissDialog();
                SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISLOGIN, false).commit();
                SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.TOKEN, "").commit();
                SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.UID, "").commit();
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;

        }
    }

    private void toTopDetail(int i) {
        Intent intent1 = new Intent(getContext(), TopicDetailActivity.class);
        intent1.putExtra("to_id", baseBean.getData().getTopic().get(i).getTo_id());
        startActivity(intent1);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        gson = GsonSingle.getGson();
        switch (resultCode) {
            case 1:
                baseBean = gson.fromJson(stringResult, NewFindBean.class);
                int results = baseBean.getResult();
                if (results == 3) {
                    CommenDialog.getInstance().showDialog(context,R.layout.view_dialog_out,this);
                }
                if (baseBean != null && baseBean.getResult() == 1) {
                    settingDate(baseBean);// 设置初始化数据
                    getCount();
                }
                break;
            case 2:
                unReadMeassgBean = gson.fromJson(stringResult, UnReadMeassgBean.class);
                String count = unReadMeassgBean.getData().getCount();
                String newfancount = unReadMeassgBean.getData().getIs_fans_new();
                if (!count.equals("0")) {
                    iv_red.setVisibility(View.VISIBLE);
                    poin_m.setVisibility(View.VISIBLE);

                }
                if (!newfancount.equals("0")) {
                    iv_red.setVisibility(View.VISIBLE);
                    poic_c.setVisibility(View.VISIBLE);

                }


                break;
        }

    }

    private void redState(String count, String newfancount) {
        if (!count.equals("0") || !newfancount.equals("0")) {
            iv_red.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        ToastUtils.show(context, errMsg);
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onTimerListener() {
        TurnClassHttp.unread(2, context, this);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        EventBus.getDefault().post(new NormalEventBean("shuaxin"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    /* 用于接收微信支付结果回调*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(NormalEventBean messageEvent) {

        if (messageEvent.getMessageCode().equals("jieshu")) {
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void getChildView(View view, int layoutResId) {
        tomig = bindView(view, R.id.tomig);
        tomig.setOnClickListener(this);
        to_circle = bindView(view, R.id.to_circle);
        to_circle.setOnClickListener(this);
        poin_m = bindView(view, R.id.poin_m);
        poic_c = bindView(view, R.id.poic_c);
    }

    @Override
    public void getChildView(View view) {
        TextView tv_errMsg = (TextView) view.findViewById(R.id.tv_errMsg);
        tv_errMsg.setText(baseBean.getMsg());
        TextView tv_gouOut = (TextView) view.findViewById(R.id.tv_gouOut);
        tv_gouOut.setOnClickListener(this);
    }
    @Override
    public void onStart() {
        super.onStart();
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                CommontUtil.setIndicator(tabLayout, 55, 55);
            }
        });
    }
}


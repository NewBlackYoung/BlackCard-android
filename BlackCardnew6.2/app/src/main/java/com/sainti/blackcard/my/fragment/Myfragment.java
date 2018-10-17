package com.sainti.blackcard.my.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseFragment;
import com.sainti.blackcard.circle.activity.MessageActivity;
import com.sainti.blackcard.coffee.coffeeorder.activity.CoffeeOrderListActivity;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.commen.mdialog.CommenDialog;
import com.sainti.blackcard.goodthings.goodtingsorder.activity.GoodThingOsderActivity;
import com.sainti.blackcard.homepage.splash.activity.LoginActivity;
import com.sainti.blackcard.housekeeperorder.activity.HousekeeperOrderActivity;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.my.activity.HezuoActivity;
import com.sainti.blackcard.my.activity.PersonalActivity;
import com.sainti.blackcard.my.activity.SetActivity;
import com.sainti.blackcard.my.bean.MyBean;
import com.sainti.blackcard.my.bonus.activity.MyBonusActivity;
import com.sainti.blackcard.my.flyorder.activity.FlyOrderActivity;
import com.sainti.blackcard.my.flyorder.drivinglicense.actvity.DrivingLicenseActivity;
import com.sainti.blackcard.my.invitingcourtesy.activity.InvitAcitvity;
import com.sainti.blackcard.my.taskcenter.activity.TaskCenterAcvitity;
import com.sainti.blackcard.privilege.bean.Bbean;
import com.sainti.blackcard.widget.CircleImageView;
import com.sainti.blackcard.widget.LoadingView;

/**
 * Created by YuZhenpeng on 2018/4/26.
 */

public class Myfragment extends BaseFragment implements View.OnClickListener, OnNetResultListener, CommenDialog.ViewInterface {

    private CircleImageView minetou;
    private TextView minename, mine_identify;
    private LinearLayout rlfl, rlf2, lin_club, ll_renwu, youhuijuan, rlmore, lychange, rlhezuo, ll_coffee, rlnbtj, jiazhao, mig_list;
    private Intent intent;
    private Gson gson;
    private MyBean myBean;
    private LoadingView loadingView;
    private Bbean baseBean;

    public static Myfragment newInstance(String param1) {
        Myfragment fragment = new Myfragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_newmine;
    }

    @Override
    protected void initView(View view) {

        rlnbtj = bindView(R.id.rlnbtj);
        jiazhao = bindView(R.id.jiazhao);
        minetou = bindView(R.id.minetou);
        minename = bindView(R.id.minename);
        ll_coffee = bindView(R.id.ll_coffee);
        mine_identify = bindView(R.id.mine_identify);
        rlfl = bindView(R.id.rlfl);
        rlf2 = bindView(R.id.rlf2);
        lin_club = bindView(R.id.lin_club);
        youhuijuan = bindView(R.id.youhuijuan);
        rlmore = bindView(R.id.rlmore);
        lychange = bindView(R.id.lychange);
        rlhezuo = bindView(R.id.rlhezuo);
        loadingView = new LoadingView(context);
        mig_list = bindView(R.id.mig_list);
        ll_renwu = bindView(R.id.ll_renwu);
    }

    @Override
    protected void initEvents() {
        minetou.setOnClickListener(this);// 头像点击事件
        rlfl.setOnClickListener(this);// 管家订单
        rlf2.setOnClickListener(this);// 好物订单
        lin_club.setOnClickListener(this);// 出行订单
        rlnbtj.setOnClickListener(this);// 邀请好礼
        youhuijuan.setOnClickListener(this);// 优惠券
        rlmore.setOnClickListener(this);// 设置
        jiazhao.setOnClickListener(this);
        rlhezuo.setOnClickListener(this);// 合作
        ll_coffee.setOnClickListener(this);
        mig_list.setOnClickListener(this);
        ll_renwu.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        loadingView.show();
        TurnClassHttp.mine(1, context, this);
    }

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        gson = GsonSingle.getGson();
        loadingView.dismiss();
        switch (resultCode) {
            case 1:
                baseBean = gson.fromJson(stringResult, Bbean.class);
                int results = baseBean.getResult();
                if (results == 3) {
                    CommenDialog.getInstance().showDialog(context,R.layout.view_dialog_out,this);
                }
                if (results == 1) {
                    myBean = gson.fromJson(stringResult, MyBean.class);
                    GlideManager.getsInstance().loadImageToUrL(context, myBean.getData().getHead(), minetou);
                    minename.setText(myBean.getData().getNickname());
                    mine_identify.setText("个人简介:" + myBean.getData().getIntroinfo());
                }
                break;
            case 2:
                break;
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();
        ToastUtils.show(context, errMsg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.minetou:// 头像点击事件
                Intent imgIntent = new Intent();
                imgIntent.setClass(getActivity(), PersonalActivity.class);
                getActivity().startActivity(imgIntent);
                break;
            case R.id.rlfl:// 管家订单
                intent = new Intent(getActivity(), HousekeeperOrderActivity.class);
                intent.putExtra("type", 0);
                startActivity(intent);
                break;
            case R.id.rlf2: // 好物订单
                intent = new Intent(getActivity(), GoodThingOsderActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            case R.id.lin_club: // 出行订单
                intent = new Intent(getActivity(), FlyOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.rlnbtj: // 邀请好礼
                intent = new Intent(context, InvitAcitvity.class);
                startActivity(intent);
                break;
            case R.id.youhuijuan: // 优惠券
                intent = new Intent(getActivity(), MyBonusActivity.class);
                startActivity(intent);
                //getYouHuiJuan();
                break;
            case R.id.rlmore:// 设置
                intent = new Intent(getActivity(), SetActivity.class);
                startActivity(intent);
                break;
            case R.id.rlhezuo: // 合作
                intent = new Intent(getActivity(), HezuoActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_coffee:
                intent = new Intent(getActivity(), CoffeeOrderListActivity.class);
                startActivity(intent);
                break;
            case R.id.jiazhao:
                Intent i = new Intent(context, DrivingLicenseActivity.class);
                startActivity(i);
                break;
            case R.id.mig_list:
                Intent msgIntent = new Intent(context, MessageActivity.class);
                msgIntent.putExtra("code", "1");
                startActivity(msgIntent);
                break;
            case R.id.ll_renwu:
                startActivity(new Intent(context, TaskCenterAcvitity.class));

                break;
            case R.id.tv_gouOut:
                CommenDialog.getInstance().dismissDialog();
                SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISLOGIN, false).commit();
                SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.TOKEN, "").commit();
                SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.UID, "").commit();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        boolean refresh = SpUtil.getBoolean(SpCodeName.SPNAME, SataicCode.REFRESH,false);
        if (refresh){
            TurnClassHttp.mine(1, context, this);
            SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SataicCode.REFRESH,false).commit();
        }
    }

    @Override
    public void getChildView(View view) {
        TextView tv_errMsg = (TextView) view.findViewById(R.id.tv_errMsg);
        tv_errMsg.setText(baseBean.getMsg());
        TextView tv_gouOut = (TextView) view.findViewById(R.id.tv_gouOut);
        tv_gouOut.setOnClickListener(this);
    }
}

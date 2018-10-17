package com.sainti.blackcard.mblcakcard.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.ppdai.loan.PPDLoanAgent;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseFragment;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.commen.mdialog.CommenDialog;
import com.sainti.blackcard.homepage.splash.activity.LoginActivity;
import com.sainti.blackcard.homepage.splash.activity.MemberBindActivity;
import com.sainti.blackcard.mblcakcard.adapter.XianjinAdapter;
import com.sainti.blackcard.mblcakcard.adapter.XinYongKaAdapter;
import com.sainti.blackcard.mblcakcard.bean.BlackcardBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.MLog;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.mwebview.HeiKaBuhuanWebActivity;
import com.sainti.blackcard.mwebview.LookMemberActivity;
import com.sainti.blackcard.mwebview.NormalWebActivity;
import com.sainti.blackcard.mwebview.PPdWebActivity;
import com.sainti.blackcard.privilege.activity.XianjinActivity;
import com.sainti.blackcard.widget.LoadingView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by YuZhenpeng on 2018/4/26.
 */

public class BlackcardFragment extends BaseFragment implements OnNetResultListener, OnRefreshListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, View.OnClickListener, CommenDialog.ViewInterface {

    private Gson gson;
    private String uids;
    private String tokens;
    private BlackcardBean blackcardBean;
    private RecyclerView rv_xianjin, rv_xiniyongka;
    private XianjinAdapter xianjinAdapter;
    private XinYongKaAdapter xinYongKaAdapter;
    private SmartRefreshLayout refresh_lay;
    private LoadingView loadingView;
    private TextView tv_more_xianjin, tv_more_xinyongk, tv_more_yue, tv_money, tv_leijimoney, tv_newmoney, tv_lookhuiji, tv_qiinyou, tv_heikabu, tv_duihuansp, tv_heiyoashi;
    private Intent intents;
    private ImageView iv_title_bg;
    private TextView card_sn_split, user_piny;
    private Intent intent;
    private LinearLayout ll_moreyue;
    private BaseBean baseBean;

    public static BlackcardFragment newInstance(String param1) {
        BlackcardFragment fragment = new BlackcardFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_blackcard;
    }

    @Override
    protected void initView(View view) {
        tv_qiinyou = bindView(R.id.tv_qiinyou);
        tv_heikabu = bindView(R.id.tv_heikabu);
        tv_duihuansp = bindView(R.id.tv_duihuansp);
        tv_heiyoashi = bindView(R.id.tv_heiyoashi);
        rv_xianjin = bindView(R.id.rv_xianjin);
        rv_xianjin.setLayoutManager(new LinearLayoutManager(context));
        rv_xianjin.setNestedScrollingEnabled(false);
        rv_xiniyongka = bindView(R.id.rv_xiniyongka);
        rv_xiniyongka.setLayoutManager(new LinearLayoutManager(context));
        rv_xiniyongka.setNestedScrollingEnabled(false);
        refresh_lay = bindView(R.id.refresh_lay);
        refresh_lay.setEnableLoadmore(false);
        loadingView = new LoadingView(context);
        tv_more_xianjin = bindView(R.id.tv_more_xianjin);
        tv_more_xinyongk = bindView(R.id.tv_more_xinyongk);
        tv_more_yue = bindView(R.id.tv_more_yue);
        tv_money = bindView(R.id.tv_money);
        tv_leijimoney = bindView(R.id.tv_leijimoney);
        tv_newmoney = bindView(R.id.tv_newmoney);
        tv_lookhuiji = bindView(R.id.tv_lookhuiji);
        iv_title_bg = bindView(R.id.iv_title_bg);
        card_sn_split = bindView(R.id.card_sn_split);
        user_piny = bindView(R.id.user_piny);
        ll_moreyue = bindView(R.id.ll_moreyue);
        PPDLoanAgent.getInstance().initConfig(context, SataicCode.appId_ppd, SataicCode.serverPublicKey_ppd, SataicCode.clientPrivateKey_ppd);
    }

    @Override
    protected void initData() {
        xianJinData();// 现金数据
        xinYongKaData();// 信用卡数据
        loadingView.show();
        uids = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
        tokens = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, "");
        TurnClassHttp.blackcardnew(1, context, this);
    }

    @Override
    protected void initEvents() {
        refresh_lay.setOnRefreshListener(this);
        tv_more_xianjin.setOnClickListener(this);
        tv_more_xinyongk.setOnClickListener(this);
        tv_more_yue.setOnClickListener(this);
        tv_lookhuiji.setOnClickListener(this);
        tv_qiinyou.setOnClickListener(this);
        tv_heikabu.setOnClickListener(this);
        tv_duihuansp.setOnClickListener(this);
        tv_heiyoashi.setOnClickListener(this);
        ll_moreyue.setOnClickListener(this);
    }


    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        gson = GsonSingle.getGson();
        baseBean = gson.fromJson(stringResult, BaseBean.class);
        String results = baseBean.getResult();
        if (results.equals("3")) {
            CommenDialog.getInstance().showDialog(context, R.layout.view_dialog_out, this);
        }
        if (results.equals("1")) {
            switch (resultCode) {
                case 1:
                    blackcardBean = gson.fromJson(stringResult, BlackcardBean.class);
                    xianjinAdapter.setNewData(blackcardBean.getData().getXianjin());
                    xinYongKaAdapter.setNewData(blackcardBean.getData().getCbank());
                    tv_money.setText(blackcardBean.getData().getBalance().getBalance());
                    tv_leijimoney.setText(blackcardBean.getData().getBalance().getLeijsy());
                    tv_newmoney.setText(blackcardBean.getData().getBalance().getZuijsy());
                    if (blackcardBean.getData().getBase().getUser_type().equals("3") || blackcardBean.getData().getBase().getUser_type().equals("4")) {
                        iv_title_bg.setBackgroundResource(R.mipmap.blackcard);
                        card_sn_split.setTextColor(context.getResources().getColor(R.color.xinhui));
                        card_sn_split.setText(blackcardBean.getData().getBase().getCard_sn_split());
                        user_piny.setTextColor(context.getResources().getColor(R.color.xinhui));
                        user_piny.setText(blackcardBean.getData().getBase().getUser_piny());
                    } else {
                        iv_title_bg.setBackgroundResource(R.mipmap.jingying);
                        card_sn_split.setTextColor(context.getResources().getColor(R.color.xinhui));
                        card_sn_split.setText(blackcardBean.getData().getBase().getCard_sn_split());
                        user_piny.setTextColor(context.getResources().getColor(R.color.xinhui));
                        user_piny.setText(blackcardBean.getData().getBase().getUser_piny());
                    }

                    break;
            }
        }
        refresh_lay.finishRefresh();
        loadingView.dismiss();
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        loadingView.dismiss();
        ToastUtils.show(context, errMsg);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        // loadingView.show();
        TurnClassHttp.blackcardnew(1, context, this);
    }

    /*现金*/
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


        String name = blackcardBean.getData().getXianjin().get(position).getName();
        String number = blackcardBean.getData().getBase().getUser_mobile();
        if (name.equals("拍拍贷")) {
            PPDLoanAgent.getInstance().initLoadChannelImage(context);
            //    PPDLoanAgent.getInstance().initLaunch(PPdWebActivity.this);
            PPDLoanAgent.getInstance().setEntityChannel(context, "0");
            PPDLoanAgent.getInstance().initLaunch(context, number);
        } else {
            Intent intent = new Intent(context, NormalWebActivity.class);
            intent.putExtra("code", "5");
            intent.putExtra("url", blackcardBean.getData().getXianjin().get(position).getUrl());
            startActivity(intent);
        }


    }

    /*信用卡*/
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(context, NormalWebActivity.class);
        intent.putExtra("code", "6");
        intent.putExtra("url", blackcardBean.getData().getCbank().get(position).getUrl());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_more_xianjin:
                MobclickAgent.onEvent(context, "xianjin");// 友盟统计
                intent = new Intent(getContext(), PPdWebActivity.class);
                intent.putExtra("title", "更多额度");
                intent.putExtra("wel_url", "http://www.qing-hei.com/mobile.php/App/Sign?uid=" + uids + "&token=" + tokens);
                intent.putExtra("code", "3");
                getContext().startActivity(intent);

                break;
            case R.id.tv_more_xinyongk:
                MobclickAgent.onEvent(context, "xinyongka");// 友盟统计
                intents = new Intent(getContext(), NormalWebActivity.class);
                intents.putExtra("code", "4");
                intents.putExtra("title", "青年黑卡信用卡申请");
                intents.putExtra("url", "http://www.qing-hei.com/mobile.php/App/Creditcard?uid=" + uids + "&token=" + tokens);
                getContext().startActivity(intents);
                break;
            case R.id.tv_more_yue:
                MobclickAgent.onEvent(context, "heikayue");// 友盟统计
                intent = new Intent(getContext(), XianjinActivity.class);
                getContext().startActivity(intent);

                break;
            case R.id.tv_lookhuiji:
                MobclickAgent.onEvent(context, "shouyehuiji");// 友盟统计
                intent = new Intent(getContext(), LookMemberActivity.class);
                intent.putExtra("title", "青年黑卡会籍");
                intent.putExtra("wel_url", "http://www.qing-hei.com/mobile.php/App/membership?uid=" + uids + "&token=" + tokens);
                getContext().startActivity(intent);
                break;
            case R.id.tv_qiinyou:
                intents = new Intent(context, MemberBindActivity.class);
                context.startActivity(intents);
                break;
            case R.id.tv_heikabu:
                MLog.d("uids", uids);
                MLog.d("tokens", tokens);
                intents = new Intent(getContext(), HeiKaBuhuanWebActivity.class);
                intents.putExtra("wel_url", " http://www.qing-hei.com/mobile.php/Buka/index/?type=1&uid=" + uids + "&token=" + tokens);
                getContext().startActivity(intents);
                break;
            case R.id.tv_duihuansp:
                intents = new Intent(getContext(), NormalWebActivity.class);
                intents.putExtra("code", "8");
                intents.putExtra("url", "http://www.qing-hei.com/mobile.php/Key/change/?uid=" + uids + "&token=" + tokens);
                getContext().startActivity(intents);
                break;
            case R.id.tv_heiyoashi:
                MobclickAgent.onEvent(context, "heiyaoshidianjishu");// 友盟统计
                intents = new Intent(getContext(), NormalWebActivity.class);
                intents.putExtra("code", "9");
                intents.putExtra("url", "http://www.qing-hei.com/mobile.php/Key/?uid=" + uids + "&token=" + tokens);
                getContext().startActivity(intents);
                break;
            case R.id.ll_moreyue:
                MobclickAgent.onEvent(context, "heikayue");// 友盟统计
                intent = new Intent(getContext(), XianjinActivity.class);
                getContext().startActivity(intent);
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

    private void xianJinData() {
        xianjinAdapter = new XianjinAdapter(R.layout.item_xianjin);
        rv_xianjin.setAdapter(xianjinAdapter);
        xianjinAdapter.setOnItemClickListener(this);
    }

    private void xinYongKaData() {
        xinYongKaAdapter = new XinYongKaAdapter(R.layout.item_xinyongka);
        xinYongKaAdapter.setContext(context);
        rv_xiniyongka.setAdapter(xinYongKaAdapter);
        xinYongKaAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void getChildView(View view) {
        TextView tv_errMsg = (TextView) view.findViewById(R.id.tv_errMsg);
        tv_errMsg.setText(baseBean.getMsg());
        TextView tv_gouOut = (TextView) view.findViewById(R.id.tv_gouOut);
        tv_gouOut.setOnClickListener(this);
    }
}

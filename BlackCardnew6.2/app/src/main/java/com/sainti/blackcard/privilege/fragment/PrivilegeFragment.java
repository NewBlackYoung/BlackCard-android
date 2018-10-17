package com.sainti.blackcard.privilege.fragment;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.LocationSource;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.library.banner.BannerLayout;
import com.example.library.banner.layoutmanager.CenterScrollListener;
import com.example.library.banner.layoutmanager.OverFlyingLayoutManager;
import com.google.gson.Gson;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.ChatManager;
import com.hyphenate.chat.Message;
import com.hyphenate.helpdesk.easeui.ui.BaseChatActivity;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.hyphenate.util.EasyUtils;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseFragment;
import com.sainti.blackcard.base.MyApp;
import com.sainti.blackcard.coffee.activity.CoffeeActivity;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.commen.mdialog.CommenDialog;
import com.sainti.blackcard.db.bean.GoodthingsBean;
import com.sainti.blackcard.db.dao.GoodThingsDao;
import com.sainti.blackcard.goodthings.activity.GoodthingsListActivity;
import com.sainti.blackcard.goodthings.spcard.activity.ShoppingCardActivity;
import com.sainti.blackcard.homepage.splash.activity.LoginActivity;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.glide.GlideManager;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.MyItemClickListener;
import com.sainti.blackcard.minterface.TimerListenerHasCode;
import com.sainti.blackcard.mtuils.AnimationUtil;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.MLog;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.mwebview.CommenWebActivity;
import com.sainti.blackcard.mwebview.LookMemberActivity;
import com.sainti.blackcard.mwebview.NormalWebActivity;
import com.sainti.blackcard.mwebview.PPdWebActivity;
import com.sainti.blackcard.my.invitingcourtesy.activity.InvitAcitvity;
import com.sainti.blackcard.privilege.activity.XianjinActivity;
import com.sainti.blackcard.privilege.adapter.HaoWuAdapter;
import com.sainti.blackcard.privilege.adapter.PrivilegeOfpeopleAdapter;
import com.sainti.blackcard.privilege.adapter.TenImageAdapter;
import com.sainti.blackcard.privilege.adapter.TopBannerAdapter;
import com.sainti.blackcard.privilege.bean.Bbean;
import com.sainti.blackcard.privilege.bean.PrivilegeBean;
import com.sainti.blackcard.privilege.moreprivilege.activity.MoreTeQuanActivity;
import com.sainti.blackcard.widget.LoadingView;
import com.sainti.blackcard.widget.ScrollRecyclerView;
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

public class PrivilegeFragment extends BaseFragment implements OnNetResultListener, MyItemClickListener, View.OnClickListener, AMapLocationListener, TimerListenerHasCode, BannerLayout.OnBannerItemClickListener, OverFlyingLayoutManager.OnPageChangeListener, BaseQuickAdapter.OnItemClickListener, CommenDialog.ViewInterface {

    private ScrollRecyclerView recyclerView, xrvPrivilegeOfpeople;
    private LoadingView loadingView;
    private PrivilegeBean privilegeBean;
    private RecyclerView xrvEightImage;
    private TenImageAdapter privilegeAdapter;
    private PrivilegeOfpeopleAdapter prvadapter;
    private BannerLayout bannersss;
    private TextView tvSmalltitleGood, tvDetailTitle, tvPrice, tv_heikayue, tvTitlePuth, tvSmalltitlePuth, tvMorebutlerService, tvGoodThings, tvRefress, tvToLook, titleTv;
    private ScrollView scrollView;
    private SmartRefreshLayout refreshLayout;
    private RelativeLayout tvBeijingTwo, rela_gengduo, rela_qianbao, rela_yue, layPuth, ll_zhaohuanguanjia;
    private LinearLayout layChakanhuiji, ll_out;
    private ImageView ivInvitation, ivStarbuckscoffee, iv_point_one, iv_point_two, ivOut, iv_shopping_card, iv_ishasCard, ivImagePuth, iv_newxiaoxi, iv_hasmsg;

    private String tokens;
    //声明AMapLocationClient类对象，定位发起端
    private AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象，定位参数
    public AMapLocationClientOption mLocationOption = null;
    //声明mListener对象，定位监听器
    private LocationSource.OnLocationChangedListener mListener = null;
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;
    private LocationManager locationManager;
    private List<String> list1;
    private String provider;
    private String aMapLocationCity = "";
    private String aMapLocationProvince = "";
    private AnimationUtil animationUtil;
    private boolean refresh;
    private TopBannerAdapter webBannerAdapter;
    private List<String> imageList;
    OverFlyingLayoutManager mOverFlyingLayoutManager;
    //  private LocalDataAdapter localDataAdapter;
    private String isguanjia;
    private Intent intent;
    private String uids;
    private String uid;
    private String token;
    private List<PrivilegeBean.DataBean.WelfareBean> list;
    private HaoWuAdapter wuAdapter;
    private Bbean baseBean;

    public static PrivilegeFragment newInstance(String param1) {
        PrivilegeFragment fragment = new PrivilegeFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_privilege;
    }


    @Override
    protected void initView(View view) {
        EventBus.getDefault().register(this);
        recyclerView = bindView(R.id.recycler_banner);
        ll_zhaohuanguanjia = bindView(R.id.ll_zhaohuanguanjia);
        xrvEightImage = (RecyclerView) view.findViewById(R.id.xrv_eightImage);
        xrvPrivilegeOfpeople = bindView(R.id.xrv_privilegeOfpeople);
        bannersss = (BannerLayout) view.findViewById(R.id.bannersss);
        tvSmalltitleGood = (TextView) view.findViewById(R.id.tv_smalltitle_good);
        tvDetailTitle = (TextView) view.findViewById(R.id.tv_detailTitle);
        tvPrice = (TextView) view.findViewById(R.id.tv_price);
        scrollView = bindView(R.id.scrollView);
        refreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refresh_lay); // 刷新控件
        tv_heikayue = bindView(R.id.tv_heikayue);
        tvBeijingTwo = (RelativeLayout) view.findViewById(R.id.tv_beijing_two);
        tvBeijingTwo = (RelativeLayout) view.findViewById(R.id.tv_beijing_two);
        tvTitlePuth = (TextView) view.findViewById(R.id.tv_title_puth);
        tvSmalltitlePuth = (TextView) view.findViewById(R.id.tv_smalltitle_puth);
        ivInvitation = (ImageView) view.findViewById(R.id.iv_invitation);
        tvMorebutlerService = (TextView) view.findViewById(R.id.tv_morebutler_service);
        tvGoodThings = (TextView) view.findViewById(R.id.tv_goodThings);
        ivStarbuckscoffee = (ImageView) view.findViewById(R.id.iv_starbuckscoffee);
        iv_point_one = (ImageView) view.findViewById(R.id.iv_point_one);
        iv_point_two = (ImageView) view.findViewById(R.id.iv_point_two);
        rela_gengduo = (RelativeLayout) view.findViewById(R.id.rela_gengduo);
        rela_qianbao = (RelativeLayout) view.findViewById(R.id.rela_qianbao);
        rela_yue = (RelativeLayout) view.findViewById(R.id.rela_yue);
        layChakanhuiji = (LinearLayout) view.findViewById(R.id.lay_chakanhuiji);
        tvRefress = (TextView) view.findViewById(R.id.tv_refress);
        layPuth = (RelativeLayout) view.findViewById(R.id.lay_puth);//  弹出信息
        ivImagePuth = (ImageView) view.findViewById(R.id.iv_image_puth);
        tvToLook = (TextView) view.findViewById(R.id.tv_to_look);
        ivOut = (ImageView) view.findViewById(R.id.iv_out);
        titleTv = (TextView) view.findViewById(R.id.title_tv);
        iv_shopping_card = (ImageView) view.findViewById(R.id.iv_shopping_card); // 顶部购物车按钮
        iv_ishasCard = (ImageView) view.findViewById(R.id.iv_ishasCard);
        iv_newxiaoxi = bindView(R.id.iv_newxiaoxi);
        ll_out = bindView(R.id.ll_out);
        loadingView = new LoadingView(context);
        animationUtil = new AnimationUtil(context);
        refreshLayout.setEnableLoadmore(false);//是否启用上拉加载功能
        refreshLayout.setHeaderHeight(100);
        iv_hasmsg = bindView(R.id.iv_hasmsg);
        tenCion(); // 10个图标列表设置
        //goodTingData(); // 好物数据
        teQuanData();// 特权模块

    }


    @Override
    protected void initData() {
        if (refresh) {
            refresh = false;
        } else {
            // 判断是否开启通知
            boolean isTasnkuiang = SpUtil.getBoolean(SpCodeName.SPNAME, SpCodeName.ISNEWPENSO_Reftresh, false);// 判断是否是新人
            if (isTasnkuiang) {
            } else {
                loadingView.show();
            }

        }

        TurnClassHttp.getPrivilegeDate(0, context, this);

        if (SpUtil.getString(SpCodeName.SPNAME, SpCodeName.XINYONKACODE, "").equals("")) {
            iv_point_one.setVisibility(View.VISIBLE);
        }
        if (SpUtil.getString(SpCodeName.SPNAME, SpCodeName.XIANJIAN, "").equals("")) {
            iv_point_two.setVisibility(View.VISIBLE);
        }
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refresh = true;
                refres();

            }
        });
        uids = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
        tokens = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, "");
        location();
        receverHuanMsg();// 环信接收消息
        boolean idnew = SpUtil.getBoolean(SpCodeName.SPNAME, SataicCode.ISRENCOIN, false);
        if (idnew) {
            animationUtil.viewAppear(iv_newxiaoxi);
            animationUtil.viewAppear(iv_hasmsg);

        }


    }


    // 10个图标列表设置
    private void tenCion() {
        privilegeAdapter = new TenImageAdapter(context);
        privilegeAdapter.setLitener(this);
        xrvEightImage.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        xrvEightImage.setAdapter(privilegeAdapter);
        xrvEightImage.setNestedScrollingEnabled(false);
    }

    private void refres() {
        initData();
    }

    @Override
    protected void initEvents() {
        ivInvitation.setOnClickListener(this);
        tvMorebutlerService.setOnClickListener(this);
        tvGoodThings.setOnClickListener(this);
        ivStarbuckscoffee.setOnClickListener(this);
        rela_yue.setOnClickListener(this); //  余额点击事件
        rela_qianbao.setOnClickListener(this); //  信用卡
        rela_gengduo.setOnClickListener(this); //  现金
        layChakanhuiji.setOnClickListener(this);
        tvRefress.setOnClickListener(this);
        ll_out.setOnClickListener(this);
        tvToLook.setOnClickListener(this);
        ivOut.setOnClickListener(this);
        ivImagePuth.setClickable(false);
        iv_shopping_card.setOnClickListener(this);
        tvBeijingTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
        layPuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
        ll_zhaohuanguanjia.setOnClickListener(this);
        list = new ArrayList<>();
    }


    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        loadingView.dismiss();
        refreshLayout.finishRefresh();
        Gson gson = GsonSingle.getGson();
        baseBean = gson.fromJson(stringResult, Bbean.class);
        int results = baseBean.getResult();
        if (results == 3) {
            CommenDialog.getInstance().showDialog(context, R.layout.view_dialog_out, this);
        }

        switch (resultCode) {
            case 0:
                if (results == 1) {
                    privilegeBean = gson.fromJson(stringResult, PrivilegeBean.class);
                    if (privilegeBean != null) {
                        imageList = new ArrayList<>();
                        for (int i = 0; i < privilegeBean.getData().getTop_banner().size(); i++) {
                            imageList.add(privilegeBean.getData().getTop_banner().get(i).getL_img());
                        }
                        CommontUtil.lateTimeHasCode(10, this, 10);
                        privilegeAdapter.setIconBeanList(privilegeBean.getData().getIcon()); // 是个图标数据
                        prvadapter.setRecommendBeanList(privilegeBean.getData().getRecommend()); // 人气特权
                        goodTingData();
                        wuAdapter.setNewData(privilegeBean.getData().getWelfare());
                        tv_heikayue.setText(privilegeBean.getData().getMoney());
                        //  获取通知信息
                        haowuTankuang();// 好物弹框信息

                    }
                }

                break;
        }


    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        refreshLayout.finishRefresh();
        loadingView.dismiss();
        ToastUtils.show(context, errMsg);
    }


    private void bannerClick(int position) {
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("linkname", privilegeBean.getData().getTop_banner().get(position).getL_title());
        MobclickAgent.onEvent(context, "lunbotudianji", map_ekv0);// 友盟统计
        Intent intent = new Intent(getContext(), CommenWebActivity.class);
        intent.putExtra("wel_url", privilegeBean.getData().getTop_banner().get(position).getL_link());
        intent.putExtra("title", privilegeBean.getData().getTop_banner().get(position).getL_title());
        intent.putExtra("img_url", privilegeBean.getData().getTop_banner().get(position).getL_img());
        intent.putExtra("name1", privilegeBean.getData().getTop_banner().get(position).getL_title());
        intent.putExtra("small_title", privilegeBean.getData().getTop_banner().get(position).getL_title3() + "");
        String l_haowu = privilegeBean.getData().getTop_banner().get(position).getL_haowu();
        intent.putExtra("isguanjia", privilegeBean.getData().getTop_banner().get(position).getIs_guanjia());
        if ((l_haowu.equals("1"))) {
            intent.putExtra("w_id", privilegeBean.getData().getTop_banner().get(position).getL_haowuid());
        } else {
            intent.putExtra("w_id", "");
        }
        startActivity(intent);
    }

    /*10个图标点击事件  甄选好物*/
    @Override
    public void onItemClick(int position, int code) {
        switch (code) {
            case 2://10个图标点击事件
                Map<String, String> map_ekv0s = new HashMap<String, String>();
                map_ekv0s.put("name", privilegeBean.getData().getIcon().get(position).getXp_name());
                MobclickAgent.onEvent(context, "tequanicon", map_ekv0s);// 友盟统计
                String xp_type = privilegeBean.getData().getIcon().get(position).getXp_type();
                String xp_id = privilegeBean.getData().getIcon().get(position).getXp_id();
                isguanjia = privilegeBean.getData().getIcon().get(position).getIs_guanjia();

                if (xp_id.equals("1")) {
                    // 我要订机票
                    Map<String, String> map_ekv0 = new HashMap<String, String>();
                    map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
                    map_ekv0.put("rukou", "shoyejipiaotequan");
                    MobclickAgent.onEvent(context, "zhaohuanguanjia", map_ekv0);// 友盟统计
                    if (ChatClient.getInstance().isLoggedInBefore()) {
                        String type = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TYPEONE, "");
                        toChat(type);
                        SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.TYPEONE, "100").commit();
                    } else {
                        //未登录，需要登录后，再进入会话界面
                        ToastUtils.show(context, "登陆失败");
                    }
                }
                if (xp_id.equals("2")) {
                    // 我要订酒店
                    if (ChatClient.getInstance().isLoggedInBefore()) {
                        Map<String, String> map_ekv0 = new HashMap<String, String>();
                        map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
                        map_ekv0.put("rukou", "shouyejiudiantequan");
                        MobclickAgent.onEvent(context, "zhaohuanguanjia", map_ekv0);// 友盟统计
                        String type = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TYPETWO, "");
                        //已经登录，可以直接进入会话界面
                        toChat(type);// 进入环信
                        SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.TYPETWO, "100").commit();
                    } else {
                        //未登录，需要登录后，再进入会话界面
                        ToastUtils.show(context, "登陆失败");
                    }
                }
                if (xp_id.equals("5")) {
                    startActivity(new Intent(getContext(), GoodthingsListActivity.class));
                }
                if (xp_id.equals("6")) {
                    if (aMapLocationCity.equals("") && aMapLocationProvince.equals("")) {
                        Toast.makeText(getContext(), "获取位置信息失败！请开启权限！", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        // Toast.makeText(getContext(), "进入咖啡！", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(getContext(), CoffeeActivity.class);
                        intent1.putExtra("city", aMapLocationCity);
                        intent1.putExtra("sheng", aMapLocationProvince);
                        startActivity(intent1);
                    }

                }
                // 更多特权
                if (xp_id.equals("0")) {
                    toMoreTequan();

                }
                if (xp_type.equals("1")) {
                    Intent intents_t = new Intent(getContext(), CommenWebActivity.class);
                    intents_t.putExtra("wel_url", privilegeBean.getData().getIcon().get(position).getXp_url());
                    intents_t.putExtra("title", privilegeBean.getData().getIcon().get(position).getXp_name());
                    intents_t.putExtra("img_url", privilegeBean.getData().getIcon().get(position).getXp_icon());
                    intents_t.putExtra("name1", privilegeBean.getData().getIcon().get(position).getXp_name());
                    intents_t.putExtra("w_id", "");
                    intents_t.putExtra("isguanjia", isguanjia);
                    startActivity(intents_t);
                }
                break;
            case 1: // 人气特权
                MobclickAgent.onEvent(context, "renqitequan");// 友盟统计
                Intent intents = new Intent(context, CommenWebActivity.class);
                String uir = privilegeBean.getData().getRecommend().get(position).getL_link();
                if (uir.equals("more")) {
                    toMoreTequan();
                } else {
                    intents.putExtra("wel_url", privilegeBean.getData().getRecommend().get(position).getL_link());
                    intents.putExtra("title", privilegeBean.getData().getRecommend().get(position).getL_title());
                    intents.putExtra("img_url", privilegeBean.getData().getRecommend().get(position).getL_img());
                    intents.putExtra("name1", privilegeBean.getData().getRecommend().get(position).getL_title());
                    String l_haowu = privilegeBean.getData().getRecommend().get(position).getL_haowu();
                    intents.putExtra("isguanjia", privilegeBean.getData().getRecommend().get(position).getIs_guanjia());
                    if ((l_haowu.equals("1"))) {
                        intents.putExtra("w_id", privilegeBean.getData().getRecommend().get(position).getL_haowuid());
                    } else {
                        intents.putExtra("w_id", "");
                    }
                    startActivity(intents);
                }

                break;
            case 3:

                break;
        }

    }

    private void toMoreTequan() {
        Intent intent1 = new Intent(getContext(), MoreTeQuanActivity.class);
        intent1.putExtra("aMapLocationCity", aMapLocationCity);
        intent1.putExtra("aMapLocationProvince", aMapLocationProvince);
        startActivity(intent1);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_invitation:// 邀请好礼
                MobclickAgent.onEvent(context, "shouyehuiji");// 友盟统计
                intent = new Intent(context, InvitAcitvity.class);
                startActivity(intent);
                break;
            case R.id.tv_morebutler_service:
              /*  startActivity(new Intent(getContext(), ButlerServiceActivity.class));*/
                break;
            case R.id.tv_goodThings:
                startActivity(new Intent(getContext(), GoodthingsListActivity.class));
                break;
            case R.id.iv_starbuckscoffee:
                tohuanjia();
                break;
            case R.id.rela_yue://  余额点击事件
                MobclickAgent.onEvent(context, "heikayue");// 友盟统计
                intent = new Intent(getContext(), XianjinActivity.class);
                getContext().startActivity(intent);

                break;
            case R.id.rela_qianbao://  信用卡
                MobclickAgent.onEvent(context, "xinyongka");// 友盟统计
                SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.XINYONKACODE, "1").commit();
                iv_point_one.setVisibility(View.GONE);
                uid = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
                token = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, "");
                Intent intents = new Intent(getContext(), NormalWebActivity.class);
                intents.putExtra("code", "4");
                intents.putExtra("title", "青年黑卡信用卡申请");
                intents.putExtra("url", "http://www.qing-hei.com/mobile.php/App/Creditcard?uid=" + uid + "&token=" + token);
                getContext().startActivity(intents);
                break;
            case R.id.rela_gengduo://  现金
                MobclickAgent.onEvent(context, "xianjin");// 友盟统计
                SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.XIANJIAN, "1").commit();
                iv_point_two.setVisibility(View.GONE);
                uids = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
                tokens = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, "");
                intent = new Intent(getContext(), PPdWebActivity.class);
                intent.putExtra("title", "更多额度");
                intent.putExtra("wel_url", "http://www.qing-hei.com/mobile.php/App/Sign?uid=" + uids + "&token=" + tokens);
                //  intent.putExtra("wel_url", "http://www.qing-hei.com/mobile.php/OrderNew");
                intent.putExtra("code", "3");
                getContext().startActivity(intent);

                break;
            case R.id.lay_chakanhuiji:
                MLog.d("友盟统计", "青年黑卡会籍");
                MobclickAgent.onEvent(context, "shouyehuiji");// 友盟统计
                intent = new Intent(getContext(), LookMemberActivity.class);
                intent.putExtra("title", "青年黑卡会籍");
                intent.putExtra("wel_url", "http://www.qing-hei.com/mobile.php/App/membership?uid=" + uids + "&token=" + tokens);
                getContext().startActivity(intent);
                break;

            case R.id.tv_to_look:// 弹框查看
                Intent intentss = new Intent(getContext(), CommenWebActivity.class);
                String wel_url = privilegeBean.getData().getMsgpushinfo().get(0).getPush_url();
                String title = privilegeBean.getData().getMsgpushinfo().get(0).getPush_title();
                String img_url = privilegeBean.getData().getMsgpushinfo().get(0).getPush_pic();
                String name1 = privilegeBean.getData().getMsgpushinfo().get(0).getPush_title();
                String small_title = privilegeBean.getData().getMsgpushinfo().get(0).getPush_des();
                String l_haowu = privilegeBean.getData().getMsgpushinfo().get(0).getPush_dataid();
                intentss.putExtra("wel_url", wel_url);
                intentss.putExtra("title", title);
                intentss.putExtra("img_url", img_url);
                intentss.putExtra("name1", name1);
                intentss.putExtra("small_title", small_title);
                intentss.putExtra("w_id", l_haowu);
                animationUtil.viewDisappear(tvBeijingTwo);
                startActivity(intentss);
                if (l_haowu.equals("")) {
                    Map<String, String> map_wenzhang = new HashMap<String, String>();
                    map_wenzhang.put("haowutitle", name1);
                    MobclickAgent.onEvent(context, "wenzhangtankuang", map_wenzhang);//
                } else {
                    Map<String, String> map_haowu = new HashMap<String, String>();
                    map_haowu.put("haowutitle", name1);
                    MobclickAgent.onEvent(context, "haowutankuang", map_haowu);//
                }
                break;
            case R.id.iv_shopping_card:
                startActivity(new Intent(getContext(), ShoppingCardActivity.class));
                break;
            case R.id.ll_out:
                animationUtil.viewDisappear(tvBeijingTwo);
                break;
            case R.id.ll_zhaohuanguanjia:
                tohuanjia();
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
            default:
                break;

        }
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                aMapLocationCity = aMapLocation.getCity();
                aMapLocationProvince = aMapLocation.getProvince();
                mLocationClient.stopLocation();

            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                // ToastUtils.show(context, "获取位置信息失败！请开启权限");
                mLocationClient.stopLocation();

            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    //消息处理者,创建一个Handler的子类对象,目的是重写Handler的处理消息的方法(handleMessage())
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SataicCode.ISRENCOIN, true).commit();
            iv_newxiaoxi.setVisibility(View.VISIBLE);
            animationUtil.viewAppear(iv_hasmsg);
            EventBus.getDefault().post(new NormalEventBean(SataicCode.EVENTCODE.TWO));
        }
    };

    private void receverHuanMsg() {
        ChatClient.getInstance().getChat().addMessageListener(new ChatManager.MessageListener() {
            @Override
            public void onMessage(List<Message> list) {

                // 判断app是否在后台
                if (!EasyUtils.isAppRunningForeground(context)) {

                } else {
                    if (isActivityTop(BaseChatActivity.class, context)) {


                    } else {
                        handler.sendEmptyMessage(0);

                    }
                }
            }

            @Override
            public void onCmdMessage(List<Message> list) {
                //收到命令消息，命令消息不存数据库，一般用来作为系统通知，例如留言评论更新，
                //会话被客服接入，被转接，被关闭提醒
            }

            @Override
            public void onMessageStatusUpdate() {

            }

            @Override
            public void onMessageSent() {
                //发送消息后，会调用，可以在此刷新列表，显示最新的消息
            }
        });
    }


    @Override
    public void onItemClick(int position) {
        bannerClick(position);
    }

    private void teQuanData() {
        // 人气特权横向列表设
        prvadapter = new PrivilegeOfpeopleAdapter(getActivity());
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        xrvPrivilegeOfpeople.setLayoutManager(linearLayoutManager);
        xrvPrivilegeOfpeople.setAdapter(prvadapter);
        prvadapter.setLitener(this);

        xrvPrivilegeOfpeople.setOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //设置什么布局管理器,就获取什么的布局管理器
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当停止滑动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition ,角标值
                    int lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    //所有条目,数量值
                    int totalItemCount = linearLayoutManager.getItemCount();

                    // 判断是否滚动到底部，并且是向右滚动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        //加载更多功能的代码
                        MLog.d("ssssssss", "DAOLE");
                        toMoreTequan();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                //dx>0:向右滑动,dx<0:向左滑动
                //dy>0:向下滑动,dy<0:向上滑动
                MLog.d("ssssssss", dx + "");
                if (dx > 0) {
                    isSlidingToLast = true;
                } else {
                    isSlidingToLast = false;
                }
            }
        });

    }

    private void goodTingData() {
        // 好物数据
        mOverFlyingLayoutManager = new OverFlyingLayoutManager(0.80f, 445, OverFlyingLayoutManager.HORIZONTAL);
        // localDataAdapter = new LocalDataAdapter(context);
        wuAdapter = new HaoWuAdapter(R.layout.item_image_over);
        mOverFlyingLayoutManager.setOnPageChangeListener(this);
        wuAdapter.setOnItemClickListener(this);
        // localDataAdapter.setListener(this);
        recyclerView.setLayoutManager(mOverFlyingLayoutManager);
        recyclerView.setAdapter(wuAdapter);
        //  mOverFlyingLayoutManager.setInfinite(false);// 循环
        recyclerView.addOnScrollListener(new CenterScrollListener());// 监听好动偏差 回弹效果

    }

    private void bannerData() {
        // 顶部轮播图数据
        webBannerAdapter = new TopBannerAdapter(context, imageList);
        webBannerAdapter.setOnBannerItemClickListener(this);
        bannersss.setAdapter(webBannerAdapter);
        bannersss.setAutoPlaying(true);
        bannersss.setAutoPlayDuration(2000);
    }

    private void toChat(String type) {
        //已经登录，可以直接进入会话界面
        Intent intents = new IntentBuilder(context)
                .setServiceIMNumber("test1") //获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“IM服务号”
                .build()
                .putExtra("type", type);
        startActivity(intents);
    }

    /**
     * 判断某activity是否处于栈顶
     *
     * @return true在栈顶 false不在栈顶
     */
    private boolean isActivityTop(Class cls, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String name = manager.getRunningTasks(1).get(0).topActivity.getClassName();
        return name.equals(cls.getName());
    }

    /* 弹出好物*/
    private void haowuTankuang() {
        boolean iskantankaung = SpUtil.getBoolean(SpCodeName.SPNAME, SpCodeName.ISHAOUWKUANG, false);
        String puthId = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.PUTHID, "");
        if (privilegeBean.getData().getMsgpushinfo() != null && privilegeBean.getData().getMsgpushinfo().size() > 0) {
            if (iskantankaung && !puthId.equals(privilegeBean.getData().getMsgpushinfo().get(0).getPush_id())) {
                GlideManager.getsInstance().loadImageToUrL(getContext(), privilegeBean.getData().getMsgpushinfo().get(0).getPush_pic(), ivImagePuth);// 设置图片
                SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.PUTHID, privilegeBean.getData().getMsgpushinfo().get(0).getPush_id()).commit();// 保存
                tvTitlePuth.setText(privilegeBean.getData().getMsgpushinfo().get(0).getPush_title());// 设置标题
                tvSmalltitlePuth.setText(privilegeBean.getData().getMsgpushinfo().get(0).getPush_des());
                CommontUtil.lateTimeHasCode(1000, this, 1);
            }

        }
    }

    private void tohuanjia() {
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
        map_ekv0.put("rukou", "shouyeguanjia");
        MobclickAgent.onEvent(context, "zhaohuanguanjia", map_ekv0);// 友盟统计
        if (ChatClient.getInstance().isLoggedInBefore()) {
            //已经登录，可以直接进入会话界面
            Intent intent = new IntentBuilder(context)
                    .setServiceIMNumber("test1") //获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“IM服务号”
                    .build();
            startActivity(intent);
            if (iv_newxiaoxi.getVisibility() == View.VISIBLE) {
                animationUtil.viewDisappear(iv_newxiaoxi);
                animationUtil.viewDisappear(iv_hasmsg);
                EventBus.getDefault().post(new NormalEventBean(SataicCode.EVENTCODE.ONE));

            }
            SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SataicCode.ISRENCOIN, false).commit();
        } else {
            //未登录，需要登录后，再进入会话界面
            ToastUtils.show(context, "登陆失败");
        }
    }


    private void location() {
        //初始化定位
        mLocationClient = new AMapLocationClient(MyApp.getInstance().getmContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void onPageSelected(int position) {
        MLog.d("huadong", "滑动位置" + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTimerListener(int code) {
        switch (code) {
            case 1:
                animationUtil.viewAppear(tvBeijingTwo);
                break;
            case 10:
                bannerData();
                break;
            case 9:
                refres();
                break;
            case 3:

                break;
        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String URL = privilegeBean.getData().getWelfare().get(position).getW_url();
        if (URL.equals("more")) {
            startActivity(new Intent(getContext(), GoodthingsListActivity.class));
        } else {
            List<GoodthingsBean> allLists = GoodThingsDao.getsInstance(context).queryForPayState("0"); // 查询所有数据传给后台
            MLog.d("数据", "没有跳页前的数据 One====================" + allLists.size() + "");
            Intent intent = new Intent(context, CommenWebActivity.class);
            intent.putExtra("wel_url", privilegeBean.getData().getWelfare().get(position).getW_url());
            intent.putExtra("title", privilegeBean.getData().getWelfare().get(position).getW_title());
            intent.putExtra("img_url", privilegeBean.getData().getWelfare().get(position).getW_pic());
            intent.putExtra("name1", privilegeBean.getData().getWelfare().get(position).getW_title3());
            intent.putExtra("small_title", privilegeBean.getData().getWelfare().get(position).getW_title3());
            intent.putExtra("w_id", privilegeBean.getData().getWelfare().get(position).getW_id());
            context.startActivity(intent);
        }
    }

    /* 用于接收微信支付结果回调*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(NormalEventBean messageEvent) {
        String code = messageEvent.getMessageCode();
        if (code.equals(SataicCode.EVENTCODE.THREE)) {
            loadingView.show();
            TurnClassHttp.getPrivilegeDate(0, context, this);
            SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISNEWPENSO_Reftresh, false).commit();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void getChildView(View view) {
        TextView tv_errMsg = (TextView) view.findViewById(R.id.tv_errMsg);
        tv_errMsg.setText(baseBean.getMsg());
        TextView tv_gouOut = (TextView) view.findViewById(R.id.tv_gouOut);
        tv_gouOut.setOnClickListener(this);
    }

}

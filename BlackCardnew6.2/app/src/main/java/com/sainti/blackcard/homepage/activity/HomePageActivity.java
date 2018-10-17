package com.sainti.blackcard.homepage.activity;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.Message;
import com.hyphenate.helpdesk.callback.Callback;
import com.hyphenate.helpdesk.easeui.UIProvider;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.hyphenate.helpdesk.model.AgentInfo;
import com.hyphenate.helpdesk.model.MessageHelper;
import com.ppdai.loan.PPDLoanAgent;
import com.sainti.blackcard.R;
import com.sainti.blackcard.base.BaseBean;
import com.sainti.blackcard.base.BaseNoTitleActivity;
import com.sainti.blackcard.circle.fragment.CircleFragment;
import com.sainti.blackcard.homepage.bean.UpdateBean;
import com.sainti.blackcard.mblcakcard.fragment.BlackcardFragment;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.meventbus.NormalEventBean;
import com.sainti.blackcard.mhttp.constant.TurnClassHttp;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.minterface.TimerListener;
import com.sainti.blackcard.minterface.TimerListenerHasCode;
import com.sainti.blackcard.mtuils.AnimationUtil;
import com.sainti.blackcard.mtuils.CommontUtil;
import com.sainti.blackcard.mtuils.FileUtils;
import com.sainti.blackcard.mtuils.GsonSingle;
import com.sainti.blackcard.mtuils.MLog;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.TimeUtil;
import com.sainti.blackcard.mtuils.ToastUtils;
import com.sainti.blackcard.mwebview.CommenWebActivity;
import com.sainti.blackcard.mwebview.PPdWebActivity;
import com.sainti.blackcard.my.bean.PerSonBena;
import com.sainti.blackcard.my.fragment.Myfragment;
import com.sainti.blackcard.privilege.fragment.PrivilegeFragment;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushManager;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

;

public class HomePageActivity extends BaseNoTitleActivity implements View.OnClickListener, OnNetResultListener, TimerListener, TimerListenerHasCode {


    int curFragment = -1;
    private TextView btn_tequan, btn_quanzai, btn_heika, btn_mine;
    private RelativeLayout lay_update, layBeijing, new_personBeijing;
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
           /* Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE*/
    };
    private static final int PERMISSON_REQUESTCODE = 0;
    private TextView tv_update;
    private ImageView iv_update_btn, iv_goToset;
    private ImageView iv_notupdate, iv_toguanjia, iv_know, ren_icon;
    private boolean isXg;
    private boolean toblcek = false;
    private RelativeLayout ll_notupdate;
    private AnimationUtil animationUtil;
    private String imageUrl;
    private Uri uri1;
    private int a;
    private String token;
    private BaseBean baseBean;
    private FragmentManager manager;
    private ArrayList<Fragment> fragmentList;

    @Override
    public int setLayout() {
        return R.layout.activity_home_page;
    }

    @Override
    protected void initView() {
        manager = getSupportFragmentManager();
        btn_tequan = bindView(R.id.btn_tequan);
        btn_quanzai = bindView(R.id.btn_quanzai);
        btn_heika = bindView(R.id.btn_heika);
        btn_mine = bindView(R.id.btn_mine);
        PPDLoanAgent.getInstance().onLaunchCreate(this);
        EventBus.getDefault().register(this);
        lay_update = bindView(R.id.lay_update);
        tv_update = bindView(R.id.tv_update);
        iv_notupdate = bindView(R.id.iv_notupdate);
        layBeijing = bindView(R.id.lay_beijing); // 灰色背景
        iv_update_btn = bindView(R.id.iv_update_btn);
        iv_goToset = bindView(R.id.iv_goToset);
        new_personBeijing = bindView(R.id.new_personBeijing);
        iv_toguanjia = bindView(R.id.iv_toguanjia);
        iv_know = bindView(R.id.iv_know);
        ll_notupdate = bindView(R.id.ll_notupdate);
        SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.TYPEONE, "1").commit();
        SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.TYPETWO, "2").commit();
        SpUtil.initEditor(SpCodeName.SPNAME).putString(SpCodeName.TYPETHREE, "3").commit();
        SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.TYPEFORE, true).commit();
        SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISHAOUWKUANG, false).commit();
        animationUtil = new AnimationUtil(this);
        ren_icon = bindView(R.id.ren_icon);

    }

    @Override
    protected void initEvents() {
        btn_tequan.setOnClickListener(this);
        btn_quanzai.setOnClickListener(this);
        btn_heika.setOnClickListener(this);
        btn_mine.setOnClickListener(this);
        lay_update.setOnClickListener(this);
        tv_update.setOnClickListener(this);
        iv_notupdate.setOnClickListener(this);
        iv_update_btn.setOnClickListener(this);
        layBeijing.setOnClickListener(this);
        iv_goToset.setOnClickListener(this);
        new_personBeijing.setOnClickListener(this);
        iv_toguanjia.setOnClickListener(this);
        iv_know.setOnClickListener(this);
        ll_notupdate.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        PPDLoanAgent.getInstance().onLaunchResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void initData() {
        loginXinge();// 登陆新歌
        a = 1;
        loginHunxin();// 登录环信
        isXg = SpUtil.getBoolean(SpCodeName.SPNAME, SataicCode.ISXINGE, false);
        if (isXg) {
            getXgDate();// 当收到新歌推送时获取数据
        } else {
            toblcek = false;
            initFragment();// 初始化fragment
        }
        checkPermissions(needPermissions);
        CommontUtil.lateTime(1500, this);
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        String string = TimeUtil.getNowTime();
        map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
        map_ekv0.put("userlogintime", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, "") + "time:" + string);
        MobclickAgent.onEvent(this, "huoyueshijian", map_ekv0);// 友盟统计
        CommontUtil.lateTimeHasCode(4000, this, 3);

    }

    private void getXgDate() {
        String uids = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
        String tokens = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, "");
        String l_title = getIntent().getStringExtra("l_title");
        String l_title2 = getIntent().getStringExtra("l_title2");
        String l_haowuid = getIntent().getStringExtra("l_haowuid");
        String l_img = getIntent().getStringExtra("l_img");
        String l_link = getIntent().getStringExtra("l_link");
        String is_guanjia = getIntent().getStringExtra("is_guanjia");
        SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SataicCode.ISXINGE, false).commit();
        if (l_title.equals("blackcard")) {
            toblcek = true;
        } else if (l_title.equals("sign")) {
            Intent intent = new Intent(this, PPdWebActivity.class);
            intent.putExtra("title", "更多额度");
            intent.putExtra("wel_url", "http://www.qing-hei.com/mobile.php/App/Sign?uid=" + uids + "&token=" + tokens);
            intent.putExtra("code", "3");
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, CommenWebActivity.class);
            intent.putExtra("wel_url", l_link);
            intent.putExtra("title", l_title);
            intent.putExtra("img_url", l_img);
            intent.putExtra("name1", l_title2);
            intent.putExtra("w_id", l_haowuid);
            intent.putExtra("isguanjia", is_guanjia);
            startActivity(intent);
        }
        initFragment();// 初始化fragment

    }


    private void loginHunxin() {
        String uid = SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, "");
        ChatClient.getInstance().login(uid, uid, new Callback() {
            @Override
            public void onSuccess() {
                MLog.d("mian", "登陆成功");

            }

            @Override
            public void onError(int code, String error) {
                MLog.d("earro", "登陆失败code" + code + "错误信息" + error
                                              );
                if (a == 1) {
                    a = 2;
                    handler.sendEmptyMessage(1);
                }

            }

            @Override
            public void onProgress(int progress, String status) {
                MLog.d("mian", "正在登录");
            }
        });
    }

    Handler handlers = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            TurnClassHttp.imresetpwd(3, HomePageActivity.this, HomePageActivity.this);
        }
    };

    /**
     * @param
     * @since 2.5.0
     */
    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }


    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.btn_tequan:
                switchTab(0);
                //  fragmentTransaction.replace(R.id.fragmlayout_houmepage, privilegeFragment);
                btn_tequan.setTextColor(getResources().getColor(R.color.e_eight));
                btn_tequan.setSelected(true);
                btn_quanzai.setTextColor(getResources().getColor(R.color.white));
                btn_quanzai.setSelected(false);
                btn_heika.setTextColor(getResources().getColor(R.color.white));
                btn_heika.setSelected(false);
                btn_mine.setTextColor(getResources().getColor(R.color.white));
                btn_mine.setSelected(false);
                break;
            case R.id.btn_quanzai:
                Map<String, String> map_ekv0 = new HashMap<String, String>();
                map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
                MobclickAgent.onEvent(this, "shequhuoyuerenshu", map_ekv0);// 友盟统计
                switchTab(1);
                // fragmentTransaction.replace(R.id.fragmlayout_houmepage, circleFragment);
                btn_tequan.setTextColor(getResources().getColor(R.color.white));
                btn_tequan.setSelected(false);
                btn_quanzai.setTextColor(getResources().getColor(R.color.e_eight));
                btn_quanzai.setSelected(true);
                btn_heika.setTextColor(getResources().getColor(R.color.white));
                btn_heika.setSelected(false);
                btn_mine.setTextColor(getResources().getColor(R.color.white));
                btn_mine.setSelected(false);
                break;
            case R.id.btn_heika:
                switchTab(2);
                //  fragmentTransaction.replace(R.id.fragmlayout_houmepage, blackcardFragment);
                btn_tequan.setTextColor(getResources().getColor(R.color.white));
                btn_tequan.setSelected(false);
                btn_quanzai.setTextColor(getResources().getColor(R.color.white));
                btn_quanzai.setSelected(false);
                btn_heika.setTextColor(getResources().getColor(R.color.e_eight));
                btn_heika.setSelected(true);
                btn_mine.setTextColor(getResources().getColor(R.color.white));
                btn_mine.setSelected(false);
                break;
            case R.id.btn_mine:
                switchTab(3);
                //  fragmentTransaction.replace(R.id.fragmlayout_houmepage, myfragment);
                btn_tequan.setTextColor(getResources().getColor(R.color.white));
                btn_tequan.setSelected(false);
                btn_quanzai.setTextColor(getResources().getColor(R.color.white));
                btn_quanzai.setSelected(false);
                btn_heika.setTextColor(getResources().getColor(R.color.white));
                btn_heika.setSelected(false);
                btn_mine.setTextColor(getResources().getColor(R.color.e_eight));
                btn_mine.setSelected(true);
                break;
            case R.id.lay_update:
                return;
            case R.id.iv_update_btn:
                //    ToastUtils.show(this, "去更新");
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("http://www.qing-hei.com/mobile.php/index/appdownload");
                intent.setData(content_url);
                startActivity(intent);
                break;
            case R.id.lay_beijing:
                animationUtil.viewDisappear(layBeijing);
                CommontUtil.lateTimeHasCode(1000, this, 1);
                break;
            case R.id.iv_goToset:
                animationUtil.viewDisappear(layBeijing);

                CommontUtil.goToSet(this);
                isNewPer();
                break;
            case R.id.new_personBeijing:
                return;
            case R.id.iv_toguanjia:
                MobclickAgent.onEvent(this, "xinrenlibao");// 友盟统计
                Map<String, String> map_ekv0s = new HashMap<String, String>();
                map_ekv0s.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
                map_ekv0s.put("rukou", "xinrenlibaotankaung");
                MobclickAgent.onEvent(this, "zhaohuanguanjia", map_ekv0s);// 友盟统计
                // 我要订酒店
                if (ChatClient.getInstance().isLoggedInBefore()) {
                    //已经登录，可以直接进入会话界面
                    toChat("5");// 进入环信
                } else {
                    //未登录，需要登录后，再进入会话界面
                    ToastUtils.show(this, "登陆用户以失效！请重新登录账号");
                }

                animationUtil.viewDisappear(new_personBeijing);
                SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISHAOUWKUANG, true).commit();
                EventBus.getDefault().post(new NormalEventBean(SataicCode.EVENTCODE.THREE));
                break;
            case R.id.iv_know:
                MobclickAgent.onEvent(this, "xinrenlibao");// 友盟统计
                animationUtil.viewDisappear(new_personBeijing);
                SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISHAOUWKUANG, true).commit();
                EventBus.getDefault().post(new NormalEventBean(SataicCode.EVENTCODE.THREE));
                break;
            case R.id.ll_notupdate:
                animationUtil.viewDisappear(lay_update);
                if (CommontUtil.isNotificationEnabled(this)) {
                    // 判断是否开启通知
                    isNewPer();
                } else {
                    CommontUtil.lateTimeHasCode(1000, this, 2);
                }
                break;
        }
        //提交事务
        fragmentTransaction.commit();
    }

    private void toChat(String type) {
        //已经登录，可以直接进入会话界面
        Intent intents = new IntentBuilder(this)
                .setServiceIMNumber("test1") //获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“IM服务号”
                .build()
                .putExtra("type", type);
        startActivity(intents);
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(PrivilegeFragment.newInstance(""));
        fragmentList.add(CircleFragment.newInstance(""));
        fragmentList.add(BlackcardFragment.newInstance(""));
        fragmentList.add(Myfragment.newInstance(""));
        if (toblcek) {
            switchTab(2);
            btn_heika.setSelected(true);
            btn_tequan.setTextColor(getResources().getColor(R.color.white));
            btn_quanzai.setTextColor(getResources().getColor(R.color.white));
            btn_heika.setTextColor(getResources().getColor(R.color.e_eight));
            btn_mine.setTextColor(getResources().getColor(R.color.white));
        } else {
            switchTab(0);
            btn_tequan.setTextColor(getResources().getColor(R.color.e_eight));
            btn_tequan.setSelected(true);
            btn_quanzai.setTextColor(getResources().getColor(R.color.white));
            btn_heika.setTextColor(getResources().getColor(R.color.white));
            btn_mine.setTextColor(getResources().getColor(R.color.white));
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ChatClient.getInstance().logout(true, new Callback() {
            @Override
            public void onSuccess() {
                //ToastUtils.show(HomePageActivity.this, "退出聊天系统成功");

            }

            @Override
            public void onError(int code, String error) {
                //ToastUtils.show(HomePageActivity.this, "退出聊天系统" + error);
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }

    /* 用于接收微信支付结果回调*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(NormalEventBean messageEvent) {
        if (messageEvent.getMessageCode().equals(SataicCode.EXIT)) {
            finish();
        }
        if (messageEvent.getMessageCode().equals(SataicCode.EVENTCODE.ONE)) {
            ren_icon.setVisibility(View.INVISIBLE);
        }
        if (messageEvent.getMessageCode().equals(SataicCode.EVENTCODE.TWO)) {
            ren_icon.setVisibility(View.VISIBLE);
        }
    }

    // 保存点击的时间
    private long exitTime = 0;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtils.show(this, "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setUpdate() {
        String versionName = CommontUtil.getVersionName(this);
        Log.i("fdfsdfsdf", "version=" + versionName);
        TurnClassHttp.setUpdate(versionName, 1, this, this);
    }

    /**
     * 对保存的图片命名
     */
    private String imagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "youhutouxiang.gif";

    @Override
    public void onSuccessfulListener(String stringResult, int resultCode) {
        Gson gson = GsonSingle.getGson();
        switch (resultCode) {
            case 1:
                UpdateBean updateBean = gson.fromJson(stringResult, UpdateBean.class);
                if (updateBean.getData() != null && updateBean.getData().getType().equals("1") && updateBean.getData().getApp_url().length() > 0) {
                    MLog.d("updateBean", "updateBean--------------" + updateBean.getData() + "");

                    if (updateBean.getData().getIs_qiangzgx().equals("1")) {
                        ll_notupdate.setVisibility(View.INVISIBLE);
                        ll_notupdate.setClickable(false);
                    } else {
                        ll_notupdate.setVisibility(View.VISIBLE);
                        ll_notupdate.setClickable(true);
                    }
                    tv_update.setText(updateBean.getData().getUpdate_text());
                    animationUtil.viewAppear(lay_update);
                } else {
                    if (CommontUtil.isNotificationEnabled(this)) {
                        isNewPer();// 是是新人
                    } else {
                        animationUtil.viewAppear(layBeijing);
                    }
                }
                break;
            case 2:
                PerSonBena perSonBena = gson.fromJson(stringResult, PerSonBena.class);
                if (perSonBena != null && perSonBena.getResult().equals("1") && perSonBena.getData().getHead() != null) {
                    imageUrl = perSonBena.getData().getHead();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String path = FileUtils.getImagePath(imageUrl, HomePageActivity.this);
                            FileUtils.copyFile(path, imagePath);
                            File file = new File(imagePath);
                            uri1 = Uri.fromFile(file);
                            shezhiHuanxinTouxiang();// 设置环信头像
                        }
                    }).start();

                }
                break;
            case 3:
                baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult().equals("1")) {
                    // 修改成功
                    loginHunxin();// 登录环信
                }

                break;
            case 4:
                baseBean = gson.fromJson(stringResult, BaseBean.class);
                if (baseBean.getResult().equals("1")) {
                    // 修改成功
                    // ToastUtils.show(this, "dddddddddddd");
                } else {
                    // ToastUtils.show(this, baseBean.getMsg());
                }
                break;
        }
    }

    private void isNewPer() {
        // 判断是否开启通知
        boolean isTasnkuiang = SpUtil.getBoolean(SpCodeName.SPNAME, SpCodeName.ISNEWPENSO, false);// 判断是否是新人
        if (isTasnkuiang) {
            animationUtil.viewAppear(new_personBeijing);
            SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISNEWPENSO, false).commit();
        } else {
            SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SpCodeName.ISHAOUWKUANG, true).commit();
        }
    }

    @Override
    public void onFailureListener(String errMsg, int resultCode) {
        switch (resultCode) {
            case 1:
                break;
        }
    }

    private void loginXinge() {
        // XGPushConfig.enableDebug(this, true);
        XGPushManager.registerPush(this, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
//token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册成功，设备token为：" + data);
                token = data.toString();
                XGPushManager.bindAccount(getApplicationContext(), "XINGE");
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            TurnClassHttp.pushtoken_update(token, 4, HomePageActivity.this, HomePageActivity.this);

        }
    };

    @Override
    public void onTimerListener() {
        setUpdate();/*更新*/
    }

    @Override
    public void onTimerListener(int code) {
        switch (code) {
            case 1:
                isNewPer();
                break;
            case 2:
                animationUtil.viewAppear(layBeijing);

                break;
            case 3:
                TurnClassHttp.mine_data(2, this, this);
                break;
            case 4:

                break;
        }
    }

    private void shezhiHuanxinTouxiang() {
        UIProvider.getInstance().setUserProfileProvider(new UIProvider.UserProfileProvider() {
            @Override
            public void setNickAndAvatar(Context context, Message message, ImageView userAvatarView, TextView usernickView) {
                if (message.direct() == Message.Direct.RECEIVE) {
                    AgentInfo agentInfo = MessageHelper.getAgentInfo(message);
                    if (usernickView != null) {
                        usernickView.setText(message.getFrom());
                        if (agentInfo != null) {
                            if (!TextUtils.isEmpty(agentInfo.getNickname())) {
                                usernickView.setText(agentInfo.getNickname());
                            }
                        }
                    }
                    if (userAvatarView != null) {
                        if (agentInfo != null) {
                            if (!TextUtils.isEmpty(agentInfo.getAvatar())) {
                                String strUrl = agentInfo.getAvatar();
                              /*shezhi客服头像*/

                                if (!TextUtils.isEmpty(strUrl)) {
                                    if (!strUrl.startsWith("http")) {
                                        strUrl = "http:" + strUrl;
                                    }
                                    Glide.with(context).load(strUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(com.hyphenate.helpdesk.R.drawable.hd_default_avatar).into(userAvatarView);
                                    return;
                                }
                            }
                        }
                        userAvatarView.setImageResource(R.mipmap.logo); // 已经设置了用户头像

                    }

                }

                if (userAvatarView != null) {
                    if (message.direct() == Message.Direct.SEND) {
                        if (uri1 != null && !uri1.equals("")) {
                            userAvatarView.setImageURI(uri1);
                        } else {
                            userAvatarView.setImageResource(R.mipmap.icon_def); // 已经设置了用户头像
                        }

                    }
                }
            }
        });
    }

    private void switchTab(int position) {
        Fragment fragment = manager.findFragmentByTag("" + position);
        FragmentTransaction beginTransaction = manager.beginTransaction();
        if (fragment == null) {
            if (manager.findFragmentByTag("" + curFragment) != null) {
                beginTransaction.hide(fragmentList.get(curFragment));
            }
            beginTransaction.add(R.id.fragmlayout_houmepage, fragmentList.get(position), "" + position)
                    .show(fragmentList.get(position))
                    .commit();
        } else if (curFragment != position) {
            beginTransaction.hide(fragmentList.get(curFragment))
                    .show(fragmentList.get(position))
                    .commit();
        }
        curFragment = position;
    }
}

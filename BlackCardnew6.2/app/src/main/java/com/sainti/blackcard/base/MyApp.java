package com.sainti.blackcard.base;


import android.app.ActivityManager;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.support.v4.app.NotificationCompat;

import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.ChatManager;
import com.hyphenate.chat.Message;
import com.hyphenate.helpdesk.easeui.UIProvider;
import com.hyphenate.helpdesk.easeui.ui.BaseChatActivity;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.hyphenate.util.EasyUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.mob.MobSDK;
import com.ppdai.loan.PPDLoanAgent;
import com.sainti.blackcard.R;
import com.sainti.blackcard.commen.mconstant.SataicCode;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mtuils.MLog;
import com.sainti.blackcard.mtuils.SpUtil;
import com.sainti.blackcard.mtuils.TimeUtil;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by $ Chenguohui on 2017/11/13.
 */

public class MyApp extends Application {
    private static MyApp initance;
    private static Context mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initance = this;
        okGoInit();
        PPDLoanAgent.getInstance().initApplication(this);
        MobSDK.init(this);
        newhuanxin();/*初始化环信*/


        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);// 友盟初始化
        MobclickAgent.setScenarioType(mContext, MobclickAgent.EScenarioType.E_UM_NORMAL);
        //  UMConfigure.setLogEnabled(true);
        //   MobclickAgent.setDebugMode(true);//开启调试模式（如果不开启debug运行不会上传umeng统计）

    }

    private void newhuanxin() {
        ChatClient.Options options = new ChatClient.Options();
        options.setAppkey("qingnianheika#blackcard");//必填项，appkey获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“AppKey”
        options.setTenantId("15228");//必填项，tenantId获取地址：kefu.easemob.com，“管理员模式 > 设置 > 企业信息”页面的“租户ID”

        // Kefu SDK 初始化
        if (!ChatClient.getInstance().init(this, options)) {
            return;
        }
        // Kefu EaseUI的初始化
        UIProvider.getInstance().init(this);


        ChatClient.getInstance().getChat().addMessageListener(new ChatManager.MessageListener() {
            @Override
            public void onMessage(List<Message> list) {

                // 判断app是否在后台
                if (!EasyUtils.isAppRunningForeground(mContext)) {
                    notifi(list);
                } else {
                    if (isActivityTop(BaseChatActivity.class, mContext)) {


                    } else {
                        SpUtil.initEditor(SpCodeName.SPNAME).putBoolean(SataicCode.ISRENCOIN, true).commit();

                    }
                }
                //

                //收到普通消息
            }

            @Override
            public void onCmdMessage(List<Message> list) {
                //收到命令消息，命令消息不存数据库，一般用来作为系统通知，例如留言评论更新，
                //会话被客服接入，被转接，被关闭提醒
            }

            @Override
            public void onMessageStatusUpdate() {
                //消息的状态修改，一般可以用来刷新列表，显示最新的状态
                MLog.d("lllllllllllllllll", "shuxainmiemian");
            }

            @Override
            public void onMessageSent() {
                //发送消息后，会调用，可以在此刷新列表，显示最新的消息
            }
        });
    }

    private void notifi(List<Message> list) {

        String time = TimeUtil.ms2HMS((int) list.get(list.size() - 1).messageTime());
        String content = list.get(list.size() - 1).getBody().toString();
        content = content.substring(5, content.length() - 1);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        builder.setSmallIcon(R.mipmap.logo);        //设置图标
        builder.setContentTitle("管家来新消息了!");                    //设置标题
        builder.setContentText(content);                 //消息内容
        builder.setWhen(System.currentTimeMillis());         //发送时间
        builder.setDefaults(Notification.DEFAULT_ALL);      //设置默认的提示音，振动方式，灯光
        builder.setAutoCancel(true);
        //跳转活动
        Intent intent = new IntentBuilder(mContext)
                .setServiceIMNumber("test1") //获取地址：kefu.easemob.com，“管理员模式 > 渠道管理 > 手机APP”页面的关联的“IM服务号”
                .build();
        PendingIntent pi = PendingIntent.getActivities(mContext, 0, new Intent[]{intent}, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pi);
        //创建通知栏对象，显示通知信息
        Notification notification = builder.build();
        manager.notify(1, notification);  //打开程序后图标消失
    }

    public MyApp() {

    }

    public static MyApp getInstance() {
        if (initance == null) {
            initance = new MyApp();
        }
        return initance;
//        return new OkGoUtil();
    }

    public Context getmContext() {
        return mContext;
    }


    // 还需要写网络请求的初始化

    /**
     * OKGO初期化
     */
    private void okGoInit() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //全局的读取超时时间
            builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
            //全局的写入超时时间
            builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
            //全局的连接超时时间
            builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
            OkGo.getInstance().init(this)
                    .setOkHttpClient(builder.build())
                    .setRetryCount(3)
                    .setCacheMode(CacheMode.NO_CACHE);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
}

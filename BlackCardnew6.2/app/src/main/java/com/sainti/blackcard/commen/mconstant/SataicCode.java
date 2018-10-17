package com.sainti.blackcard.commen.mconstant;

/**
 * Created by Cgh on 2018/4/25.
 */
/* 静态常量类*/
public class SataicCode {
    public static final String WXAPP_ID = "wx343550af32846843";// 微信支付
    public static final String LOGIN_APPID = "wx343550af32846843";
    public static final String LOGIN_APPSECRET = "157400ab9a6feaa5120f393cc4e034e4"; //微信绑定的
    public static final String UMENGKEY = "58a67570c62dca56d9002372"; //友盟统计的key
    public final static String FINISHCHOICELOGIN = "login";// eventbus 通知结束启动界面
    public final static String EXIT = "exit";
    public final static String CARDPAYSUCESS = "cardpaysucess";// eventbus 通知已经开启了权限
    public final static String PAYSUCESSCOFFEE = "paySucessCoffee";// 平安支付coffee 支付成功
    public final static String PAYSUCESSHAOWU = "paySucessHaowu";//平安支付好物付成功
    public final static String PAYSUCESSGUANJIA = "paySucessGuanjia";// 平安支付管家支付成功
    public static final String ISRENCOIN = "isredcoin ";// 环信是都有新消息
    public static final String ISXINGE = "isxinge ";// 是否有信鸽推送消息
    public final static int GetDateCode = 0;
    public final static String appId_ppd = "de2bf7fd929e4a539bd0ce4d081116dd";
    public final static String serverPublicKey_ppd = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCkHwWoVrT0V+teSfas7450uFtqh1JMw0SNCoWA81R74/i3uznCzLKoFTMd9FXfTQHr1LSpUEznMBxDpsBN4vXVM+L8m8miigy7croH7GolmrCjxB5CPzjo6BUxVwgw7McNgKWBvAX11pjna/bLscC8YHJ3AVzjUZzB+WDzwXhefwIDAQAB";
    public final static String clientPrivateKey_ppd = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIQbz9eNBYbxKKLRqD6sC6Wvll5w8s9k+Du3tZkql80Dav5Z1/asGpqr8nXPmwgjnhD/g7GOGQ3DOG/bg7TV0oFuxMGdp17xURAc4Yzh9MMiXIZKlDP1oMUWkc4TkbVwbP1T3W5H/JYT/u7r24bkpBs5YCWcb5SBOJCcn+JgH1HBAgMBAAECgYEAg1EB8EkFTXU5515v1pw9l5xJ4+Zx9SVweivKH03vRqI3IE0+FrxMVaLFh8WtXRHKdSsHRN7ymHONDeMtr/s7Gm0biEc7oQ32D0zHh86jFl71vp26aUpvG40NWDSvv8Z8Xa0olWSbILA6Ijgt7MQK49V43xpDA2U8mw3ywjkjn2ECQQDP7s5mhZWNkw8qCUw8JA9XZCzrZiSwkyKgXDzpoxYIfSsunhC73+vTCx7IzPEuVeZ3IxzlEOPdYEBaDJ00BJ3bAkEAoqXUJMnKW6J5iKbQW7RTVTftJf111UIMvMi9Q221i5pLBM9vmpJJEMc5afL+hNLH4yrAJRydzsIF1g9K6P4XkwJAQFFuJuhLSm+i0S3vFutQk4e5HVeLIdNhcpkVfm+j8RxGrzTVmEe7epyF+Tbro5mOoGuMFI8U7PVI8pHUTrLe4QJAUts7iUorBids1kp0lmL0mNMWwmWuY2d0aa+xiuoWs/l0Ag1Sg2HGSv/SpcCeHDfskDM1EzLSyYNZycScmrjmdQJAP8LuWKVMfrTVjk96s6/2DT4yWlCSACozDOXnYCOWOKAMRQnPJg2ab0yF8+jY5/50ltJt4otrS6qdvNB44B3Efw==";
    public final static String REFRESH = "refreshConmment";
    public class EVENTCODE {
        public final static String ONE = "one";// 隐藏底部红点
        public final static String TWO = "two";// 显示红点
        public final static String THREE = "three";// 新人
    }

    public class PayCode {
        public static final int ZERO = 0; // 余额支付
        public static final int ONE = 1; // 支付宝支付
        public static final int TWO = 2; // 微信支付
        public static final int ALL_SHOW = 1;// 全部显示
        public static final int HIDE_YUE = 2;// 余额支付隐藏code
        public static final int HIDE_APIPAY = 3;//  支付宝支付隐藏code
        public static final int HIDE_WXPAY = 4;//  微信支付隐藏code
    }
}

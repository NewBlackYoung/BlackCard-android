package com.sainti.blackcard.mhttp.constant;

import android.content.Context;

import com.lzy.okgo.model.HttpParams;
import com.sainti.blackcard.commen.mconstant.SpCodeName;
import com.sainti.blackcard.mhttp.okgo.OkGoUtils;
import com.sainti.blackcard.mhttp.okgo.OnNetResultListener;
import com.sainti.blackcard.mtuils.SpUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;


public class TurnClassHttp {

    //好物列表
    public static void getFuLiList(String page, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.fuli_list;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("page", page);
        // map.put("action", action);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
        //HttpVolleyRequest.getInstance().post(url, map, listener);
    }

    //好物分类导航
    public static void getFuListFenLei(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.fulifenlei;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //好物分类列表
    public static void getHaoWuFenLei(String page, String action, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.fuli_list;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("page", page);
        map.put("action", action);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //启动页轮播图
    public static void getshanping(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.shanping;
        HttpParams map = new HttpParams();
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    public static void getPrivilegeDate(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.privilege;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //快速重置密码
    public static void setPassword(String name, String idcard, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.mima;
        HttpParams map = new HttpParams();
        map.put("name", name);
        map.put("idcard", idcard);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //获取登录验证码
    public static void getYanZhengMa(String phone, String action, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.yanzhengma;
        HttpParams map = new HttpParams();
        map.put("phone", phone);
        map.put("action", action);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //验证码登录
    public static void yanZhengMaLogin(String phone, String code, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.yanzhengmalogin;
        HttpParams map = new HttpParams();
        map.put("phone", phone);
        map.put("code", code);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //绑定人信息
    public static void login_bindinfo(String name, String nickname, String id_number, String phone, String sex, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.login_bindinfo;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("name", name);
        map.put("nickname", nickname);
        map.put("id_number", id_number);
        map.put("phone", phone);
        map.put("email", "");
        map.put("sex", sex);
        map.put("qq", "");
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //绑定人信息
    public static void reg_info(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.reg_info;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //绑定人信息
    public static void login(String cardid, String pwd, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.login;
        HttpParams map = new HttpParams();
        map.put("cardid", cardid);
        map.put("pwd", pwd);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //获取圈子列表
    public static void getNewFindV(String page, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.newfindv4;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("page", page);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //话题详情
    public static void topicDetail(String to_id, String page, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.topic_detail;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("to_id", to_id);
        map.put("page", page);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    public static void getGuangGao(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.guanggao;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //删除评论
    public static void deleteFind(String find_id, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.delete_find;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("find_id", find_id);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //评论详情
    public static void publishComment(String find_id, String comment, String replyid, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.publish_comment;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("find_id", find_id);
        map.put("comment", comment);
        map.put("replyid", replyid);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
        MobclickAgent.onEvent(context, "pinglunliang");// 友盟统计

    }

    //评论详情
    public static void findDetail(String find_id, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.find_detail;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("find_id", find_id);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //关注与取消关注
    public static void isFollow(String uid2, String action, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.isfollow;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("uid2", uid2);
        map.put("action", action);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //点赞和取消点赞
    public static void isPraise(String find_id, String action, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.praise;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("find_id", find_id);
        map.put("action", action);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
        if (action.equals("1")) {
            Map<String, String> map_ekv0 = new HashMap<String, String>();
            MobclickAgent.onEvent(context, "dianzanliang");// 友盟统计
        }
    }

    //我的圈子列表
    public static void myFindlist(String page, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.my_findlist;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("page", page);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //她的圈子列表
    public static void userFindlist(String user_uid, String page, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.user_findlist;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("user_uid", user_uid);
        map.put("page", page);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //粉丝列表
    public static void fansList(String user_uid, String page, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.fansList;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("user_uid", user_uid);
        map.put("page", page);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //关注人物列表
    public static void follow(String page, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.follow;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("page", page);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //互动消息和系统消息列表
    public static void getHuDong(String is_read, String page, String m_type, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.hudong_list;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("page", page);
        map.put("m_type", m_type);
        map.put("is_read", is_read);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    // 未读互动消息个数
    public static void unread(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.unread;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));

        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //发布朋友圈
    public static void fabu(String content, String images, String imageb, String location, String video_url, String image_an, String to_id, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.shangchuan;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("content", content);
        map.put("images", images);
        map.put("imageb", imageb);
        map.put("location", location);
        map.put("video_url", video_url);
        map.put("qx", image_an);
        map.put("to_id", to_id);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        MobclickAgent.onEvent(context, "fatieliang");// 友盟统计


    }

    //话题列表
    public static void getHuaTiList(String page, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.huatilist;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("page", page);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //获取获取毫无详情
    public static void getHaoWuXiangQing(String w_id, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.haowuxiangqing;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("w_id", w_id);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //获取收件人地址
    public static void getDiZhi(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.getdizhi;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //获取优惠券信息
    public static void getYouHuiJuan(String price, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.getyouhuijuan;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("price", price);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //毫无下单
    public static void haowuxiadan(String order_name, String wo_feiyong, String welfare_id, String kuaidi, String num, String weltype, String youhuiquan,
                                   String danjia, String kuaidifei, String wo_shoujianren, String shoujiandianhua, String wo_dizhi, String is_blackkey, String note, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.haowuxiadan;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("order_name", order_name);
        map.put("wo_feiyong", wo_feiyong);
        map.put("welfare_id", welfare_id);
        map.put("kuaidi", kuaidi);
        map.put("num", num);
        map.put("weltype", weltype);
        map.put("youhuiquan", youhuiquan);
        map.put("danjia", danjia);
        map.put("kuaidifei", kuaidifei);
        map.put("wo_shoujianren", wo_shoujianren);
        map.put("shoujiandianhua", shoujiandianhua);
        map.put("wo_dizhi", wo_dizhi);
        map.put("is_blackkey", is_blackkey);
        map.put("note", note);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
        map_ekv0.put("orderkind", "haowu");
        MobclickAgent.onEvent(context, "dingdanquerentijiao", map_ekv0);// 友盟统计
    }

    //修改地址
    public static void upDateDiZhi(String ad_province, String ad_city, String ad_area, String ad_address, String ad_user,
                                   String ad_tel, String ad_id, String ad_moren, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.updatadizhi;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("ad_province", ad_province);
        map.put("ad_city", ad_city);
        map.put("ad_area", ad_area);
        map.put("ad_address", ad_address);
        map.put("ad_user", ad_user);
        map.put("ad_tel", ad_tel);
        map.put("ad_id", ad_id);
        map.put("ad_moren", ad_moren);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //修改头像
    public static void head_update(String head, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.head_update;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("head", head);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //删除收件人地址
    public static void deleteDiZhi(String ad_id, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.deletedizhi;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("ad_id", ad_id);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //添加收件人地址
    public static void addDiZhi(String ad_province, String ad_city, String ad_area, String ad_address, String ad_user,
                                String ad_tel, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.adddizhi;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("ad_province", ad_province);
        map.put("ad_city", ad_city);
        map.put("ad_area", ad_area);
        map.put("ad_address", ad_address);
        map.put("ad_user", ad_user);
        map.put("ad_tel", ad_tel);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //其获平安支付回调
    public static void pingan_pay(String orderNo, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.pingan_pay;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("orderNo", orderNo);
        map.put("source", "android");
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //  好物下單請求下單單號
    public static void getHaoWuDanHao(String body, String subject, String out_trade_no, String total_amount,
                                      int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.alipay_apporder;
        HttpParams map = new HttpParams();
        map.put("body", body);
        map.put("subject", subject);
        map.put("out_trade_no", out_trade_no);
        map.put("total_amount", total_amount);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //获取coffee微信支付订单信息
    public static void getApporder(String total_fee, String out_trade_no, String body, String device_info, String timestamp, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.apporder;
        HttpParams map = new HttpParams();
        map.put("total_fee", total_fee);
        map.put("out_trade_no", out_trade_no);
        map.put("body", body);
        map.put("device_info", device_info);
        map.put("timestamp", timestamp);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
        map_ekv0.put("orderkind", "coffee");
        MobclickAgent.onEvent(context, "dingdanquerentijiao", map_ekv0);// 友盟统计
    }

    //平安测试接口
    public static void getPingAnCeShi(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.pingan;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //提现
    public static void getMoney(String tag, String money, String bank, String bank_card, String user_name, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.getMoney;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("tag", tag);
        map.put("money", money);
        map.put("bank", bank);
        map.put("bank_card", bank_card);
        map.put("user_name", user_name);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //提现
    public static void topup_record(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.topup_record;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //最新分享网址
    public static void getNewShare(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.newshare;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //获取好物购物车数据
    public static void getWelcartDate(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.welcart;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);

    }


    //  获取更多特权
    public static void putMoreTequan(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.moreTequan;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    // 好物添加购物车
    public static void addWelcart(String json, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.welcart_add;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("xw_json", json);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //将购物车数据提交到后台
    public static void getCartOrder(String xw_json, String xw_name, String xw_tel, String xw_address, String xw_youhuiquan, String is_blackkey, String note, String wo_feiyong, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.welcart_order;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("xw_json", xw_json);
        map.put("xw_name", xw_name);
        map.put("xw_tel", xw_tel);
        map.put("xw_address", xw_address);
        map.put("xw_youhuiquan", xw_youhuiquan);
        map.put("is_blackkey", is_blackkey);
        map.put("note", note);
        map.put("wo_feiyong", wo_feiyong);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
        map_ekv0.put("orderkind", "haowucard");
        MobclickAgent.onEvent(context, "dingdanquerentijiao", map_ekv0);// 友盟统计
    }

    //星巴克咖啡列表数据
    public static void getLeftCoffeeDate(String areaType, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.coffeeleft;
        HttpParams map = new HttpParams();
        map.put("method", "categorylist.get");
        map.put("areaType", areaType);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //星巴克咖啡
    public static void getRightffeeDate(String categoryId, int requestCode, Context context, OnNetResultListener listener) {

        String url = TurnClassUrl.coffeeleft;
        HttpParams map = new HttpParams();
        map.put("method", "item.getv2");
        map.put("categoryId", categoryId);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    public static void getAdreeDate(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.adree;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    public static void getAdreeFanWei(String city, String adress, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.coffeeleft;
        HttpParams map = new HttpParams();
        map.put("method", "address.checkv2");
        map.put("city", city);
        map.put("address", adress);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    public static void getOrderInfo(String xf_price, String xf_list, String fi_start_deliverytime,
                                    int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.orderInfo;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("xf_price", xf_price);
        map.put("xf_list", xf_list);
        map.put("fi_start_deliverytime", fi_start_deliverytime);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
        Map<String, String> map_ekv0 = new HashMap<String, String>();
        map_ekv0.put("kahao", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.KAHAO, ""));
        map_ekv0.put("orderkind", "coffee");
        MobclickAgent.onEvent(context, "dingdanquerentijiao", map_ekv0);// 友盟统计


    }

    // 提交支出成功的订单信息
    public static void putOrderInfo(String trades,
                                    int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.coffeeleft;
        HttpParams map = new HttpParams();
        map.put("method", "order.put");
        map.put("trades", trades);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    public static void AddAdreeFanWei(String xo_city, String xo_address,
                                      String xo_name, String xo_tel, String xo_province, String xo_area, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.addAdree;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("xo_city", xo_city);
        map.put("xo_address", xo_address);
        map.put("xo_name", xo_name);
        map.put("xo_tel", xo_tel);
        map.put("xo_province", xo_province);
        map.put("xo_area", xo_area);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    /*我的界面*/
    public static void mine(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.mine;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    /*个人资料*/
    public static void mine_data(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.mine_data;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    /*修改性别*/
    public static void sex_update(String sex, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.sex_update;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("sex", sex);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    /*修改昵称*/
    public static void nickname_update(String nickname, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.nickname_update;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("nickname", nickname);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //修改简介
    public static void setJianjie(String introinfo, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.jianjie;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("introinfo", introinfo);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);


    }

    //修改职业
    public static void setZhiYe(String business, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.zhiYe;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("business", business);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //修改爱好
    public static void setHobby(String hobby_array, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.sendHobby;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("hobby_array", hobby_array);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //获取爱好列表
    public static void getHobby(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.getHobby;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //修改地区
    public static void setLocation(String location, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.sendLocation;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("location", location);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //修改地区
    public static void phone_update(String phone, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.phone_update;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("phone", phone);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //修改12306
    public static void train_update(String idnum, String pass, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.train_update;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("idnum", idnum);
        map.put("pass", pass);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    /*  //获取openID
      public static void getOpenID(String url, int requestCode, Context context, OnNetResultListener listener) {
          HttpParams map = new HttpParams();
          OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
      }*/
    //获取openID
    public static void getOpenID(String url, int requestCode, Context context, OnNetResultListener listener) {
        HttpParams map = new HttpParams();
        OkGoUtils.getInstance().get(url, requestCode, context, listener);
    }

    //修改12306
    public static void pwd_update(String pwd_old, String pwd_new, String pwd_new2, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.pwd_update;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("pwd_old", pwd_old);
        map.put("pwd_new", pwd_new);
        map.put("pwd_new2", pwd_new2);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //修改12306
    public static void exit(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.exit;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //设置隐私
    public static void setYinSi(String age_qx, String birth_qx, String location_qx,
                                String business_qx, String hobby_qx, String action, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.setYinSi;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("age_qx", age_qx);
        map.put("birth_qx", birth_qx);
        map.put("location_qx", location_qx);
        map.put("business_qx", business_qx);
        map.put("hobby_qx", hobby_qx);
        map.put("action", action);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //黑卡页面
    public static void blackcardnew(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.blackcardnew;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //亲友绑定数据
    public static void getbangding(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.getbangding;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //亲友绑定
    public static void setBangding(String num, String name, String pass, String phone, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.setBangding;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("num", num);
        map.put("name", name);
        map.put("pass", pass);
        map.put("phone", phone);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //获取好物订单列表
    public static void haowuorder(String state, String page, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.haowuorder;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("state", state);
        map.put("page", page);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //获取管家订单列表
    public static void guanjiaorder(String state, String page, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.guanjiaorder;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("state", state);
        map.put("page", page);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //获取好物订单详情
    public static void haowuorderdetail(String id, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.haowuorderdetail;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("id", id);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //获取好物订单详情
    public static void delhaowuorder(String id, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.delhaowuorder;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("id", id);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //获取好物订单详情
    public static void guanjiaorderdetail(String id, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.guanjiaorderdetail;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("id", id);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //删除管家订单
    public static void delguanjiaorder(String id, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.delguanjiaorder;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("id", id);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //咖啡订单列表
    public static void myCoffeeOrder(String page, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.myCoffeeOrder;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("page", page);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //给后台传订单号
    public static void updateCOrder(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.updateCOrder;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //给后台传订单号
    public static void getPayCode(String payment, String order_sn, String timestamp, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.getPayCode;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("payment", payment);
        map.put("order_sn", order_sn);
        map.put("source", "android");
        map.put("timestamp", timestamp);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //给后台传订单号
    public static void delCoffeeOrder(String id, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.delCoffeeOrder;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("id", id);

        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //检查更新
    public static void setUpdate(String version, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.update;
        HttpParams map = new HttpParams();
        map.put("version", version);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //物流接口
    public static void queryKd(String id, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.queryKd;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("id", id);
        map.put("type", "haow");
        map.put("source", "android");
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //机票订单列表接口
    public static void airorder(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.airorder;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //物流接口
    public static void airorder_detail(String ao_id, String ao_orderno, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.airorder_detail;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("ao_id", ao_id);
        map.put("ao_orderno", ao_orderno);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //获取用户黑钥匙
    public static void getUserBlackkey(String total,int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.getUserBlackkey;
        HttpParams map = new HttpParams();
        map.put("total", total);
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //获取用户消费后获得多少黑钥匙
    public static void getBlackkey(String order_sn, String type, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.getBlackkey;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("order_sn", order_sn);
        map.put("type", type);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //打赏管家
    public static void reward(String butler_nick, String re_money, String re_dec, String device_info, String timestamp, String payment, String butler_sn, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.reward;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("butler_nick", butler_nick);
        map.put("re_money", re_money);
        map.put("re_dec", re_dec);
        map.put("device_info", device_info);
        map.put("timestamp", timestamp);
        map.put("payment", payment);
        map.put("butler_sn", butler_sn);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //投诉管家
    public static void complain(String manager_id, String complaint_id, String comment, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.complain;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("manager_id", manager_id);
        map.put("complaint_id", complaint_id);
        map.put("comment", comment);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //投诉理由
    public static void complaintlist(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.complaintlist;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //评价标签
    public static void tag(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.tag;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //评价标签
    public static void update_openid(String opinId, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.update_openid;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("openid", opinId);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //修改环信密码
    public static void imresetpwd(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.imresetpwd;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //更新环信token
    public static void pushtoken_update(String pushtoken, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.pushtoken_update;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("pushtoken", pushtoken);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //会籍升级
    public static void levelUp(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.levelUp;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //获取升级订单
    public static void levelUpOrder(String level_id, String price, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.levelUpOrder;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("level_id", level_id);
        map.put("price", price);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //升级支付
    public static void getPayData(String order_sn, String payment, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.getPayData;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("order_sn", order_sn);
        map.put("payment", payment);
        map.put("source", "android");
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }


    //打卡
    public static void signIn(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.signIn;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //打卡
    public static void doSignIn(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.doSignIn;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //驾照订单
    public static void myDriverOrder(String state, String page, String size, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.myDriverOrder;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("state", state);
        map.put("page", page);
        map.put("size", size);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //删除管家订单
    public static void delDriverOrder(String id, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.delDriverOrder;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("id", id);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //删除管家订单
    public static void getUerBonus(String gids, String amount, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.getUerBonus;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("gids", gids);
        map.put("amount", amount);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //获取好物订单详情
    public static void myDriverOrderDetail(String id, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.myDriverOrderDetail;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("id", id);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //个人中心优惠券
    public static void getMyBonus(String state, String page, String size, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.getMyBonus;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("state", state);
        map.put("page", page);
        map.put("size", size);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }

    //新的商品分类列表
    public static void welfareCate(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.welfareCate;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }
    //邀请分享
    public static void myShare(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.myShare;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }
    //我的邀请
    public static void myInvite(String page,String size,String state,int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.myInvite;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("page", page);
        map.put("size", size);
        map.put("state", state);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }
    //邀请好礼配送地址
    public static void inviteChange(String consignee,String mobile,String address,int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.inviteChange;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("consignee", consignee);
        map.put("mobile", mobile);
        map.put("address", address);
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }
    //获取默认收货地址
    public static void getDefaultAddress(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.getDefaultAddress;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }
    //物流接口
    public static void queryKdyao(String id, int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.queryKd;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        map.put("id", id);
        map.put("type", "invite");
        map.put("source", "android");
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }
    //我的任务
    public static void myTask(int requestCode, Context context, OnNetResultListener listener) {
        String url = TurnClassUrl.myTask;
        HttpParams map = new HttpParams();
        map.put("uid", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.UID, ""));
        map.put("token", SpUtil.getString(SpCodeName.SPNAME, SpCodeName.TOKEN, ""));
        OkGoUtils.getInstance().post(url, requestCode, map, context, listener);
    }
}

package com.sainti.blackcard.my.invitingcourtesy.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/7/31.
 */

public class ShairBean {


    /**
     * result : 1
     * msg :
     * data : {"url":"http://www.qing-hei.com/mobile.php/Share/invite?blackcard=18518318345&code=df4bfbb8c1ea4bd7c0ca906ae6e55d99","goods":{"goods_name":"青黑专属定制银戒","goods_desc":"俱乐部荣誉标志，知名独立设计师倾心制作。\n【钥匙】灵感源于开启精英世界大门，斑痕氧化工艺也留下您对生活的理解。仅可通过好友邀请获得。","goods_spec":[{"name":"尺寸","value":["钥匙柄 宽约2cm","钥匙齿 约8cm","初始尺寸约15号（周长55mm）","可调节范围12号-60号（周长52mm-60mm）极大或极小尺寸，戒身形态稍有变化"]},{"name":"材质","value":["925银"]}]},"rule":["点击下面\u201c邀请好友\u201d分享专属邀请图片给您的朋友，好友识别图片内的二维码后在注册网址内开通青黑会员成功，您将获得黑钥匙奖励，邀请两名朋友开通会员成功后还能换取青黑定制限量版纯银戒指一枚。","每邀请一名用户加入青黑开通会员可获赠黑钥匙3把，价值30元。可用于\u201c专享好物\u201d消费抵扣费用，也可以在黑卡\u201c兑换商城\u201d内免费兑换礼品和服务。","邀请两名用户加入青黑开通会员后，可在本页面内点击\u201c查看邀请\u201d，在点击\u201c兑换邀请礼\u201d填写地址并进行申请，我们会在5个工作日内确定您的物流信息和邀请资料，免费邮寄给您价值人民币488元的青黑定制限量版925纯银戒指一枚。"],"user":{"nick":"yolin","avatar":"http://s.qing-hei.com/Public/Uploads/20180616/5b2495066dd2c.jpg"},"qrcodelogo":"http://s.qing-hei.com/Public/xin/images/qrcode_logo.jpg"}
     */

    private String result;
    private String msg;
    private DataBean data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * url : http://www.qing-hei.com/mobile.php/Share/invite?blackcard=18518318345&code=df4bfbb8c1ea4bd7c0ca906ae6e55d99
         * goods : {"goods_name":"青黑专属定制银戒","goods_desc":"俱乐部荣誉标志，知名独立设计师倾心制作。\n【钥匙】灵感源于开启精英世界大门，斑痕氧化工艺也留下您对生活的理解。仅可通过好友邀请获得。","goods_spec":[{"name":"尺寸","value":["钥匙柄 宽约2cm","钥匙齿 约8cm","初始尺寸约15号（周长55mm）","可调节范围12号-60号（周长52mm-60mm）极大或极小尺寸，戒身形态稍有变化"]},{"name":"材质","value":["925银"]}]}
         * rule : ["点击下面\u201c邀请好友\u201d分享专属邀请图片给您的朋友，好友识别图片内的二维码后在注册网址内开通青黑会员成功，您将获得黑钥匙奖励，邀请两名朋友开通会员成功后还能换取青黑定制限量版纯银戒指一枚。","每邀请一名用户加入青黑开通会员可获赠黑钥匙3把，价值30元。可用于\u201c专享好物\u201d消费抵扣费用，也可以在黑卡\u201c兑换商城\u201d内免费兑换礼品和服务。","邀请两名用户加入青黑开通会员后，可在本页面内点击\u201c查看邀请\u201d，在点击\u201c兑换邀请礼\u201d填写地址并进行申请，我们会在5个工作日内确定您的物流信息和邀请资料，免费邮寄给您价值人民币488元的青黑定制限量版925纯银戒指一枚。"]
         * user : {"nick":"yolin","avatar":"http://s.qing-hei.com/Public/Uploads/20180616/5b2495066dd2c.jpg"}
         * qrcodelogo : http://s.qing-hei.com/Public/xin/images/qrcode_logo.jpg
         */

        private String url;
        private GoodsBean goods;
        private UserBean user;
        private String qrcodelogo;
        private List<String> rule;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getQrcodelogo() {
            return qrcodelogo;
        }

        public void setQrcodelogo(String qrcodelogo) {
            this.qrcodelogo = qrcodelogo;
        }

        public List<String> getRule() {
            return rule;
        }

        public void setRule(List<String> rule) {
            this.rule = rule;
        }

        public static class GoodsBean {
            /**
             * goods_name : 青黑专属定制银戒
             * goods_desc : 俱乐部荣誉标志，知名独立设计师倾心制作。
             【钥匙】灵感源于开启精英世界大门，斑痕氧化工艺也留下您对生活的理解。仅可通过好友邀请获得。
             * goods_spec : [{"name":"尺寸","value":["钥匙柄 宽约2cm","钥匙齿 约8cm","初始尺寸约15号（周长55mm）","可调节范围12号-60号（周长52mm-60mm）极大或极小尺寸，戒身形态稍有变化"]},{"name":"材质","value":["925银"]}]
             */

            private String goods_name;
            private String goods_desc;
            private List<GoodsSpecBean> goods_spec;

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_desc() {
                return goods_desc;
            }

            public void setGoods_desc(String goods_desc) {
                this.goods_desc = goods_desc;
            }

            public List<GoodsSpecBean> getGoods_spec() {
                return goods_spec;
            }

            public void setGoods_spec(List<GoodsSpecBean> goods_spec) {
                this.goods_spec = goods_spec;
            }

            public static class GoodsSpecBean {
                /**
                 * name : 尺寸
                 * value : ["钥匙柄 宽约2cm","钥匙齿 约8cm","初始尺寸约15号（周长55mm）","可调节范围12号-60号（周长52mm-60mm）极大或极小尺寸，戒身形态稍有变化"]
                 */

                private String name;
                private List<String> value;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<String> getValue() {
                    return value;
                }

                public void setValue(List<String> value) {
                    this.value = value;
                }
            }
        }

        public static class UserBean {
            /**
             * nick : yolin
             * avatar : http://s.qing-hei.com/Public/Uploads/20180616/5b2495066dd2c.jpg
             */

            private String nick;
            private String avatar;

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }
    }
}

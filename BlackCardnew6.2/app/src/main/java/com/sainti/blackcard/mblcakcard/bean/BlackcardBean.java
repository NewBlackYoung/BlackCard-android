package com.sainti.blackcard.mblcakcard.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/5/10.
 */

public class BlackcardBean {


    /**
     * result : 1
     * msg :
     * data : {"base":{"card_sn":"15724377399","card_sn_split":"1572 4377 399","user_piny":"CHEN GUO HUI","user_type":null,"user_type_name":"精英会籍","user_mobile":"15724377399"},"balance":{"balance":"0.00","zuijsy":"0.00","leijsy":"0.00"},"cbank":[{"id":"10","name":"天涯明月刀","url":"https://creditcardapp.bankcomm.com/applynew/front/apply/track/record.html?trackCode=A0328162356649&cardId=129","logo":"http://s.qing-hei.com/Public/Uploads/20180301/5a9791e10d79e.png","des1":"新户办卡赠200元礼包","des2":"国风情结炫酷多卡面","des3":"全额度取现超低手续费"},{"id":"11","name":"Y-power","url":"https://creditcardapp.bankcomm.com/applynew/front/apply/track/record.html?trackCode=A0328162356649&cardId=2","logo":"http://s.qing-hei.com/Public/Uploads/20180301/5a979213ee847.png","des1":"新户100元刷卡金轻松享","des2":"年轻首选，取现百分百","des3":"分期消费门槛低至500元"},{"id":"12","name":"VISA金卡","url":"https://creditcardapp.bankcomm.com/applynew/front/apply/track/record.html?trackCode=A0328162356649&cardId=17","logo":"http://s.qing-hei.com/Public/Uploads/20180301/5a97a9e0124a0.png","des1":"新户100元刷卡金轻松享","des2":"超市、加油全年返5%","des3":"免息还款期最长达56天"}],"xianjin":[{"id":"10","name":"玖富万卡","subname":"额度500-20万","description":"有信用卡额度最高可达20万","url":"https://test.doraemoney.com/wkCubeNew/#/register?proId=hxd2a4f58e7ce5b82143b41abe0041d89397"},{"id":"6","name":"拍拍贷","subname":"青年黑卡持卡人专享","description":"更高额度更快审批更低利息","url":""},{"id":"7","name":"金牛贷","subname":"人人可贷，极速下款","description":"仅用身份证，最高50万额度等你拿。","url":"http://click.xuezhionline.com/union/KeRmjH3XSr6MnMIRkI9XqA%3D%3D"},{"id":"2","name":"小赢卡贷","subname":"6万3分钟极速放款","description":"即充即到账，想花尽兴花","url":"http://cardloan.xiaoying.com/kadai/index?source=100009608"}]}
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
         * base : {"card_sn":"15724377399","card_sn_split":"1572 4377 399","user_piny":"CHEN GUO HUI","user_type":null,"user_type_name":"精英会籍","user_mobile":"15724377399"}
         * balance : {"balance":"0.00","zuijsy":"0.00","leijsy":"0.00"}
         * cbank : [{"id":"10","name":"天涯明月刀","url":"https://creditcardapp.bankcomm.com/applynew/front/apply/track/record.html?trackCode=A0328162356649&cardId=129","logo":"http://s.qing-hei.com/Public/Uploads/20180301/5a9791e10d79e.png","des1":"新户办卡赠200元礼包","des2":"国风情结炫酷多卡面","des3":"全额度取现超低手续费"},{"id":"11","name":"Y-power","url":"https://creditcardapp.bankcomm.com/applynew/front/apply/track/record.html?trackCode=A0328162356649&cardId=2","logo":"http://s.qing-hei.com/Public/Uploads/20180301/5a979213ee847.png","des1":"新户100元刷卡金轻松享","des2":"年轻首选，取现百分百","des3":"分期消费门槛低至500元"},{"id":"12","name":"VISA金卡","url":"https://creditcardapp.bankcomm.com/applynew/front/apply/track/record.html?trackCode=A0328162356649&cardId=17","logo":"http://s.qing-hei.com/Public/Uploads/20180301/5a97a9e0124a0.png","des1":"新户100元刷卡金轻松享","des2":"超市、加油全年返5%","des3":"免息还款期最长达56天"}]
         * xianjin : [{"id":"10","name":"玖富万卡","subname":"额度500-20万","description":"有信用卡额度最高可达20万","url":"https://test.doraemoney.com/wkCubeNew/#/register?proId=hxd2a4f58e7ce5b82143b41abe0041d89397"},{"id":"6","name":"拍拍贷","subname":"青年黑卡持卡人专享","description":"更高额度更快审批更低利息","url":""},{"id":"7","name":"金牛贷","subname":"人人可贷，极速下款","description":"仅用身份证，最高50万额度等你拿。","url":"http://click.xuezhionline.com/union/KeRmjH3XSr6MnMIRkI9XqA%3D%3D"},{"id":"2","name":"小赢卡贷","subname":"6万3分钟极速放款","description":"即充即到账，想花尽兴花","url":"http://cardloan.xiaoying.com/kadai/index?source=100009608"}]
         */

        private BaseBean base;
        private BalanceBean balance;
        private List<CbankBean> cbank;
        private List<XianjinBean> xianjin;

        public BaseBean getBase() {
            return base;
        }

        public void setBase(BaseBean base) {
            this.base = base;
        }

        public BalanceBean getBalance() {
            return balance;
        }

        public void setBalance(BalanceBean balance) {
            this.balance = balance;
        }

        public List<CbankBean> getCbank() {
            return cbank;
        }

        public void setCbank(List<CbankBean> cbank) {
            this.cbank = cbank;
        }

        public List<XianjinBean> getXianjin() {
            return xianjin;
        }

        public void setXianjin(List<XianjinBean> xianjin) {
            this.xianjin = xianjin;
        }

        public static class BaseBean {
            /**
             * card_sn : 15724377399
             * card_sn_split : 1572 4377 399
             * user_piny : CHEN GUO HUI
             * user_type : null
             * user_type_name : 精英会籍
             * user_mobile : 15724377399
             */

            private String card_sn;
            private String card_sn_split;
            private String user_piny;
            private Object user_type;
            private String user_type_name;
            private String user_mobile;

            public String getCard_sn() {
                return card_sn;
            }

            public void setCard_sn(String card_sn) {
                this.card_sn = card_sn;
            }

            public String getCard_sn_split() {
                return card_sn_split;
            }

            public void setCard_sn_split(String card_sn_split) {
                this.card_sn_split = card_sn_split;
            }

            public String getUser_piny() {
                return user_piny;
            }

            public void setUser_piny(String user_piny) {
                this.user_piny = user_piny;
            }

            public Object getUser_type() {
                return user_type;
            }

            public void setUser_type(Object user_type) {
                this.user_type = user_type;
            }

            public String getUser_type_name() {
                return user_type_name;
            }

            public void setUser_type_name(String user_type_name) {
                this.user_type_name = user_type_name;
            }

            public String getUser_mobile() {
                return user_mobile;
            }

            public void setUser_mobile(String user_mobile) {
                this.user_mobile = user_mobile;
            }
        }

        public static class BalanceBean {
            /**
             * balance : 0.00
             * zuijsy : 0.00
             * leijsy : 0.00
             */

            private String balance;
            private String zuijsy;
            private String leijsy;

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getZuijsy() {
                return zuijsy;
            }

            public void setZuijsy(String zuijsy) {
                this.zuijsy = zuijsy;
            }

            public String getLeijsy() {
                return leijsy;
            }

            public void setLeijsy(String leijsy) {
                this.leijsy = leijsy;
            }
        }

        public static class CbankBean {
            /**
             * id : 10
             * name : 天涯明月刀
             * url : https://creditcardapp.bankcomm.com/applynew/front/apply/track/record.html?trackCode=A0328162356649&cardId=129
             * logo : http://s.qing-hei.com/Public/Uploads/20180301/5a9791e10d79e.png
             * des1 : 新户办卡赠200元礼包
             * des2 : 国风情结炫酷多卡面
             * des3 : 全额度取现超低手续费
             */

            private String id;
            private String name;
            private String url;
            private String logo;
            private String des1;
            private String des2;
            private String des3;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getDes1() {
                return des1;
            }

            public void setDes1(String des1) {
                this.des1 = des1;
            }

            public String getDes2() {
                return des2;
            }

            public void setDes2(String des2) {
                this.des2 = des2;
            }

            public String getDes3() {
                return des3;
            }

            public void setDes3(String des3) {
                this.des3 = des3;
            }
        }

        public static class XianjinBean {
            /**
             * id : 10
             * name : 玖富万卡
             * subname : 额度500-20万
             * description : 有信用卡额度最高可达20万
             * url : https://test.doraemoney.com/wkCubeNew/#/register?proId=hxd2a4f58e7ce5b82143b41abe0041d89397
             */

            private String id;
            private String name;
            private String subname;
            private String description;
            private String url;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSubname() {
                return subname;
            }

            public void setSubname(String subname) {
                this.subname = subname;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}

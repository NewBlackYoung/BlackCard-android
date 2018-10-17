package com.sainti.blackcard.goodthings.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by YuZhenpeng on 2018/4/8.
 */

public class WxPayBean {

    /**
     * result : 1
     * msg :
     * data : {"appid":"wx343550af32846843","noncestr":"vmlXJLmr6ZwM73R2","package":"Sign=WXPay","partnerid":"1500381202","timestamp":"1523172991854","prepayid":"wx081536501313689dd655a3411578395020","sign":"34330EAF85970FAE8080EBEDEE031AF2","result_code":"SUCCESS","trade_type":"APP","err_code_des":""}
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
         * appid : wx343550af32846843
         * noncestr : vmlXJLmr6ZwM73R2
         * package : Sign=WXPay
         * partnerid : 1500381202
         * timestamp : 1523172991854
         * prepayid : wx081536501313689dd655a3411578395020
         * sign : 34330EAF85970FAE8080EBEDEE031AF2
         * result_code : SUCCESS
         * trade_type : APP
         * err_code_des :
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String timestamp;
        private String prepayid;
        private String sign;
        private String result_code;
        private String trade_type;
        private String err_code_des;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getResult_code() {
            return result_code;
        }

        public void setResult_code(String result_code) {
            this.result_code = result_code;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }

        public String getErr_code_des() {
            return err_code_des;
        }

        public void setErr_code_des(String err_code_des) {
            this.err_code_des = err_code_des;
        }
    }
}

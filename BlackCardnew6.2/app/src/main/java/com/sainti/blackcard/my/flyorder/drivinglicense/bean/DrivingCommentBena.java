package com.sainti.blackcard.my.flyorder.drivinglicense.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/7/3.
 */

public class DrivingCommentBena {


    /**
     * result : 1
     * msg :
     * data : [{"order_id":"1146","order_sn":"QHCG5187986812376","order_name":"驾照订单(普通配送)","order_amount":"22.00","order_time":"2018-05-18 13:19","is_pay":"1","is_del":"0","state":"0","status":"待付款"},{"order_id":"1145","order_sn":"QHCG5187904731130","order_name":"驾照订单(普通配送)","order_amount":"22.00","order_time":"2018-05-18 13:19","is_pay":"1","is_del":"0","state":"0","status":"待付款"},{"order_id":"701","order_sn":"QHCG3280331306588","order_name":"","order_amount":"60.00","order_time":"1970-01-01 08:00","is_pay":"1","is_del":"0","state":"0","status":"待付款"},{"order_id":"702","order_sn":"QHCG3280395878807","order_name":"","order_amount":"60.00","order_time":"1970-01-01 08:00","is_pay":"1","is_del":"0","state":"0","status":"待付款"},{"order_id":"704","order_sn":"QHCG3289302523315","order_name":"","order_amount":"60.00","order_time":"1970-01-01 08:00","is_pay":"1","is_del":"0","state":"0","status":"待付款"},{"order_id":"705","order_sn":"QHCG3280562873378","order_name":"","order_amount":"60.00","order_time":"1970-01-01 08:00","is_pay":"1","is_del":"0","state":"0","status":"待付款"},{"order_id":"706","order_sn":"QHCG3280709038331","order_name":"","order_amount":"60.00","order_time":"1970-01-01 08:00","is_pay":"1","is_del":"0","state":"0","status":"待付款"},{"order_id":"707","order_sn":"QHCG3282822221004","order_name":"","order_amount":"60.00","order_time":"1970-01-01 08:00","is_pay":"1","is_del":"0","state":"0","status":"待付款"},{"order_id":"708","order_sn":"QHCG3284529506492","order_name":"","order_amount":"60.00","order_time":"1970-01-01 08:00","is_pay":"1","is_del":"0","state":"0","status":"待付款"},{"order_id":"709","order_sn":"QHCG3286568521406","order_name":"","order_amount":"60.00","order_time":"1970-01-01 08:00","is_pay":"1","is_del":"0","state":"0","status":"待付款"}]
     */

    private String result;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * order_id : 1146
         * order_sn : QHCG5187986812376
         * order_name : 驾照订单(普通配送)
         * order_amount : 22.00
         * order_time : 2018-05-18 13:19
         * is_pay : 1
         * is_del : 0
         * state : 0
         * status : 待付款
         */

        private String order_id;
        private String order_sn;
        private String order_name;
        private String order_amount;
        private String order_time;
        private String is_pay;
        private String is_del;
        private String state;
        private String status;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getOrder_name() {
            return order_name;
        }

        public void setOrder_name(String order_name) {
            this.order_name = order_name;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getOrder_time() {
            return order_time;
        }

        public void setOrder_time(String order_time) {
            this.order_time = order_time;
        }

        public String getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(String is_pay) {
            this.is_pay = is_pay;
        }

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}

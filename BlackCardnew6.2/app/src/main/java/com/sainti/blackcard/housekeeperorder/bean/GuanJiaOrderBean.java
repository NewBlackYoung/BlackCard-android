package com.sainti.blackcard.housekeeperorder.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/5/10.
 */

public class GuanJiaOrderBean {

    /**
     * result : 1
     * msg :
     * data : [{"order_id":"51645","order_sn":"QHCC3228748816249","product_name":"测试","product_price":"0.00","youhq_money":"0.00","order_amount":"0.01","add_time":"2018-03-22 15:14","tag":"1","state":"1","is_cancel":"0","is_pay":"1","status":"未付款","is_del":0},{"order_id":"51582","order_sn":"QHCC3208850814855","product_name":"在线担保待支付测试订单","product_price":"0.01","youhq_money":"0.00","order_amount":"0.01","add_time":"2018-03-20 12:08","tag":"1","state":"1","is_cancel":"0","is_pay":"1","status":"未付款","is_del":0}]
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
         * order_id : 51645
         * order_sn : QHCC3228748816249
         * product_name : 测试
         * product_price : 0.00
         * youhq_money : 0.00
         * order_amount : 0.01
         * add_time : 2018-03-22 15:14
         * tag : 1
         * state : 1
         * is_cancel : 0
         * is_pay : 1
         * status : 未付款
         * is_del : 0
         */

        private String order_id;
        private String order_sn;
        private String product_name;
        private String product_price;
        private String youhq_money;
        private String order_amount;
        private String add_time;
        private String tag;
        private String state;
        private String is_cancel;
        private String is_pay;
        private String status;
        private int is_del;

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

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getProduct_price() {
            return product_price;
        }

        public void setProduct_price(String product_price) {
            this.product_price = product_price;
        }

        public String getYouhq_money() {
            return youhq_money;
        }

        public void setYouhq_money(String youhq_money) {
            this.youhq_money = youhq_money;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getIs_cancel() {
            return is_cancel;
        }

        public void setIs_cancel(String is_cancel) {
            this.is_cancel = is_cancel;
        }

        public String getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(String is_pay) {
            this.is_pay = is_pay;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getIs_del() {
            return is_del;
        }

        public void setIs_del(int is_del) {
            this.is_del = is_del;
        }
    }
}

package com.sainti.blackcard.housekeeperorder.bean;

/**
 * Created by YuZhenpeng on 2018/5/11.
 */

public class HousekeeperDetailBean {

    /**
     * result : 1
     * msg :
     * data : {"order_id":"52807","order_sn":"QHCC5118615707003","product_type":"1","product_name":"测试","product_price":"0.01","youhq_money":"0.00","order_amount":"0.01","payment":"4","add_time":"2018-05-11 15:14","tag":"1","state":"1","creater_user":"252","is_cancel":"1","product_type_name":"酒店","admin_sn":"","admin_nick":"","admin_name":"杨林","is_pay":0,"status":"已失效","is_del":1,"payment_name":"在线预付","pay_amount":"0.01"}
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
         * order_id : 52807
         * order_sn : QHCC5118615707003
         * product_type : 1
         * product_name : 测试
         * product_price : 0.01
         * youhq_money : 0.00
         * order_amount : 0.01
         * payment : 4
         * add_time : 2018-05-11 15:14
         * tag : 1
         * state : 1
         * creater_user : 252
         * is_cancel : 1
         * product_type_name : 酒店
         * admin_sn :
         * admin_nick :
         * admin_name : 杨林
         * is_pay : 0
         * status : 已失效
         * is_del : 1
         * payment_name : 在线预付
         * pay_amount : 0.01
         */

        private String order_id;
        private String order_sn;
        private String product_type;
        private String product_name;
        private String product_price;
        private String youhq_money;
        private String order_amount;
        private String payment;
        private String add_time;
        private String tag;
        private String state;
        private String creater_user;
        private String is_cancel;
        private String product_type_name;
        private String admin_sn;
        private String admin_nick;
        private String admin_name;
        private int is_pay;
        private String status;
        private int is_del;
        private String payment_name;
        private String pay_amount;

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

        public String getProduct_type() {
            return product_type;
        }

        public void setProduct_type(String product_type) {
            this.product_type = product_type;
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

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
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

        public String getCreater_user() {
            return creater_user;
        }

        public void setCreater_user(String creater_user) {
            this.creater_user = creater_user;
        }

        public String getIs_cancel() {
            return is_cancel;
        }

        public void setIs_cancel(String is_cancel) {
            this.is_cancel = is_cancel;
        }

        public String getProduct_type_name() {
            return product_type_name;
        }

        public void setProduct_type_name(String product_type_name) {
            this.product_type_name = product_type_name;
        }

        public String getAdmin_sn() {
            return admin_sn;
        }

        public void setAdmin_sn(String admin_sn) {
            this.admin_sn = admin_sn;
        }

        public String getAdmin_nick() {
            return admin_nick;
        }

        public void setAdmin_nick(String admin_nick) {
            this.admin_nick = admin_nick;
        }

        public String getAdmin_name() {
            return admin_name;
        }

        public void setAdmin_name(String admin_name) {
            this.admin_name = admin_name;
        }

        public int getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(int is_pay) {
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

        public String getPayment_name() {
            return payment_name;
        }

        public void setPayment_name(String payment_name) {
            this.payment_name = payment_name;
        }

        public String getPay_amount() {
            return pay_amount;
        }

        public void setPay_amount(String pay_amount) {
            this.pay_amount = pay_amount;
        }
    }
}

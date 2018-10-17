package com.sainti.blackcard.my.flyorder.drivinglicense.bean;

/**
 * Created by YuZhenpeng on 2018/7/4.
 */

public class DrDetailBean {

    /**
     * result : 1
     * msg :
     * data : {"order_id":"1319","order_sn":"QHCG7036798229937","order_name":"驾照订单(普通配送)","order_amount":"22.00","order_time":"2018-07-03 18:27","order_status":"5","consignee":"陈国辉","phone":"15724377399","address":"哈哈哈哈","is_pay":"0","is_del":"0","state":"5","status":"退款中","payment_name":"","pay_amount":"22.00"}
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
         * order_id : 1319
         * order_sn : QHCG7036798229937
         * order_name : 驾照订单(普通配送)
         * order_amount : 22.00
         * order_time : 2018-07-03 18:27
         * order_status : 5
         * consignee : 陈国辉
         * phone : 15724377399
         * address : 哈哈哈哈
         * is_pay : 0
         * is_del : 0
         * state : 5
         * status : 退款中
         * payment_name :
         * pay_amount : 22.00
         */

        private String order_id;
        private String order_sn;
        private String order_name;
        private String order_amount;
        private String order_time;
        private String order_status;
        private String consignee;
        private String phone;
        private String address;
        private String is_pay;
        private String is_del;
        private String state;
        private String status;
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

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

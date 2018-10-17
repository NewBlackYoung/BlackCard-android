package com.sainti.blackcard.goodthings.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/5/18.
 */

public class GetKeyCountBean {

    /**
     * result : 1
     * msg :
     * blackkey : 0.00
     * order : [{"order_id":"32683","order_sn":"QHCF5184593422491","product_id":"345","product_price":"136.00","order_amount":"276.40","buy_number":"3","type_id":"0","pay_sn":"","wo_status":"0"}]
     */

    private String result;
    private String msg;
    private String blackkey;
    private List<OrderBean> order;

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

    public String getBlackkey() {
        return blackkey;
    }

    public void setBlackkey(String blackkey) {
        this.blackkey = blackkey;
    }

    public List<OrderBean> getOrder() {
        return order;
    }

    public void setOrder(List<OrderBean> order) {
        this.order = order;
    }

    public static class OrderBean {
        /**
         * order_id : 32683
         * order_sn : QHCF5184593422491
         * product_id : 345
         * product_price : 136.00
         * order_amount : 276.40
         * buy_number : 3
         * type_id : 0
         * pay_sn :
         * wo_status : 0
         */

        private String order_id;
        private String order_sn;
        private String product_id;
        private String product_price;
        private String order_amount;
        private String buy_number;
        private String type_id;
        private String pay_sn;
        private String wo_status;

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

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getProduct_price() {
            return product_price;
        }

        public void setProduct_price(String product_price) {
            this.product_price = product_price;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getBuy_number() {
            return buy_number;
        }

        public void setBuy_number(String buy_number) {
            this.buy_number = buy_number;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public String getPay_sn() {
            return pay_sn;
        }

        public void setPay_sn(String pay_sn) {
            this.pay_sn = pay_sn;
        }

        public String getWo_status() {
            return wo_status;
        }

        public void setWo_status(String wo_status) {
            this.wo_status = wo_status;
        }
    }
}

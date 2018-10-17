package com.sainti.blackcard.coffee.bean;

/**
 * Created by YuZhenpeng on 2018/3/18.
 */

public class OrderDetailBean {

    /**
     * result : 1
     * msg :
     * data : {"xo_id":"5","xo_uid":"319099","xo_name":"陈国辉","xo_tel":"15724377399","xo_province":"辽宁","xo_city":"大连","xo_area":"","xo_address":"还差还差还差哈"}
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
         * xo_id : 5
         * xo_uid : 319099
         * xo_name : 陈国辉
         * xo_tel : 15724377399
         * xo_province : 辽宁
         * xo_city : 大连
         * xo_area :
         * xo_address : 还差还差还差哈
         */

        private String xo_id;
        private String xo_uid;
        private String xo_name;
        private String xo_tel;
        private String xo_province;
        private String xo_city;
        private String xo_area;
        private String xo_address;

        public String getXo_id() {
            return xo_id;
        }

        public void setXo_id(String xo_id) {
            this.xo_id = xo_id;
        }

        public String getXo_uid() {
            return xo_uid;
        }

        public void setXo_uid(String xo_uid) {
            this.xo_uid = xo_uid;
        }

        public String getXo_name() {
            return xo_name;
        }

        public void setXo_name(String xo_name) {
            this.xo_name = xo_name;
        }

        public String getXo_tel() {
            return xo_tel;
        }

        public void setXo_tel(String xo_tel) {
            this.xo_tel = xo_tel;
        }

        public String getXo_province() {
            return xo_province;
        }

        public void setXo_province(String xo_province) {
            this.xo_province = xo_province;
        }

        public String getXo_city() {
            return xo_city;
        }

        public void setXo_city(String xo_city) {
            this.xo_city = xo_city;
        }

        public String getXo_area() {
            return xo_area;
        }

        public void setXo_area(String xo_area) {
            this.xo_area = xo_area;
        }

        public String getXo_address() {
            return xo_address;
        }

        public void setXo_address(String xo_address) {
            this.xo_address = xo_address;
        }
    }
}

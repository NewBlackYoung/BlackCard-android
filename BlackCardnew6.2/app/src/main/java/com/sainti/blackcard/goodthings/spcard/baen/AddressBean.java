package com.sainti.blackcard.goodthings.spcard.baen;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/4/3.
 */

public class AddressBean {

    /**
     * result : 1
     * msg :
     * data : [{"ad_id":"817","ad_province":"广东","ad_city":"深圳","ad_area":"福田区","ad_code":"","ad_address":"测试","ad_user":"把","ad_tel":"18698717416","ad_uid":"319099","ad_moren":"1"},{"ad_id":"818","ad_province":"天津市","ad_city":"天津市","ad_area":"和平区","ad_code":"","ad_address":"攻击力里HK","ad_user":"陈国辉","ad_tel":"15724377399","ad_uid":"319099","ad_moren":"0"}]
     */

    private int result;
    private String msg;
    private List<DataBean> data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
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
         * ad_id : 817
         * ad_province : 广东
         * ad_city : 深圳
         * ad_area : 福田区
         * ad_code :
         * ad_address : 测试
         * ad_user : 把
         * ad_tel : 18698717416
         * ad_uid : 319099
         * ad_moren : 1
         */

        private String ad_id;
        private String ad_province;
        private String ad_city;
        private String ad_area;
        private String ad_code;
        private String ad_address;
        private String ad_user;
        private String ad_tel;
        private String ad_uid;
        private String ad_moren;

        public String getAd_id() {
            return ad_id;
        }

        public void setAd_id(String ad_id) {
            this.ad_id = ad_id;
        }

        public String getAd_province() {
            return ad_province;
        }

        public void setAd_province(String ad_province) {
            this.ad_province = ad_province;
        }

        public String getAd_city() {
            return ad_city;
        }

        public void setAd_city(String ad_city) {
            this.ad_city = ad_city;
        }

        public String getAd_area() {
            return ad_area;
        }

        public void setAd_area(String ad_area) {
            this.ad_area = ad_area;
        }

        public String getAd_code() {
            return ad_code;
        }

        public void setAd_code(String ad_code) {
            this.ad_code = ad_code;
        }

        public String getAd_address() {
            return ad_address;
        }

        public void setAd_address(String ad_address) {
            this.ad_address = ad_address;
        }

        public String getAd_user() {
            return ad_user;
        }

        public void setAd_user(String ad_user) {
            this.ad_user = ad_user;
        }

        public String getAd_tel() {
            return ad_tel;
        }

        public void setAd_tel(String ad_tel) {
            this.ad_tel = ad_tel;
        }

        public String getAd_uid() {
            return ad_uid;
        }

        public void setAd_uid(String ad_uid) {
            this.ad_uid = ad_uid;
        }

        public String getAd_moren() {
            return ad_moren;
        }

        public void setAd_moren(String ad_moren) {
            this.ad_moren = ad_moren;
        }
    }
}

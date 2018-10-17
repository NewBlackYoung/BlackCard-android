package com.sainti.blackcard.my.coupon.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/7/5.
 */

public class CommenCouponBean {


    /**
     * result : 1
     * msg :
     * data : {"is_use_bonus":"1","keyy_bonus_data":[{"cl_id":"4790096","cl_time":"2018-07-11","cl_cid":"38","co_name":"618购物节专享","co_menkan":"199","co_dizhi":"20","co_btype":"0","co_stype":"满199元减20元","co_jianjie":"满199-20","cl_keyong":"1"}],"buky_bonus_data":[{"cl_id":"4790097","cl_time":"2018-07-11","cl_cid":"39","co_name":"618购物节专享","co_menkan":"399","co_dizhi":"50","co_btype":"0","co_stype":"满399元减50元","co_jianjie":"满399-50","cl_keyong":"0"}],"bonus_data":[{"cl_id":"4790096","cl_time":"2018-07-11","cl_cid":"38","co_name":"618购物节专享","co_menkan":"199","co_dizhi":"20","co_btype":"0","co_stype":"满199元减20元","co_jianjie":"满199-20","cl_keyong":"1"},{"cl_id":"4790097","cl_time":"2018-07-11","cl_cid":"39","co_name":"618购物节专享","co_menkan":"399","co_dizhi":"50","co_btype":"0","co_stype":"满399元减50元","co_jianjie":"满399-50","cl_keyong":"0"}]}
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
         * is_use_bonus : 1
         * keyy_bonus_data : [{"cl_id":"4790096","cl_time":"2018-07-11","cl_cid":"38","co_name":"618购物节专享","co_menkan":"199","co_dizhi":"20","co_btype":"0","co_stype":"满199元减20元","co_jianjie":"满199-20","cl_keyong":"1"}]
         * buky_bonus_data : [{"cl_id":"4790097","cl_time":"2018-07-11","cl_cid":"39","co_name":"618购物节专享","co_menkan":"399","co_dizhi":"50","co_btype":"0","co_stype":"满399元减50元","co_jianjie":"满399-50","cl_keyong":"0"}]
         * bonus_data : [{"cl_id":"4790096","cl_time":"2018-07-11","cl_cid":"38","co_name":"618购物节专享","co_menkan":"199","co_dizhi":"20","co_btype":"0","co_stype":"满199元减20元","co_jianjie":"满199-20","cl_keyong":"1"},{"cl_id":"4790097","cl_time":"2018-07-11","cl_cid":"39","co_name":"618购物节专享","co_menkan":"399","co_dizhi":"50","co_btype":"0","co_stype":"满399元减50元","co_jianjie":"满399-50","cl_keyong":"0"}]
         */

        private String is_use_bonus;
        private List<KeyyBonusDataBean> keyy_bonus_data;
        private List<BukyBonusDataBean> buky_bonus_data;
        private List<BonusDataBean> bonus_data;

        public String getIs_use_bonus() {
            return is_use_bonus;
        }

        public void setIs_use_bonus(String is_use_bonus) {
            this.is_use_bonus = is_use_bonus;
        }

        public List<KeyyBonusDataBean> getKeyy_bonus_data() {
            return keyy_bonus_data;
        }

        public void setKeyy_bonus_data(List<KeyyBonusDataBean> keyy_bonus_data) {
            this.keyy_bonus_data = keyy_bonus_data;
        }

        public List<BukyBonusDataBean> getBuky_bonus_data() {
            return buky_bonus_data;
        }

        public void setBuky_bonus_data(List<BukyBonusDataBean> buky_bonus_data) {
            this.buky_bonus_data = buky_bonus_data;
        }

        public List<BonusDataBean> getBonus_data() {
            return bonus_data;
        }

        public void setBonus_data(List<BonusDataBean> bonus_data) {
            this.bonus_data = bonus_data;
        }

        public static class KeyyBonusDataBean {
            /**
             * cl_id : 4790096
             * cl_time : 2018-07-11
             * cl_cid : 38
             * co_name : 618购物节专享
             * co_menkan : 199
             * co_dizhi : 20
             * co_btype : 0
             * co_stype : 满199元减20元
             * co_jianjie : 满199-20
             * cl_keyong : 1
             */

            private String cl_id;
            private String cl_time;
            private String cl_cid;
            private String co_name;
            private String co_menkan;
            private String co_dizhi;
            private String co_btype;
            private String co_stype;
            private String co_jianjie;
            private String cl_keyong;

            public String getCl_id() {
                return cl_id;
            }

            public void setCl_id(String cl_id) {
                this.cl_id = cl_id;
            }

            public String getCl_time() {
                return cl_time;
            }

            public void setCl_time(String cl_time) {
                this.cl_time = cl_time;
            }

            public String getCl_cid() {
                return cl_cid;
            }

            public void setCl_cid(String cl_cid) {
                this.cl_cid = cl_cid;
            }

            public String getCo_name() {
                return co_name;
            }

            public void setCo_name(String co_name) {
                this.co_name = co_name;
            }

            public String getCo_menkan() {
                return co_menkan;
            }

            public void setCo_menkan(String co_menkan) {
                this.co_menkan = co_menkan;
            }

            public String getCo_dizhi() {
                return co_dizhi;
            }

            public void setCo_dizhi(String co_dizhi) {
                this.co_dizhi = co_dizhi;
            }

            public String getCo_btype() {
                return co_btype;
            }

            public void setCo_btype(String co_btype) {
                this.co_btype = co_btype;
            }

            public String getCo_stype() {
                return co_stype;
            }

            public void setCo_stype(String co_stype) {
                this.co_stype = co_stype;
            }

            public String getCo_jianjie() {
                return co_jianjie;
            }

            public void setCo_jianjie(String co_jianjie) {
                this.co_jianjie = co_jianjie;
            }

            public String getCl_keyong() {
                return cl_keyong;
            }

            public void setCl_keyong(String cl_keyong) {
                this.cl_keyong = cl_keyong;
            }
        }

        public static class BukyBonusDataBean {
            /**
             * cl_id : 4790097
             * cl_time : 2018-07-11
             * cl_cid : 39
             * co_name : 618购物节专享
             * co_menkan : 399
             * co_dizhi : 50
             * co_btype : 0
             * co_stype : 满399元减50元
             * co_jianjie : 满399-50
             * cl_keyong : 0
             */

            private String cl_id;
            private String cl_time;
            private String cl_cid;
            private String co_name;
            private String co_menkan;
            private String co_dizhi;
            private String co_btype;
            private String co_stype;
            private String co_jianjie;
            private String cl_keyong;

            public String getCl_id() {
                return cl_id;
            }

            public void setCl_id(String cl_id) {
                this.cl_id = cl_id;
            }

            public String getCl_time() {
                return cl_time;
            }

            public void setCl_time(String cl_time) {
                this.cl_time = cl_time;
            }

            public String getCl_cid() {
                return cl_cid;
            }

            public void setCl_cid(String cl_cid) {
                this.cl_cid = cl_cid;
            }

            public String getCo_name() {
                return co_name;
            }

            public void setCo_name(String co_name) {
                this.co_name = co_name;
            }

            public String getCo_menkan() {
                return co_menkan;
            }

            public void setCo_menkan(String co_menkan) {
                this.co_menkan = co_menkan;
            }

            public String getCo_dizhi() {
                return co_dizhi;
            }

            public void setCo_dizhi(String co_dizhi) {
                this.co_dizhi = co_dizhi;
            }

            public String getCo_btype() {
                return co_btype;
            }

            public void setCo_btype(String co_btype) {
                this.co_btype = co_btype;
            }

            public String getCo_stype() {
                return co_stype;
            }

            public void setCo_stype(String co_stype) {
                this.co_stype = co_stype;
            }

            public String getCo_jianjie() {
                return co_jianjie;
            }

            public void setCo_jianjie(String co_jianjie) {
                this.co_jianjie = co_jianjie;
            }

            public String getCl_keyong() {
                return cl_keyong;
            }

            public void setCl_keyong(String cl_keyong) {
                this.cl_keyong = cl_keyong;
            }
        }

        public static class BonusDataBean {
            /**
             * cl_id : 4790096
             * cl_time : 2018-07-11
             * cl_cid : 38
             * co_name : 618购物节专享
             * co_menkan : 199
             * co_dizhi : 20
             * co_btype : 0
             * co_stype : 满199元减20元
             * co_jianjie : 满199-20
             * cl_keyong : 1
             */

            private String cl_id;
            private String cl_time;
            private String cl_cid;
            private String co_name;
            private String co_menkan;
            private String co_dizhi;
            private String co_btype;
            private String co_stype;
            private String co_jianjie;
            private String cl_keyong;

            public String getCl_id() {
                return cl_id;
            }

            public void setCl_id(String cl_id) {
                this.cl_id = cl_id;
            }

            public String getCl_time() {
                return cl_time;
            }

            public void setCl_time(String cl_time) {
                this.cl_time = cl_time;
            }

            public String getCl_cid() {
                return cl_cid;
            }

            public void setCl_cid(String cl_cid) {
                this.cl_cid = cl_cid;
            }

            public String getCo_name() {
                return co_name;
            }

            public void setCo_name(String co_name) {
                this.co_name = co_name;
            }

            public String getCo_menkan() {
                return co_menkan;
            }

            public void setCo_menkan(String co_menkan) {
                this.co_menkan = co_menkan;
            }

            public String getCo_dizhi() {
                return co_dizhi;
            }

            public void setCo_dizhi(String co_dizhi) {
                this.co_dizhi = co_dizhi;
            }

            public String getCo_btype() {
                return co_btype;
            }

            public void setCo_btype(String co_btype) {
                this.co_btype = co_btype;
            }

            public String getCo_stype() {
                return co_stype;
            }

            public void setCo_stype(String co_stype) {
                this.co_stype = co_stype;
            }

            public String getCo_jianjie() {
                return co_jianjie;
            }

            public void setCo_jianjie(String co_jianjie) {
                this.co_jianjie = co_jianjie;
            }

            public String getCl_keyong() {
                return cl_keyong;
            }

            public void setCl_keyong(String cl_keyong) {
                this.cl_keyong = cl_keyong;
            }
        }
    }
}

package com.sainti.blackcard.my.bonus.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/7/10.
 */

public class CanUsedBean {

    /**
     * result : 1
     * msg :
     * data : [{"cl_id":"4790097","cl_time":"2018-07-11","cl_cid":"39","co_name":"618购物节专享","co_menkan":"399","co_dizhi":"50","co_btype":"0","co_stype":"满399减50","co_jianjie":"满399-50"},{"cl_id":"4790096","cl_time":"2018-07-11","cl_cid":"38","co_name":"618购物节专享","co_menkan":"199","co_dizhi":"20","co_btype":"0","co_stype":"满199减20","co_jianjie":"满199-20"}]
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
         * cl_id : 4790097
         * cl_time : 2018-07-11
         * cl_cid : 39
         * co_name : 618购物节专享
         * co_menkan : 399
         * co_dizhi : 50
         * co_btype : 0
         * co_stype : 满399减50
         * co_jianjie : 满399-50
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
    }
}

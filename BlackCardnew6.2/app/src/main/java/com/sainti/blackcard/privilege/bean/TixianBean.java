package com.sainti.blackcard.privilege.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/5/3.
 */

public class TixianBean {

    /**
     * result : 1
     * msg :
     * data : [{"t_type":"余额充值","t_money":"+0.01","t_time":"2018-05-03 14:12:01"}]
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
         * t_type : 余额充值
         * t_money : +0.01
         * t_time : 2018-05-03 14:12:01
         */

        private String t_type;
        private String t_money;
        private String t_time;

        public String getT_type() {
            return t_type;
        }

        public void setT_type(String t_type) {
            this.t_type = t_type;
        }

        public String getT_money() {
            return t_money;
        }

        public void setT_money(String t_money) {
            this.t_money = t_money;
        }

        public String getT_time() {
            return t_time;
        }

        public void setT_time(String t_time) {
            this.t_time = t_time;
        }
    }
}

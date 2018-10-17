package com.sainti.blackcard.chat.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/5/23.
 */

public class LiyouBean {

    /**
     * result : 1
     * msg :
     * data : [{"complaintslist_id":"1","name":"产品价格不满","px":"0"},{"complaintslist_id":"2","name":"服务态度不满","px":"1"},{"complaintslist_id":"5","name":"回复时间过长","px":"2"},{"complaintslist_id":"3","name":"其他投诉原因","px":"3"},{"complaintslist_id":"6","name":"信用额度问题","px":"4"}]
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
         * complaintslist_id : 1
         * name : 产品价格不满
         * px : 0
         */

        private String complaintslist_id;
        private String name;
        private String px;

        public String getComplaintslist_id() {
            return complaintslist_id;
        }

        public void setComplaintslist_id(String complaintslist_id) {
            this.complaintslist_id = complaintslist_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPx() {
            return px;
        }

        public void setPx(String px) {
            this.px = px;
        }
    }
}

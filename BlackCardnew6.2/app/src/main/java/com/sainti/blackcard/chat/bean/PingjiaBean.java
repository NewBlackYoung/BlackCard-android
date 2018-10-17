package com.sainti.blackcard.chat.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/5/25.
 */

public class PingjiaBean {

    /**
     * result : 1
     * msg :
     * data : [{"ta_id":"1","ta_name":"服务态度好"},{"ta_id":"2","ta_name":"文艺小管家"},{"ta_id":"3","ta_name":"尽职尽责"},{"ta_id":"4","ta_name":"专业维权"},{"ta_id":"5","ta_name":"快奖励鸡腿"},{"ta_id":"6","ta_name":"机票小能手"},{"ta_id":"7","ta_name":"小话痨"},{"ta_id":"8","ta_name":"有点高冷"},{"ta_id":"9","ta_name":"酒店专家"},{"ta_id":"10","ta_name":"旅行达人"},{"ta_id":"11","ta_name":"贴心小棉袄"},{"ta_id":"12","ta_name":"各种给力"}]
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
         * ta_id : 1
         * ta_name : 服务态度好
         */

        private String ta_id;
        private String ta_name;

        public String getTa_id() {
            return ta_id;
        }

        public void setTa_id(String ta_id) {
            this.ta_id = ta_id;
        }

        public String getTa_name() {
            return ta_name;
        }

        public void setTa_name(String ta_name) {
            this.ta_name = ta_name;
        }
    }
}

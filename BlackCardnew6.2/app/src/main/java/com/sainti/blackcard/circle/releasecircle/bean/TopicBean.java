package com.sainti.blackcard.circle.releasecircle.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/4/17.
 */

public class TopicBean {

    /**
     * result : 1
     * msg :
     * data : [{"to_id":"21","to_name":"#影评知晓#","to_des":"影评","to_pic":"http://s.qing-hei.com/Public/Uploads/20171031/59f7ec8069a19.jpg","to_view":"2677","to_time":"1508908982","to_state":"0"},{"to_id":"19","to_name":"#书评看品#","to_des":"书评","to_pic":"http://s.qing-hei.com/Public/Uploads/20171031/59f7ec901418a.jpg","to_view":"436","to_time":"1508908978","to_state":"0"},{"to_id":"15","to_name":"#青黑日签#","to_des":"一秒圈粉十几万","to_pic":"http://s.qing-hei.com/Public/Uploads/20171031/59f7ec5956970.jpg","to_view":"2809","to_time":"1505963302","to_state":"0"},{"to_id":"5","to_name":"#品质生活#","to_des":"掌控生活，做生活的有心人","to_pic":"http://s.qing-hei.com/Public/Uploads/20171031/59f7ec656196e.jpg","to_view":"3674","to_time":"1497402500","to_state":"0"}]
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
         * to_id : 21
         * to_name : #影评知晓#
         * to_des : 影评
         * to_pic : http://s.qing-hei.com/Public/Uploads/20171031/59f7ec8069a19.jpg
         * to_view : 2677
         * to_time : 1508908982
         * to_state : 0
         */

        private String to_id;
        private String to_name;
        private String to_des;
        private String to_pic;
        private String to_view;
        private String to_time;
        private String to_state;

        public String getTo_id() {
            return to_id;
        }

        public void setTo_id(String to_id) {
            this.to_id = to_id;
        }

        public String getTo_name() {
            return to_name;
        }

        public void setTo_name(String to_name) {
            this.to_name = to_name;
        }

        public String getTo_des() {
            return to_des;
        }

        public void setTo_des(String to_des) {
            this.to_des = to_des;
        }

        public String getTo_pic() {
            return to_pic;
        }

        public void setTo_pic(String to_pic) {
            this.to_pic = to_pic;
        }

        public String getTo_view() {
            return to_view;
        }

        public void setTo_view(String to_view) {
            this.to_view = to_view;
        }

        public String getTo_time() {
            return to_time;
        }

        public void setTo_time(String to_time) {
            this.to_time = to_time;
        }

        public String getTo_state() {
            return to_state;
        }

        public void setTo_state(String to_state) {
            this.to_state = to_state;
        }
    }
}

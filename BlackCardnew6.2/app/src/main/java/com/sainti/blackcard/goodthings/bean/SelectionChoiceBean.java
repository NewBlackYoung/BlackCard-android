package com.sainti.blackcard.goodthings.bean;


import java.util.List;

public class SelectionChoiceBean {

    /**
     * result : 1
     * msg :
     * data : [{"wt_id":"","wt_name":"全部","wt_img":"","wt_state":"0","wt_px":"0"},{"wt_id":"20","wt_name":"推荐","wt_img":"http://s.qing-hei.com/Public/Uploads/20171201/5a20b91f1b913.jpg","wt_state":"0","wt_px":"0"},{"wt_id":"12","wt_name":"出行","wt_img":"http://s.qing-hei.com/Public/Uploads/20171123/s_5a16a02e63f3a.jpg","wt_state":"0","wt_px":"1"},{"wt_id":"13","wt_name":"健康","wt_img":"http://s.qing-hei.com/Public/Uploads/20171123/s_5a16a044389d8.jpg","wt_state":"0","wt_px":"1"},{"wt_id":"14","wt_name":"科技","wt_img":"http://s.qing-hei.com/Public/Uploads/20171123/s_5a16a04e2a3b3.jpg","wt_state":"0","wt_px":"2"},{"wt_id":"15","wt_name":"品酒","wt_img":"http://s.qing-hei.com/Public/Uploads/20171123/s_5a16a05a11877.jpg","wt_state":"0","wt_px":"3"},{"wt_id":"16","wt_name":"生活","wt_img":"http://s.qing-hei.com/Public/Uploads/20171123/s_5a16a064c1a86.jpg","wt_state":"0","wt_px":"4"},{"wt_id":"17","wt_name":"食用","wt_img":"http://s.qing-hei.com/Public/Uploads/20171123/s_5a16a06f7429e.jpg","wt_state":"0","wt_px":"5"},{"wt_id":"18","wt_name":"玩物","wt_img":"http://s.qing-hei.com/Public/Uploads/20171123/s_5a16a098459a9.jpg","wt_state":"0","wt_px":"6"},{"wt_id":"19","wt_name":"专享","wt_img":"http://s.qing-hei.com/Public/Uploads/20171123/s_5a16a0a51c8ed.jpg","wt_state":"0","wt_px":"7"}]
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
         * wt_id :
         * wt_name : 全部
         * wt_img :
         * wt_state : 0
         * wt_px : 0
         */

        private String wt_id;
        private String wt_name;
        private String wt_img;
        private String wt_state;
        private String wt_px;

        public String getWt_id() {
            return wt_id;
        }

        public void setWt_id(String wt_id) {
            this.wt_id = wt_id;
        }

        public String getWt_name() {
            return wt_name;
        }

        public void setWt_name(String wt_name) {
            this.wt_name = wt_name;
        }

        public String getWt_img() {
            return wt_img;
        }

        public void setWt_img(String wt_img) {
            this.wt_img = wt_img;
        }

        public String getWt_state() {
            return wt_state;
        }

        public void setWt_state(String wt_state) {
            this.wt_state = wt_state;
        }

        public String getWt_px() {
            return wt_px;
        }

        public void setWt_px(String wt_px) {
            this.wt_px = wt_px;
        }
    }
}

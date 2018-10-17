package com.sainti.blackcard.homepage.splash.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/4/26.
 */

public class GuangGaoBean {


    /**
     * result : 1
     * msg :
     * num : 7
     * data : [{"g_id":"1","g_img":"http://s.qing-hei.com/Public/new/app/images/guide/1.jpg"},{"g_id":"2","g_img":"http://s.qing-hei.com/Public/new/app/images/guide/2.jpg"},{"g_id":"3","g_img":"http://s.qing-hei.com/Public/new/app/images/guide/3.jpg"},{"g_id":"4","g_img":"http://s.qing-hei.com/Public/new/app/images/guide/4.jpg"},{"g_id":"5","g_img":"http://s.qing-hei.com/Public/new/app/images/guide/5.jpg"},{"g_id":"6","g_img":"http://s.qing-hei.com/Public/new/app/images/guide/6.jpg"},{"g_id":"7","g_img":"http://s.qing-hei.com/Public/new/app/images/guide/7.jpg"}]
     * queryorder : http://www.qing-hei.com/mobile.php/App/queryCardOrder
     */

    private int result;
    private String msg;
    private int num;
    private String queryorder;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getQueryorder() {
        return queryorder;
    }

    public void setQueryorder(String queryorder) {
        this.queryorder = queryorder;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * g_id : 1
         * g_img : http://s.qing-hei.com/Public/new/app/images/guide/1.jpg
         */

        private String g_id;
        private String g_img;

        public String getG_id() {
            return g_id;
        }

        public void setG_id(String g_id) {
            this.g_id = g_id;
        }

        public String getG_img() {
            return g_img;
        }

        public void setG_img(String g_img) {
            this.g_img = g_img;
        }
    }
}

package com.sainti.blackcard.circle.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/5/16.
 */

public class MineBaseBean {

    /**
     * result : 1
     * msg :
     * data : {"user_nick":"Cgenguohui","head":"","app_bg":"http://s.qing-hei.com/Public/Images/share/sharebg.jpg","fans":"1","publishnum":"0","cert_pic":"http://s.qing-hei.com/Public/Uploads/20180410/5acc6a8a362ec.png","introinfo":"tttt ","follow":"2","user_sex":"1","findlist":[]}
     * pagecount :
     */

    private String result;
    private String msg;
    private DataBean data;
    private String pagecount;

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

    public String getPagecount() {
        return pagecount;
    }

    public void setPagecount(String pagecount) {
        this.pagecount = pagecount;
    }

    public static class DataBean {
        /**
         * user_nick : Cgenguohui
         * head :
         * app_bg : http://s.qing-hei.com/Public/Images/share/sharebg.jpg
         * fans : 1
         * publishnum : 0
         * cert_pic : http://s.qing-hei.com/Public/Uploads/20180410/5acc6a8a362ec.png
         * introinfo : tttt
         * follow : 2
         * user_sex : 1
         * findlist : []
         */

        private String user_nick;
        private String head;
        private String app_bg;
        private String fans;
        private String publishnum;
        private String cert_pic;
        private String introinfo;
        private String follow;
        private String user_sex;
        private List<?> findlist;

        public String getUser_nick() {
            return user_nick;
        }

        public void setUser_nick(String user_nick) {
            this.user_nick = user_nick;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getApp_bg() {
            return app_bg;
        }

        public void setApp_bg(String app_bg) {
            this.app_bg = app_bg;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public String getPublishnum() {
            return publishnum;
        }

        public void setPublishnum(String publishnum) {
            this.publishnum = publishnum;
        }

        public String getCert_pic() {
            return cert_pic;
        }

        public void setCert_pic(String cert_pic) {
            this.cert_pic = cert_pic;
        }

        public String getIntroinfo() {
            return introinfo;
        }

        public void setIntroinfo(String introinfo) {
            this.introinfo = introinfo;
        }

        public String getFollow() {
            return follow;
        }

        public void setFollow(String follow) {
            this.follow = follow;
        }

        public String getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(String user_sex) {
            this.user_sex = user_sex;
        }

        public List<?> getFindlist() {
            return findlist;
        }

        public void setFindlist(List<?> findlist) {
            this.findlist = findlist;
        }
    }
}

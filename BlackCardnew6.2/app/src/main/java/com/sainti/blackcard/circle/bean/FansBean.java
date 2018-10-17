package com.sainti.blackcard.circle.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/4/16.
 */

public class FansBean {

    /**
     * result : 1
     * msg :
     * data : [{"uid":"323226","user_upimg":"http://s.qing-hei.com/Public/Uploads/20180413/s_5ad0800fbff3f.jpg","user_nick":"葫芦娃救爷爷呢，一个一个送！","user_sex":"2","time":"2018-04-10 17:48:21","level":"http://s.qing-hei.com/Public/Uploads/20180410/5acc6a8a362ec.png","cert_pic":"","introinfo":"Ta很懒哦，什么都没有写~","isfollow":"0","fans":"0"},{"uid":"21334","user_upimg":"http://s.qing-hei.com/Public/Uploads/20170927/s_59cb7da310281.jpeg","user_nick":"Vernon","user_sex":"1","time":"2018-04-10 15:47:38","level":"http://s.qing-hei.com/Public/Uploads/20180410/5acc6a9923479.png","cert_pic":"http://s.qing-hei.com/Public/Images/cert/icon1.png","introinfo":"执子之手，与子偕老。","isfollow":"1","fans":"1882"}]
     * pagecount : 1
     */

    private String result;
    private String msg;
    private int pagecount;
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

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 323226
         * user_upimg : http://s.qing-hei.com/Public/Uploads/20180413/s_5ad0800fbff3f.jpg
         * user_nick : 葫芦娃救爷爷呢，一个一个送！
         * user_sex : 2
         * time : 2018-04-10 17:48:21
         * level : http://s.qing-hei.com/Public/Uploads/20180410/5acc6a8a362ec.png
         * cert_pic :
         * introinfo : Ta很懒哦，什么都没有写~
         * isfollow : 0
         * fans : 0
         */

        private String uid;
        private String user_upimg;
        private String user_nick;
        private String user_sex;
        private String time;
        private String level;
        private String cert_pic;
        private String introinfo;
        private String isfollow;
        private String fans;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUser_upimg() {
            return user_upimg;
        }

        public void setUser_upimg(String user_upimg) {
            this.user_upimg = user_upimg;
        }

        public String getUser_nick() {
            return user_nick;
        }

        public void setUser_nick(String user_nick) {
            this.user_nick = user_nick;
        }

        public String getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(String user_sex) {
            this.user_sex = user_sex;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
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

        public String getIsfollow() {
            return isfollow;
        }

        public void setIsfollow(String isfollow) {
            this.isfollow = isfollow;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }
    }
}

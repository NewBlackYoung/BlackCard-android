package com.sainti.blackcard.circle.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/4/16.
 */

public class FollowBean {

    /**
     * result : 1
     * msg :
     * data : [{"uid":"258302","user_upimg":"http://s.qing-hei.com/Public/Uploads/20171219/s_5a38f5549a993.jpeg","user_nick":"孟某人","user_sex":"1","time":"2018-04-10 17:29:42","level":"http://s.qing-hei.com/Public/Uploads/20180410/5acc6a9923479.png","introinfo":"不气馁 有态度 爱自由","cert_pic":"","isfollow":"1","fans":"7"},{"uid":"313103","user_upimg":"http://s.qing-hei.com/Public/Uploads/20180210/s_5a7e5a89447f4.jpg","user_nick":"高雅馨","user_sex":"2","time":"2018-04-10 15:47:38","level":"http://s.qing-hei.com/Public/Uploads/20180410/5acc6a9923479.png","introinfo":"iOS开发","cert_pic":"","isfollow":"1","fans":"2"},{"uid":"19586","user_upimg":"http://s.qing-hei.com/Public/Uploads/20161016/5802f7504047c.jpg","user_nick":"黑黑","user_sex":"1","time":"2018-03-23 13:28:54","level":"http://s.qing-hei.com/Public/Uploads/20180410/5acc6a8a362ec.png","introinfo":"Ta很懒哦，什么都没有写~","cert_pic":"","isfollow":"1","fans":"37"},{"uid":"22554","user_upimg":"http://s.qing-hei.com/Public/Uploads/20160906/57ceb4b967207.jpg","user_nick":"陈瑜诺妍他爸","user_sex":"1","time":"2017-11-24 13:14:19","level":"http://s.qing-hei.com/Public/Uploads/20180410/5acc6a9923479.png","introinfo":"我就是我，不一样的我","cert_pic":"","isfollow":"1","fans":"22"},{"uid":"3040","user_upimg":"http://s.qing-hei.com/Public/Uploads/20170818/s_59969941d1b8e.jpg","user_nick":"鲸鱼君","user_sex":"1","time":"2017-11-24 13:13:57","level":"http://s.qing-hei.com/Public/Uploads/20180410/5acc6a8a362ec.png","introinfo":"苏州Whale Studio运动文化中心","cert_pic":"","isfollow":"1","fans":"2"},{"uid":"231117","user_upimg":"http://s.qing-hei.com/Public/Uploads/20171119/s_5a11172e1bc64.jpg","user_nick":"周沫","user_sex":"2","time":"2017-11-24 13:13:02","level":"http://s.qing-hei.com/Public/Uploads/20180410/5acc6a8a362ec.png","introinfo":"努力遇见最美的自己","cert_pic":"","isfollow":"1","fans":"37"},{"uid":"139759","user_upimg":"http://s.qing-hei.com/Public/Uploads/20170817/s_59956681997f5.jpeg","user_nick":"嗲嗲贺的小相公","user_sex":"1","time":"2017-11-24 13:11:57","level":"http://s.qing-hei.com/Public/Uploads/20180410/5acc6a9923479.png","introinfo":"止于唇齿，掩于岁月","cert_pic":"","isfollow":"1","fans":"1"},{"uid":"179021","user_upimg":"http://s.qing-hei.com/Public/Uploads/20170928/s_59cccd9d34e4e.jpeg","user_nick":"邓先森","user_sex":"1","time":"2017-10-22 22:19:30","level":"http://s.qing-hei.com/Public/Uploads/20180410/5acc6a9923479.png","introinfo":"Ta很懒哦，什么都没有写~","cert_pic":"","isfollow":"1","fans":"1"},{"uid":"169398","user_upimg":"http://s.qing-hei.com/Public/Uploads/20170913/s_59b8a8f9f25c3.jpeg","user_nick":"未来一定很美好","user_sex":"1","time":"2017-10-22 22:19:29","level":"http://s.qing-hei.com/Public/Uploads/20180410/5acc6a9923479.png","introinfo":"私人个体","cert_pic":"","isfollow":"1","fans":"1"},{"uid":"98884","user_upimg":"","user_nick":"海榔","user_sex":"1","time":"2017-10-22 22:19:29","level":"http://s.qing-hei.com/Public/Uploads/20180410/5acc6a8a362ec.png","introinfo":"艺术品收藏交流","cert_pic":"","isfollow":"1","fans":"10"}]
     * pagecount : 23
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
         * uid : 258302
         * user_upimg : http://s.qing-hei.com/Public/Uploads/20171219/s_5a38f5549a993.jpeg
         * user_nick : 孟某人
         * user_sex : 1
         * time : 2018-04-10 17:29:42
         * level : http://s.qing-hei.com/Public/Uploads/20180410/5acc6a9923479.png
         * introinfo : 不气馁 有态度 爱自由
         * cert_pic :
         * isfollow : 1
         * fans : 7
         */

        private String uid;
        private String user_upimg;
        private String user_nick;
        private String user_sex;
        private String time;
        private String level;
        private String introinfo;
        private String cert_pic;
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

        public String getIntroinfo() {
            return introinfo;
        }

        public void setIntroinfo(String introinfo) {
            this.introinfo = introinfo;
        }

        public String getCert_pic() {
            return cert_pic;
        }

        public void setCert_pic(String cert_pic) {
            this.cert_pic = cert_pic;
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

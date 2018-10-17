package com.sainti.blackcard.circle.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/4/16.
 */

public class MessageBean {

    /**
     * result : 1
     * msg :
     * unreadcount : 1
     * data : [{"m_id":"101063","m_type":"0","m_ctype":"1","m_title":"评论了你：Jjjjjjjj","m_uid":"21334","m_o_uid":"313103","m_dataid":"11940","m_time":"2018-04-16 14:19:54","is_read":"0","find_img":"http://s.qing-hei.com/Public/Uploads/20180410/5acc8243cccc4.jpg","find_img2":"http://s.qing-hei.com/Public/Uploads/20180410/an_5acc8243cccc4.jpg","user_img":"http://s.qing-hei.com/Public/Uploads/20180210/s_5a7e5a89447f4.jpg","user_nick":"高雅馨"}]
     * pagecount : 1
     */

    private String result;
    private String msg;
    private String unreadcount;
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

    public String getUnreadcount() {
        return unreadcount;
    }

    public void setUnreadcount(String unreadcount) {
        this.unreadcount = unreadcount;
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
         * m_id : 101063
         * m_type : 0
         * m_ctype : 1
         * m_title : 评论了你：Jjjjjjjj
         * m_uid : 21334
         * m_o_uid : 313103
         * m_dataid : 11940
         * m_time : 2018-04-16 14:19:54
         * is_read : 0
         * find_img : http://s.qing-hei.com/Public/Uploads/20180410/5acc8243cccc4.jpg
         * find_img2 : http://s.qing-hei.com/Public/Uploads/20180410/an_5acc8243cccc4.jpg
         * user_img : http://s.qing-hei.com/Public/Uploads/20180210/s_5a7e5a89447f4.jpg
         * user_nick : 高雅馨
         */

        private String m_id;
        private String m_type;
        private String m_ctype;
        private String m_title;
        private String m_uid;
        private String m_o_uid;
        private String m_dataid;
        private String m_time;
        private String is_read;
        private String find_img;
        private String find_img2;
        private String user_img;
        private String user_nick;

        public String getM_id() {
            return m_id;
        }

        public void setM_id(String m_id) {
            this.m_id = m_id;
        }

        public String getM_type() {
            return m_type;
        }

        public void setM_type(String m_type) {
            this.m_type = m_type;
        }

        public String getM_ctype() {
            return m_ctype;
        }

        public void setM_ctype(String m_ctype) {
            this.m_ctype = m_ctype;
        }

        public String getM_title() {
            return m_title;
        }

        public void setM_title(String m_title) {
            this.m_title = m_title;
        }

        public String getM_uid() {
            return m_uid;
        }

        public void setM_uid(String m_uid) {
            this.m_uid = m_uid;
        }

        public String getM_o_uid() {
            return m_o_uid;
        }

        public void setM_o_uid(String m_o_uid) {
            this.m_o_uid = m_o_uid;
        }

        public String getM_dataid() {
            return m_dataid;
        }

        public void setM_dataid(String m_dataid) {
            this.m_dataid = m_dataid;
        }

        public String getM_time() {
            return m_time;
        }

        public void setM_time(String m_time) {
            this.m_time = m_time;
        }

        public String getIs_read() {
            return is_read;
        }

        public void setIs_read(String is_read) {
            this.is_read = is_read;
        }

        public String getFind_img() {
            return find_img;
        }

        public void setFind_img(String find_img) {
            this.find_img = find_img;
        }

        public String getFind_img2() {
            return find_img2;
        }

        public void setFind_img2(String find_img2) {
            this.find_img2 = find_img2;
        }

        public String getUser_img() {
            return user_img;
        }

        public void setUser_img(String user_img) {
            this.user_img = user_img;
        }

        public String getUser_nick() {
            return user_nick;
        }

        public void setUser_nick(String user_nick) {
            this.user_nick = user_nick;
        }
    }
}

package com.sainti.blackcard.mblcakcard.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/5/10.
 */

public class FriendBean {


    /**
     * result : 1
     * msg :
     * data : [{"user_id":"332680","user_card":"15724377399-1","user_pwd":"123456","user_nick":"","user_name":"高雅馨","user_upimg":"","user_tel":"13898494969","is_bind":"0","weixin_id":"","state":"0"}]
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
         * user_id : 332680
         * user_card : 15724377399-1
         * user_pwd : 123456
         * user_nick :
         * user_name : 高雅馨
         * user_upimg :
         * user_tel : 13898494969
         * is_bind : 0
         * weixin_id :
         * state : 0
         */

        private String user_id;
        private String user_card;
        private String user_pwd;
        private String user_nick;
        private String user_name;
        private String user_upimg;
        private String user_tel;
        private String is_bind;
        private String weixin_id;
        private String state;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_card() {
            return user_card;
        }

        public void setUser_card(String user_card) {
            this.user_card = user_card;
        }

        public String getUser_pwd() {
            return user_pwd;
        }

        public void setUser_pwd(String user_pwd) {
            this.user_pwd = user_pwd;
        }

        public String getUser_nick() {
            return user_nick;
        }

        public void setUser_nick(String user_nick) {
            this.user_nick = user_nick;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_upimg() {
            return user_upimg;
        }

        public void setUser_upimg(String user_upimg) {
            this.user_upimg = user_upimg;
        }

        public String getUser_tel() {
            return user_tel;
        }

        public void setUser_tel(String user_tel) {
            this.user_tel = user_tel;
        }

        public String getIs_bind() {
            return is_bind;
        }

        public void setIs_bind(String is_bind) {
            this.is_bind = is_bind;
        }

        public String getWeixin_id() {
            return weixin_id;
        }

        public void setWeixin_id(String weixin_id) {
            this.weixin_id = weixin_id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}

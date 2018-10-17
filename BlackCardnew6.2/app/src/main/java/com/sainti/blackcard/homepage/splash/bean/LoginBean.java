package com.sainti.blackcard.homepage.splash.bean;

/**
 * Created by YuZhenpeng on 2018/4/27.
 */

public class LoginBean {


    /**
     * result : 1
     * msg :
     * data : {"uid":"329919","token":"84ee38c357639b00378429c1cf58f003","is_bind":"0","user_tel":""}
     */

    private String result;
    private String msg;
    private DataBean data;

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

    public static class DataBean {
        /**
         * uid : 329919
         * token : 84ee38c357639b00378429c1cf58f003
         * is_bind : 0
         * user_tel :
         */

        private String uid;
        private String token;
        private String is_bind;
        private String user_tel;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getIs_bind() {
            return is_bind;
        }

        public void setIs_bind(String is_bind) {
            this.is_bind = is_bind;
        }

        public String getUser_tel() {
            return user_tel;
        }

        public void setUser_tel(String user_tel) {
            this.user_tel = user_tel;
        }
    }
}

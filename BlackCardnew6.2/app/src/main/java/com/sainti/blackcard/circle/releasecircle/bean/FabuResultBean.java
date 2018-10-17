package com.sainti.blackcard.circle.releasecircle.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by YuZhenpeng on 2018/4/17.
 */

public class FabuResultBean {

    /**
     * result : 1
     * msg :
     * data : {"uid":"319099","token":"9bfe3863cbe5f729fae5dbad37b633e1","@":false,"find_id":11946}
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
         * uid : 319099
         * token : 9bfe3863cbe5f729fae5dbad37b633e1
         * @ : false
         * find_id : 11946
         */

        private String uid;
        private String token;
        @SerializedName("@")
        private boolean _$297; // FIXME check this code
        private int find_id;

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

        public boolean is_$297() {
            return _$297;
        }

        public void set_$297(boolean _$297) {
            this._$297 = _$297;
        }

        public int getFind_id() {
            return find_id;
        }

        public void setFind_id(int find_id) {
            this.find_id = find_id;
        }
    }
}

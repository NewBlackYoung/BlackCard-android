package com.sainti.blackcard.circle.bean;

/**
 * Created by YuZhenpeng on 2018/4/20.
 */

public class UnReadMeassgBean {

    /**
     * result : 1
     * msg :
     * data : {"count":"0","is_fans_new":"0"}
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
         * count : 0
         * is_fans_new : 0
         */

        private String count;
        private String is_fans_new;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getIs_fans_new() {
            return is_fans_new;
        }

        public void setIs_fans_new(String is_fans_new) {
            this.is_fans_new = is_fans_new;
        }
    }
}

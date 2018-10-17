package com.sainti.blackcard.homepage.bean;

/**
 * Created by YuZhenpeng on 2018/5/15.
 */

public class UpdateBean {

    /**
     * result : 1
     * msg :
     * data : {"type":"0","is_qiangzgx":"0","version_new":"5.0.3","date":"","app_url":"http://a.app.qq.com/o/simple.jsp?pkgname=com.sainti.blackcard","update_text":"1.修复一些bug，运行更流畅；\r\n2.优化圈子点赞，返回顶部，提高体验度；","img":"http://s.qing-hei.com/Public/Uploads/20180115/5a5c6f0d40420.png"}
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
         * type : 0
         * is_qiangzgx : 0
         * version_new : 5.0.3
         * date :
         * app_url : http://a.app.qq.com/o/simple.jsp?pkgname=com.sainti.blackcard
         * update_text : 1.修复一些bug，运行更流畅；
         2.优化圈子点赞，返回顶部，提高体验度；
         * img : http://s.qing-hei.com/Public/Uploads/20180115/5a5c6f0d40420.png
         */

        private String type;
        private String is_qiangzgx;
        private String version_new;
        private String date;
        private String app_url;
        private String update_text;
        private String img;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIs_qiangzgx() {
            return is_qiangzgx;
        }

        public void setIs_qiangzgx(String is_qiangzgx) {
            this.is_qiangzgx = is_qiangzgx;
        }

        public String getVersion_new() {
            return version_new;
        }

        public void setVersion_new(String version_new) {
            this.version_new = version_new;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getApp_url() {
            return app_url;
        }

        public void setApp_url(String app_url) {
            this.app_url = app_url;
        }

        public String getUpdate_text() {
            return update_text;
        }

        public void setUpdate_text(String update_text) {
            this.update_text = update_text;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}

package com.sainti.blackcard.my.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/5/7.
 */

public class MyBean {

    /**
     * result : 1
     * msg :
     * data : {"assistant":[],"head":"http://s.qing-hei.com/Public/Uploads/20180420/s_5ad988dcadabe.jpeg","nickname":"彩虹直至黑白","name":"陈国辉","level":"1","tel":"15724377399","idcard":"211481199410197012","level_ico":"http://s.qing-hei.com/Public/Uploads/20180410/5acc6a9923479.png","fans":"2","follow":"10","cert_pic":"","certification":"","certmold":"","introinfo":"你很懒哦，什么都没有写~","sign":"0","sex":"1","id_number2":"211481199410197012"}
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
         * assistant : []
         * head : http://s.qing-hei.com/Public/Uploads/20180420/s_5ad988dcadabe.jpeg
         * nickname : 彩虹直至黑白
         * name : 陈国辉
         * level : 1
         * tel : 15724377399
         * idcard : 211481199410197012
         * level_ico : http://s.qing-hei.com/Public/Uploads/20180410/5acc6a9923479.png
         * fans : 2
         * follow : 10
         * cert_pic :
         * certification :
         * certmold :
         * introinfo : 你很懒哦，什么都没有写~
         * sign : 0
         * sex : 1
         * id_number2 : 211481199410197012
         */

        private String head;
        private String nickname;
        private String name;
        private String level;
        private String tel;
        private String idcard;
        private String level_ico;
        private String fans;
        private String follow;
        private String cert_pic;
        private String certification;
        private String certmold;
        private String introinfo;
        private String sign;
        private String sex;
        private String id_number2;
        private List<?> assistant;

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getLevel_ico() {
            return level_ico;
        }

        public void setLevel_ico(String level_ico) {
            this.level_ico = level_ico;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public String getFollow() {
            return follow;
        }

        public void setFollow(String follow) {
            this.follow = follow;
        }

        public String getCert_pic() {
            return cert_pic;
        }

        public void setCert_pic(String cert_pic) {
            this.cert_pic = cert_pic;
        }

        public String getCertification() {
            return certification;
        }

        public void setCertification(String certification) {
            this.certification = certification;
        }

        public String getCertmold() {
            return certmold;
        }

        public void setCertmold(String certmold) {
            this.certmold = certmold;
        }

        public String getIntroinfo() {
            return introinfo;
        }

        public void setIntroinfo(String introinfo) {
            this.introinfo = introinfo;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getId_number2() {
            return id_number2;
        }

        public void setId_number2(String id_number2) {
            this.id_number2 = id_number2;
        }

        public List<?> getAssistant() {
            return assistant;
        }

        public void setAssistant(List<?> assistant) {
            this.assistant = assistant;
        }
    }
}

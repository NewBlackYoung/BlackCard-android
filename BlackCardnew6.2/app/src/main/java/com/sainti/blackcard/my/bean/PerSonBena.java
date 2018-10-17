package com.sainti.blackcard.my.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by YuZhenpeng on 2018/5/7.
 */

public class PerSonBena {

    /**
     * result : 1
     * msg :
     * data : {"head":"http://s.qing-hei.com/Public/Uploads/20180420/s_5ad988dcadabe.jpeg","nickname":"彩虹直至黑白","name":"陈国辉","sex":"1","phone":"15724377399","phonenum":"15724377399","cardid":"15724377399","id_number":"211481****7012","id_number2":"211481199410197012","qq":"575632132","email":"575632132@qq.com ","12306id":"","fans":"2","follow":"10","introinfo":"","zhiyeinfo":"","location":"","weixin_state":"0"}
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
         * head : http://s.qing-hei.com/Public/Uploads/20180420/s_5ad988dcadabe.jpeg
         * nickname : 彩虹直至黑白
         * name : 陈国辉
         * sex : 1
         * phone : 15724377399
         * phonenum : 15724377399
         * cardid : 15724377399
         * id_number : 211481****7012
         * id_number2 : 211481199410197012
         * qq : 575632132
         * email : 575632132@qq.com
         * 12306id :
         * fans : 2
         * follow : 10
         * introinfo :
         * zhiyeinfo :
         * location :
         * weixin_state : 0
         */

        private String head;
        private String nickname;
        private String name;
        private String sex;
        private String phone;
        private String phonenum;
        private String cardid;
        private String id_number;
        private String id_number2;
        private String qq;
        private String email;
        @SerializedName("12306id")
        private String _$12306id;
        private String fans;
        private String follow;
        private String introinfo;
        private String zhiyeinfo;
        private String location;
        private String weixin_state;

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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhonenum() {
            return phonenum;
        }

        public void setPhonenum(String phonenum) {
            this.phonenum = phonenum;
        }

        public String getCardid() {
            return cardid;
        }

        public void setCardid(String cardid) {
            this.cardid = cardid;
        }

        public String getId_number() {
            return id_number;
        }

        public void setId_number(String id_number) {
            this.id_number = id_number;
        }

        public String getId_number2() {
            return id_number2;
        }

        public void setId_number2(String id_number2) {
            this.id_number2 = id_number2;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String get_$12306id() {
            return _$12306id;
        }

        public void set_$12306id(String _$12306id) {
            this._$12306id = _$12306id;
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

        public String getIntroinfo() {
            return introinfo;
        }

        public void setIntroinfo(String introinfo) {
            this.introinfo = introinfo;
        }

        public String getZhiyeinfo() {
            return zhiyeinfo;
        }

        public void setZhiyeinfo(String zhiyeinfo) {
            this.zhiyeinfo = zhiyeinfo;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getWeixin_state() {
            return weixin_state;
        }

        public void setWeixin_state(String weixin_state) {
            this.weixin_state = weixin_state;
        }
    }
}

package com.sainti.blackcard.my.invitingcourtesy.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/8/1.
 */

public class MyInvitBean {

    /**
     * result : 1
     * msg :
     * data : {"count":"1","changebtn":"0","wuliubtn":"0","invites":[{"s_id":"1468","s_uid":"332151","s_time":"2018-06-14 16:34:18","s_ship":"精英","order_name":"精英终身会籍+随机选号+不定制姓名=199元","user_name1":"测试邀请","is_yes":"1","money":"30.00"}]}
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
         * count : 1
         * changebtn : 0
         * wuliubtn : 0
         * invites : [{"s_id":"1468","s_uid":"332151","s_time":"2018-06-14 16:34:18","s_ship":"精英","order_name":"精英终身会籍+随机选号+不定制姓名=199元","user_name1":"测试邀请","is_yes":"1","money":"30.00"}]
         */

        private String count;
        private String changebtn;
        private String wuliubtn;
        private List<InvitesBean> invites;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getChangebtn() {
            return changebtn;
        }

        public void setChangebtn(String changebtn) {
            this.changebtn = changebtn;
        }

        public String getWuliubtn() {
            return wuliubtn;
        }

        public void setWuliubtn(String wuliubtn) {
            this.wuliubtn = wuliubtn;
        }

        public List<InvitesBean> getInvites() {
            return invites;
        }

        public void setInvites(List<InvitesBean> invites) {
            this.invites = invites;
        }

        public static class InvitesBean {
            /**
             * s_id : 1468
             * s_uid : 332151
             * s_time : 2018-06-14 16:34:18
             * s_ship : 精英
             * order_name : 精英终身会籍+随机选号+不定制姓名=199元
             * user_name1 : 测试邀请
             * is_yes : 1
             * money : 30.00
             */

            private String s_id;
            private String s_uid;
            private String s_time;
            private String s_ship;
            private String order_name;
            private String user_name1;
            private String is_yes;
            private String money;

            public String getS_id() {
                return s_id;
            }

            public void setS_id(String s_id) {
                this.s_id = s_id;
            }

            public String getS_uid() {
                return s_uid;
            }

            public void setS_uid(String s_uid) {
                this.s_uid = s_uid;
            }

            public String getS_time() {
                return s_time;
            }

            public void setS_time(String s_time) {
                this.s_time = s_time;
            }

            public String getS_ship() {
                return s_ship;
            }

            public void setS_ship(String s_ship) {
                this.s_ship = s_ship;
            }

            public String getOrder_name() {
                return order_name;
            }

            public void setOrder_name(String order_name) {
                this.order_name = order_name;
            }

            public String getUser_name1() {
                return user_name1;
            }

            public void setUser_name1(String user_name1) {
                this.user_name1 = user_name1;
            }

            public String getIs_yes() {
                return is_yes;
            }

            public void setIs_yes(String is_yes) {
                this.is_yes = is_yes;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }
    }
}

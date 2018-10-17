package com.sainti.blackcard.my.taskcenter.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/8/8.
 */

public class TaskBean {

    /**
     * result : 1
     * msg :
     * data : {"blackkey":"7.55","daytask":[{"task_id":"1","task_code":"sign","task_name":"每日打卡","task_desc":"每日打卡获取黑钥匙","task_prize":"钥匙","task_reward":"0.01","task_reward_unit":"把","task_state":"0","task_state_name":"去打卡"},{"task_id":"2","task_code":"group","task_name":"发布青黑圈子","task_desc":"发布青黑圈子，一天前3次发布可获得黑钥匙","task_prize":"钥匙","task_reward":"0.01","task_reward_unit":"把","task_state":"0","task_state_name":"去发布"}],"newtask":[{"task_id":"3","task_code":"invite","task_name":"邀请朋友加入青黑，得钥匙还得925纯银至尊戒指","task_desc":"邀请朋友加入青黑，得钥匙还得925纯银至尊戒指","task_prize":"钥匙","task_reward":"3","task_reward_unit":"把","task_state":"0","task_state_name":"去邀请"},{"task_id":"4","task_code":"follow","task_name":"关注青年黑卡公众号，参与文章互动得黑钥匙","task_desc":"关注青年黑卡公众号，参与文章互动得黑钥匙","task_prize":"钥匙","task_reward":"","task_state":"0","task_state_name":"去关注"}]}
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
         * blackkey : 7.55
         * daytask : [{"task_id":"1","task_code":"sign","task_name":"每日打卡","task_desc":"每日打卡获取黑钥匙","task_prize":"钥匙","task_reward":"0.01","task_reward_unit":"把","task_state":"0","task_state_name":"去打卡"},{"task_id":"2","task_code":"group","task_name":"发布青黑圈子","task_desc":"发布青黑圈子，一天前3次发布可获得黑钥匙","task_prize":"钥匙","task_reward":"0.01","task_reward_unit":"把","task_state":"0","task_state_name":"去发布"}]
         * newtask : [{"task_id":"3","task_code":"invite","task_name":"邀请朋友加入青黑，得钥匙还得925纯银至尊戒指","task_desc":"邀请朋友加入青黑，得钥匙还得925纯银至尊戒指","task_prize":"钥匙","task_reward":"3","task_reward_unit":"把","task_state":"0","task_state_name":"去邀请"},{"task_id":"4","task_code":"follow","task_name":"关注青年黑卡公众号，参与文章互动得黑钥匙","task_desc":"关注青年黑卡公众号，参与文章互动得黑钥匙","task_prize":"钥匙","task_reward":"","task_state":"0","task_state_name":"去关注"}]
         */

        private String blackkey;
        private List<DaytaskBean> daytask;
        private List<NewtaskBean> newtask;

        public String getBlackkey() {
            return blackkey;
        }

        public void setBlackkey(String blackkey) {
            this.blackkey = blackkey;
        }

        public List<DaytaskBean> getDaytask() {
            return daytask;
        }

        public void setDaytask(List<DaytaskBean> daytask) {
            this.daytask = daytask;
        }

        public List<NewtaskBean> getNewtask() {
            return newtask;
        }

        public void setNewtask(List<NewtaskBean> newtask) {
            this.newtask = newtask;
        }

        public static class DaytaskBean {
            /**
             * task_id : 1
             * task_code : sign
             * task_name : 每日打卡
             * task_desc : 每日打卡获取黑钥匙
             * task_prize : 钥匙
             * task_reward : 0.01
             * task_reward_unit : 把
             * task_state : 0
             * task_state_name : 去打卡
             */

            private String task_id;
            private String task_code;
            private String task_name;
            private String task_desc;
            private String task_prize;
            private String task_reward;
            private String task_reward_unit;
            private String task_state;
            private String task_state_name;

            public String getTask_id() {
                return task_id;
            }

            public void setTask_id(String task_id) {
                this.task_id = task_id;
            }

            public String getTask_code() {
                return task_code;
            }

            public void setTask_code(String task_code) {
                this.task_code = task_code;
            }

            public String getTask_name() {
                return task_name;
            }

            public void setTask_name(String task_name) {
                this.task_name = task_name;
            }

            public String getTask_desc() {
                return task_desc;
            }

            public void setTask_desc(String task_desc) {
                this.task_desc = task_desc;
            }

            public String getTask_prize() {
                return task_prize;
            }

            public void setTask_prize(String task_prize) {
                this.task_prize = task_prize;
            }

            public String getTask_reward() {
                return task_reward;
            }

            public void setTask_reward(String task_reward) {
                this.task_reward = task_reward;
            }

            public String getTask_reward_unit() {
                return task_reward_unit;
            }

            public void setTask_reward_unit(String task_reward_unit) {
                this.task_reward_unit = task_reward_unit;
            }

            public String getTask_state() {
                return task_state;
            }

            public void setTask_state(String task_state) {
                this.task_state = task_state;
            }

            public String getTask_state_name() {
                return task_state_name;
            }

            public void setTask_state_name(String task_state_name) {
                this.task_state_name = task_state_name;
            }
        }

        public static class NewtaskBean {
            /**
             * task_id : 3
             * task_code : invite
             * task_name : 邀请朋友加入青黑，得钥匙还得925纯银至尊戒指
             * task_desc : 邀请朋友加入青黑，得钥匙还得925纯银至尊戒指
             * task_prize : 钥匙
             * task_reward : 3
             * task_reward_unit : 把
             * task_state : 0
             * task_state_name : 去邀请
             */

            private String task_id;
            private String task_code;
            private String task_name;
            private String task_desc;
            private String task_prize;
            private String task_reward;
            private String task_reward_unit;
            private String task_state;
            private String task_state_name;

            public String getTask_id() {
                return task_id;
            }

            public void setTask_id(String task_id) {
                this.task_id = task_id;
            }

            public String getTask_code() {
                return task_code;
            }

            public void setTask_code(String task_code) {
                this.task_code = task_code;
            }

            public String getTask_name() {
                return task_name;
            }

            public void setTask_name(String task_name) {
                this.task_name = task_name;
            }

            public String getTask_desc() {
                return task_desc;
            }

            public void setTask_desc(String task_desc) {
                this.task_desc = task_desc;
            }

            public String getTask_prize() {
                return task_prize;
            }

            public void setTask_prize(String task_prize) {
                this.task_prize = task_prize;
            }

            public String getTask_reward() {
                return task_reward;
            }

            public void setTask_reward(String task_reward) {
                this.task_reward = task_reward;
            }

            public String getTask_reward_unit() {
                return task_reward_unit;
            }

            public void setTask_reward_unit(String task_reward_unit) {
                this.task_reward_unit = task_reward_unit;
            }

            public String getTask_state() {
                return task_state;
            }

            public void setTask_state(String task_state) {
                this.task_state = task_state;
            }

            public String getTask_state_name() {
                return task_state_name;
            }

            public void setTask_state_name(String task_state_name) {
                this.task_state_name = task_state_name;
            }
        }
    }
}

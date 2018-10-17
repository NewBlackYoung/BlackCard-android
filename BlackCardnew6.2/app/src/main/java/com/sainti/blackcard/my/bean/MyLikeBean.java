package com.sainti.blackcard.my.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/5/7.
 */

public class MyLikeBean {


    /**
     * result : 1
     * msg :
     * data : {"all":[{"ho_id":"1","ho_name":"摄影","selected":0},{"ho_id":"2","ho_name":"美食","selected":0},{"ho_id":"3","ho_name":"旅游","selected":0},{"ho_id":"4","ho_name":"网购","selected":0},{"ho_id":"5","ho_name":"手工","selected":0},{"ho_id":"6","ho_name":"风尚","selected":0},{"ho_id":"7","ho_name":"设计","selected":0},{"ho_id":"8","ho_name":"软件","selected":0},{"ho_id":"9","ho_name":"科技","selected":0},{"ho_id":"10","ho_name":"运动","selected":0},{"ho_id":"11","ho_name":"泡吧","selected":0},{"ho_id":"12","ho_name":"动漫","selected":0},{"ho_id":"13","ho_name":"小资","selected":0},{"ho_id":"14","ho_name":"篮球","selected":0},{"ho_id":"15","ho_name":"网球","selected":0},{"ho_id":"16","ho_name":"桌球","selected":0}]}
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
        private List<AllBean> all;

        public List<AllBean> getAll() {
            return all;
        }

        public void setAll(List<AllBean> all) {
            this.all = all;
        }

        public static class AllBean {
            /**
             * ho_id : 1
             * ho_name : 摄影
             * selected : 0
             */

            private String ho_id;
            private String ho_name;
            private int selected;

            public String getHo_id() {
                return ho_id;
            }

            public void setHo_id(String ho_id) {
                this.ho_id = ho_id;
            }

            public String getHo_name() {
                return ho_name;
            }

            public void setHo_name(String ho_name) {
                this.ho_name = ho_name;
            }

            public int getSelected() {
                return selected;
            }

            public void setSelected(int selected) {
                this.selected = selected;
            }
        }
    }
}

package com.sainti.blackcard.coffee.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/3/15.
 */

public class LeftCoffeeBean {


    /**
     * itemsList : [{"cg_name":"咖啡and茶","cg_id":19},{"cg_name":"星冰乐","cg_id":20},{"cg_name":"糕点","cg_id":21},{"cg_name":"咖啡附加品","cg_id":22},{"cg_name":"春意新品","cg_id":70}]
     * resultmsg : 成功
     * resultcode : 0000
     */

    private String resultmsg;
    private String resultcode;
    private List<ItemsListBean> itemsList;

    public String getResultmsg() {
        return resultmsg;
    }

    public void setResultmsg(String resultmsg) {
        this.resultmsg = resultmsg;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public List<ItemsListBean> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<ItemsListBean> itemsList) {
        this.itemsList = itemsList;
    }

    public static class ItemsListBean {
        /**
         * cg_name : 咖啡and茶
         * cg_id : 19
         */

        private String cg_name;
        private int cg_id;

        public String getCg_name() {
            return cg_name;
        }

        public void setCg_name(String cg_name) {
            this.cg_name = cg_name;
        }

        public int getCg_id() {
            return cg_id;
        }

        public void setCg_id(int cg_id) {
            this.cg_id = cg_id;
        }
    }
}

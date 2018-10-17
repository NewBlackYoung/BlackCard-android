package com.sainti.blackcard.goodthings.spcard.baen;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/3/29.
 */

public class ShoppingCardBean {


    /**
     * result : 1
     * msg :
     * data : [{"w_id":"328","w_num":"1","w_name":"日本梅乃宿柚子酒","w_img":"http://s.qing-hei.com/Public/Uploads/5abc84d62a8cb.jpg","w_price":"258","w_o_price":"328","w_sale":"0","w_kind":"","w_kindid":"0"},{"w_id":"330","w_num":"1","w_name":"缓解眼干涩 美国润眼喷雾","w_img":"http://s.qing-hei.com/Public/Uploads/5ab9b7fda1ca4.jpg","w_price":"198","w_o_price":"258","w_sale":"0","w_kind":"","w_kindid":"0"},{"w_id":"152","w_num":"1","w_name":"澳洲solid男士固体香水","w_img":"http://s.qing-hei.com/Public/Uploads/5a8e67b556dce.jpg","w_price":"148","w_o_price":"189","w_sale":"1","w_kind":"","w_kindid":"0"},{"w_id":"334","w_num":"1","w_name":"【青黑特辑】洗护专场","w_img":"http://s.qing-hei.com/Public/Uploads/5abd9f60dbbc0.png","w_price":"359.00","w_o_price":"599","w_sale":"1","w_kind":"套餐四","w_kindid":"468"}]
     */

    private String result;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * w_id : 328
         * w_num : 1
         * w_name : 日本梅乃宿柚子酒
         * w_img : http://s.qing-hei.com/Public/Uploads/5abc84d62a8cb.jpg
         * w_price : 258
         * w_o_price : 328
         * w_sale : 0
         * w_kind :
         * w_kindid : 0
         */

        private String w_id;
        private String w_num;
        private String w_name;
        private String w_img;
        private String w_price;
        private String w_o_price;
        private String w_sale;
        private String w_kind;
        private String w_kindid;

        public String getW_id() {
            return w_id;
        }

        public void setW_id(String w_id) {
            this.w_id = w_id;
        }

        public String getW_num() {
            return w_num;
        }

        public void setW_num(String w_num) {
            this.w_num = w_num;
        }

        public String getW_name() {
            return w_name;
        }

        public void setW_name(String w_name) {
            this.w_name = w_name;
        }

        public String getW_img() {
            return w_img;
        }

        public void setW_img(String w_img) {
            this.w_img = w_img;
        }

        public String getW_price() {
            return w_price;
        }

        public void setW_price(String w_price) {
            this.w_price = w_price;
        }

        public String getW_o_price() {
            return w_o_price;
        }

        public void setW_o_price(String w_o_price) {
            this.w_o_price = w_o_price;
        }

        public String getW_sale() {
            return w_sale;
        }

        public void setW_sale(String w_sale) {
            this.w_sale = w_sale;
        }

        public String getW_kind() {
            return w_kind;
        }

        public void setW_kind(String w_kind) {
            this.w_kind = w_kind;
        }

        public String getW_kindid() {
            return w_kindid;
        }

        public void setW_kindid(String w_kindid) {
            this.w_kindid = w_kindid;
        }
    }
}

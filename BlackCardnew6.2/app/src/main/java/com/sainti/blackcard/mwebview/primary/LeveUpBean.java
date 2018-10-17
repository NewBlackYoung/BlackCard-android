package com.sainti.blackcard.mwebview.primary;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/6/13.
 */

public class LeveUpBean {


    /**
     * result : 1
     * msg :
     * data : {"user_id":"319099","user_name":"陈国辉","user_type":"1","user_card":"15724377399","user_tel":"15724377399","user_viptime":"","user_piny":"CHEN GUO HUI","card_sn_split":"1572 4377 399","user_type_name":"精英会籍","is_vip":0,"is_levelup":"1","levelup_data":[{"type_id":"3","type_name":"云逸会籍","type_price":"2800.00"}]}
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
         * user_id : 319099
         * user_name : 陈国辉
         * user_type : 1
         * user_card : 15724377399
         * user_tel : 15724377399
         * user_viptime :
         * user_piny : CHEN GUO HUI
         * card_sn_split : 1572 4377 399
         * user_type_name : 精英会籍
         * is_vip : 0
         * is_levelup : 1
         * levelup_data : [{"type_id":"3","type_name":"云逸会籍","type_price":"2800.00"}]
         */

        private String user_id;
        private String user_name;
        private String user_type;
        private String user_card;
        private String user_tel;
        private String user_viptime;
        private String user_piny;
        private String card_sn_split;
        private String user_type_name;
        private int is_vip;
        private String is_levelup;
        private List<LevelupDataBean> levelup_data;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getUser_card() {
            return user_card;
        }

        public void setUser_card(String user_card) {
            this.user_card = user_card;
        }

        public String getUser_tel() {
            return user_tel;
        }

        public void setUser_tel(String user_tel) {
            this.user_tel = user_tel;
        }

        public String getUser_viptime() {
            return user_viptime;
        }

        public void setUser_viptime(String user_viptime) {
            this.user_viptime = user_viptime;
        }

        public String getUser_piny() {
            return user_piny;
        }

        public void setUser_piny(String user_piny) {
            this.user_piny = user_piny;
        }

        public String getCard_sn_split() {
            return card_sn_split;
        }

        public void setCard_sn_split(String card_sn_split) {
            this.card_sn_split = card_sn_split;
        }

        public String getUser_type_name() {
            return user_type_name;
        }

        public void setUser_type_name(String user_type_name) {
            this.user_type_name = user_type_name;
        }

        public int getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(int is_vip) {
            this.is_vip = is_vip;
        }

        public String getIs_levelup() {
            return is_levelup;
        }

        public void setIs_levelup(String is_levelup) {
            this.is_levelup = is_levelup;
        }

        public List<LevelupDataBean> getLevelup_data() {
            return levelup_data;
        }

        public void setLevelup_data(List<LevelupDataBean> levelup_data) {
            this.levelup_data = levelup_data;
        }

        public static class LevelupDataBean {
            /**
             * type_id : 3
             * type_name : 云逸会籍
             * type_price : 2800.00
             */

            private String type_id;
            private String type_name;
            private String type_price;

            public String getType_id() {
                return type_id;
            }

            public void setType_id(String type_id) {
                this.type_id = type_id;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getType_price() {
                return type_price;
            }

            public void setType_price(String type_price) {
                this.type_price = type_price;
            }
        }
    }
}

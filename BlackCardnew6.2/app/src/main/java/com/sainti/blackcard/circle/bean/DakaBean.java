package com.sainti.blackcard.circle.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/6/25.
 */

public class DakaBean {


    /**
     * result : 1
     * msg :
     * data : {"days":[{"balckkey":"0.01","day":"1","is_sign":"1","sign_time":1529895797,"sign_date":"2018-06-25","is_curday":"1"},{"balckkey":"0","day":"2","is_sign":"0","sign_time":1529982197,"sign_date":"2018-06-26","is_curday":"0"},{"balckkey":"0","day":"3","is_sign":"0","sign_time":1530068597,"sign_date":"2018-06-27","is_curday":"0"},{"balckkey":"0","day":"4","is_sign":"0","sign_time":1530154997,"sign_date":"2018-06-28","is_curday":"0"},{"balckkey":"0","day":"5","is_sign":"0","sign_time":1530241397,"sign_date":"2018-06-29","is_curday":"0"},{"balckkey":"0","day":"6","is_sign":"0","sign_time":1530327797,"sign_date":"2018-06-30","is_curday":"0"},{"balckkey":"0","day":"7","is_sign":"0","sign_time":1530414197,"sign_date":"2018-07-01","is_curday":"0"}],"is_allowsign":"0","cur_blackkey":"0.01","con_blackkey":"0.04","con_day":"7","total_balckkey":"0.1"}
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
         * days : [{"balckkey":"0.01","day":"1","is_sign":"1","sign_time":1529895797,"sign_date":"2018-06-25","is_curday":"1"},{"balckkey":"0","day":"2","is_sign":"0","sign_time":1529982197,"sign_date":"2018-06-26","is_curday":"0"},{"balckkey":"0","day":"3","is_sign":"0","sign_time":1530068597,"sign_date":"2018-06-27","is_curday":"0"},{"balckkey":"0","day":"4","is_sign":"0","sign_time":1530154997,"sign_date":"2018-06-28","is_curday":"0"},{"balckkey":"0","day":"5","is_sign":"0","sign_time":1530241397,"sign_date":"2018-06-29","is_curday":"0"},{"balckkey":"0","day":"6","is_sign":"0","sign_time":1530327797,"sign_date":"2018-06-30","is_curday":"0"},{"balckkey":"0","day":"7","is_sign":"0","sign_time":1530414197,"sign_date":"2018-07-01","is_curday":"0"}]
         * is_allowsign : 0
         * cur_blackkey : 0.01
         * con_blackkey : 0.04
         * con_day : 7
         * total_balckkey : 0.1
         */

        private String is_allowsign;
        private String cur_blackkey;
        private String con_blackkey;
        private String con_day;
        private String total_balckkey;
        private List<DaysBean> days;

        public String getIs_allowsign() {
            return is_allowsign;
        }

        public void setIs_allowsign(String is_allowsign) {
            this.is_allowsign = is_allowsign;
        }

        public String getCur_blackkey() {
            return cur_blackkey;
        }

        public void setCur_blackkey(String cur_blackkey) {
            this.cur_blackkey = cur_blackkey;
        }

        public String getCon_blackkey() {
            return con_blackkey;
        }

        public void setCon_blackkey(String con_blackkey) {
            this.con_blackkey = con_blackkey;
        }

        public String getCon_day() {
            return con_day;
        }

        public void setCon_day(String con_day) {
            this.con_day = con_day;
        }

        public String getTotal_balckkey() {
            return total_balckkey;
        }

        public void setTotal_balckkey(String total_balckkey) {
            this.total_balckkey = total_balckkey;
        }

        public List<DaysBean> getDays() {
            return days;
        }

        public void setDays(List<DaysBean> days) {
            this.days = days;
        }

        public static class DaysBean {
            /**
             * balckkey : 0.01
             * day : 1
             * is_sign : 1
             * sign_time : 1529895797
             * sign_date : 2018-06-25
             * is_curday : 1
             */

            private String balckkey;
            private String day;
            private String is_sign;
            private int sign_time;
            private String sign_date;
            private String is_curday;

            public String getBalckkey() {
                return balckkey;
            }

            public void setBalckkey(String balckkey) {
                this.balckkey = balckkey;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getIs_sign() {
                return is_sign;
            }

            public void setIs_sign(String is_sign) {
                this.is_sign = is_sign;
            }

            public int getSign_time() {
                return sign_time;
            }

            public void setSign_time(int sign_time) {
                this.sign_time = sign_time;
            }

            public String getSign_date() {
                return sign_date;
            }

            public void setSign_date(String sign_date) {
                this.sign_date = sign_date;
            }

            public String getIs_curday() {
                return is_curday;
            }

            public void setIs_curday(String is_curday) {
                this.is_curday = is_curday;
            }
        }
    }
}

package com.sainti.blackcard.coffee.coffeeorder.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/5/14.
 */

public class CoffeelistBean {


    /**
     * result : 1
     * msg :
     * data : [{"xf_id":"267","xf_uid":"319099","xf_price":"47.99","xf_list":{"orders":[{"fi_start_deliverytime":" ","fi_pay_amt":"47.989998","fi_name":"陈国辉","fi_area":"大连市","fi_address":"中山区昆仑写字间610","fi_note":"","fi_mobile":"15724377399"}],"orderdetails":[{"or_num":"1","or_price":"43.0","or_name":"酷黑摩卡奶香星冰乐MMCRM 超大杯"}]},"xf_time":"2018-05-04 19:20:44","xf_shipping_time":"0","xf_state":0,"xf_payment":"0","xf_orderid":"","xf_order_sn":"QHCP50432844138035052","api_fi_tid":"","user_card":"15724377399","user_name":"陈国辉","status":"待付款"},{"xf_id":"266","xf_uid":"319099","xf_price":"32.99","xf_list":{"orders":[{"fi_start_deliverytime":" ","fi_pay_amt":"32.989998","fi_name":"陈国辉","fi_area":"大连市","fi_address":"中山区昆仑写字间610","fi_note":"","fi_mobile":"15724377399"}],"orderdetails":[{"or_num":"1","or_price":"28.0","or_name":"豆奶拿铁S,L 热中杯"}]},"xf_time":"2018-05-04 19:06:10","xf_shipping_time":"0","xf_state":0,"xf_payment":"0","xf_orderid":"","xf_order_sn":"QHCP50431970505771061","api_fi_tid":"","user_card":"15724377399","user_name":"陈国辉","status":"待付款"},{"xf_id":"247","xf_uid":"319099","xf_price":"40.99","xf_list":{"orders":[{"fi_start_deliverytime":" ","fi_pay_amt":"40.989998","fi_name":"陈国辉","fi_area":"大连市","fi_address":"中山区昆仑写字间610","fi_note":"","fi_tid":"QHCP40963253947565086","fi_mobile":"15724377399"}],"orderdetails":[{"or_num":"1","or_price":"36.0","or_name":"馥芮白FW 热大杯","or_tid":"QHCP40963253947565086"}]},"xf_time":"2018-04-09 16:40:55","xf_shipping_time":"0","xf_state":0,"xf_payment":"0","xf_orderid":"","xf_order_sn":"QHCP40963255584555086","api_fi_tid":"","user_card":"15724377399","user_name":"陈国辉","status":"待付款"},{"xf_id":"246","xf_uid":"319099","xf_price":"40.99","xf_list":{"orders":[{"fi_start_deliverytime":" ","fi_pay_amt":"40.989998","fi_name":"陈国辉","fi_area":"大连市","fi_address":"中山区昆仑写字间610","fi_note":"","fi_tid":"QHCP40963240879709099","fi_mobile":"15724377399"}],"orderdetails":[{"or_num":"1","or_price":"36.0","or_name":"馥芮白FW 热大杯","or_tid":"QHCP40963240879709099"}]},"xf_time":"2018-04-09 16:40:53","xf_shipping_time":"0","xf_state":0,"xf_payment":"0","xf_orderid":"","xf_order_sn":"QHCP40963253947565086","api_fi_tid":"","user_card":"15724377399","user_name":"陈国辉","status":"待付款"},{"xf_id":"245","xf_uid":"319099","xf_price":"40.99","xf_list":{"orders":[{"fi_start_deliverytime":" ","fi_pay_amt":"40.989998","fi_name":"陈国辉","fi_area":"大连市","fi_address":"中山区昆仑写字间610","fi_note":"","fi_mobile":"15724377399"}],"orderdetails":[{"or_num":"1","or_price":"36.0","or_name":"馥芮白FW 热大杯"}]},"xf_time":"2018-04-09 16:40:40","xf_shipping_time":"0","xf_state":0,"xf_payment":"0","xf_orderid":"","xf_order_sn":"QHCP40963240879709099","api_fi_tid":"","user_card":"15724377399","user_name":"陈国辉","status":"待付款"},{"xf_id":"244","xf_uid":"319099","xf_price":"40.99","xf_list":{"orders":[{"fi_start_deliverytime":" ","fi_pay_amt":"40.989998","fi_name":"陈国辉","fi_area":"大连市","fi_address":"中山区昆仑写字间610","fi_note":"","fi_mobile":"15724377399"}],"orderdetails":[{"or_num":"1","or_price":"36.0","or_name":"香草馥芮白V,FW 热中杯"}]},"xf_time":"2018-04-09 12:24:50","xf_shipping_time":"0","xf_state":0,"xf_payment":"0","xf_orderid":"","xf_order_sn":"QHCP40947890520393056","api_fi_tid":"","user_card":"15724377399","user_name":"陈国辉","status":"待付款"},{"xf_id":"243","xf_uid":"319099","xf_price":"37.99","xf_list":{"orders":[{"fi_start_deliverytime":" ","fi_pay_amt":"37.989998","fi_name":"陈国辉","fi_area":"大连市","fi_address":"中山区昆仑写字间610","fi_note":"","fi_mobile":"15724377399"}],"orderdetails":[{"or_num":"1","or_price":"33.0","or_name":"馥芮白FW 热中杯"}]},"xf_time":"2018-04-09 12:08:30","xf_shipping_time":"0","xf_state":0,"xf_payment":"0","xf_orderid":"","xf_order_sn":"QHCP40946910118912065","api_fi_tid":"","user_card":"15724377399","user_name":"陈国辉","status":"待付款"}]
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
         * xf_id : 267
         * xf_uid : 319099
         * xf_price : 47.99
         * xf_list : {"orders":[{"fi_start_deliverytime":" ","fi_pay_amt":"47.989998","fi_name":"陈国辉","fi_area":"大连市","fi_address":"中山区昆仑写字间610","fi_note":"","fi_mobile":"15724377399"}],"orderdetails":[{"or_num":"1","or_price":"43.0","or_name":"酷黑摩卡奶香星冰乐MMCRM 超大杯"}]}
         * xf_time : 2018-05-04 19:20:44
         * xf_shipping_time : 0
         * xf_state : 0
         * xf_payment : 0
         * xf_orderid :
         * xf_order_sn : QHCP50432844138035052
         * api_fi_tid :
         * user_card : 15724377399
         * user_name : 陈国辉
         * status : 待付款
         */

        private String xf_id;
        private String xf_uid;
        private String xf_price;
        private XfListBean xf_list;
        private String xf_time;
        private String xf_shipping_time;
        private int xf_state;
        private String xf_payment;
        private String xf_orderid;
        private String xf_order_sn;
        private String api_fi_tid;
        private String user_card;
        private String user_name;
        private String status;

        public String getXf_id() {
            return xf_id;
        }

        public void setXf_id(String xf_id) {
            this.xf_id = xf_id;
        }

        public String getXf_uid() {
            return xf_uid;
        }

        public void setXf_uid(String xf_uid) {
            this.xf_uid = xf_uid;
        }

        public String getXf_price() {
            return xf_price;
        }

        public void setXf_price(String xf_price) {
            this.xf_price = xf_price;
        }

        public XfListBean getXf_list() {
            return xf_list;
        }

        public void setXf_list(XfListBean xf_list) {
            this.xf_list = xf_list;
        }

        public String getXf_time() {
            return xf_time;
        }

        public void setXf_time(String xf_time) {
            this.xf_time = xf_time;
        }

        public String getXf_shipping_time() {
            return xf_shipping_time;
        }

        public void setXf_shipping_time(String xf_shipping_time) {
            this.xf_shipping_time = xf_shipping_time;
        }

        public int getXf_state() {
            return xf_state;
        }

        public void setXf_state(int xf_state) {
            this.xf_state = xf_state;
        }

        public String getXf_payment() {
            return xf_payment;
        }

        public void setXf_payment(String xf_payment) {
            this.xf_payment = xf_payment;
        }

        public String getXf_orderid() {
            return xf_orderid;
        }

        public void setXf_orderid(String xf_orderid) {
            this.xf_orderid = xf_orderid;
        }

        public String getXf_order_sn() {
            return xf_order_sn;
        }

        public void setXf_order_sn(String xf_order_sn) {
            this.xf_order_sn = xf_order_sn;
        }

        public String getApi_fi_tid() {
            return api_fi_tid;
        }

        public void setApi_fi_tid(String api_fi_tid) {
            this.api_fi_tid = api_fi_tid;
        }

        public String getUser_card() {
            return user_card;
        }

        public void setUser_card(String user_card) {
            this.user_card = user_card;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public static class XfListBean {
            private List<OrdersBean> orders;
            private List<OrderdetailsBean> orderdetails;

            public List<OrdersBean> getOrders() {
                return orders;
            }

            public void setOrders(List<OrdersBean> orders) {
                this.orders = orders;
            }

            public List<OrderdetailsBean> getOrderdetails() {
                return orderdetails;
            }

            public void setOrderdetails(List<OrderdetailsBean> orderdetails) {
                this.orderdetails = orderdetails;
            }

            public static class OrdersBean {
                /**
                 * fi_start_deliverytime :
                 * fi_pay_amt : 47.989998
                 * fi_name : 陈国辉
                 * fi_area : 大连市
                 * fi_address : 中山区昆仑写字间610
                 * fi_note :
                 * fi_mobile : 15724377399
                 */

                private String fi_start_deliverytime;
                private String fi_pay_amt;
                private String fi_name;
                private String fi_area;
                private String fi_address;
                private String fi_note;
                private String fi_mobile;

                public String getFi_start_deliverytime() {
                    return fi_start_deliverytime;
                }

                public void setFi_start_deliverytime(String fi_start_deliverytime) {
                    this.fi_start_deliverytime = fi_start_deliverytime;
                }

                public String getFi_pay_amt() {
                    return fi_pay_amt;
                }

                public void setFi_pay_amt(String fi_pay_amt) {
                    this.fi_pay_amt = fi_pay_amt;
                }

                public String getFi_name() {
                    return fi_name;
                }

                public void setFi_name(String fi_name) {
                    this.fi_name = fi_name;
                }

                public String getFi_area() {
                    return fi_area;
                }

                public void setFi_area(String fi_area) {
                    this.fi_area = fi_area;
                }

                public String getFi_address() {
                    return fi_address;
                }

                public void setFi_address(String fi_address) {
                    this.fi_address = fi_address;
                }

                public String getFi_note() {
                    return fi_note;
                }

                public void setFi_note(String fi_note) {
                    this.fi_note = fi_note;
                }

                public String getFi_mobile() {
                    return fi_mobile;
                }

                public void setFi_mobile(String fi_mobile) {
                    this.fi_mobile = fi_mobile;
                }
            }

            public static class OrderdetailsBean {
                /**
                 * or_num : 1
                 * or_price : 43.0
                 * or_name : 酷黑摩卡奶香星冰乐MMCRM 超大杯
                 */

                private String or_num;
                private String or_price;
                private String or_name;

                public String getOr_num() {
                    return or_num;
                }

                public void setOr_num(String or_num) {
                    this.or_num = or_num;
                }

                public String getOr_price() {
                    return or_price;
                }

                public void setOr_price(String or_price) {
                    this.or_price = or_price;
                }

                public String getOr_name() {
                    return or_name;
                }

                public void setOr_name(String or_name) {
                    this.or_name = or_name;
                }
            }
        }
    }
}

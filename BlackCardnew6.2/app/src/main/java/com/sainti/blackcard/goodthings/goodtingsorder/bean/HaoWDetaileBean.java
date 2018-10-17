package com.sainti.blackcard.goodthings.goodtingsorder.bean;

/**
 * Created by YuZhenpeng on 2018/5/11.
 */

public class HaoWDetaileBean {

    /**
     * result : 1
     * msg :
     * data : {"order_id":"32359","order_sn":"QHCF5100789247959","product_id":"364","product_name":"日本手工皂之父Eiichi Ishino EI礼盒套装","product_price":"188.00","order_amount":"188.00","add_time":"2018-05-10 17:04","buy_number":"1","type_id":"589","bonus_id":"0","pay_sn":"","wo_blackkey":"0.00","wo_kuaidi":"","tuikuanchuli":"0","is_tuikuan":"0","wo_status":"3","consignee":"陈国会","mobile":"2","address":"山东省济南市市中区兔兔","kuaidi_sn":"","user_note":"","admin_note":"","shipping_fee":"0.00","payment":"0","shipping_name":"中通包邮","is_pay":0,"is_del":1,"state":3,"status":"已失效","spec_name":"套装","product_img":"http://s.qing-hei.com/Public/Uploads/5aec0b08733c1.jpg","youhq_money":"0.00","payment_name":"微信"}
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
         * order_id : 32359
         * order_sn : QHCF5100789247959
         * product_id : 364
         * product_name : 日本手工皂之父Eiichi Ishino EI礼盒套装
         * product_price : 188.00
         * order_amount : 188.00
         * add_time : 2018-05-10 17:04
         * buy_number : 1
         * type_id : 589
         * bonus_id : 0
         * pay_sn :
         * wo_blackkey : 0.00
         * wo_kuaidi :
         * tuikuanchuli : 0
         * is_tuikuan : 0
         * wo_status : 3
         * consignee : 陈国会
         * mobile : 2
         * address : 山东省济南市市中区兔兔
         * kuaidi_sn :
         * user_note :
         * admin_note :
         * shipping_fee : 0.00
         * payment : 0
         * shipping_name : 中通包邮
         * is_pay : 0
         * is_del : 1
         * state : 3
         * status : 已失效
         * spec_name : 套装
         * product_img : http://s.qing-hei.com/Public/Uploads/5aec0b08733c1.jpg
         * youhq_money : 0.00
         * payment_name : 微信
         */

        private String order_id;
        private String order_sn;
        private String product_id;
        private String product_name;
        private String product_price;
        private String order_amount;
        private String add_time;
        private String buy_number;
        private String type_id;
        private String bonus_id;
        private String pay_sn;
        private String wo_blackkey;
        private String wo_kuaidi;
        private String tuikuanchuli;
        private String is_tuikuan;
        private String wo_status;
        private String consignee;
        private String mobile;
        private String address;
        private String kuaidi_sn;
        private String user_note;
        private String admin_note;
        private String shipping_fee;
        private String payment;
        private String shipping_name;
        private int is_pay;
        private int is_del;
        private int state;
        private String status;
        private String spec_name;
        private String product_img;
        private String youhq_money;
        private String payment_name;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getProduct_price() {
            return product_price;
        }

        public void setProduct_price(String product_price) {
            this.product_price = product_price;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getBuy_number() {
            return buy_number;
        }

        public void setBuy_number(String buy_number) {
            this.buy_number = buy_number;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public String getBonus_id() {
            return bonus_id;
        }

        public void setBonus_id(String bonus_id) {
            this.bonus_id = bonus_id;
        }

        public String getPay_sn() {
            return pay_sn;
        }

        public void setPay_sn(String pay_sn) {
            this.pay_sn = pay_sn;
        }

        public String getWo_blackkey() {
            return wo_blackkey;
        }

        public void setWo_blackkey(String wo_blackkey) {
            this.wo_blackkey = wo_blackkey;
        }

        public String getWo_kuaidi() {
            return wo_kuaidi;
        }

        public void setWo_kuaidi(String wo_kuaidi) {
            this.wo_kuaidi = wo_kuaidi;
        }

        public String getTuikuanchuli() {
            return tuikuanchuli;
        }

        public void setTuikuanchuli(String tuikuanchuli) {
            this.tuikuanchuli = tuikuanchuli;
        }

        public String getIs_tuikuan() {
            return is_tuikuan;
        }

        public void setIs_tuikuan(String is_tuikuan) {
            this.is_tuikuan = is_tuikuan;
        }

        public String getWo_status() {
            return wo_status;
        }

        public void setWo_status(String wo_status) {
            this.wo_status = wo_status;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getKuaidi_sn() {
            return kuaidi_sn;
        }

        public void setKuaidi_sn(String kuaidi_sn) {
            this.kuaidi_sn = kuaidi_sn;
        }

        public String getUser_note() {
            return user_note;
        }

        public void setUser_note(String user_note) {
            this.user_note = user_note;
        }

        public String getAdmin_note() {
            return admin_note;
        }

        public void setAdmin_note(String admin_note) {
            this.admin_note = admin_note;
        }

        public String getShipping_fee() {
            return shipping_fee;
        }

        public void setShipping_fee(String shipping_fee) {
            this.shipping_fee = shipping_fee;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public String getShipping_name() {
            return shipping_name;
        }

        public void setShipping_name(String shipping_name) {
            this.shipping_name = shipping_name;
        }

        public int getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(int is_pay) {
            this.is_pay = is_pay;
        }

        public int getIs_del() {
            return is_del;
        }

        public void setIs_del(int is_del) {
            this.is_del = is_del;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSpec_name() {
            return spec_name;
        }

        public void setSpec_name(String spec_name) {
            this.spec_name = spec_name;
        }

        public String getProduct_img() {
            return product_img;
        }

        public void setProduct_img(String product_img) {
            this.product_img = product_img;
        }

        public String getYouhq_money() {
            return youhq_money;
        }

        public void setYouhq_money(String youhq_money) {
            this.youhq_money = youhq_money;
        }

        public String getPayment_name() {
            return payment_name;
        }

        public void setPayment_name(String payment_name) {
            this.payment_name = payment_name;
        }
    }
}

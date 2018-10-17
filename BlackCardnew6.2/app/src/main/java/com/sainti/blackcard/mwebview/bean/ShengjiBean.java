package com.sainti.blackcard.mwebview.bean;

/**
 * Created by YuZhenpeng on 2018/8/9.
 */

public class ShengjiBean {


    /**
     * Ordersn : QHCK80982902480403085
     * Ordername : 精英终身会籍+随机选号+不定制姓名=199元
     * Orderprice : 199.00
     * payment : wechat
     */

    private String Ordersn;
    private String Ordername;
    private String Orderprice;
    private String payment;

    public String getOrdersn() {
        return Ordersn;
    }

    public void setOrdersn(String Ordersn) {
        this.Ordersn = Ordersn;
    }

    public String getOrdername() {
        return Ordername;
    }

    public void setOrdername(String Ordername) {
        this.Ordername = Ordername;
    }

    public String getOrderprice() {
        return Orderprice;
    }

    public void setOrderprice(String Orderprice) {
        this.Orderprice = Orderprice;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}

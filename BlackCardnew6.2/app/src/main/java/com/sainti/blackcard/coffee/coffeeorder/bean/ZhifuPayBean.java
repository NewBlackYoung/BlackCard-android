package com.sainti.blackcard.coffee.coffeeorder.bean;

/**
 * Created by YuZhenpeng on 2018/5/14.
 */

public class ZhifuPayBean {

    /**
     * result : 1
     * msg :
     * data : QHCP51568910397416016
     * alipay_data : alipay_sdk=alipay-sdk-php-20161101&app_id=2018011101773478&biz_content=%7B%22body%22%3A%22%25E5%2592%2596%25E5%2595%25A1%25E8%25AE%25A2%25E5%258D%2595%25E6%2594%25AF%25E4%25BB%2598%22%2C%22subject%22%3A%22%25E5%2592%2596%25E5%2595%25A1%25E8%25AE%25A2%25E5%258D%2595%25E6%2594%25AF%25E4%25BB%2598%22%2C%22out_trade_no%22%3A%22QHCP51568910397416016%22%2C%22total_amount%22%3A%2276.99%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22timeout_express%22%3A%2215m%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fwww.qing-hei.com%2Fmobile.php%2Findex%2Fnew_alipay_notifyUrl&sign_type=RSA2&timestamp=2018-05-15+15%3A37%3A40&version=1.0&sign=mFulLBVnOGlo%2FAlhISxF7l1nuWmTj9ide8c%2BciMZ3j9Ic3OHAEDmmL8%2B%2BB%2FLEOqG69RiF8e64TQbmx%2BAeZCxlMg52LUECtTdK45%2BU8PS3Hbf52fH5QX53mTarW2jH4uBiyvTHGb62O39yH%2BfkcZyf4pKZjuO3N7nM7kcq%2FX9oqA%2FjKHjcheoGgAM%2BUuu6HjA9PIqkMVbenSv%2BphvwozIsVaDA0XAbpj290N8jzBwxtsZH6UIh2inZ9YqLKNUH8ICW48T8MguBk36fAj41jRWLQhWtmG4Xne3IRR47eJ3IQ6wKDmhlaPCAXkE9C%2BVR7Y5rHVR1IdYiKIwNb2d4UgzmQ%3D%3D
     * pay_data : alipay_sdk=alipay-sdk-php-20161101&app_id=2018011101773478&biz_content=%7B%22body%22%3A%22%25E5%2592%2596%25E5%2595%25A1%25E8%25AE%25A2%25E5%258D%2595%25E6%2594%25AF%25E4%25BB%2598%22%2C%22subject%22%3A%22%25E5%2592%2596%25E5%2595%25A1%25E8%25AE%25A2%25E5%258D%2595%25E6%2594%25AF%25E4%25BB%2598%22%2C%22out_trade_no%22%3A%22QHCP51568910397416016%22%2C%22total_amount%22%3A%2276.99%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22timeout_express%22%3A%2215m%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fwww.qing-hei.com%2Fmobile.php%2Findex%2Fnew_alipay_notifyUrl&sign_type=RSA2&timestamp=2018-05-15+15%3A37%3A40&version=1.0&sign=mFulLBVnOGlo%2FAlhISxF7l1nuWmTj9ide8c%2BciMZ3j9Ic3OHAEDmmL8%2B%2BB%2FLEOqG69RiF8e64TQbmx%2BAeZCxlMg52LUECtTdK45%2BU8PS3Hbf52fH5QX53mTarW2jH4uBiyvTHGb62O39yH%2BfkcZyf4pKZjuO3N7nM7kcq%2FX9oqA%2FjKHjcheoGgAM%2BUuu6HjA9PIqkMVbenSv%2BphvwozIsVaDA0XAbpj290N8jzBwxtsZH6UIh2inZ9YqLKNUH8ICW48T8MguBk36fAj41jRWLQhWtmG4Xne3IRR47eJ3IQ6wKDmhlaPCAXkE9C%2BVR7Y5rHVR1IdYiKIwNb2d4UgzmQ%3D%3D
     */

    private String result;
    private String msg;
    private String data;
    private String alipay_data;
    private String pay_data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAlipay_data() {
        return alipay_data;
    }

    public void setAlipay_data(String alipay_data) {
        this.alipay_data = alipay_data;
    }

    public String getPay_data() {
        return pay_data;
    }

    public void setPay_data(String pay_data) {
        this.pay_data = pay_data;
    }
}

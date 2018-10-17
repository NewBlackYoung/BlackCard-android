package com.sainti.blackcard.coffee.bean;

/**
 * Created by YuZhenpeng on 2018/3/19.
 */

public class ZhifubaoPayBean {

    /**
     * result : 1
     * msg :
     * data : QHCCO31937630165179090
     * alipay_data : alipay_sdk=alipay-sdk-php-20161101&amp;app_id=2018011101773478&amp;biz_content=%7B%22body%22%3A%22%5Cu5496%5Cu5561%5Cu8ba2%5Cu5355%5Cu652f%5Cu4ed8%22%2C%22subject%22%3A%22%5Cu5496%5Cu5561%5Cu8ba2%5Cu5355%5Cu652f%5Cu4ed8%22%2C%22out_trade_no%22%3A%22QHCCO31937630165179090%22%2C%22total_amount%22%3A%2276.99%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22timeout_express%22%3A%2215m%22%7D&amp;charset=UTF-8&amp;format=json&amp;method=alipay.trade.pay&amp;notify_url=https%3A%2F%2Fwww.qing-hei.com%2Fmobile.php%2FIndex%2FappnotifyUrl&amp;sign_type=RSA2&amp;timestamp=2018-03-19+13%3A33%3A50&amp;version=1.0&amp;sign=JsB0tHTJRL9tOWMtU1u6e8AepA4f84LqWfTXxiMdYj%2BEJXojZR7K585KYBelQxKNfg4QK0aw5wKkZTkPNmrBq3Fqr8A7RAJUhTu%2FjQWY8tG41TigMWBzNR3DhwqAUY5P%2FhuC1mzAUoqtlDl5ZTlYr%2FCcEbAQtu7t%2FbUykBEftOkqW7cz6g2suUdp%2ByS2lHwCZRkF7tgjEHyhjsFzlRnPKnYko8UqJdk3jCM73HG2PgyrxibqGWEeEF9eahJRTrCR94U0e%2ByFKZnyR7dHirthi7jEWhfQli8LGBk4scFv8XOIkrTtR9XDRzUht1RHg45hu6cAdsMT2eWIATm7fQQwWg%3D%3D
     */

    private int result;
    private String msg;
    private String data;
    private String alipay_data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
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
}

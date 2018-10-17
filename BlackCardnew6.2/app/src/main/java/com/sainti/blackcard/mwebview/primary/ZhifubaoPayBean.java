package com.sainti.blackcard.mwebview.primary;

/**
 * Created by YuZhenpeng on 2018/6/20.
 */

public class ZhifubaoPayBean {

    /**
     * result : 1
     * msg :
     * data : alipay_sdk=alipay-sdk-php-20161101&app_id=2018011101773478&biz_content=%7B%22body%22%3A%22%5Cu4f1a%5Cu5458%5Cu4f1a%5Cu7c4d%5Cu81ea%5Cu52a9%5Cu5347%5Cu7ea7%5Cu652f%5Cu4ed8%22%2C%22subject%22%3A%22%5Cu4f1a%5Cu5458%5Cu4f1a%5Cu7c4d%5Cu81ea%5Cu52a9%5Cu5347%5Cu7ea7%5Cu652f%5Cu4ed8%22%2C%22out_trade_no%22%3A%22QHCS62064710971618040%22%2C%22total_amount%22%3A%221100.00%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22timeout_express%22%3A%2230m%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fwww.qing-hei.com%2Fmobile.php%2Findex%2Fnew_alipay_notifyUrl&sign_type=RSA2&timestamp=2018-06-20+11%3A18%3A39&version=1.0&sign=o4OTmx9LRFql5Zams7GSKchQadNPDA14%2FOy187z1bbbic6gnG%2FlQclGrZ60pjtSFb61mNbVW3rCvTriqeYYVigtxk3mUQ8gOWHk5EQJDwp%2F6kmLPYPbxzlWbr31YmCPBCZ2yV%2FZmsWG3U6PtcpabhH5aD3ogGhPeXvposhjNueoSWw7sXlfHeLgOEEJ3WtWtl6RDwClazBdfyn89B%2BfmQRcxOq1XjOfa%2BcTst3DNX64KJ3VYGNVJ2aEPlt1afuaWt1IWbRVbj9%2BvCAq28iFkcLfthQO6TY7p%2BpNHPbBDriu8Xd8085M7yceOFaRqzvY8V3r1I1qlXB5LZsM1swHbiQ%3D%3D
     */

    private String result;
    private String msg;
    private String data;

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
}

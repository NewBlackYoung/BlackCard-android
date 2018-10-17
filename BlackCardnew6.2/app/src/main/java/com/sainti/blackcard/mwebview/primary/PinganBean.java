package com.sainti.blackcard.mwebview.primary;

/**
 * Created by YuZhenpeng on 2018/6/20.
 */

public class PinganBean {

    /**
     * result : 1
     * msg :
     * pay_data : http://www.qing-hei.com/mobile.php/Order/pingan?uid=319099&orderNo=QHCS62074224608669089&source=android
     */

    private String result;
    private String msg;
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

    public String getPay_data() {
        return pay_data;
    }

    public void setPay_data(String pay_data) {
        this.pay_data = pay_data;
    }
}

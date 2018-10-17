package com.sainti.blackcard.meventbus;

/**
 * Created by YuZhenpeng on 2018/5/29.
 */

public class WxBindEventBean {
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public WxBindEventBean(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

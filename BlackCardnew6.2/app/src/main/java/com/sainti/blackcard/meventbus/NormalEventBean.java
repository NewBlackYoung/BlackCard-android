package com.sainti.blackcard.meventbus;

/**
 * Created by YuZhenpeng on 2018/4/27.
 */

public class NormalEventBean {
    private String messageCode;

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public NormalEventBean(String messageCode) {
        this.messageCode = messageCode;
    }
}

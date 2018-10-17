package com.sainti.blackcard.privilege.bean;

/**
 * Created by YuZhenpeng on 2018/4/27.
 */

public class Bbean {
    /**
     * result : 3
     * msg : 尊敬的黑卡会员，你的账号已于2018-03-12 13:56:48 在其他设备进行登录，设备信息:FrameWork/4.0.2 (iPhone; iOS 11.2.6; Scale/2.00)，如果并非您本人操作，你的密码很可能已经泄漏，请尽快进行修改以免造成更大的损失，如有任何疑问欢迎致电青年黑卡客服热线：4000490700
     */

    private int result;
    private String msg;

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
}

package com.sainti.blackcard.goodthings.bean;

/**
 * Created by YuZhenpeng on 2018/4/20.
 */

public class PinganBean {

    /**
     * result : 1
     * msg :
     * pay_url : http://www.qing-hei.com/mobile.php/Order/pingan
     * test : https://rmb.pingan.com.cn/brcp/uc/cust/uc-third-auth.autoLogin.do?mchId=B170600301&encryptData=66c6507eb8b0468a573ff3b25474e5607e93709b09d0110aaf08a3df023a4a4bb303c6f9efe5e31e5016204a56bd459f63714f84e4727ea9200e332cfb6073ae66f81f45026121f96c0edbb91c46f9daace4517006f9818000767a330fd3465385209dfc2e8cde9a3ade9b2ba81f7a6ee3830c98790871f57d067b020c9642c0ef7dac1a778095ae98156d5d2109dac916056fbf773254fa646ace61996932d201f1fda1a0c7f0049a952f469962bdb0c56a731f73a117fe220863910901a8cffe13b463ddf436385f5b9fbd788d9f2e9f3bc682cb17ac87f0c5f68127e95afbbefe0b9b7f2b354f7dceaed172f083f147ad864bf4efdac65010e43aa51ca4be833d40ef3cf7&redirectUrl=https%3A%2F%2Fbank-static.pingan.com.cn%2Fbbc%2Findex%2Flanding.html%3FmenuId%3DM002&state=mechantNo%3D2000849679%26orderNo%3D31816%26orderDate%3D2017-05-01%26payAmount%3D%26currency%3DRMB%26returnURL%3Dhttp%3A%2F%2Fwww.qing-hei.com%2Fmobile.php%2FOrder%2Fpa_returnurl.html%26termNo%3D00000000%26jumpURL%3Dhttp%3A%2F%2Fwww.qing-hei.com%2Fmobile.php%2FOrder%2Fpa_jumpurl.html%26reservedField%3Dnull%26version%3D2.0%26signature%3DtvGzvu9j3TgOOzW%252BO7ZRkR957ZpMIK1P6s5Ndx1dhLYskUsJAyb9Cv%252Fu2UWeFnzVxLvGkCPJ8rKAFabjjUcr5UkoiVt%252BYaBJ3B7aMZ4Se9FMkY40OkcqY8oPSD2QqEopMtUgPiOdo9FjrClYroCXqZjrKqCjvoLjyQD57G7UJoE%253D
     */

    private String result;
    private String msg;
    private String pay_url;
    private String test;

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

    public String getPay_url() {
        return pay_url;
    }

    public void setPay_url(String pay_url) {
        this.pay_url = pay_url;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}

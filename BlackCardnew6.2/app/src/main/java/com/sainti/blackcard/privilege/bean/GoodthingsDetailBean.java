package com.sainti.blackcard.privilege.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/5/2.
 */

public class GoodthingsDetailBean {


    /**
     * result : 1
     * msg :
     * data : {"w_id":"42","apply":"0","w_title":"美国UNO一体成型背包","w_title2":"众筹8百万，一体成型，自定义内胆包设计","w_content2":"","w_jiage":"373","w_yuanjia":"499","w_jinjia":"260.00","w_pic":"http://s.qing-hei.com/Public/Uploads/5b3057ced84fd.jpg","w_pic1":"5b3057ced8336.jpg","w_pic2":"5b3057ced8678.jpg","px":"1002","open":"0","kuaidi":"0.00","kuaidi2":"","danwei":"个","cuxiao":"","kuaidiname":"EMS","is_kc":"0","kcnum":"0","is_type":"1","is_xianliang":"0","xianliang":"0","maintype":"12","good_type":"0","tejia":"0","newtype":"0","w_title3":"美国UNO一体成型背包","w_old_price":"0.00","blackkey_num":"0.00","supplier_id":"151","w_view":"212","wel_url":"http://www.qing-hei.com/mobile.php/welfare/info/type/1/id/42","w_type":[{"w_typeid":"44","w_id":"42","wtype_name":"【搭配内胆】写生","wtype_price":"590.00","wtype_jinjia":"0.00","wtype_yuanjia":"0.00","wtype_img":""},{"w_typeid":"45","w_id":"42","wtype_name":"【搭配内胆】运动","wtype_price":"500.00","wtype_jinjia":"0.00","wtype_yuanjia":"0.00","wtype_img":""},{"w_typeid":"46","w_id":"42","wtype_name":"【搭配内胆】洗漱出行","wtype_price":"458.00","wtype_jinjia":"0.00","wtype_yuanjia":"0.00","wtype_img":""},{"w_typeid":"47","w_id":"42","wtype_name":"通勤基本款","wtype_price":"399.00","wtype_jinjia":"0.00","wtype_yuanjia":"0.00","wtype_img":""}]}
     * pagecount :
     */

    private String result;
    private String msg;
    private DataBean data;
    private String pagecount;

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

    public String getPagecount() {
        return pagecount;
    }

    public void setPagecount(String pagecount) {
        this.pagecount = pagecount;
    }

    public static class DataBean {
        /**
         * w_id : 42
         * apply : 0
         * w_title : 美国UNO一体成型背包
         * w_title2 : 众筹8百万，一体成型，自定义内胆包设计
         * w_content2 :
         * w_jiage : 373
         * w_yuanjia : 499
         * w_jinjia : 260.00
         * w_pic : http://s.qing-hei.com/Public/Uploads/5b3057ced84fd.jpg
         * w_pic1 : 5b3057ced8336.jpg
         * w_pic2 : 5b3057ced8678.jpg
         * px : 1002
         * open : 0
         * kuaidi : 0.00
         * kuaidi2 :
         * danwei : 个
         * cuxiao :
         * kuaidiname : EMS
         * is_kc : 0
         * kcnum : 0
         * is_type : 1
         * is_xianliang : 0
         * xianliang : 0
         * maintype : 12
         * good_type : 0
         * tejia : 0
         * newtype : 0
         * w_title3 : 美国UNO一体成型背包
         * w_old_price : 0.00
         * blackkey_num : 0.00
         * supplier_id : 151
         * w_view : 212
         * wel_url : http://www.qing-hei.com/mobile.php/welfare/info/type/1/id/42
         * w_type : [{"w_typeid":"44","w_id":"42","wtype_name":"【搭配内胆】写生","wtype_price":"590.00","wtype_jinjia":"0.00","wtype_yuanjia":"0.00","wtype_img":""},{"w_typeid":"45","w_id":"42","wtype_name":"【搭配内胆】运动","wtype_price":"500.00","wtype_jinjia":"0.00","wtype_yuanjia":"0.00","wtype_img":""},{"w_typeid":"46","w_id":"42","wtype_name":"【搭配内胆】洗漱出行","wtype_price":"458.00","wtype_jinjia":"0.00","wtype_yuanjia":"0.00","wtype_img":""},{"w_typeid":"47","w_id":"42","wtype_name":"通勤基本款","wtype_price":"399.00","wtype_jinjia":"0.00","wtype_yuanjia":"0.00","wtype_img":""}]
         */

        private String w_id;
        private String apply;
        private String w_title;
        private String w_title2;
        private String w_content2;
        private String w_jiage;
        private String w_yuanjia;
        private String w_jinjia;
        private String w_pic;
        private String w_pic1;
        private String w_pic2;
        private String px;
        private String open;
        private String kuaidi;
        private String kuaidi2;
        private String danwei;
        private String cuxiao;
        private String kuaidiname;
        private String is_kc;
        private String kcnum;
        private String is_type;
        private String is_xianliang;
        private String xianliang;
        private String maintype;
        private String good_type;
        private String tejia;
        private String newtype;
        private String w_title3;
        private String w_old_price;
        private String blackkey_num;
        private String supplier_id;
        private String w_view;
        private String wel_url;
        private List<WTypeBean> w_type;

        public String getW_id() {
            return w_id;
        }

        public void setW_id(String w_id) {
            this.w_id = w_id;
        }

        public String getApply() {
            return apply;
        }

        public void setApply(String apply) {
            this.apply = apply;
        }

        public String getW_title() {
            return w_title;
        }

        public void setW_title(String w_title) {
            this.w_title = w_title;
        }

        public String getW_title2() {
            return w_title2;
        }

        public void setW_title2(String w_title2) {
            this.w_title2 = w_title2;
        }

        public String getW_content2() {
            return w_content2;
        }

        public void setW_content2(String w_content2) {
            this.w_content2 = w_content2;
        }

        public String getW_jiage() {
            return w_jiage;
        }

        public void setW_jiage(String w_jiage) {
            this.w_jiage = w_jiage;
        }

        public String getW_yuanjia() {
            return w_yuanjia;
        }

        public void setW_yuanjia(String w_yuanjia) {
            this.w_yuanjia = w_yuanjia;
        }

        public String getW_jinjia() {
            return w_jinjia;
        }

        public void setW_jinjia(String w_jinjia) {
            this.w_jinjia = w_jinjia;
        }

        public String getW_pic() {
            return w_pic;
        }

        public void setW_pic(String w_pic) {
            this.w_pic = w_pic;
        }

        public String getW_pic1() {
            return w_pic1;
        }

        public void setW_pic1(String w_pic1) {
            this.w_pic1 = w_pic1;
        }

        public String getW_pic2() {
            return w_pic2;
        }

        public void setW_pic2(String w_pic2) {
            this.w_pic2 = w_pic2;
        }

        public String getPx() {
            return px;
        }

        public void setPx(String px) {
            this.px = px;
        }

        public String getOpen() {
            return open;
        }

        public void setOpen(String open) {
            this.open = open;
        }

        public String getKuaidi() {
            return kuaidi;
        }

        public void setKuaidi(String kuaidi) {
            this.kuaidi = kuaidi;
        }

        public String getKuaidi2() {
            return kuaidi2;
        }

        public void setKuaidi2(String kuaidi2) {
            this.kuaidi2 = kuaidi2;
        }

        public String getDanwei() {
            return danwei;
        }

        public void setDanwei(String danwei) {
            this.danwei = danwei;
        }

        public String getCuxiao() {
            return cuxiao;
        }

        public void setCuxiao(String cuxiao) {
            this.cuxiao = cuxiao;
        }

        public String getKuaidiname() {
            return kuaidiname;
        }

        public void setKuaidiname(String kuaidiname) {
            this.kuaidiname = kuaidiname;
        }

        public String getIs_kc() {
            return is_kc;
        }

        public void setIs_kc(String is_kc) {
            this.is_kc = is_kc;
        }

        public String getKcnum() {
            return kcnum;
        }

        public void setKcnum(String kcnum) {
            this.kcnum = kcnum;
        }

        public String getIs_type() {
            return is_type;
        }

        public void setIs_type(String is_type) {
            this.is_type = is_type;
        }

        public String getIs_xianliang() {
            return is_xianliang;
        }

        public void setIs_xianliang(String is_xianliang) {
            this.is_xianliang = is_xianliang;
        }

        public String getXianliang() {
            return xianliang;
        }

        public void setXianliang(String xianliang) {
            this.xianliang = xianliang;
        }

        public String getMaintype() {
            return maintype;
        }

        public void setMaintype(String maintype) {
            this.maintype = maintype;
        }

        public String getGood_type() {
            return good_type;
        }

        public void setGood_type(String good_type) {
            this.good_type = good_type;
        }

        public String getTejia() {
            return tejia;
        }

        public void setTejia(String tejia) {
            this.tejia = tejia;
        }

        public String getNewtype() {
            return newtype;
        }

        public void setNewtype(String newtype) {
            this.newtype = newtype;
        }

        public String getW_title3() {
            return w_title3;
        }

        public void setW_title3(String w_title3) {
            this.w_title3 = w_title3;
        }

        public String getW_old_price() {
            return w_old_price;
        }

        public void setW_old_price(String w_old_price) {
            this.w_old_price = w_old_price;
        }

        public String getBlackkey_num() {
            return blackkey_num;
        }

        public void setBlackkey_num(String blackkey_num) {
            this.blackkey_num = blackkey_num;
        }

        public String getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(String supplier_id) {
            this.supplier_id = supplier_id;
        }

        public String getW_view() {
            return w_view;
        }

        public void setW_view(String w_view) {
            this.w_view = w_view;
        }

        public String getWel_url() {
            return wel_url;
        }

        public void setWel_url(String wel_url) {
            this.wel_url = wel_url;
        }

        public List<WTypeBean> getW_type() {
            return w_type;
        }

        public void setW_type(List<WTypeBean> w_type) {
            this.w_type = w_type;
        }

        public static class WTypeBean {
            /**
             * w_typeid : 44
             * w_id : 42
             * wtype_name : 【搭配内胆】写生
             * wtype_price : 590.00
             * wtype_jinjia : 0.00
             * wtype_yuanjia : 0.00
             * wtype_img :
             */

            private String w_typeid;
            private String w_id;
            private String wtype_name;
            private String wtype_price;
            private String wtype_jinjia;
            private String wtype_yuanjia;
            private String wtype_img;

            public String getW_typeid() {
                return w_typeid;
            }

            public void setW_typeid(String w_typeid) {
                this.w_typeid = w_typeid;
            }

            public String getW_id() {
                return w_id;
            }

            public void setW_id(String w_id) {
                this.w_id = w_id;
            }

            public String getWtype_name() {
                return wtype_name;
            }

            public void setWtype_name(String wtype_name) {
                this.wtype_name = wtype_name;
            }

            public String getWtype_price() {
                return wtype_price;
            }

            public void setWtype_price(String wtype_price) {
                this.wtype_price = wtype_price;
            }

            public String getWtype_jinjia() {
                return wtype_jinjia;
            }

            public void setWtype_jinjia(String wtype_jinjia) {
                this.wtype_jinjia = wtype_jinjia;
            }

            public String getWtype_yuanjia() {
                return wtype_yuanjia;
            }

            public void setWtype_yuanjia(String wtype_yuanjia) {
                this.wtype_yuanjia = wtype_yuanjia;
            }

            public String getWtype_img() {
                return wtype_img;
            }

            public void setWtype_img(String wtype_img) {
                this.wtype_img = wtype_img;
            }
        }
    }
}

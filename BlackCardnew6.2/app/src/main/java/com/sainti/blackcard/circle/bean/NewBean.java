package com.sainti.blackcard.circle.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/6/4.
 */

public class NewBean {

    /**
     * result : 1
     * msg :
     * data : {"topimg":{"0":{"xh_id":"1","xh_name":"测试","xh_img":"http://s.qing-hei.com/Public/Uploads/topic/banner1.jpg","xh_url":"http://www.qing-hei.com/mobile.php/Welfare/info/id/371","xh_status":"1"},"xh_img":"http://s.qing-hei.com/"},"topic":[{"to_id":"1","to_name":"#6月旅行目的地#","to_des":"6月旅行目的地","to_pic":"http://s.qing-hei.com/Public/Uploads/topic/lvxing.png","to_view":"0","to_time":"","to_state":"0"},{"to_id":"2","to_name":"#今日健身打卡#","to_des":"今日健身打卡","to_pic":"http://s.qing-hei.com/Public/Uploads/topic/yundong.png","to_view":"3","to_time":"","to_state":"0"},{"to_id":"3","to_name":"#一人一部高分电影#","to_des":"一人一部高分电影","to_pic":"http://s.qing-hei.com/Public/Uploads/topic/dianying.png","to_view":"0","to_time":"","to_state":"0"},{"to_id":"4","to_name":"#全款拿下最贵的单品#","to_des":"全款拿下最贵的单品","to_pic":"http://s.qing-hei.com/Public/Uploads/topic/danpin.png","to_view":"0","to_time":"","to_state":"0"}],"myflow":[],"allfind":[]}
     */

    private String result;
    private String msg;
    private DataBean data;

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

    public static class DataBean {
        /**
         * topimg : {"0":{"xh_id":"1","xh_name":"测试","xh_img":"http://s.qing-hei.com/Public/Uploads/topic/banner1.jpg","xh_url":"http://www.qing-hei.com/mobile.php/Welfare/info/id/371","xh_status":"1"},"xh_img":"http://s.qing-hei.com/"}
         * topic : [{"to_id":"1","to_name":"#6月旅行目的地#","to_des":"6月旅行目的地","to_pic":"http://s.qing-hei.com/Public/Uploads/topic/lvxing.png","to_view":"0","to_time":"","to_state":"0"},{"to_id":"2","to_name":"#今日健身打卡#","to_des":"今日健身打卡","to_pic":"http://s.qing-hei.com/Public/Uploads/topic/yundong.png","to_view":"3","to_time":"","to_state":"0"},{"to_id":"3","to_name":"#一人一部高分电影#","to_des":"一人一部高分电影","to_pic":"http://s.qing-hei.com/Public/Uploads/topic/dianying.png","to_view":"0","to_time":"","to_state":"0"},{"to_id":"4","to_name":"#全款拿下最贵的单品#","to_des":"全款拿下最贵的单品","to_pic":"http://s.qing-hei.com/Public/Uploads/topic/danpin.png","to_view":"0","to_time":"","to_state":"0"}]
         * myflow : []
         * allfind : []
         */

        private TopimgBean topimg;
        private List<TopicBean> topic;
        private List<?> myflow;
        private List<?> allfind;

        public TopimgBean getTopimg() {
            return topimg;
        }

        public void setTopimg(TopimgBean topimg) {
            this.topimg = topimg;
        }

        public List<TopicBean> getTopic() {
            return topic;
        }

        public void setTopic(List<TopicBean> topic) {
            this.topic = topic;
        }

        public List<?> getMyflow() {
            return myflow;
        }

        public void setMyflow(List<?> myflow) {
            this.myflow = myflow;
        }

        public List<?> getAllfind() {
            return allfind;
        }

        public void setAllfind(List<?> allfind) {
            this.allfind = allfind;
        }

        public static class TopimgBean {
            /**
             * 0 : {"xh_id":"1","xh_name":"测试","xh_img":"http://s.qing-hei.com/Public/Uploads/topic/banner1.jpg","xh_url":"http://www.qing-hei.com/mobile.php/Welfare/info/id/371","xh_status":"1"}
             * xh_img : http://s.qing-hei.com/
             */

            @SerializedName("0")
            private _$0Bean _$0;
            private String xh_img;

            public _$0Bean get_$0() {
                return _$0;
            }

            public void set_$0(_$0Bean _$0) {
                this._$0 = _$0;
            }

            public String getXh_img() {
                return xh_img;
            }

            public void setXh_img(String xh_img) {
                this.xh_img = xh_img;
            }

            public static class _$0Bean {
                /**
                 * xh_id : 1
                 * xh_name : 测试
                 * xh_img : http://s.qing-hei.com/Public/Uploads/topic/banner1.jpg
                 * xh_url : http://www.qing-hei.com/mobile.php/Welfare/info/id/371
                 * xh_status : 1
                 */

                private String xh_id;
                private String xh_name;
                private String xh_img;
                private String xh_url;
                private String xh_status;

                public String getXh_id() {
                    return xh_id;
                }

                public void setXh_id(String xh_id) {
                    this.xh_id = xh_id;
                }

                public String getXh_name() {
                    return xh_name;
                }

                public void setXh_name(String xh_name) {
                    this.xh_name = xh_name;
                }

                public String getXh_img() {
                    return xh_img;
                }

                public void setXh_img(String xh_img) {
                    this.xh_img = xh_img;
                }

                public String getXh_url() {
                    return xh_url;
                }

                public void setXh_url(String xh_url) {
                    this.xh_url = xh_url;
                }

                public String getXh_status() {
                    return xh_status;
                }

                public void setXh_status(String xh_status) {
                    this.xh_status = xh_status;
                }
            }
        }

        public static class TopicBean {
            /**
             * to_id : 1
             * to_name : #6月旅行目的地#
             * to_des : 6月旅行目的地
             * to_pic : http://s.qing-hei.com/Public/Uploads/topic/lvxing.png
             * to_view : 0
             * to_time :
             * to_state : 0
             */

            private String to_id;
            private String to_name;
            private String to_des;
            private String to_pic;
            private String to_view;
            private String to_time;
            private String to_state;

            public String getTo_id() {
                return to_id;
            }

            public void setTo_id(String to_id) {
                this.to_id = to_id;
            }

            public String getTo_name() {
                return to_name;
            }

            public void setTo_name(String to_name) {
                this.to_name = to_name;
            }

            public String getTo_des() {
                return to_des;
            }

            public void setTo_des(String to_des) {
                this.to_des = to_des;
            }

            public String getTo_pic() {
                return to_pic;
            }

            public void setTo_pic(String to_pic) {
                this.to_pic = to_pic;
            }

            public String getTo_view() {
                return to_view;
            }

            public void setTo_view(String to_view) {
                this.to_view = to_view;
            }

            public String getTo_time() {
                return to_time;
            }

            public void setTo_time(String to_time) {
                this.to_time = to_time;
            }

            public String getTo_state() {
                return to_state;
            }

            public void setTo_state(String to_state) {
                this.to_state = to_state;
            }
        }
    }
}

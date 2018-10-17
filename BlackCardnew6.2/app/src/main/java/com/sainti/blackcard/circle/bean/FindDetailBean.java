package com.sainti.blackcard.circle.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/4/11.
 */

public class FindDetailBean {


    /**
     * result : 1
     * msg :
     * data : {"uid":"313103","head":"http://s.qing-hei.com/Public/Uploads/20180210/s_5a7e5a89447f4.jpg","name":"高雅馨","level":"3","sex":"2","time":"2018-06-07 11:45:28","content":"","location":"","image_urls":[{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa564fb05.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa5692598.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa56cc9a2.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa5708e88.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa573d1cb.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa5773917.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa579da2c.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa57d1532.jpg"}],"image_urlb":[{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa564fb05.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa5692598.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa56cc9a2.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa5708e88.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa573d1cb.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa5773917.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa579da2c.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa57d1532.jpg"}],"agent":"Mozilla/5.0 (Linux; U; Android 7.0; en-us; Android SDK built for x86 Build/NYC) AppleWebKit/534.30 (","video_url":"","scannum":"6","praisenum":"1","commentnum":"1","level_ico":"http://s.qing-hei.com/Public/Uploads/user/yuy_icon.png","isfollow":"1","comment":[{"comment_id":"12468","uid":"319099","user_upimg":"http://s.qing-hei.com/Public/Uploads/20180601/5b10c530eca18.jpg","comment":"2121","user_nick":"我在学校","time":"2018-06-12 14:53:06","replyid":"0","user_nick2":"","find_id":"12105"}],"praiselist":[{"uid":"319099","user_upimg":"http://s.qing-hei.com/Public/Uploads/20180601/5b10c530eca18.jpg","user_nick":"我在学校","cert_pic":"","level":"1","user_sex":"1","isfollow":"0"}],"ispraise":"1"}
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
         * uid : 313103
         * head : http://s.qing-hei.com/Public/Uploads/20180210/s_5a7e5a89447f4.jpg
         * name : 高雅馨
         * level : 3
         * sex : 2
         * time : 2018-06-07 11:45:28
         * content :
         * location :
         * image_urls : [{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa564fb05.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa5692598.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa56cc9a2.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa5708e88.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa573d1cb.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa5773917.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa579da2c.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa57d1532.jpg"}]
         * image_urlb : [{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa564fb05.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa5692598.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa56cc9a2.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa5708e88.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa573d1cb.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa5773917.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa579da2c.jpg"},{"img":"http://s.qing-hei.com/Public/Uploads/20180607/5b18aa57d1532.jpg"}]
         * agent : Mozilla/5.0 (Linux; U; Android 7.0; en-us; Android SDK built for x86 Build/NYC) AppleWebKit/534.30 (
         * video_url :
         * scannum : 6
         * praisenum : 1
         * commentnum : 1
         * level_ico : http://s.qing-hei.com/Public/Uploads/user/yuy_icon.png
         * isfollow : 1
         * comment : [{"comment_id":"12468","uid":"319099","user_upimg":"http://s.qing-hei.com/Public/Uploads/20180601/5b10c530eca18.jpg","comment":"2121","user_nick":"我在学校","time":"2018-06-12 14:53:06","replyid":"0","user_nick2":"","find_id":"12105"}]
         * praiselist : [{"uid":"319099","user_upimg":"http://s.qing-hei.com/Public/Uploads/20180601/5b10c530eca18.jpg","user_nick":"我在学校","cert_pic":"","level":"1","user_sex":"1","isfollow":"0"}]
         * ispraise : 1
         */

        private String uid;
        private String head;
        private String name;
        private String level;
        private String sex;
        private String time;
        private String content;
        private String location;
        private String agent;
        private String video_url;
        private String scannum;
        private String praisenum;
        private String commentnum;
        private String level_ico;
        private String isfollow;
        private String ispraise;
        private List<ImageUrlsBean> image_urls;
        private List<ImageUrlbBean> image_urlb;
        private List<CommentBean> comment;
        private List<PraiselistBean> praiselist;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getAgent() {
            return agent;
        }

        public void setAgent(String agent) {
            this.agent = agent;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getScannum() {
            return scannum;
        }

        public void setScannum(String scannum) {
            this.scannum = scannum;
        }

        public String getPraisenum() {
            return praisenum;
        }

        public void setPraisenum(String praisenum) {
            this.praisenum = praisenum;
        }

        public String getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(String commentnum) {
            this.commentnum = commentnum;
        }

        public String getLevel_ico() {
            return level_ico;
        }

        public void setLevel_ico(String level_ico) {
            this.level_ico = level_ico;
        }

        public String getIsfollow() {
            return isfollow;
        }

        public void setIsfollow(String isfollow) {
            this.isfollow = isfollow;
        }

        public String getIspraise() {
            return ispraise;
        }

        public void setIspraise(String ispraise) {
            this.ispraise = ispraise;
        }

        public List<ImageUrlsBean> getImage_urls() {
            return image_urls;
        }

        public void setImage_urls(List<ImageUrlsBean> image_urls) {
            this.image_urls = image_urls;
        }

        public List<ImageUrlbBean> getImage_urlb() {
            return image_urlb;
        }

        public void setImage_urlb(List<ImageUrlbBean> image_urlb) {
            this.image_urlb = image_urlb;
        }

        public List<CommentBean> getComment() {
            return comment;
        }

        public void setComment(List<CommentBean> comment) {
            this.comment = comment;
        }

        public List<PraiselistBean> getPraiselist() {
            return praiselist;
        }

        public void setPraiselist(List<PraiselistBean> praiselist) {
            this.praiselist = praiselist;
        }

        public static class ImageUrlsBean {
            /**
             * img : http://s.qing-hei.com/Public/Uploads/20180607/5b18aa564fb05.jpg
             */

            private String img;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }

        public static class ImageUrlbBean {
            /**
             * img : http://s.qing-hei.com/Public/Uploads/20180607/5b18aa564fb05.jpg
             */

            private String img;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }

        public static class CommentBean {
            /**
             * comment_id : 12468
             * uid : 319099
             * user_upimg : http://s.qing-hei.com/Public/Uploads/20180601/5b10c530eca18.jpg
             * comment : 2121
             * user_nick : 我在学校
             * time : 2018-06-12 14:53:06
             * replyid : 0
             * user_nick2 :
             * find_id : 12105
             */

            private String comment_id;
            private String uid;
            private String user_upimg;
            private String comment;
            private String user_nick;
            private String time;
            private String replyid;
            private String user_nick2;
            private String find_id;

            public String getComment_id() {
                return comment_id;
            }

            public void setComment_id(String comment_id) {
                this.comment_id = comment_id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getUser_upimg() {
                return user_upimg;
            }

            public void setUser_upimg(String user_upimg) {
                this.user_upimg = user_upimg;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getUser_nick() {
                return user_nick;
            }

            public void setUser_nick(String user_nick) {
                this.user_nick = user_nick;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getReplyid() {
                return replyid;
            }

            public void setReplyid(String replyid) {
                this.replyid = replyid;
            }

            public String getUser_nick2() {
                return user_nick2;
            }

            public void setUser_nick2(String user_nick2) {
                this.user_nick2 = user_nick2;
            }

            public String getFind_id() {
                return find_id;
            }

            public void setFind_id(String find_id) {
                this.find_id = find_id;
            }
        }

        public static class PraiselistBean {
            /**
             * uid : 319099
             * user_upimg : http://s.qing-hei.com/Public/Uploads/20180601/5b10c530eca18.jpg
             * user_nick : 我在学校
             * cert_pic :
             * level : 1
             * user_sex : 1
             * isfollow : 0
             */

            private String uid;
            private String user_upimg;
            private String user_nick;
            private String cert_pic;
            private String level;
            private String user_sex;
            private String isfollow;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getUser_upimg() {
                return user_upimg;
            }

            public void setUser_upimg(String user_upimg) {
                this.user_upimg = user_upimg;
            }

            public String getUser_nick() {
                return user_nick;
            }

            public void setUser_nick(String user_nick) {
                this.user_nick = user_nick;
            }

            public String getCert_pic() {
                return cert_pic;
            }

            public void setCert_pic(String cert_pic) {
                this.cert_pic = cert_pic;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getUser_sex() {
                return user_sex;
            }

            public void setUser_sex(String user_sex) {
                this.user_sex = user_sex;
            }

            public String getIsfollow() {
                return isfollow;
            }

            public void setIsfollow(String isfollow) {
                this.isfollow = isfollow;
            }
        }
    }
}

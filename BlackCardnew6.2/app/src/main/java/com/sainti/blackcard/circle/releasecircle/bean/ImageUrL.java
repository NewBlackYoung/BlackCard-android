package com.sainti.blackcard.circle.releasecircle.bean;

import java.util.List;

/**
 * Created by YuZhenpeng on 2018/4/20.
 */

public class ImageUrL {

    /**
     * result : 1
     * msg :
     * data : [{"imageb":"http://s.qing-hei.com/Public/Uploads/20180420/5ad9b308ca91e.jpg","images":"http://s.qing-hei.com/Public/Uploads/20180420/s_5ad9b308ca91e.jpg","image_an":"http://s.qing-hei.com/Public/Uploads/20180420/an_5ad9b308ca91e.jpg"},{"imageb":"http://s.qing-hei.com/Public/Uploads/20180420/5ad9b308d1762.jpg","images":"http://s.qing-hei.com/Public/Uploads/20180420/s_5ad9b308d1762.jpg","image_an":"http://s.qing-hei.com/Public/Uploads/20180420/an_5ad9b308d1762.jpg"},{"imageb":"http://s.qing-hei.com/Public/Uploads/20180420/5ad9b308d76e0.jpg","images":"http://s.qing-hei.com/Public/Uploads/20180420/s_5ad9b308d76e0.jpg","image_an":"http://s.qing-hei.com/Public/Uploads/20180420/an_5ad9b308d76e0.jpg"}]
     */

    private String result;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * imageb : http://s.qing-hei.com/Public/Uploads/20180420/5ad9b308ca91e.jpg
         * images : http://s.qing-hei.com/Public/Uploads/20180420/s_5ad9b308ca91e.jpg
         * image_an : http://s.qing-hei.com/Public/Uploads/20180420/an_5ad9b308ca91e.jpg
         */

        private String imageb;
        private String images;
        private String image_an;

        public String getImageb() {
            return imageb;
        }

        public void setImageb(String imageb) {
            this.imageb = imageb;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getImage_an() {
            return image_an;
        }

        public void setImage_an(String image_an) {
            this.image_an = image_an;
        }
    }
}

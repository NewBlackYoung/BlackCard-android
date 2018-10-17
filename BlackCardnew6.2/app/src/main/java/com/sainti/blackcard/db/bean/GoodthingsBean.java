package com.sainti.blackcard.db.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "tb_goodthings_list")
public class GoodthingsBean {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "tb_name")
    private String tingsName;

    @DatabaseField(columnName = "tb_count")
    private String tingsCount;

    @DatabaseField(columnName = "tb_price")
    private String tingsPrice;

    @DatabaseField(columnName = "tb_type")
    private String tingsType;

    @DatabaseField(columnName = "tb_yuanjia")
    private String tingsYuanjia;

    @DatabaseField(columnName = "tb_thingsId")
    private String tingsId;

    @DatabaseField(columnName = "tb_w_id")
    private String wId;

    public String getwId() {
        return wId;
    }

    public void setwId(String wId) {
        this.wId = wId;
    }

    @DatabaseField(columnName = "tb_thingsKind")
    private String tingsKind;

    @DatabaseField(columnName = "tb_thingsKindId")
    private String tingsKindId;

    @DatabaseField(columnName = "tb_imageurl")
    private String imageurl;

    @DatabaseField(columnName = "tb_isChoice")// 0 是未被选择的
    private String isChoice;

    @DatabaseField(columnName = "tb_isPay") // 0 是未支付的显示状态
    private String isPay;

    public String getTingsKindId() {
        return tingsKindId;
    }

    public void setTingsKindId(String tingsKindId) {
        this.tingsKindId = tingsKindId;
    }

    public String getTingsKind() {
        return tingsKind;
    }

    public void setTingsKind(String tingsKind) {
        this.tingsKind = tingsKind;
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    public String getIsChoice() {
        return isChoice;
    }

    public void setIsChoice(String isChoice) {
        this.isChoice = isChoice;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTingsName() {
        return tingsName;
    }

    public void setTingsName(String tingsName) {
        this.tingsName = tingsName;
    }

    public String getTingsCount() {
        return tingsCount;
    }

    public void setTingsCount(String tingsCount) {
        this.tingsCount = tingsCount;
    }

    public String getTingsPrice() {
        return tingsPrice;
    }

    public void setTingsPrice(String tingsPrice) {
        this.tingsPrice = tingsPrice;
    }

    public String getTingsType() {
        return tingsType;
    }

    public void setTingsType(String tingsType) {
        this.tingsType = tingsType;
    }

    public String getTingsYuanjia() {
        return tingsYuanjia;
    }

    public void setTingsYuanjia(String tingsYuanjia) {
        this.tingsYuanjia = tingsYuanjia;
    }

    public String getTingsId() {
        return tingsId;
    }

    public void setTingsId(String tingsId) {
        this.tingsId = tingsId;
    }
}

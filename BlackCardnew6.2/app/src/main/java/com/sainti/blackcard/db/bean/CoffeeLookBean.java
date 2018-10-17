package com.sainti.blackcard.db.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tb_coffee_list")
public class CoffeeLookBean {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "tb_name")
    private String coffeeName;

    @DatabaseField(columnName = "tb_count")
    private String choiceCount;

    @DatabaseField(columnName = "tb_price")
    private String coffeePrice;

    @DatabaseField(columnName = "tb_kind")
    private String coffeeKind;

    @DatabaseField(columnName = "tb_imageurl")
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public String getChoiceCount() {
        return choiceCount;
    }

    public void setChoiceCount(String choiceCount) {
        this.choiceCount = choiceCount;
    }

    public String getCoffeePrice() {
        return coffeePrice;
    }

    public void setCoffeePrice(String coffeePrice) {
        this.coffeePrice = coffeePrice;
    }

    public String getCoffeeKind() {
        return coffeeKind;
    }

    public void setCoffeeKind(String coffeeKind) {
        this.coffeeKind = coffeeKind;
    }
}

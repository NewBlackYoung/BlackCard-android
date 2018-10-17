package com.sainti.blackcard.coffee.bean;

/**
 * Created by YuZhenpeng on 2018/3/16.
 */

public class LookCoffeeBean {
    private String coffeeName;
    private String coffeeKind;
    private String coffeePrice;
    private String choiceCount;

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public String getCoffeeKind() {
        return coffeeKind;
    }

    public void setCoffeeKind(String coffeeKind) {
        this.coffeeKind = coffeeKind;
    }

    public String getCoffeePrice() {
        return coffeePrice;
    }

    public void setCoffeePrice(String coffeePrice) {
        this.coffeePrice = coffeePrice;
    }

    public String getChoiceCount() {
        return choiceCount;
    }

    public void setChoiceCount(String choiceCount) {
        this.choiceCount = choiceCount;
    }
}

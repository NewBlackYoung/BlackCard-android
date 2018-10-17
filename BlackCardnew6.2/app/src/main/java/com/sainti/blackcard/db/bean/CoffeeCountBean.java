package com.sainti.blackcard.db.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by YuZhenpeng on 2018/3/17.
 */
@DatabaseTable(tableName = "tb_coffee_count")
public class CoffeeCountBean {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "tb_name")
    private String coffeeName;
    @DatabaseField(columnName = "tb_count")
    private String choiceCount;

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
}

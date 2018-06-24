package br.hackthon.drugstore.order.model.entities.nested;

import java.io.Serializable;

public class Item implements Serializable {

    private String mame;

    private Integer amount;

    public Item() {

    }

    public Item(String mame, Integer amount) {
        this();
        this.mame = mame;
        this.amount = amount;
    }

    public String getMame() {
        return mame;
    }

    public void setMame(String mame) {
        this.mame = mame;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

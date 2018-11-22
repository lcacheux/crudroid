package net.cacheux.crud.example.model;

import android.support.annotation.NonNull;

import net.cacheux.crud.example.storage.BaseItem;

public class Article extends BaseItem {

    @NonNull
    private String label = "";

    private float price;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

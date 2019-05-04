package com.example.zetatask.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemsWrapper {

    @SerializedName("items")
    @Expose
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ItemsWrapper{" +
                "items=" + items +
                '}';
    }
}

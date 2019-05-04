package com.example.zetatask.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("pagemap")
    @Expose
    private PageMap pageMap;

    public PageMap getPageMap() {
        return pageMap;
    }

    public void setPageMap(PageMap pageMap) {
        this.pageMap = pageMap;
    }

    @Override
    public String toString() {
        return "Item{" +
                "pageMap=" + pageMap +
                '}';
    }
}

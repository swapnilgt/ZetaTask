package com.example.zetatask.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CseThumbnail {

    @SerializedName("src")
    @Expose
    private String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "CseThumbnail{" +
                "src='" + src + '\'' +
                '}';
    }
}

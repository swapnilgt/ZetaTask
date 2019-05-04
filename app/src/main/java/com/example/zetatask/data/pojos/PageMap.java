package com.example.zetatask.data.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PageMap {

    @SerializedName("cse_thumbnail")
    @Expose
    private List<CseThumbnail> cseThumbnail;
    @SerializedName("cse_image")
    @Expose
    private List<CseThumbnail> cseImage;

    public List<CseThumbnail> getCseThumbnail() {
        return cseThumbnail;
    }

    public void setCseThumbnail(List<CseThumbnail> cseThumbnail) {
        this.cseThumbnail = cseThumbnail;
    }

    public List<CseThumbnail> getCseImage() {
        return cseImage;
    }

    public void setCseImage(List<CseThumbnail> cseImage) {
        this.cseImage = cseImage;
    }

    @Override
    public String toString() {
        return "PageMap{" +
                "cseThumbnail=" + cseThumbnail +
                ", cseImage=" + cseImage +
                '}';
    }
}

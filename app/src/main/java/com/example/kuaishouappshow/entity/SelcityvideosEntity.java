package com.example.kuaishouappshow.entity;

public class SelcityvideosEntity {

    /**
     * coverImg : string
     * userId : 0
     * vedioId : 0
     * vedioUrl : string
     */

    private String coverImg;
    private long userId;
    private long vedioId;
    private String vedioUrl;

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getVedioId() {
        return vedioId;
    }

    public void setVedioId(int vedioId) {
        this.vedioId = vedioId;
    }

    public String getVedioUrl() {
        return vedioUrl;
    }

    public void setVedioUrl(String vedioUrl) {
        this.vedioUrl = vedioUrl;
    }
}

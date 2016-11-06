package com.bigshark.budejie_mvp.bean;

/**
 * Created by luyanhong on 16/9/26.
 */
public class EssenceTestData {
    private String  url;
    private String title;
    private String sencendTitle;
    private String distance;

    public EssenceTestData() {
    }

    @Override
    public String toString() {
        return "EssenceTestData{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", sencendTitle='" + sencendTitle + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSencendTitle() {
        return sencendTitle;
    }

    public void setSencendTitle(String sencendTitle) {
        this.sencendTitle = sencendTitle;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}

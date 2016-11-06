package com.bigshark.budejie_mvp.bean;

/**
 * Created by luyanhong on 16/9/27.
 */
public class ActivityTestData {
    private String url;
    private String name;
    private String content;

    public ActivityTestData() {
    }

    @Override
    public String toString() {
        return "ActivityTestData{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

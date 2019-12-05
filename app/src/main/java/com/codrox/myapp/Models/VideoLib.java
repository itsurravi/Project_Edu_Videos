package com.codrox.myapp.Models;

public class VideoLib {
    private String id;
    private TopicsInfo topicsInfo;

    public VideoLib(String id, TopicsInfo topicsInfo) {
        this.id = id;
        this.topicsInfo = topicsInfo;
    }

    public String getId() {
        return id;
    }

    public TopicsInfo getTopicsInfo() {
        return topicsInfo;
    }
}

package com.codrox.myapp.Models;

public class VideoLib {
    private String id;
    private SubTopicsInfo subTopicsInfo;

    public VideoLib(String id, SubTopicsInfo subTopicsInfo) {
        this.id = id;
        this.subTopicsInfo = subTopicsInfo;
    }

    public String getId() {
        return id;
    }

    public SubTopicsInfo getSubTopicsInfo() {
        return subTopicsInfo;
    }
}

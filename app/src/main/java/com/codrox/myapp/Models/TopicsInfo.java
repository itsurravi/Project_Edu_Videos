package com.codrox.myapp.Models;

import java.util.List;

public class TopicsInfo {

    private String topicName;
    private List<SubTopicsInfo> subTopicList;

    public TopicsInfo(String topicName, List<SubTopicsInfo> subTopicList) {
        this.topicName = topicName;
        this.subTopicList = subTopicList;
    }

    public String getTopicName() {
        return topicName;
    }

    public List<SubTopicsInfo> getSubTopicList() {
        return subTopicList;
    }
}

package com.codrox.myapp.Models;

public class TopicsInfo {
    private String id;
    private String className;
    private String subject;
    private String chapter;
    private String title;
    private String price;

    public TopicsInfo(String id, String className, String subject, String chapter, String title, String price) {
        this.id = id;
        this.className = className;
        this.subject = subject;
        this.chapter = chapter;
        this.title = title;
        this.price = price;
    }

    public TopicsInfo(String id, String title, String price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getClassName() {
        return className;
    }

    public String getSubject() {
        return subject;
    }

    public String getChapter() {
        return chapter;
    }
}

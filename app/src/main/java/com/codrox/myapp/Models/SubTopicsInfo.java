package com.codrox.myapp.Models;

public class SubTopicsInfo {
    private String id;
    private String className;
    private String subject;
    private String chapter;
    private String title;
    private String subtitle;
    private String price;
    private String videoUrl;

    public SubTopicsInfo(String id, String className, String subject, String chapter, String title, String subtitle, String price, String videoUrl) {
        this.id = id;
        this.className = className;
        this.subject = subject;
        this.chapter = chapter;
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
        this.videoUrl = videoUrl;
    }

    public String getId() {
        return id;
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

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getPrice() {
        return price;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}

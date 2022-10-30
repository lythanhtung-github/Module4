package com.codegym.model;

public class Mail {
    private int id;
    private String language;
    private int pageSize;
    private boolean isSpam;
    private String signature;

    public Mail() {
    }

    public Mail(int id, String language, int pageSize, boolean isSpam, String signature) {
        this.id = id;
        this.language = language;
        this.pageSize = pageSize;
        this.isSpam = isSpam;
        this.signature = signature;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isSpam() {
        return isSpam;
    }

    public void setSpam(boolean spam) {
        isSpam = spam;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

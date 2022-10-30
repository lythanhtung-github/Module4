package com.codegym.model;

public class Music {
    private int id;
    private String name;
    private String singer;
    private String type;
    private String path;

    private static int count =0;

    public Music() {
    }

    public Music(String name, String singer, String type, String path) {
        this.id = ++count;
        this.name = name;
        this.singer = singer;
        this.type = type;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

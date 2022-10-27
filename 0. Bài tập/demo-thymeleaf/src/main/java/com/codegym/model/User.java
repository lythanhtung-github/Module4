package com.codegym.model;

public class User {
    private int id;
    private String name;
    private String address;
    private int role;

    public User() {
    }

    public User(int id, String name, String address, int role) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.role = role;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}


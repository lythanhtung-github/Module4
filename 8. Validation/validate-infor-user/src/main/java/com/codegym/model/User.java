package com.codegym.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {

    @NotEmpty(message = "Tên không được để trống")
    @Size(min = 2, max = 30, message = "Độ dài của tên nằm trong khoảng 2 - 30 ký tự")
    private String name;

    @Min(value = 18, message = "Tuổi ít nhất là 18")
    private int age;

    public User() {
    }

    public User(@NotEmpty @Size(min = 2, max = 30) String name, @Min(18) int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

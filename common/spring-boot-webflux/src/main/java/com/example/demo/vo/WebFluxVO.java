package com.example.demo.vo;


public class WebFluxVO {
    String name;
    int age;

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

    public WebFluxVO(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

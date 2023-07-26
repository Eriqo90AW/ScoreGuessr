package com.eriqoariefjsleeprj.frontend.model;

public class MyReward {
    public int id;
    public String name, type, image, code;

    public MyReward(int id, String name, String type, String image, String code) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.image = image;
        this.code = code;
    }
}

package com.eriqoariefjsleeprj.frontend.model;

public class Reward {
    public int id, cost;
    public String name, type, image;

    public Reward(int id, int cost, String name, String type, String image) {
        this.id = id;
        this.cost = cost;
        this.name = name;
        this.type = type;
        this.image = image;
    }
}

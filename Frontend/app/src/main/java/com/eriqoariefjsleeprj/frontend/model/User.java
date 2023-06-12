package com.eriqoariefjsleeprj.frontend.model;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private int[] predictions_id;
    private String mini_league_code;
    private float total_points;
    private int[] rewards_id;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int[] getPredictions_id() {
        return predictions_id;
    }

    public String getMini_league_code() {
        return mini_league_code;
    }

    public float getTotal_points() {
        return total_points;
    }

    public int[] getRewards_id() {
        return rewards_id;
    }
}

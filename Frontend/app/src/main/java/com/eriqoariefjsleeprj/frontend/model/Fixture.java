package com.eriqoariefjsleeprj.frontend.model;

import java.util.Date;

public class Fixture {
    public int id, home_id, home_score, away_id, away_score, gameweek;
    public float home_odds, away_odds;
    public Date date;
    public String status;
    public String home_team, home_crest, away_team, away_crest;

    public Fixture(int id, int home_id, int home_score, int away_id, int away_score, int gameweek, float home_odds, float away_odds, Date date, String status, String home_team, String home_crest, String away_team, String away_crest) {
        this.id = id;
        this.home_id = home_id;
        this.home_score = home_score;
        this.away_id = away_id;
        this.away_score = away_score;
        this.gameweek = gameweek;
        this.home_odds = home_odds;
        this.away_odds = away_odds;
        this.date = date;
        this.status = status;
        this.home_team = home_team;
        this.home_crest = home_crest;
        this.away_team = away_team;
        this.away_crest = away_crest;
    }
}

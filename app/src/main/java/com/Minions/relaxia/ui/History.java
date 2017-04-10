package com.Minions.relaxia.ui;

/**
 * Created by JJ on 10/4/2017.
 */

public class History {
    private String attempt;
    private String theme;
    private String dateTime;
    private String difficulty;
    private String stars;

    public String getAttempt() {
        return attempt;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getTheme() {
        return theme;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getStars() {
        return stars;
    }

    public void setAttempt(String attempt) {
        this.attempt = attempt;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}

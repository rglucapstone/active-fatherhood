package com.rglucapstone.activefatherhood.apis;

/**
 * Created by ronald on 17/12/15.
 */
public class Question {
    public String user;
    public String date;
    public String content;
    public String tagEnfermedades;
    public String tagBebes;

    public Question(String user, String date, String content, String tagEnfermedades, String tagBebes) {
        this.user = user;
        this.date = date;
        this.content = content;
        this.tagEnfermedades = tagEnfermedades;
        this.tagBebes = tagBebes;
    }
}

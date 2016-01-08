package com.rglucapstone.activefatherhood.apis;

/**
 * Created by ronald on 17/12/15.
 */
public class Question {
    public String user;
    public String date;
    public String content;
    public String pregunta_tag;
    public String reporte_likes;
    public String reporte_respuestas;

    public Question(String user, String date, String content, String pregunta_tag, String reporte_likes, String reporte_respuestas) {
        this.user = user;
        this.date = date;
        this.content = content;
        this.pregunta_tag = pregunta_tag;
        this.reporte_likes = reporte_likes;
        this.reporte_respuestas = reporte_respuestas;
    }
}

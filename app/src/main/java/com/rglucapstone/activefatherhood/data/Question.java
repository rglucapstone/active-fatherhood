package com.rglucapstone.activefatherhood.data;

/**
 * Created by ronald on 13/01/16.
 */
public class Question {

    public String user;
    public String datetime;
    public String content;
    public String tags;
    public String likes;
    public String answers;

    public Question(String quser, String qdatetime, String qcontent, String qtags,
                    String qlikes, String qanswers) {
        this.user = quser;
        this.datetime = qdatetime;
        this.content = qcontent;
        this.tags = qtags;
        this.likes = qlikes;
        this.answers = qanswers;
    }
}

package com.rglucapstone.activefatherhood.data;

/**
 * Created by ronald on 14/01/16.
 */
public class Post {

    public String title;
    public String content;
    public String user;
    public String datetime;
    public String tags;
    public String likes;
    public String comments;

    public Post(String ptitle, String pcontent, String puser, String pdatetime,
                    String ptags, String plikes, String pcomments) {
        this.title = ptitle;
        this.content = pcontent;
        this.user = puser;
        this.datetime = pdatetime;
        this.tags = ptags;
        this.likes = plikes;
        this.comments = pcomments;
    }
}

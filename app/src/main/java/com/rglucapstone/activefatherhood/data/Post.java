package com.rglucapstone.activefatherhood.data;

import android.content.Context;

import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ronald on 14/01/16.
 */
public class Post {

    public String id;
    public String title;
    public String content;
    public String created;
    public String likes;
    public long created_ago;
    public String[] themes;

    public User user;
    public ArrayList<Comment> listComments;
    public RestfulClient asynctask;
    public Context context;


    public Post(RestfulClient task) {
        this.asynctask = task;
    }

    public Post(Context context, RestfulClient task) {
        this.context = context;
        this.asynctask = task;
    }

    // Constructor to convert JSON object into a Question class instance
    public Post(JSONObject object) {
        try {
            JSONObject q = object.getJSONObject("post");
            if (q.has("id")) this.id = q.getString("id");
            if (q.has("title")) this.title = q.getString("title");
            if (q.has("content")) this.content = q.getString("content");
            if (q.has("created")) {
                this.created = q.getString("created");
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = format.parse(this.created);
                    this.created_ago = date.getTime();
                }catch (ParseException e){}
            }
            if (q.has("themes")) {
                this.themes = q.getString("themes").split(",");
            }

            if (q.has("likes")) this.likes = q.getString("likes");
            this.user = new User(q);
            if (q.has("comments")) {
                this.listComments = Comment.fromJson(q.getJSONArray("comments"));
            }
            //if (q.has("question_id"))  this.question_id = q.getString("question_id");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList fromJson(JSONArray data) {
        ArrayList items = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            try {
                items.add(new Post(data.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return items;
    }

    public ArrayList<Post> getAll() {
        ArrayList<Post> items = new ArrayList<>();
        RestfulClient rest = this.asynctask;
        rest.method = "GET";
        rest.uri = "/posts";
        try{
            rest.execute();
        }catch (Exception e) {
        }
        return items;
    }

    public ArrayList<Post> find(String themes) {
        ArrayList<Post> items = new ArrayList<>();
        RestfulClient rest = this.asynctask;
        rest.method = "GET";
        rest.uri = "/posts/?themes="+themes;
        try{
            rest.execute();
        }catch (Exception e) {
        }
        return items;
    }

    public Post load(String id){
        Post post = null;
        RestfulClient rest = this.asynctask;
        rest.method = "GET";
        rest.uri = "/posts/" + id;
        try{
            rest.execute();
        }catch (Exception e) {
        }
        return post;
    }
}

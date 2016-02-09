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

    public RestfulClient AsyncTask;


    public ArrayList<Like> likes;
    public ArrayList<Comment> listComments;

    public String user_request_id;


    public String id;
    public String title;
    public String content;
    public String created;
    public long created_ago;
    public String[] themes;
    public String user_request;
    public String answer_request;
    public User user;
    public Context context;

    // Constructors
    public Post(RestfulClient task) {
        this.AsyncTask = task;
    }

    public Post(String id){
        this.id = id;
    }




    public Post(Context context, RestfulClient task) {
        this.context = context;
        this.AsyncTask = task;
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
            if (q.has("themes") ){
                this.themes = new String[0];
                String themes = q.getString("themes");
                if( themes.length() > 0 )
                    this.themes = q.getString("themes").split(",");
            }
            /*if (q.has("themes") ){
                this.themes = new String[0];
                String themes = q.getString("themes");
                if( themes.length() > 0 )
                    this.themes = q.getString("themes").split(",");
            }*/

            //if (q.has("likes")) this.likes = q.getString("likes");
            if (q.has("likes")){
                this.likes = Like.fromJson(q.getJSONArray("likes"));
            }
            this.user = new User(q);
            if (q.has("comments")) {
                this.listComments = Comment.fromJson(q.getJSONArray("comments"));
            }
            //if (q.has("question_id"))  this.question_id = q.getString("question_id");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Post> fromJson(JSONArray data) {
        ArrayList<Post> items = new ArrayList<>();
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
        RestfulClient rest = this.AsyncTask;
        rest.method = "GET";
        rest.uri = "/posts";
        try{
            rest.execute();
        }catch (Exception e) {
        }
        return items;
    }

    // find posts
    public void find(String themes, String viewBy) {
        try{
            this.AsyncTask.method = "GET";
            this.AsyncTask.uri = "/posts";
            if( themes.length() > 0 || viewBy.length() > 0){
                this.AsyncTask.uri += "/?";
                if( themes.length() > 0 ) this.AsyncTask.uri += "themes=" + themes + "&";
                if( viewBy.length() > 0 ) this.AsyncTask.uri += "view=" + viewBy + "&";
            }
            this.AsyncTask.execute();
        }catch (Exception e) {
        }
    }

    // make like to post
    public boolean like(String post_id, String user_id) {
        boolean like = false;
        this.AsyncTask.method = "POST";
        this.AsyncTask.uri = "/posts/" + post_id + "/likes";
        try{
            JSONObject json = new JSONObject();
            json.put("user_id", user_id);
            json.put("created", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            this.AsyncTask.execute(json.toString());
            if( this.AsyncTask.status == 200 )
                like = true;
        }catch (Exception e) {}
        return like;
    }

    // send a post
    public boolean send(String prefers) {
        boolean send = false;
        this.AsyncTask.method = "POST";
        this.AsyncTask.uri = "/posts";
        try{
            JSONObject json = new JSONObject();
            try {
                json.put("content", this.content);
                json.put("title", this.title);
                json.put("user_id", this.user.id);
                json.put("created", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                json.put("user_request_id", this.user_request_id);
                json.put("themes", prefers);
            } catch (JSONException e) {}

            this.AsyncTask.execute(json.toString());
            if( this.AsyncTask.status == 201 )
                send = true;
        }catch (Exception e) {
        }
        return send;
    }






    public Post load(String id){
        Post post = null;
        RestfulClient rest = this.AsyncTask;
        rest.method = "GET";
        rest.uri = "/posts/" + id;
        try{
            rest.execute();
        }catch (Exception e) {
        }
        return post;
    }



}

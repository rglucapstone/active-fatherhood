package com.rglucapstone.activefatherhood.data;

import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ronald on 31/01/16.
 */
public class Comment extends Model {

    public String id;
    public String content;
    public String created;
    public long created_ago;
    public User user;
    public Post post;

    public RestfulClient AsyncTask;


    public String post_id;



    public Comment(RestfulClient task) {
        this.AsyncTask = task;
    }

    public Comment(JSONObject object) {
        try {
            JSONObject q = object.getJSONObject("comment");
            if (q.has("id")) this.id = q.getString("id");
            if (q.has("content")) this.content = q.getString("content");
            if (q.has("created")) this.created = q.getString("created");
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = format.parse(this.created);
                this.created_ago = date.getTime();
            }catch (ParseException e){}
            this.user = new User(q);
            if (q.has("post_id"))  this.post_id = q.getString("post_id");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList fromJson(JSONArray data) {
        ArrayList items = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            try {
                items.add(new Comment(data.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return items;
    }


    public boolean send() {
        boolean send = false;
        this.AsyncTask.method = "POST";
        this.AsyncTask.uri = "/posts/" + this.post.id + "/comments";
        try{
            JSONObject json = new JSONObject();
            try {
                json.put("content", this.content);
                json.put("created", this.created);
                json.put("user_id", this.user.id);
            } catch (JSONException e) {}

            this.AsyncTask.execute(json.toString());
            if( this.AsyncTask.status == 201 )
                send = true;
        }catch (Exception e) {
        }
        return send;
    }
}

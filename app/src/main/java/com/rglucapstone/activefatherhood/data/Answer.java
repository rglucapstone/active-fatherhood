package com.rglucapstone.activefatherhood.data;

import android.content.Context;
import android.view.View;

import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ronald on 27/01/16.
 */
public class Answer extends Model{

    public String id;
    public String content;
    public String created;
    public User user;
    public String question_id;
    public ArrayList<Like> likes;

    public Context context;
    public RestfulClient asynctask;
    public View view;

    public Answer() {

    }

    public Answer(RestfulClient task) {
        this.asynctask = task;
    }

    public Answer(Context context, RestfulClient task) {
        this.context = context;
        this.asynctask = task;
    }

    public Answer(JSONObject object) {
        try {
            JSONObject q = object.getJSONObject("answer");
            if (q.has("id")) this.id = q.getString("id");
            if (q.has("content")) this.content = q.getString("content");
            if (q.has("created")) this.created = q.getString("created");
            if (q.has("question_id")) this.question_id = q.getString("question_id");
            if (q.has("likes")){
                this.likes = Like.fromJson(q.getJSONArray("likes"));
            }
            this.user = new User(q);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList fromJson(JSONArray data) {
        ArrayList<Answer> items = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            try {
                items.add(new Answer(data.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return items;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put("content", this.content);
            json.put("created", this.created);
            json.put("user_id", this.user.id);
            json.put("question_id", this.question_id);
        } catch (JSONException e) {}
        return json;
    }


    public boolean send() {
        boolean send = false;
        RestfulClient rest = this.asynctask;
        rest.method = "POST";
        rest.uri = "/answers";
        try{
            rest.execute(this.toJson().toString());
            if( rest.status == 201 )
                send = true;
        }catch (Exception e) {
        }
        return send;
    }

    public boolean like(String user_id) {
        boolean like = false;
        RestfulClient rest = this.asynctask;
        rest.method = "POST";
        rest.uri = "/answers/"+this.id+"/likes";
        try{
            JSONObject json = new JSONObject();
            json.put("user_id", user_id);
            rest.execute(json.toString());
            if( rest.status == 200 )
                like = true;
        }catch (Exception e) {
        }
        return like;
    }
}

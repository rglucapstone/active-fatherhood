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

    public RestfulClient AsyncTask;



    public String id;
    public String content;
    public String created;
    public User user;
    public String question_id;
    public String question_user_id;
    public ArrayList<Like> likes;
    public ArrayList<Suggest> suggestions;

    public Context context;

    public View view;

    public Answer() { }

    // Constructors
    public Answer(RestfulClient task) {
        this.AsyncTask = task;
    }

    public Answer(JSONObject object) {
        try {
            JSONObject q = object.getJSONObject("answer");
            if (q.has("id")) this.id = q.getString("id");
            if (q.has("content")) this.content = q.getString("content");
            if (q.has("created")) this.created = q.getString("created");
            if (q.has("question_id")) this.question_id = q.getString("question_id");
            if (q.has("question_user_id")) this.question_user_id = q.getString("question_user_id");
            if (q.has("likes")){
                this.likes = Like.fromJson(q.getJSONArray("likes"));
            }
            if (q.has("suggestions")){
                this.suggestions = Suggest.fromJson(q.getJSONArray("suggestions"));
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

    // like an answer
    public boolean like(String answer_id, String user_id) {
        boolean like = false;
        this.AsyncTask.method = "POST";
        this.AsyncTask.uri = "/answers/" + answer_id + "/likes";
        try{
            JSONObject json = new JSONObject();
            json.put("user_id", user_id);
            this.AsyncTask.execute(json.toString());
            if( this.AsyncTask.status == 200 )
                like = true;
        }catch (Exception e) {}
        return like;
    }




    /************************************************************/

    public Answer(Context context, RestfulClient task) {
        this.context = context;
        this.AsyncTask = task;
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
        RestfulClient rest = this.AsyncTask;
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



    public boolean suggest(String user_owner, String user_request) {
        boolean like = false;
        RestfulClient rest = this.AsyncTask;
        rest.method = "POST";
        rest.uri = "/answers/"+this.id+"/suggestions";
        try{
            JSONObject json = new JSONObject();
            json.put("user_owner_id", user_owner);
            json.put("user_request_id", user_request);
            rest.execute(json.toString());
            if( rest.status == 201 )
                like = true;
        }catch (Exception e) {
        }
        return like;
    }
}

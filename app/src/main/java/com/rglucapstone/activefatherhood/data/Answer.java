package com.rglucapstone.activefatherhood.data;

import android.content.Context;
import android.view.View;

import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ronald on 27/01/16.
 */
public class Answer extends Model{

    public RestfulClient AsyncTask;

    public String id;
    public String content;
    public String created;
    public long created_ago;





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
            if (q.has("question_id")) this.question_id = q.getString("question_id");
            if (q.has("question_user_id")) this.question_user_id = q.getString("question_user_id");
            if (q.has("created")){
                this.created = q.getString("created");
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = format.parse(this.created);
                    this.created_ago = date.getTime();
                }catch (ParseException e){}
            }
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

    // sending an answer
    public boolean send() {
        boolean send = false;
        this.AsyncTask.method = "POST";
        this.AsyncTask.uri = "/answers";
        try{
            JSONObject json = new JSONObject();
            try {
                json.put("content", this.content);
                json.put("created", this.created);
                json.put("user_id", this.user.id);
                json.put("question_id", this.question_id);
            } catch (JSONException e) {}
            this.AsyncTask.execute(json.toString());
            if( this.AsyncTask.status == 201 )
                send = true;
        }catch (Exception e) {}
        return send;
    }

    // suggest an answer
    public boolean suggest(User user) {
        boolean status = false;
        this.AsyncTask.method = "POST";
        this.AsyncTask.uri = "/answers/" + this.id + "/suggestions";
        try{
            JSONObject json = new JSONObject();
            json.put("user_id", user.id);
            json.put("created", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            this.AsyncTask.execute(json.toString());
            if( this.AsyncTask.status == 201 )
                status = true;
        }catch (Exception e) {
        }
        return status;
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







}

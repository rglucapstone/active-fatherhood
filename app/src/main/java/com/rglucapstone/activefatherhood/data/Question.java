package com.rglucapstone.activefatherhood.data;

import com.rglucapstone.activefatherhood.sync.RestfulClient;
import com.rglucapstone.activefatherhood.sync.RestfulClientDos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by ronald on 13/01/16.
 */
public class Question extends Model implements Serializable {

    public Context context;
    public ArrayAdapter adapter;
    public RestfulClient asynctask;

    public String id;
    public String content;
    public String created;
    public long created_ago;
    public String total_answers;
    public String[] themes;
    public User user;
    public ArrayList<Answer> listAnswers;
    public String question_id;


    public String userstr;
    public String contentstr;
    public String datetime;
    public String tags;
    public String likes;


    public Question(RestfulClient task) {
        this.asynctask = task;
    }

    public Question(Context context) {
        this.context = context;
    }

    public Question(Context context, RestfulClient task) {
        this.context = context;
        this.asynctask = task;
    }

    // Constructor to convert JSON object into a Question class instance
    public Question(JSONObject object) {
        try {
            JSONObject q = object.getJSONObject("question");
            if (q.has("id")) this.id = q.getString("id");
            if (q.has("content")) this.content = q.getString("content");
            if (q.has("created")){
                this.created = q.getString("created");
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = format.parse(this.created);
                    this.created_ago = date.getTime();
                }catch (ParseException e){}
            }
            if (q.has("themes")){
                this.themes = q.getString("themes").split(",");
            }
            this.user = new User(q);
            this.listAnswers = Answer.fromJson(q.getJSONArray("answers"));
            if (q.has("question_id"))  this.question_id = q.getString("question_id");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Constructor to send Question
    public Question(String content, String created, String user_id) {
        this.content = content;
        this.created = created;
        this.user = new User(user_id);
    }

    public ArrayList<Question> getAll() {
        ArrayList<Question> items = new ArrayList<>();
        RestfulClient rest = this.asynctask;
        rest.method = "GET";
        rest.uri = "/questions";
        try{
            rest.execute();
        }catch (Exception e) {
        }
        return items;
    }

    public ArrayList<Question> find(String themes) {
        ArrayList<Question> items = new ArrayList<>();
        RestfulClient rest = this.asynctask;
        rest.method = "GET";
        rest.uri = "/questions/?themes="+themes;
        //this.content = rest.uri;
        try{
            rest.execute();
        }catch (Exception e) {
        }
        return items;
    }

    public Question load(String id){
        Question question = null;
        RestfulClient rest = this.asynctask;
        rest.method = "GET";
        rest.uri = "/questions/" + id;
        try{
            rest.execute();
        }catch (Exception e) {
        }
        return question;
    }

    public boolean send() {
        boolean send = false;
        RestfulClient rest = this.asynctask;
        rest.method = "POST";
        rest.uri = "/questions";
        try{
            rest.execute(this.toJson().toString());
            if( rest.status == 201 )
                send = true;
        }catch (Exception e) {
        }
        return send;
    }


    public static ArrayList fromJson(JSONArray data) {
        ArrayList items = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            try {
                items.add(new Question(data.getJSONObject(i)));
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
}
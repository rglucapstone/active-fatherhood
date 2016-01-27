package com.rglucapstone.activefatherhood.data;

import com.rglucapstone.activefatherhood.sync.RestfulClient;
import com.rglucapstone.activefatherhood.sync.RestfulClientDos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by ronald on 13/01/16.
 */
public class Question extends Model {

    public Context context;
    public ArrayAdapter adapter;
    public RestfulClient asynctask;

    public String content;
    public String created;
    public String total_answers;
    public String[] themes;
    public User user;



    public String userstr;
    public String contentstr;
    public String datetime;
    public String tags;
    public String likes;
    public String answers;

    public Question(RestfulClient task) {
        this.asynctask = task;
    }

    public Question(Context context) {
        this.context = context;
    }

    public Question(Context context, ArrayAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    // Constructor to convert JSON object into a Java class instance
    public Question(JSONObject object) {
        try {
            JSONObject q = object.getJSONObject("question");
            this.content = q.getString("content");
            this.created = q.getString("created");
            this.total_answers = q.getString("answers");
            this.themes = q.getString("themes").split(",");
            this.user = new User(q);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

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

    public boolean send() {
        boolean send = false;
        RestfulClient rest = new RestfulClient();
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
        } catch (JSONException e) {}
        return json;
    }
}
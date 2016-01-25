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

    public Question(Context context) {
        this.context = context;
        this.adapter = adapter;
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

    public Question(String content, String created) {
        this.content = content;
        this.created = created;
    }

    public static ArrayList<Question> getAll() {
        ArrayList<Question> items = new ArrayList<>();
        RestfulClient rest = new RestfulClient();
        rest.method = "GET";
        rest.uri = "/questions";
        try{
            JSONArray data = rest.execute().get();
            items = Question.fromJson(data);
        }catch (Exception e) {
        }
        return items;
    }

    // Factory method to convert an array of JSON objects into a list of objects
    // User.fromJson(jsonArray);
    public static ArrayList<Question> fromJson(JSONArray data) {
        ArrayList<Question> items = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            try {
                items.add(new Question(data.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return items;
    }

    public String testRest() {
        JSONArray data;
        String result = "";
        RestfulClient rest = new RestfulClient(this, this.context, this.adapter);
        rest.method = "GET";
        rest.uri = "/questions";
        try{
            data = rest.execute().get();
            try {
                JSONObject obj = data.getJSONObject(6);
                Question q = new Question(obj);
                result = q.content;
            }catch(JSONException e){

            }
        }catch (Exception e) {
        }
        return result;
    }
}
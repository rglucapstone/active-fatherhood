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
 * Created by ronald on 04/02/16.
 */
public class Suggest {

    public Answer answer;
    public User user;



    public String content;
    public String created;
    public User user_request;
    public long created_ago;
    public String answer_id;

    public RestfulClient asynctask;

    public Suggest(RestfulClient task) {
        this.asynctask = task;
    }

    public Suggest(JSONObject object) {
        try {
            JSONObject q = object.getJSONObject("suggest");

            this.answer = new Answer(q);
            this.user = new User(q);
            if (q.has("created")){
                this.created = q.getString("created");
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = format.parse(this.created);
                    this.created_ago = date.getTime();
                }catch (ParseException e){}
            }

            /*if( q.has("answer") ){
                JSONObject a = q.getJSONObject("answer");
                this.content = a.getString("content");
                this.answer_id = a.getString("id");
            }

            this.user_request = new User(q);*/
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList fromJson(JSONArray data) {
        ArrayList items = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            try {
                items.add(new Suggest(data.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return items;
    }
}

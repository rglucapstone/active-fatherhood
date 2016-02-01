package com.rglucapstone.activefatherhood.data;

import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ronald on 31/01/16.
 */
public class Comment extends Model {

    public String id;
    public String content;
    public String created;
    public User user;
    public String post_id;

    public RestfulClient asynctask;

    public Comment(RestfulClient task) {
        this.asynctask = task;
    }

    public Comment(JSONObject object) {
        try {
            JSONObject q = object.getJSONObject("comment");
            if (q.has("id")) this.id = q.getString("id");
            if (q.has("content")) this.content = q.getString("content");
            if (q.has("created")) this.created = q.getString("created");
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
}

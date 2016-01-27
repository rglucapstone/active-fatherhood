package com.rglucapstone.activefatherhood.data;

import android.content.Context;

import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ronald on 18/01/16.
 */
public class User extends Model
{
    public String id;
    public String login;
    public String name;
    public String email;
    public String buen_padre;

    public RestfulClient asynctask;
    public Context context;

    public User(String id){
        this.id = id;
    }

    public User(Context context, RestfulClient task) {
        this.context = context;
        this.asynctask = task;
    }

    public User(String id, String login, String name){
        this.id = id;
        this.login = login;
        this.name = name;
    }

    public User(JSONObject object){
        try {
            JSONObject u = object.getJSONObject("user");
            this.id = u.getString("id");
            this.login = u.getString("login");
            this.name = u.getString("name");
            this.email = u.getString("email");
            this.buen_padre = u.getString("buen_padre");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public User load(String id){
        User user = null;
        RestfulClient rest = this.asynctask;
        rest.method = "GET";
        rest.uri = "/users/" + id;
        try{
            rest.execute();
        }catch (Exception e) {
        }
        return user;
    }

    public static ArrayList<User> fromJson(JSONArray data) {
        ArrayList<User> items = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            try {
                items.add(new User(data.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return items;
    }
}

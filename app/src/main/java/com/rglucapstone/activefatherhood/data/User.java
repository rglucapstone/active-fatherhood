package com.rglucapstone.activefatherhood.data;

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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static User load(String id){
        User user = null;
        RestfulClient rest = new RestfulClient();
        rest.method = "GET";
        rest.uri = "/users/" + id;
        try{
            JSONArray data = rest.execute().get();
            ArrayList<User> users = User.fromJson(data);
            if( !users.isEmpty() ){
                user = users.get(0);
            }
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

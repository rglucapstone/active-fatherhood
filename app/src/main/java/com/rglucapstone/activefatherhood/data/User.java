package com.rglucapstone.activefatherhood.data;

import android.content.Context;
import android.widget.Toast;

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
    public String password;
    public String edad;

    public String buen_padre;
    public String kind_dad;
    public int level;
    public float rating;

    public String total_questions;
    public String total_answers;
    public String total_posts;
    public String total_likes;

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

    //******************************* Constructor for New User *************************************
    public User(String login, String email, String password, String edad) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.edad = edad;
    }

    public boolean send() {
        boolean send = false;
        RestfulClient rest = this.asynctask;
        rest.method = "POST";
        rest.uri = "/users";
        try{
            rest.execute(this.toJson().toString());
            if( rest.status == 201 )
                send = true;
        }catch (Exception e) {
        }
        return send;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put("login", this.login);
            json.put("email", this.email);
            json.put("password", this.password);
            json.put("edad", this.edad);
        } catch (JSONException e) {}
        return json;
    }

    public User(JSONObject object){
        try {
            JSONObject u = object.getJSONObject("user");
            if (u.has("login")) this.login = u.getString("login");
            if (u.has("id")) this.id = u.getString("id");
            if (u.has("name")) this.name = u.getString("name");
            if (u.has("email")) this.email = u.getString("email");
            if (u.has("password")) this.password = u.getString("password");
            if (u.has("edad")) this.edad = u.getString("edad");
            if (u.has("buen_padre")) this.buen_padre = u.getString("buen_padre");
            if (u.has("kind_dad")) this.kind_dad = u.getString("kind_dad");
            if (u.has("rate")) this.rating = Float.parseFloat(u.getString("rate"));
            if( u.has("guru") ){
                JSONObject g = u.getJSONObject("guru");
                if (g.has("level")) this.level = g.getInt("level");
            }
            if( u.has("aportes") ){
                JSONObject a = u.getJSONObject("aportes");
                if (a.has("preguntas")) this.total_questions = a.getString("preguntas");
                if (a.has("respuestas")) this.total_answers = a.getString("respuestas");
                if (a.has("publicaciones")) this.total_posts = a.getString("publicaciones");
                if (a.has("me_gusta")) this.total_likes = a.getString("me_gusta");
            }
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

    public User loadbyLogin(String login){
        User user = null;
        RestfulClient rest = this.asynctask;
        rest.method = "GET";
        rest.uri = "/users/";
        JSONObject json = new JSONObject();
        try {
            json.put("login", login);
        } catch (JSONException e) {}
        try{
            rest.execute(json.toString());
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
    public boolean getLikeAnswerStatus(String id){
        return true;
    }
    public boolean getSuggestAnswerStatus(String id){
        return false;
    }
    public boolean getHighlightFatherStatus(String id){
        return true;
    }
    public void setSuggestAnswerStatus(boolean status){
        Toast.makeText(context, "suggest", Toast.LENGTH_SHORT).show();
    }
    public int setCountLikeAnwer(String id, String tipo){
        //se obtiene el numero de likes y se le debe sumar si type es add y restar si type dis
        int count = 4; //obtener el numero de likes
        if(tipo == "add"){
            count ++;
        }else
          count --;
        return count;
    }
}

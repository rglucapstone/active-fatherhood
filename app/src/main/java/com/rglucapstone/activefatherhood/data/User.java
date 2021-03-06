package com.rglucapstone.activefatherhood.data;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.rglucapstone.activefatherhood.R;
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
    public RestfulClient AsyncTask;


    public String id;
    public String login;
    public String name;
    public String email;
    public String password;
    public String edad;

    public String buen_padre;
    public String kind_dad;

    public int level = 1;
    public String rating;

    public String rate;
    public String kind_dad_id;
    public String birthdate;

    public String[] themes;

    public String total_questions;
    public String total_answers;
    public String total_posts;
    public String total_likes;

    public Guru guru;


    public Context context;

    public ImageView img_user;

    // Constructors
    public User(RestfulClient task) {
        this.AsyncTask = task;
    }

    public User(String id){
        this.id = id;
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
            if (u.has("rate")) this.rating = u.getString("rate");
            if (u.has("rate")) this.rate = u.getString("rate");
            //if (u.has("rate")) this.rating = Float.parseFloat(u.getString("rate"));
            if( u.has("guru") ){
                JSONObject g = u.getJSONObject("guru");
                this.guru = new Guru();
                if (g.has("level")) this.level = g.getInt("level");
                if (g.has("level")) this.guru.level = g.getInt("level");
                if (g.has("name")) this.guru.name = g.getString("name");
            }
            if( u.has("aportes") ){
                JSONObject a = u.getJSONObject("aportes");
                if (a.has("preguntas")) this.total_questions = a.getString("preguntas");
                if (a.has("respuestas")) this.total_answers = a.getString("respuestas");
                if (a.has("publicaciones")) this.total_posts = a.getString("publicaciones");
                if (a.has("me_gusta")) this.total_likes = a.getString("me_gusta");
            }
            if (u.has("themes") ){
                this.themes = new String[0];
                String themes = u.getString("themes");
                if( themes.length() > 0 )
                    this.themes = u.getString("themes").split(",");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean send() {
        boolean send = false;
        this.AsyncTask.method = "POST";
        this.AsyncTask.uri = "/users";
        try{
            if( !this.id.equals("") ){
                this.AsyncTask.uri += "/" + this.id;
            }
            this.AsyncTask.execute(this.toJson().toString());
            if( this.AsyncTask.status == 201 || this.AsyncTask.status == 200)
                send = true;
        }catch (Exception e) {
        }
        return send;
    }

    public void doLogin(String login) {
        this.login = login;
        this.AsyncTask.method = "GET";
        this.AsyncTask.uri = "/users/?login=" + this.login;
        this.AsyncTask.execute();
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

    // check if user did like to an answer
    public boolean getLikeAnswerStatus(ArrayList<Like> likes){
        for (int i = 0; i < likes.size(); i++) {
            Like like = likes.get(i);
            if( like.user_id.equals(this.id) )
                return true;
        }
        return false;
    }

    // check if user did like to an entity (answer - post)
    public boolean getLikeStatus(ArrayList<Like> likes){
        for (int i = 0; i < likes.size(); i++) {
            Like like = likes.get(i);
            if( like.user_id.equals(this.id) )
                return true;
        }
        return false;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put("login", this.login);
            json.put("email", this.email);
            json.put("password", this.password);
            json.put("edad", this.edad);
            json.put("rate", this.rate);
            json.put("kind_dad_id", this.kind_dad_id);
            json.put("name", this.name);
            json.put("buen_padre", this.buen_padre);
            json.put("birthdate", this.birthdate);
        } catch (JSONException e) {}
        return json;
    }


    /********************************************************/



    public User(){

    }



    public User(Context context, RestfulClient task) {
        this.context = context;
        this.AsyncTask = task;
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







    public User load(String id){
        User user = null;
        RestfulClient rest = this.AsyncTask;
        rest.method = "GET";
        rest.uri = "/users/" + id;
        try{
            rest.execute();
        }catch (Exception e) {
        }
        return user;
    }


    public User load_suggest(){
        User user = null;
        RestfulClient rest = this.AsyncTask;
        rest.method = "GET";
        rest.uri = "/users/" + this.id + "/suggestions";
        try{
            rest.execute();
        }catch (Exception e) {
        }
        return user;
    }



    public User loadQuestions(String filter){
        User user = null;
        RestfulClient rest = this.AsyncTask;
        rest.method = "GET";
        rest.uri = "/users/"+this.id+"/questions";
        if( filter.length() > 0 )
            rest.uri += "/?f="+filter;
        rest.execute();
        return user;
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

    public void setImageUser(ImageView img_view,String id){
        switch (id){
            case "1":
                img_view.setBackgroundResource(R.drawable.padre2);
                break;
            case "2":
                img_view.setBackgroundResource(R.drawable.padre4);
                break;
            case "3":
                img_view.setBackgroundResource(R.drawable.padre2);
                break;
            case "4":
                img_view.setBackgroundResource(R.drawable.padre4);
                break;
            case "5":
                img_view.setBackgroundResource(R.drawable.padre5);
                break;
            case "6":
                img_view.setBackgroundResource(R.drawable.padre4);
                break;
            case "8":
                img_view.setBackgroundResource(R.drawable.padre10);
                break;
            case "9":
                img_view.setBackgroundResource(R.drawable.padre5);
                break;
            case "10":
                img_view.setBackgroundResource(R.drawable.padre5);
                break;
            case "11":
                img_view.setBackgroundResource(R.drawable.padre10);
                break;
            case "13":
                img_view.setBackgroundResource(R.drawable.padre8);
                break;
            case "15":
                img_view.setBackgroundResource(R.drawable.padre8);
                break;
            case "14":
                img_view.setBackgroundResource(R.drawable.padre10);
                break;
            case "16":
                img_view.setBackgroundResource(R.drawable.padre2);
                break;
            default:
                img_view.setBackgroundResource(R.drawable.ico_avatar_white);
                break;
        }
    }
}

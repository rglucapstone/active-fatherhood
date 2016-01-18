package com.rglucapstone.activefatherhood.data;

import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import android.content.Context;

/**
 * Created by ronald on 13/01/16.
 */
public class Question {

    public Context context;
    public AsyncTask task;

    public String content;
    public String created;

    public User user;



    public String userstr;
    public String contentstr;
    public String datetime;
    public String tags;
    public String likes;
    public String answers;

    public Question(String quser, String qdatetime, String qcontent, String qtags,
                    String qlikes, String qanswers) {
        this.userstr = quser;
        this.datetime = qdatetime;
        this.contentstr = qcontent;
        this.tags = qtags;
        this.likes = qlikes;
        this.answers = qanswers;
    }

    public Question(String content, String created){
        this.content = content;
        this.created = created;
    }

    public Question(Context context){
        this.context = context;
    }

    public ArrayList getAll(){
        ArrayList<Question> list = new ArrayList<>();
        try{
            RestfulClient rest = new RestfulClient(this.context, "GET", "/questions");
            JSONArray data = rest.execute().get();
            this.task = rest;
            try {
                for (int i = 0; i < data.length(); i++) {
                    JSONObject obj = data.getJSONObject(i);
                    Question q = new Question(obj.getString("content"), obj.getString("created"));

                    JSONObject objUser = obj.getJSONObject("user");
                    User u = new User(objUser.getString("login"), objUser.getString("name"));
                    q.user = u;
                    list.add(q);
                }
            } catch (JSONException e) {
            }
        }catch (ExecutionException | InterruptedException ei) {
        }
        return list;
    }

}

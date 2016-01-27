package com.rglucapstone.activefatherhood.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ronald on 27/01/16.
 */
public class Answer extends Model{

    public String id;
    public String content;
    public String created;

    public Answer() {

    }

    public Answer(JSONObject object) {
        try {
            JSONObject q = object.getJSONObject("answer");
            this.id = q.getString("id");
            this.content = q.getString("content");
            this.created = q.getString("created");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList fromJson(JSONArray data) {
        ArrayList<Answer> items = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            try {
                items.add(new Answer(data.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return items;
    }
}

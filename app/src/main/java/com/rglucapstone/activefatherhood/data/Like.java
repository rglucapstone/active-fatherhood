package com.rglucapstone.activefatherhood.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ronald on 03/02/16.
 */
public class Like extends Model{

    public String id;
    public String user_id;
    public String clase;
    public String clase_id;
    public String created;


    public Like(JSONObject object) {
        try {
            JSONObject q = object.getJSONObject("like");
            if (q.has("id")) this.id = q.getString("id");
            if (q.has("user_id")) this.user_id = q.getString("user_id");
            if (q.has("class")) this.clase = q.getString("class");
            if (q.has("class_id")) this.clase_id = q.getString("class_id");
            if (q.has("created")) this.created = q.getString("created");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList fromJson(JSONArray data) {
        ArrayList<Like> items = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            try {
                items.add(new Like(data.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return items;
    }
}

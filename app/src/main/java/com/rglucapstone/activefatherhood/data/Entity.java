package com.rglucapstone.activefatherhood.data;

import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by ronald on 02/02/16.
 */
public class Entity {

    public RestfulClient asynctask;

    public Entity(RestfulClient task) {
        this.asynctask = task;
    }


    public HashMap<String, ArrayList> search(String query) {
        HashMap<String, ArrayList> items = new HashMap<>();
        RestfulClient rest = this.asynctask;
        rest.method = "GET";
        rest.uri = "/entities/?q="+query;
        try{
            rest.execute();
        }catch (Exception e) {
        }
        return items;
    }

    public static HashMap<String, ArrayList> fromJsontoHash(JSONArray data) {

        HashMap<String, ArrayList> hashEntities = new HashMap<>();
        /*for (int i = 0; i < data.length(); i++) {
            try {
                JSONObject object = data.getJSONObject(i);
                Iterator<String> iterator = object.keys();
                while(iterator.hasNext()) {
                    String currentKey = iterator.next();
                    JSONObject u = object.getJSONObject(currentKey);
                    gurus.put(new Theme(currentKey, u.getString("name")), User.fromJson(u.getJSONArray("gurus")));
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }*/
        return hashEntities;
    }
}

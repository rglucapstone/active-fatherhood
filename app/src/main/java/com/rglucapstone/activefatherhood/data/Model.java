package com.rglucapstone.activefatherhood.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ronald on 25/01/16.
 */
public class Model {

    public Model() {

    }

    public Model(JSONObject object) {

    }

    // Factory method to convert an array of JSON objects into a list of objects
    // User.fromJson(jsonArray);
    public static ArrayList fromJson(JSONArray data) {
        ArrayList items = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            try {
                items.add(new Model(data.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return items;
    }

    public static HashMap fromJsontoHash(JSONArray data) {
        HashMap<String, ArrayList<Model>> items = new HashMap<>();
        for (int i = 0; i < data.length(); i++) {
            /*try {
                //items.put(Integer.toString(i),new Model(data.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }*/
        }
        return items;
    }
}

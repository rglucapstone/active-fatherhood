package com.rglucapstone.activefatherhood.data;

import com.rglucapstone.activefatherhood.sync.RestfulClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by ronald on 18/01/16.
 */
public class Prueba {

    public String nombre;
    public String abreviatura;

    public Prueba(String nombre, String abreviatura) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
    }


    public static ArrayList getAll(){
        ArrayList<Prueba> list = new ArrayList<>();
        try {
            RestfulClient rest = new RestfulClient(null, "GET", "/pruebas");
            //rest.execute();
            //JSONArray data = rest.response;
            JSONArray data = rest.execute().get();
            try {
                for (int i = 0; i < data.length(); i++) {
                    JSONObject object = data.getJSONObject(i);
                    Prueba p = new Prueba(object.getString("nombre"), object.getString("abreviatura"));
                    list.add(p);
                }
            } catch (JSONException e) {
            }
        }catch (ExecutionException | InterruptedException ei) {
            //ei.printStackTrace();
        }
        return list;
    }



}

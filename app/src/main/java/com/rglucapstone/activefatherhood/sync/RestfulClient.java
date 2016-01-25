package com.rglucapstone.activefatherhood.sync;

import android.os.AsyncTask;
import java.net.URL;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.TextView;
//import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;
import org.json.JSONException;

import org.json.JSONArray;

import java.util.ArrayList;


import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Model;
import com.rglucapstone.activefatherhood.interfaces.restfulResponse;

/**
 * Created by ronald on 17/01/16.
 */
public class RestfulClient extends AsyncTask<String, Void, JSONArray>{

    private Context context;
    private ArrayAdapter adapter;
    private Model element;

    private String server = "http://ct-alpha.funiber.org/rgonzales/paternidad_plus/api";
    private HttpURLConnection conn;

    public String uri;
    public String method;
    public JSONArray response;


    public RestfulClient(Model element, Context context, ArrayAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
        this.element = element;
    }

    public RestfulClient(Model element, Context context) {
        this.context = context;
        this.element = element;
    }
    public RestfulClient() {

    }



    @Override
    protected void onPreExecute() {

    }

    @Override
    protected JSONArray doInBackground(String... params) {
        String str_content = "";

        // REQUEST
        this.setRequest();
        try {
            switch (this.method) {
                case "GET":
                    String line;
                    InputStream content = this.conn.getInputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(content));
                    while ((line = in.readLine()) != null) {
                        str_content = str_content + line;
                    }
                    break;
                case "POST":
                    break;
            }
        }catch(Exception e) {
        }

        // RESPONSE
        this.setResponse(str_content);
        //this.element.loadData(this.response);
        return this.response;
    }

    @Override
    protected void onPostExecute(JSONArray result) {
        if (result.length() > 0){
            //this.element.loadData(result);
            //this.adapter.notifyDataSetChanged();
        }
    }


    private void setRequest(){
        String uri = this.server + this.uri;
        try{
            URL url = new URL(uri);
            this.conn = (HttpURLConnection) url.openConnection();
            this.conn.setRequestProperty("Accept", "application/json");
            this.conn.setRequestProperty("Accept-Charset", "UTF-8");
            this.conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            this.conn.setRequestProperty("Accept-Encoding", "UTF-8");
            this.conn.setRequestMethod(this.method);
        }catch(Exception e) {
            this.conn = null;
        }
    }

    private void setResponse(String str){
        try{
            JSONObject json = new JSONObject(str);
            if (json.has("data")) {
                JSONArray data = json.getJSONArray("data");
                this.response = data;
            }
        } catch (JSONException e) {
        }
    }




}

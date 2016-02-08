package com.rglucapstone.activefatherhood.sync;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;
import android.content.Context;
import android.view.View;
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
import java.io.IOException;

import org.json.JSONArray;

import 	java.io.OutputStreamWriter;
import 	java.io.BufferedWriter;
import 	java.io.OutputStream;
import java.io.DataOutputStream;
import 	java.net.URLEncoder;

import java.util.ArrayList;


import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.data.Model;
import com.rglucapstone.activefatherhood.interfaces.restfulResponse;

/**
 * Created by ronald on 17/01/16.
 */
public class RestfulClient extends AsyncTask<String, Void, JSONObject>{

    private Context context;
    private ArrayAdapter adapter;
    private Model element;
    //public View view;

    //private String server = "http://ct-alpha.funiber.org/rgonzales/paternidad_plus/api";
    private String server = "http://betasg.funiber.org/pruebas/api";

    public HttpURLConnection conn;

    public String uri;
    public String method;
    public JSONObject response;
    public int status;


    //public RestfulClient(View view) { this.view = view; }

    public RestfulClient(Context context) {
        this.context = context;
    }

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
    protected JSONObject doInBackground(String... params) {
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
                    this.setResponse(str_content);
                    break;
                case "POST":
                    this.conn.setDoInput(true);
                    this.conn.setDoOutput(true);
                    OutputStream os = this.conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
                    writer.write(params[0]);
                    writer.flush();
                    writer.close();
                    os.close();
                    this.conn.connect();
                    try{
                        if( this.conn.getResponseCode() == 200 || this.conn.getResponseCode() == 201){
                            String output;
                            BufferedReader buf = new BufferedReader(new InputStreamReader(this.conn.getInputStream()));
                            while ((output = buf.readLine()) != null) {
                                str_content = str_content + output;
                            }
                            this.setResponse(str_content);
                        }
                    }catch(IOException e){}
                    break;
            }
        }catch(Exception e) {
        }

        try{
            this.status = this.conn.getResponseCode();
        }catch(IOException e){

        }

        return this.response;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        //if (result.length() > 0){
            //this.element.loadData(result);
            //this.adapter.notifyDataSetChanged();
        //}
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
            this.response = json;
        } catch (JSONException e) {
        }
    }




}

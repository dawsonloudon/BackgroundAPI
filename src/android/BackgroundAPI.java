package com.dawsonloudon.backgroundapi;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class BackgroundAPI extends CordovaPlugin {
    
    public static final String MAKE_API_CALL = "makeApiCall";
    public static final String CALL_TYPE_GET = "GET";
    public static final String CALL_TYPE_POST = "POST";
    public static CallbackContext cb;
    
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        cb = callbackContext;
        String dataType = "";
        String url = "";
        try {
            if(MAKE_API_CALL.equals(action)) {
                JSONObject arg_object = args.getJSONObject(0);
                dataType = arg_object.getString("dataType");
                url = arg_object.getString("url");
                HttpClient httpClient = new DefaultHttpClient();
                if(CALL_TYPE_GET.equals(dataType)) {
                    HttpGet get_req = new HttpGet(url);

                        HttpResponse response = httpClient.execute(get_req);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                    String json = reader.readLine();
                    JSONTokener tokener = new JSONTokener(json);
                    JSONObject finalResult = new JSONObject(tokener);
                        cb.success(finalResult);
                        return true;

                }
                else if(CALL_TYPE_POST.equals(dataType)) {
                    HttpPost post_req = new HttpPost(url);
                    return true;
                }
                else {
                    cb.error("Request type not supported yet");
                    return false;
                }
            }
            cb.error("Invalid action");
            return false;
        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
            cb.error(e.getMessage());
            return false;
        }
    }
    
}
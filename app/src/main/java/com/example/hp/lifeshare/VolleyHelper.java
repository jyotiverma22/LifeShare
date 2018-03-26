package com.example.hp.lifeshare;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class VolleyHelper {

    private final Context mContext;
    private final RequestQueue mRequestQueue;
   // private final ImageLoader mImageLoader;
 //public  final static String mBaseUrl="http://192.168.43.154:3000";
    public  final static String mBaseUrl="http://life-share.herokuapp.com";

    public VolleyHelper(Context c){
        mContext = c;
        mRequestQueue = Volley.newRequestQueue(mContext);
              //  mBaseUrl = baseUrl;
      // mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache());
    }

    private String contructUrl(String method){
        return mBaseUrl + "/" + method;
    }

    /* public ImageLoader getImageLoader(){
        return mImageLoader;
    }*/

    public void get(String method, JSONObject jsonRequest,
                    Listener<JSONObject> listener, ErrorListener errorListener){

        JsonObjectRequest objRequest = new JsonObjectRequest(Method.GET, contructUrl(method), jsonRequest, listener, errorListener);
        objRequest.setRetryPolicy(new DefaultRetryPolicy(20*1000,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(objRequest);
    }
    public void getArr(String method, JSONArray jsonRequest,
                       Listener<JSONArray> listener, ErrorListener errorListener){

        JsonArrayRequest objRequest = new JsonArrayRequest(Method.GET, contructUrl(method), jsonRequest, listener, errorListener);
        objRequest.setRetryPolicy(new DefaultRetryPolicy(20*1000,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(objRequest);
    }


    public void post(String method, JSONObject jsonRequest,
                     Listener<JSONObject> listener, ErrorListener errorListener){

        JsonObjectRequest objRequest = new JsonObjectRequest(Method.POST, contructUrl(method), jsonRequest, listener, errorListener);
        objRequest.setRetryPolicy(new DefaultRetryPolicy(20*1000,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(objRequest);
    }
    public void postArr(String method, JSONArray jsonRequest,
                     Listener<JSONArray> listener, ErrorListener errorListener){

        JsonArrayRequest objRequest = new JsonArrayRequest(Method.POST, contructUrl(method), jsonRequest, listener, errorListener);
        objRequest.setRetryPolicy(new DefaultRetryPolicy(20*1000,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(objRequest);
    }


}
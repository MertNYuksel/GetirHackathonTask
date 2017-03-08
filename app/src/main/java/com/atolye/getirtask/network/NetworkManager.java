package com.atolye.getirtask.network;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.atolye.getirtask.App;
import com.google.gson.Gson;


/**
 * Created by mertn on 08-Mar-17.
 */

public class NetworkManager{
    private static NetworkManager instance;
    private final Gson gson;
    private final RequestQueue requestQueue;

    public static synchronized NetworkManager getInstance(App app){
        if (instance == null)
            instance = new NetworkManager(app);
        return instance;
    }
    private NetworkManager(App app){
        this.gson = app.getGson();
        this.requestQueue = Volley.newRequestQueue(app);
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);

    }


}

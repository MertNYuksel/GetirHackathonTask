package com.atolye.getirtask.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.atolye.getirtask.App;
import com.atolye.getirtask.network.NetworkManager;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mertn on 08-Mar-17.
 */

public class BaseActivity extends AppCompatActivity {
    private NetworkManager networkManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkManager = NetworkManager.getInstance(getApp());

    }

    public App getApp() {
        return ((App) getApplication());
    }

    public Gson getGson() {
        return getApp().getGson();
    }

    public <T> Request sendRequest(int method, String url, Object payload, final Class<T> responseClass, final Response.Listener<T> successListener, final Response.ErrorListener failListener) {
        JSONObject jsonPayload = null;
        showProgress();
        try {
            jsonPayload = new JSONObject(getGson().toJson(payload));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(method, url, jsonPayload,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (successListener != null){
                            successListener.onResponse(getGson().fromJson(response.toString(), responseClass));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (failListener != null){
                            failListener.onErrorResponse(error);
                        }
                        else{
                            Toast.makeText(BaseActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        networkManager.addToRequestQueue(jsObjRequest);
        hideProgress();
        return jsObjRequest;
    }

    private void addProgressBar() {
        progressBar = new ProgressBar(this);
        int px = App.convertDpToPixel(30);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(px, px);
        lp.gravity = Gravity.CENTER;
        getWindow().addContentView(progressBar, lp);
    }

    public void showProgress() {
        if (progressBar == null)
            addProgressBar();
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        if (progressBar != null)
            progressBar.setVisibility(View.GONE);
    }
}

package com.atolye.getirtask;

import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by mertn on 08-Mar-17.
 */

public class App extends Application {
    private Gson gson;
    @Override
    public void onCreate() {
        super.onCreate();
        gson = new Gson();
    }
    public Gson getGson(){
        return gson;
    }
    public static int convertDpToPixel(int dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static int convertPixelsToDp(int px) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return px / (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}

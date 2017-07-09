package com.otoniel.g6pd.g6pddeficiencyapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.otoniel.g6pd.g6pddeficiencyapp.MyApplication;

/**
 * Created by eltonjhony on 20/05/17.
 */

public class NetworkUtil {

    private NetworkUtil() {
    }

    public static boolean isNetworkAvailable() {
        Context applicationContext = MyApplication.getMyApplication().getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}

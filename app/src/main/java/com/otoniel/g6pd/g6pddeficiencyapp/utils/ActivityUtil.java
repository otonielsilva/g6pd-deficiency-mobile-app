package com.otoniel.g6pd.g6pddeficiencyapp.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.otoniel.g6pd.g6pddeficiencyapp.MyApplication;

/**
 * Created by eltonjhony on 3/31/17.
 */

public class ActivityUtil {

    private ActivityUtil() {
    }

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static Context getGlobalContext() {
        return MyApplication.getMyApplication().getApplicationContext();
    }
}

package com.otoniel.g6pd.g6pddeficiencyapp.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by eltonjhony on 09/07/17.
 */

public class PermissionsUtil {

    public static final int PERMISSIONS_GRANTED = 100;

    private PermissionsUtil() {
    }

    public static void requestPermissions(Activity context, PermissionGranted callback, String... permissions) {
        if (Build.VERSION.SDK_INT >= 23) {

            int target = 0;
            int total = permissions.length;

            for(String permission: permissions) {

                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) {

                        // Show an expanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                        callback.onExplanation(permission);

                    }

                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(context, new String[]{permission}, PERMISSIONS_GRANTED);
                } else {
                    target++;
                }
            }

            if (target == total) {
                callback.onExecute();
            }


        } else {
            callback.onExecute();
        }
    }

    public interface PermissionGranted {
        void onExecute();
        void onExplanation(String permission);
    }
}

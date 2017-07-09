package com.otoniel.g6pd.g6pddeficiencyapp.common.provider;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * Created by eltonjhony on 09/07/17.
 */

public class GenericFileProvider extends FileProvider {

    public static final String AUTHORITY = "com.otoniel.g6pd.g6pddeficiencyapp.common.provider";

    public static Uri createUriFromFile(Context context) {
        return FileProvider.getUriForFile(context, AUTHORITY, createFile());
    }

    @NonNull
    private static File createFile() {
        return new File(Environment.getExternalStorageDirectory(), "pic_"+ String.valueOf(System.currentTimeMillis()) + ".jpg");
    }
}

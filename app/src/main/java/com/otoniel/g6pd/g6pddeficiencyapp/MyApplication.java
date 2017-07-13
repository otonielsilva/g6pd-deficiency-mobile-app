package com.otoniel.g6pd.g6pddeficiencyapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by eltonjhony on 08/07/17.
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;
    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setupGlobalContext();
        setupDIManager();
        setupRealm();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }

    private void setupGlobalContext() {
        myApplication = this;
    }

    private void setupDIManager() {
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule()).build();
    }

    private void setupRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getApplicationContext())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}

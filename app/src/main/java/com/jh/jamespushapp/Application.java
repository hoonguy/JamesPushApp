package com.jh.jamespushapp;

import android.support.multidex.MultiDexApplication;

import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.config.AWSConfiguration;

public class Application extends MultiDexApplication {
    private static final String TAG = Application.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        initilazieApplication();
    }

    private void initilazieApplication() {
        AWSConfiguration awsConfiguration = new AWSConfiguration(getApplicationContext());

        //If IdentityManager is not created, create it
        if(IdentityManager.getDefaultIdentityManager() == null) {
            IdentityManager identityManager = new IdentityManager(getApplicationContext(), awsConfiguration);
            identityManager.setDefaultIdentityManager(identityManager);
        }
     }
}

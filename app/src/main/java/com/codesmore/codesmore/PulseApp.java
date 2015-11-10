package com.codesmore.codesmore;

import android.app.Application;

import com.codesmore.codesmore.integration.backend.pojo.ParseCategory;
import com.codesmore.codesmore.integration.backend.pojo.ParseIssue;
import com.codesmore.codesmore.utils.MainThreadBus;
import com.parse.Parse;
import com.parse.ParseObject;
import com.squareup.otto.Bus;

public class PulseApp extends Application {

    public static final String TAG = "pulse";

    private static PulseApp sInstance;
    private Bus mBus;

    public static PulseApp getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        initParse();
    }

    private void initParse() {
        ParseObject.registerSubclass(ParseCategory.class);
        ParseObject.registerSubclass(ParseIssue.class);
        Parse.initialize(this, getString(R.string.parse_application_id), getString(R.string.parse_client_id));
    }

    public Bus getBus() {
        if (mBus == null) {
            mBus = new MainThreadBus();
        }

        return mBus;
    }
}

package com.codesmore.codesmore;

import android.app.Application;

import com.codesmore.codesmore.utils.MainThreadBus;
import com.squareup.otto.Bus;

public class PulseApp extends Application {

    private static PulseApp sInstance;
    private Bus mBus;

    public static PulseApp getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
    }

    public Bus getBus() {
        if (mBus == null) {
            mBus = new MainThreadBus();
        }

        return mBus;
    }
}

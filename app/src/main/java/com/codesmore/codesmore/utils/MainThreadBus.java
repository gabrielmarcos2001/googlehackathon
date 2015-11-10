package com.codesmore.codesmore.utils;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

/**
 * Thread-safe Bus. Can be called from any thread,
 * it'll make sure to execute all events on the MainThread.
 */
public class MainThreadBus extends Bus {

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    MainThreadBus.super.post(event);
                }
            });
        }
    }
}

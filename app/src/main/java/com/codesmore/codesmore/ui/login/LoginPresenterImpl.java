package com.codesmore.codesmore.ui.login;

import android.os.Handler;

/**
 * Created by demouser on 11/9/15.
 */
public class LoginPresenterImpl implements LoginPresenter {

    private LoginView mView;

    public LoginPresenterImpl(LoginView view) {
        mView = view;
    }

    public void onLoginClicked() {
        if (mView != null) {
            mView.showLoader();
        }

        // Lets remove this fake later :P
        final Handler offset0 = new Handler();
        offset0.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mView != null) {
                    mView.onAuthenticationComplete();
                }
            }
        }, 2000);
    }

    public void onAuthenticationComplete() {
        mView.onAuthenticationComplete();
    }

}
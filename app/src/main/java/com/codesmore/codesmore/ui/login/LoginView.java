package com.codesmore.codesmore.ui.login;

/**
 * Created by demouser on 11/9/15.
 */
public interface LoginView {
    void onAuthenticationComplete();
    void onAuthenticationFailed();
    void showLoader();

}

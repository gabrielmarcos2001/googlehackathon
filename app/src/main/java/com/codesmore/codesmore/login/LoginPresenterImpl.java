package com.codesmore.codesmore.login;

/**
 * Created by demouser on 11/9/15.
 */
public class LoginPresenterImpl implements LoginPresenter {

    private LoginView mView;

    public LoginPresenterImpl(LoginView view) {
        mView = view;
    }

    public void onLoginClicked() {

    }

    public void onAuthenticationComplete() {
        mView.onAuthenticationComplete();
    }

}
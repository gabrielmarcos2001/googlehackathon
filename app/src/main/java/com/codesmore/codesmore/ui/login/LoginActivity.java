package com.codesmore.codesmore.ui.login;


import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.codesmore.codesmore.BaseActivity;
import com.codesmore.codesmore.MainActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginView {

    private LoginPresenter mPresenter;

    @Override
    public void onClick(View v) {
        mPresenter.onLoginClicked();
    }

    @Override
    public void onAuthenticationComplete() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAuthenticationFailed() {
        Toast.makeText(this, "dfsdfs", Toast.LENGTH_LONG);
    }
}
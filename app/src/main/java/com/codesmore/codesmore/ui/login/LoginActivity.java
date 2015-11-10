package com.codesmore.codesmore.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.codesmore.codesmore.BaseActivity;
import com.codesmore.codesmore.ui.main.MainActivity;
import com.codesmore.codesmore.R;
import com.codesmore.codesmore.ui.bubbleviews.ViewAnimatedBackground;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginView {

    private View mContainer;
    private LoginPresenter mPresenter;
    private ViewAnimatedBackground mOverlayView;
    private Button mLoginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mContainer = findViewById(R.id.container);

        if (mPresenter == null) {
            mPresenter = new LoginPresenterImpl(this);
        }

        mOverlayView = new ViewAnimatedBackground(this);
        mOverlayView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        mOverlayView.setReduceAlpha(false);

        ((ViewGroup)mContainer).addView(mOverlayView);

        mLoginButton = (Button)findViewById(R.id.google_login);
        mLoginButton.setOnClickListener(this);

    }

    @Override
    public void showLoader() {
        this.mOverlayView.increaseSpeed(true);
    }

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
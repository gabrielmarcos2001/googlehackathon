package com.codesmore.codesmore.ui.navdrawer;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.codesmore.codesmore.R;

import butterknife.ButterKnife;

/**
 * User Profile Image
 */
public class ProfileImage extends RelativeLayout {

    private String mImageUrl;
    private ImageView mImageView;
    private View mLoaderSection;

    /**
     * Default view constructor
     *
     * @param context
     */
    public ProfileImage(Context context) {
        super(context);

        inflateLayout(context, null);

    }

    /**
     * View constructor with attributes
     *
     * @param context
     * @param attrs
     */
    public ProfileImage(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflateLayout(context, attrs);

    }

    /**
     * Inflates the view layout
     *
     * @param context
     */
    private void inflateLayout(Context context, AttributeSet attrs) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_profile_image, this, true);

        mImageView = (ImageView)findViewById(R.id.image_view);
        mLoaderSection = findViewById(R.id.loader_section);

        mImageView.setImageResource(R.drawable.profile);
        updateViewData();

    }

    private void updateViewData() {

        final Handler fakeContextualEvent = new Handler();
        fakeContextualEvent.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideLoader();
            }
        }, 1000);
    }

    /**
     * Refreshes the User Image
     */
    public void refreshUserImage() {



    }

    public void setImage(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
    }

    /**
     * Hides the Loader
     */
    public void hideLoader() {

        if (mLoaderSection.getVisibility() == View.GONE) return;

        Animation hideAnim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        mLoaderSection.startAnimation(hideAnim);
        hideAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLoaderSection.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * Shows the loader
     */
    public void showLoader() {
        if (mLoaderSection.getVisibility() == View.VISIBLE) return;

        Animation showAnim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        mLoaderSection.setVisibility(VISIBLE);
        mLoaderSection.startAnimation(showAnim);

    }

}

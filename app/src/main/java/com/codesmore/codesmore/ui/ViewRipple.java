package com.codesmore.codesmore.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.codesmore.codesmore.R;

/**
 * Created by gabrielmarcos on 11/9/15.
 */
public class ViewRipple extends RelativeLayout {

    private View mCircle1;
    private View mCircle2;
    private View mCircle3;

    public ViewRipple(Context context) {
        super(context);
        inflateLayout(context, null);
    }

    public ViewRipple(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayout(context, null);

    }

    private void inflateLayout(Context context, AttributeSet attrs) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_ripple, this, true);

        mCircle1 = findViewById(R.id.circle_1);
        mCircle2 = findViewById(R.id.circle_2);
        mCircle3 = findViewById(R.id.circle_3);

        mCircle1.setVisibility(View.GONE);
        mCircle2.setVisibility(View.GONE);
        mCircle3.setVisibility(View.GONE);

    }

    public void showRipple() {

        ScaleAnimation scaleCircle1Animation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleCircle1Animation.setDuration(3000);
        scaleCircle1Animation.setInterpolator(new AccelerateDecelerateInterpolator());

        scaleCircle1Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mCircle1.setVisibility(GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final AlphaAnimation alphaAnimation1 = new AlphaAnimation(1,0);
        alphaAnimation1.setDuration(3000);
        alphaAnimation1.setInterpolator(new AccelerateInterpolator());

        final AlphaAnimation alphaAnimation2 = new AlphaAnimation(1,0);
        alphaAnimation2.setDuration(3000);
        alphaAnimation2.setInterpolator(new AccelerateInterpolator());

        final AlphaAnimation alphaAnimation3 = new AlphaAnimation(1,0);
        alphaAnimation3.setDuration(3000);
        alphaAnimation3.setInterpolator(new AccelerateInterpolator());

        AnimationSet set1 = new AnimationSet(false);
        set1.addAnimation(scaleCircle1Animation);
        set1.addAnimation(alphaAnimation1);

        final ScaleAnimation scaleCircle2Animation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleCircle2Animation.setDuration(3000);
        scaleCircle2Animation.setInterpolator(new AccelerateDecelerateInterpolator());

        scaleCircle2Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mCircle2.setVisibility(GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final AnimationSet set2 = new AnimationSet(false);
        set2.addAnimation(scaleCircle2Animation);
        set2.addAnimation(alphaAnimation2);

        final ScaleAnimation scaleCircle3Animation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleCircle3Animation.setDuration(3000);
        scaleCircle3Animation.setInterpolator(new AccelerateDecelerateInterpolator());

        scaleCircle3Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mCircle3.setVisibility(GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final AnimationSet set3 = new AnimationSet(false);
        set3.addAnimation(scaleCircle3Animation);
        set3.addAnimation(alphaAnimation3);

        mCircle1.startAnimation(set1);
        mCircle1.setVisibility(VISIBLE);

        final Handler offset = new Handler();
        offset.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCircle2.setVisibility(VISIBLE);
                mCircle2.startAnimation(set2);
            }
        }, 1000);

        final Handler offset2 = new Handler();
        offset2.postDelayed(new Runnable() {
            @Override
            public void run() {

                mCircle3.setVisibility(VISIBLE);
                mCircle3.startAnimation(set3);

            }
        }, 2000);

    }

}

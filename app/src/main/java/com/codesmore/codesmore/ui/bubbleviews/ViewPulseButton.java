package com.codesmore.codesmore.ui.bubbleviews;

import android.content.Context;
import android.os.Handler;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.codesmore.codesmore.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by gabrielmarcos on 11/9/15.
 */
public class ViewPulseButton extends RelativeLayout {

    protected static final int RIPPLE_DURATION_MS = 3000;
    protected static final int RIPPLE_INTERVAL_MS = 5000;
    protected static final int CONTEXTUAL_STATUS_MS = 5000;
    protected static final int RIPPLE_INTERVAL_1_MS = 1000;
    protected static final int RIPPLE_INTERVAL_2_MS = 2000;

    private View mCircle1;
    private View mCircle2;
    private View mCircle3;
    private AnimationSet mCircle1Set;
    private AnimationSet mCircle2Set;
    private AnimationSet mCircle3Set;
    private View mContextualBackground;
    private boolean mActive = true;
    private boolean mAnimationsInitialized = false;
    private ImageView mImageIcon;

    public ViewPulseButton(Context context) {
        super(context);
        inflateLayout(context, null);
    }

    public ViewPulseButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayout(context, null);

    }

    private void inflateLayout(Context context, AttributeSet attrs) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_pulse, this, true);

        initViews();
    }

    protected void initViews() {

        mCircle1 = findViewById(R.id.circle_1);
        mCircle2 = findViewById(R.id.circle_2);
        mCircle3 = findViewById(R.id.circle_3);
        mImageIcon = (ImageView)findViewById(R.id.icon);
        mContextualBackground = findViewById(R.id.contextual_background);

        mContextualBackground.setVisibility(INVISIBLE);
        mCircle1.setVisibility(View.GONE);
        mCircle2.setVisibility(View.GONE);
        mCircle3.setVisibility(View.GONE);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if (mActive) {

                    Handler mainHandler = new Handler(getContext().getMainLooper());

                    Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() {
                            showRipple();
                        }
                    };

                    mainHandler.post(myRunnable);
                }
            }
        }, 0, RIPPLE_INTERVAL_MS);
    }

    private void initAnimations() {

        ScaleAnimation scaleCircle1Animation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleCircle1Animation.setDuration(RIPPLE_DURATION_MS);
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

        final AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.5f,0);
        alphaAnimation1.setDuration(RIPPLE_DURATION_MS);
        alphaAnimation1.setInterpolator(new AccelerateInterpolator());

        final AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.15f,0);
        alphaAnimation2.setDuration(RIPPLE_DURATION_MS);
        alphaAnimation2.setInterpolator(new AccelerateInterpolator());

        final AlphaAnimation alphaAnimation3 = new AlphaAnimation(0.5f,0);
        alphaAnimation3.setDuration(RIPPLE_DURATION_MS);
        alphaAnimation3.setInterpolator(new AccelerateInterpolator());

        mCircle1Set = new AnimationSet(false);
        mCircle1Set.addAnimation(scaleCircle1Animation);
        mCircle1Set.addAnimation(alphaAnimation1);

        final ScaleAnimation scaleCircle2Animation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleCircle2Animation.setDuration(RIPPLE_DURATION_MS);
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

        mCircle2Set = new AnimationSet(false);
        mCircle2Set.addAnimation(scaleCircle2Animation);
        mCircle2Set.addAnimation(alphaAnimation2);

        final ScaleAnimation scaleCircle3Animation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleCircle3Animation.setDuration(RIPPLE_DURATION_MS);
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

        mCircle3Set = new AnimationSet(false);
        mCircle3Set.addAnimation(scaleCircle3Animation);
        mCircle3Set.addAnimation(alphaAnimation3);

        mAnimationsInitialized = true;

    }

    /**
     * Shows the ripple effect
     */
    public void showRipple() {

        if (!mAnimationsInitialized) {
            initAnimations();
        }

        mCircle1.startAnimation(mCircle1Set);
        mCircle1.setVisibility(VISIBLE);

        final Handler offset = new Handler();
        offset.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCircle2.setVisibility(VISIBLE);
                mCircle2.startAnimation(mCircle2Set);
            }
        }, RIPPLE_INTERVAL_1_MS);

        final Handler offset2 = new Handler();
        offset2.postDelayed(new Runnable() {
            @Override
            public void run() {

                mCircle3.setVisibility(VISIBLE);
                mCircle3.startAnimation(mCircle3Set);

            }
        }, RIPPLE_INTERVAL_2_MS);

    }

    public void activatePulse() {
        mActive = true;
    }

    public void deactivatePulse() {
        mActive = false;
    }

    public void showContextualEvent() {

        Vibrator v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);

        mImageIcon.setImageResource(R.drawable.exclamation_white);

        final AlphaAnimation alphaAnimation = new AlphaAnimation(0f,1f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());

        mContextualBackground.setVisibility(VISIBLE);
        mContextualBackground.startAnimation(alphaAnimation);

        final Handler offset2 = new Handler();
        offset2.postDelayed(new Runnable() {
            @Override
            public void run() {

                showNormalState();

            }
        }, CONTEXTUAL_STATUS_MS);

    }

    public void showNormalState() {

        mImageIcon.setImageResource(R.drawable.add_white);

        final AlphaAnimation alphaAnimation = new AlphaAnimation(1f,0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());

        mContextualBackground.startAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mContextualBackground.setVisibility(INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}

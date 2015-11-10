package com.codesmore.codesmore.ui.bubbleviews;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codesmore.codesmore.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by gabrielmarcos on 11/9/15.
 */
public class ViewBubble extends RelativeLayout {

    private static final int RIPPLE_DURATION_MS = 4000;
    private static final int ALPHA_DURATION_MS = 3000;
    private static final int RIPPLE_INTERVAL_MS = 5500;
    private static final int RIPPLE_INTERVAL_1_MS = 700;

    private View mCircle1;
    private View mCircle2;
    private View mContainer;
    private View mButtonView;
    private View mDownVote;
    private View mUpVote;
    private View mButtonConatiner;
    private View mRippleContainer;
    private int mRandomOffset;
    private TextView mTitle;

    private AnimationSet mCircle1Set;
    private AnimationSet mCircle2Set;
    private boolean mActive = true;
    private boolean mAnimationsInitialized = false;
    private ImageView mImageIcon;

    public ViewBubble(Context context) {
        super(context);
        inflateLayout(context, null);
    }

    public ViewBubble(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayout(context, null);

    }

    private void inflateLayout(Context context, AttributeSet attrs) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_bubble, this, true);

        mCircle1 = findViewById(R.id.circle_1);
        mCircle2 = findViewById(R.id.circle_2);
        mImageIcon = (ImageView)findViewById(R.id.icon);
        mContainer = findViewById(R.id.container);
        mButtonView = findViewById(R.id.button_view);
        mDownVote = findViewById(R.id.downvote);
        mUpVote = findViewById(R.id.upvote);
        mButtonConatiner = findViewById(R.id.button_container);
        mRippleContainer = findViewById(R.id.ripple_container);
        mTitle = (TextView)findViewById(R.id.bubble_title);

        mTitle.setVisibility(GONE);
        mDownVote.setVisibility(GONE);
        mUpVote.setVisibility(GONE);

        setVisibility(INVISIBLE);

        mButtonView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    expand();
                    return true;
                }else if (event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP) {

                    compress();
                    return true;
                }

                return false;
            }
        });

        mCircle1.setVisibility(View.GONE);
        mCircle2.setVisibility(View.GONE);

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

        Random r = new Random();
        mRandomOffset = r.nextInt(1500);

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

        final AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.2f,0);
        alphaAnimation1.setDuration(ALPHA_DURATION_MS);
        alphaAnimation1.setInterpolator(new AccelerateInterpolator());

        final AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.2f,0);
        alphaAnimation2.setDuration(ALPHA_DURATION_MS);
        alphaAnimation2.setInterpolator(new AccelerateInterpolator());

        final AlphaAnimation alphaAnimation3 = new AlphaAnimation(0.2f,0);
        alphaAnimation3.setDuration(ALPHA_DURATION_MS);
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

        mAnimationsInitialized = true;

    }

    /**
     * Shows the ripple effect
     */
    public void showRipple() {

        if (!mAnimationsInitialized) {
            initAnimations();
        }

        final Handler offset0 = new Handler();
        offset0.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCircle1.setVisibility(VISIBLE);
                mCircle1.startAnimation(mCircle1Set);
            }
        }, mRandomOffset);


        final Handler offset = new Handler();
        offset.postDelayed(new Runnable() {
            @Override
            public void run() {
                mCircle2.setVisibility(VISIBLE);
                mCircle2.startAnimation(mCircle2Set);
            }
        }, RIPPLE_INTERVAL_1_MS + mRandomOffset);

    }

    public void activatePulse() {
        mActive = true;
    }

    public void deactivatePulse() {
        mActive = false;
    }

    public void setPosition(int posX, int posY) {

        if (mContainer != null) {

            LayoutParams layoutParams = (RelativeLayout.LayoutParams)mContainer.getLayoutParams();
            layoutParams.setMargins(posX,posY,0,0);

            mContainer.setLayoutParams(layoutParams);
        }
    }


    public void showBubbleWithDelay(int delayOffset) {

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.show_from_bottom);
        animation.setStartOffset(delayOffset);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                showTitle();

                final Handler offset0 = new Handler();
                offset0.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideTitle();
                    }
                }, 3000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        startAnimation(animation);
    }

    public void expand() {

        deactivatePulse();

        final ScaleAnimation upvoteAnimation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        upvoteAnimation.setDuration(500);
        upvoteAnimation.setFillAfter(true);
        upvoteAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        final ScaleAnimation downVoteAnimation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        downVoteAnimation.setDuration(500);
        downVoteAnimation.setFillAfter(true);
        downVoteAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        final ScaleAnimation scaleButtonAnim = new ScaleAnimation(1f, 2f, 1f, 2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleButtonAnim.setDuration(500);
        scaleButtonAnim.setFillAfter(true);
        scaleButtonAnim.setInterpolator(new AccelerateDecelerateInterpolator());

        scaleButtonAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                showTitle();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



        mButtonView.startAnimation(scaleButtonAnim);
    }

    public void compress() {

        hideTitle();

        final ScaleAnimation upvoteAnimation = new ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        upvoteAnimation.setDuration(500);
        upvoteAnimation.setFillAfter(true);
        upvoteAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        final ScaleAnimation downVoteAnimation = new ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        downVoteAnimation.setDuration(500);
        downVoteAnimation.setFillAfter(true);
        downVoteAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        final ScaleAnimation scaleButtonAnim = new ScaleAnimation(2f, 1f, 2f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleButtonAnim.setDuration(500);
        scaleButtonAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleButtonAnim.setFillAfter(true);
        scaleButtonAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mUpVote.startAnimation(upvoteAnimation);
        mDownVote.startAnimation(downVoteAnimation);
        mButtonView.startAnimation(scaleButtonAnim);

        activatePulse();
    }

    public void setNumber(int number) {

        float maxScale = 2f;
        float minScale = 0.5f;

        float scale = (number / 150f) + 1;

        if (scale > maxScale) scale = maxScale;
        if (scale < minScale) scale = minScale;

        ObjectAnimator buttonScaleX = ObjectAnimator.ofFloat(mButtonConatiner, "scaleX", scale);
        ObjectAnimator buttonScaleY = ObjectAnimator.ofFloat(mButtonConatiner, "scaleY", scale);
        buttonScaleX.setDuration(0);
        buttonScaleY.setDuration(0);

        buttonScaleX.start();
        buttonScaleY.start();

        ObjectAnimator pulseScaleX = ObjectAnimator.ofFloat(mRippleContainer, "scaleX", scale);
        ObjectAnimator pulseScaleY = ObjectAnimator.ofFloat(mRippleContainer, "scaleY", scale);
        pulseScaleX.setDuration(0);
        pulseScaleY.setDuration(0);

        pulseScaleX.start();
        pulseScaleY.start();
    }

    public void showTitle() {

        mTitle.setVisibility(VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.show_title_from_bottom);
        mTitle.startAnimation(animation);
    }

    public void hideTitle() {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.hide_title_to_bottom);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mTitle.setVisibility(GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mTitle.startAnimation(animation);
    }

}
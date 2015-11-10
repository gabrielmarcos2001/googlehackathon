package com.codesmore.codesmore.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codesmore.codesmore.R;
import com.codesmore.codesmore.ui.bubbleviews.ViewAnimatedBackground;
import com.codesmore.codesmore.ui.bubbleviews.ViewBubble;
import com.codesmore.codesmore.ui.bubbleviews.ViewBubblesAdapter;
import com.codesmore.codesmore.ui.bubbleviews.ViewPulseButton;
import com.codesmore.codesmore.utils.UnitsConverter;

/**
 * Created by gabrielmarcos on 11/9/15.
 */
public class MainFragment extends Fragment {

    private View mUpVoteLoader;
    private View mDownVoteLoader;

    private View mTextArea;
    private TextView mTitle;
    private TextView mStatusMessage;
    private View mDownVoteArea;
    private View mUpVoteArea;

    private ViewPulseButton mPulseButton;
    private ViewBubblesAdapter mViewsAdapter;

    /**
     * Returns a new instance of the Fragment
     *
     * @return fragment instance
     */
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mPulseButton = (ViewPulseButton)rootView.findViewById(R.id.ripple_view);
        mViewsAdapter = (ViewBubblesAdapter)rootView.findViewById(R.id.bubbles_adapter);
        mTextArea = rootView.findViewById(R.id.text_area);
        mTitle = (TextView)rootView.findViewById(R.id.title);
        mStatusMessage = (TextView)rootView.findViewById(R.id.message);
        mDownVoteArea = rootView.findViewById(R.id.downvote_area);
        mUpVoteArea = rootView.findViewById(R.id.upvote_area);
        mUpVoteLoader = rootView.findViewById(R.id.upvote_loader);
        mDownVoteLoader = rootView.findViewById(R.id.downvote_loader);

        mUpVoteArea.setVisibility(View.INVISIBLE);
        mDownVoteArea.setVisibility(View.INVISIBLE);
        mUpVoteLoader.setVisibility(View.INVISIBLE);
        mDownVoteLoader.setVisibility(View.INVISIBLE);

        mPulseButton.setVisibility(View.GONE);
        mViewsAdapter.setVisibility(View.GONE);

        mViewsAdapter.setmInterface(new ViewBubblesAdapter.BubblesInterface() {
            @Override
            public void onBubbleSelected(ViewBubble bubble) {
                showVotingAreas();
            }

            @Override
            public void onBubbleUnselected() {
                hideVotingAreas();
            }

            @Override
            public void upVoteBubble(ViewBubble bubble) {
                mUpVoteLoader.setVisibility(View.VISIBLE);

                final Handler fakeContextualEvent = new Handler();
                fakeContextualEvent.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (isAdded()) {
                            onBubbleUpVoted();
                        }

                    }
                }, 2000);
            }

            @Override
            public void downVoteBubble(ViewBubble bubble) {
                mDownVoteLoader.setVisibility(View.VISIBLE);

                final Handler fakeContextualEvent = new Handler();
                fakeContextualEvent.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (isAdded()) {
                            onBubbleDownVoted();
                        }

                    }
                }, 2000);
            }
        });

        startOnBoardingFlow();

        ViewAnimatedBackground view = new ViewAnimatedBackground(getActivity());
        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

        ((ViewGroup)rootView).addView(view);

        /*
        final Handler fakeContextualEvent = new Handler();
        fakeContextualEvent.postDelayed(new Runnable() {
            @Override
            public void run() {
                showContextualEvent();
            }
        }, 5000);
        */

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    void onBubbleUpVoted() {

        mUpVoteLoader.setVisibility(View.INVISIBLE);
        hideVotingAreas();
    }

    void onBubbleDownVoted() {

        mDownVoteLoader.setVisibility(View.INVISIBLE);
        hideVotingAreas();
    }

    private void startOnBoardingFlow() {

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mTextArea.getLayoutParams();
        params.setMargins(0, (int) UnitsConverter.convertDpToPixel(200, getActivity()),0,0);

        mStatusMessage.setText(getString(R.string.on_boarding_1));

        final Handler fakeContextualEvent = new Handler();
        fakeContextualEvent.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (isAdded()) {
                    mPulseButton.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.show_from_bottom);
                    mPulseButton.startAnimation(animation);
                }

            }
        }, 2000);

        final Handler offset0 = new Handler();
        offset0.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (isAdded()) {
                    TranslateAnimation rePositionText = new TranslateAnimation(0, 0, 0, UnitsConverter.convertDpToPixel(-150, getContext()));
                    rePositionText.setDuration(1000);
                    rePositionText.setFillAfter(true);
                    rePositionText.setInterpolator(new AccelerateDecelerateInterpolator());

                    mViewsAdapter.setVisibility(View.VISIBLE);
                    mTextArea.startAnimation(rePositionText);
                    mStatusMessage.setText(getString(R.string.on_boarding_2));

                    showData();

                    final Handler fakeContextualEvent = new Handler();
                    fakeContextualEvent.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (isAdded()) {
                                showContextualEvent();
                            }
                        }
                    }, 5000);

                }
            }
        }, 4000);

    }

    private void showVotingAreas() {

        mUpVoteArea.setVisibility(View.VISIBLE);
        mDownVoteArea.setVisibility(View.VISIBLE);

        Animation showDownVote = AnimationUtils.loadAnimation(getContext(), R.anim.show_from_bottom);
        mDownVoteArea.startAnimation(showDownVote);

        Animation showUpVote = AnimationUtils.loadAnimation(getContext(), R.anim.show_from_top);
        mUpVoteArea.startAnimation(showUpVote);
    }

    private void hideVotingAreas() {
        Animation showDownVote = AnimationUtils.loadAnimation(getContext(), R.anim.hide_to_bottom);
        mDownVoteArea.startAnimation(showDownVote);

        Animation showUpVote = AnimationUtils.loadAnimation(getContext(), R.anim.hide_to_top);
        mUpVoteArea.startAnimation(showUpVote);

        showUpVote.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mUpVoteArea.setVisibility(View.INVISIBLE);
                mDownVoteArea.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void showData() {
        mViewsAdapter.showBubbles();
    }

    private void showContextualEvent() {
        mPulseButton.showContextualEvent();
    }
}

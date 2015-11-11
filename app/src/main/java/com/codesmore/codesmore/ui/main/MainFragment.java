package com.codesmore.codesmore.ui.main;

import android.content.Intent;
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
import com.codesmore.codesmore.integration.db.PulseDataWrapper;
import com.codesmore.codesmore.model.pojo.Issue;
import com.codesmore.codesmore.ui.bubbleviews.ViewAnimatedBackground;
import com.codesmore.codesmore.ui.bubbleviews.ViewBubble;
import com.codesmore.codesmore.ui.bubbleviews.ViewBubblesAdapter;
import com.codesmore.codesmore.ui.bubbleviews.ViewPulseButton;
import com.codesmore.codesmore.ui.details.DetailsActivity;
import com.codesmore.codesmore.ui.report.ReportActivity;
import com.codesmore.codesmore.utils.UnitsConverter;

import java.util.List;

/**
 * Created by gabrielmarcos on 11/9/15.
 */
public class MainFragment extends Fragment implements MainView {

    private MainPresenter mPresenter;

    private View mUpVoteLoader;
    private View mDownVoteLoader;
    private boolean mDataVisible = false;

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

        mPresenter = new MainPresenterImpl(this,new PulseDataWrapper());
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

        mPulseButton.setmInterface(new ViewPulseButton.PulseButtonActions() {
            @Override
            public void onReportClicked() {
                Intent i = new Intent(getActivity(), ReportActivity.class);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.show_from_bottom, R.anim.still_anim);
            }

            @Override
            public void onContextualIssueClicked() {

            }
        });

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
            public void upVoteIssue(ViewBubble bubble) {
                mUpVoteLoader.setVisibility(View.VISIBLE);
                mPresenter.onIssueUpVoted(bubble.getmIssueData());
            }

            @Override
            public void downVoteIssue(ViewBubble bubble) {
                mDownVoteLoader.setVisibility(View.VISIBLE);
                mPresenter.onIssueDownVoted(bubble.getmIssueData());
            }

            @Override
            public void onBubleTap(ViewBubble bubble) {

                Issue issue = bubble.getmIssueData();

                Intent i = new Intent(getActivity(), DetailsActivity.class);
                startActivity(i);
            }
        });

        startOnBoardingFlow();

        ViewAnimatedBackground view = new ViewAnimatedBackground(getActivity());
        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

        ((ViewGroup)rootView).addView(view);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.setView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.setView(null);
    }

    /**
     * Starts the Initial Fake OnBoarding Flow
     */
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

    }

    @Override
    public void showVotingAreas() {

        if (mUpVoteArea.getVisibility() == View.VISIBLE) return;

        mUpVoteArea.setVisibility(View.VISIBLE);
        mDownVoteArea.setVisibility(View.VISIBLE);

        Animation showDownVote = AnimationUtils.loadAnimation(getContext(), R.anim.show_from_bottom);
        mDownVoteArea.startAnimation(showDownVote);

        Animation showUpVote = AnimationUtils.loadAnimation(getContext(), R.anim.show_from_top);
        mUpVoteArea.startAnimation(showUpVote);
    }

    @Override
    public void hideVotingAreas() {

        if (mUpVoteArea.getVisibility() == View.INVISIBLE) return;

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

    @Override
    public void showIssues(List<Issue> issueList) {
        if (isAdded() && mViewsAdapter != null) {
            mViewsAdapter.setItems(issueList);

            if (!mDataVisible) {
                TranslateAnimation rePositionText = new TranslateAnimation(0, 0, 0, UnitsConverter.convertDpToPixel(-150, getContext()));
                rePositionText.setDuration(1000);
                rePositionText.setFillAfter(true);
                rePositionText.setInterpolator(new AccelerateDecelerateInterpolator());

                mViewsAdapter.setVisibility(View.VISIBLE);
                mTextArea.startAnimation(rePositionText);
                mStatusMessage.setText(getString(R.string.on_boarding_2));
                mDataVisible = true;
            }
        }
    }

    @Override
    public void showNewIssue(Issue issue) {

    }

    @Override
    public void onIssueUpVoted(Issue issue) {
        mUpVoteLoader.setVisibility(View.INVISIBLE);
        hideVotingAreas();

        mPresenter.refreshIssues();
    }

    @Override
    public void onIssueDownVoted(Issue issue) {
        mDownVoteLoader.setVisibility(View.INVISIBLE);
        hideVotingAreas();

        mPresenter.refreshIssues();
    }

    @Override
    public void showContextualIssue(Issue issue) {
        mPulseButton.showContextualEvent();
    }
}

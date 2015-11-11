package com.codesmore.codesmore.ui.main;

import android.content.ContentResolver;
import android.os.Handler;
import android.os.Looper;

import com.codesmore.codesmore.integration.db.PulseDataWrapper;
import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Issue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabrielmarcos on 11/10/15.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mView;
    private DataWrapper mWrapper;


    public MainPresenterImpl(MainView mView, DataWrapper dataWrapper) {
        this.mView = mView;
        this.mWrapper = dataWrapper;
    }

    @Override
    public void onIssueUpVoted(final Issue issue) {
        mWrapper.upvote(issue, null);

        final Handler fakeData = new Handler();
        fakeData.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mView != null) {
                    mView.onIssueUpVoted(issue);
                }

            }
        }, 2000);
    }

    @Override
    public void onIssueDownVoted(final Issue issue) {
        mWrapper.downvote(issue);

        final Handler fakeData = new Handler();
        fakeData.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mView != null) {
                    mView.onIssueDownVoted(issue);
                }

            }
        }, 2000);


    }


    @Override
    public void setView(MainView view) {
        this.mView = view;

        if (mView != null) {

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    final List<Issue> issues = mWrapper.getUnresolvedIssues(0,0);

                    Handler mainHandler = new Handler(Looper.getMainLooper());

                    Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() {

                            if (mView != null) {
                                mView.showIssues(issues);
                            }
                        }
                    };

                    mainHandler.post(myRunnable);

                }
            });

            t.start();

            /*
            final Handler fakeData = new Handler();
            fakeData.postDelayed(new Runnable() {
                @Override
                public void run() {

                    List<Issue> mItems = new ArrayList<>();

                    Issue i1 = new Issue(null,"Description 1");
                    i1.setTitle("Issue 1");
                    i1.setPriority(90);

                    Issue i2 = new Issue(null,"Description 2");
                    i2.setTitle("Issue 2");
                    i2.setPriority(20);

                    Issue i3 = new Issue(null,"Description 3");
                    i3.setTitle("Issue 3");
                    i3.setPriority(60);

                    Issue i4 = new Issue(null,"Description 4");
                    i4.setTitle("Issue 4");
                    i4.setPriority(120);

                    Issue i5 = new Issue(null,"Description 5");
                    i5.setTitle("Issue 5");
                    i5.setPriority(170);

                    Issue i6 = new Issue(null,"Description 6");
                    i6.setTitle("Issue 6");
                    i6.setPriority(15);

                    mItems.add(i1);
                    mItems.add(i2);
                    mItems.add(i3);
                    mItems.add(i4);
                    mItems.add(i5);
                    mItems.add(i6);

                    if (mView != null) {
                        mView.showIssues(mItems);
                    }

                }
            }, 5000);
            */

        }
    }

    @Override
    public void refreshIssues() {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                final List<Issue> issues = mWrapper.getUnresolvedIssues(0,0);

                Handler mainHandler = new Handler(Looper.getMainLooper());

                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {

                        if (mView != null) {
                            mView.showIssues(issues);
                        }
                    }
                };

                mainHandler.post(myRunnable);

            }
        });

        t.start();
    }
}

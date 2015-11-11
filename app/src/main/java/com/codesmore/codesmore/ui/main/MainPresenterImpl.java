package com.codesmore.codesmore.ui.main;

import android.os.Handler;
import android.os.Looper;

import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Issue;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                mWrapper.downVote(issue);

                Handler mainHandler = new Handler(Looper.getMainLooper());

                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {

                        if (mView != null) {
                            mView.onIssueUpVoted(issue);
                        }
                    }
                };

                mainHandler.post(myRunnable);

            }
        });
    }

    @Override
    public void onIssueDownVoted(final Issue issue) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                mWrapper.downVote(issue);

                Handler mainHandler = new Handler(Looper.getMainLooper());

                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {

                        if (mView != null) {
                            mView.onIssueDownVoted(issue);
                        }
                    }
                };

                mainHandler.post(myRunnable);

            }
        });

        t.start();
        /*
        mWrapper.downVote(issue);

        final Handler fakeData = new Handler();
        fakeData.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mView != null) {
                    mView.onIssueDownVoted(issue);
                }

            }
        }, 500);
        */


    }


    @Override
    public void setView(MainView view) {
        this.mView = view;

        if (mView != null) {
            refreshIssues();
        }
    }

    @Override
    public void refreshIssues() {
        mWrapper.getUnresolvedIssues(0, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Issue>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<Issue> issues) {
                        mView.showIssues(issues);
                    }
                });
    }
}

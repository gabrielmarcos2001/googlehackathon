package com.codesmore.codesmore.ui.main;

import android.content.ContentResolver;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.codesmore.codesmore.R;
import com.codesmore.codesmore.integration.db.PulseDataWrapper;
import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Issue;
import com.codesmore.codesmore.ui.login.LoginView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabrielmarcos on 11/10/15.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mView;
    private DataWrapper mWrapper;


    public MainPresenterImpl(MainView mView, ContentResolver resolver) {
        this.mView = mView;
        this.mWrapper = new PulseDataWrapper(resolver);
    }

    @Override
    public void onIssueUpVoted(final Issue issue) {

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

}
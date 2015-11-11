package com.codesmore.codesmore.ui.details;

import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Issue;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kchang on 11/10/15.
 */
public class DetailsPresenterImpl implements DetailsPresenter {

    private DetailsView mView;
    private DataWrapper mDataWrapper;

    public DetailsPresenterImpl(DetailsView mainView, DataWrapper dataWrapper) {
        mDataWrapper = dataWrapper;
        mView = mainView;
    }

    @Override
    public void loadIssueById(String issueParseId) {
        mDataWrapper.getIssue(issueParseId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Issue>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Issue issue) {
                        onIssueLoaded(issue);
                    }
                });
    }

    public void onIssueLoaded(Issue unresolvedIssue) {
        mView.onIssueLoaded(unresolvedIssue);
    }
}

package com.codesmore.codesmore.ui.completeddetails;

import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Issue;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by demouser on 11/9/15.
 */
public class CompletedDetailsPresenterImpl implements CompletedDetailsPresenter {

    private CompletedDetailsView mView;
    private DataWrapper mDataWrapper;

    public CompletedDetailsPresenterImpl(CompletedDetailsView mainView, DataWrapper dataWrapper) {
        mDataWrapper = dataWrapper;
        mView = mainView;
    }

    @Override
    public void loadIssueByParseId(String issueParseId) {
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

    private void onIssueLoaded(Issue resolvedIssue) {
        mView.onIssueLoaded(resolvedIssue);
    }
}

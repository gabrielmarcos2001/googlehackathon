package com.codesmore.codesmore.ui.completeddetails;

import com.codesmore.codesmore.integration.backend.WebService;
import com.codesmore.codesmore.model.pojo.Issue;

/**
 * Created by demouser on 11/9/15.
 */
public class CompletedDetailsPresenterImpl implements CompletedDetailsPresenter {

    private CompletedDetailsView mView;
    private WebService mDataWrapper;

    public CompletedDetailsPresenterImpl(CompletedDetailsView mainView, WebService dataWrapper) {
        mDataWrapper = dataWrapper;
        mView = mainView;
    }

    @Override
    public void loadIssueById(String issueParseId) {
        Issue resolvedIssue = mDataWrapper.getIssue(issueParseId);
        onIssueLoaded(resolvedIssue);
    }

    public void onIssueLoaded(Issue resolvedIssue) {
        mView.onIssueLoaded(resolvedIssue);
    }
}

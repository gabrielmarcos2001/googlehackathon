package com.codesmore.codesmore.ui.details;

import com.codesmore.codesmore.integration.backend.WebService;
import com.codesmore.codesmore.model.pojo.Issue;

/**
 * Created by kchang on 11/10/15.
 */
public class DetailsPresenterImpl implements DetailsPresenter {

    private DetailsView mView;
    private WebService mDataWrapper;

    public DetailsPresenterImpl(DetailsView mainView, WebService dataWrapper) {
        mDataWrapper = dataWrapper;
        mView = mainView;
    }

    @Override
    public void loadIssueById(String issueParseId) {
        Issue unresolvedIssue = mDataWrapper.getIssue(issueParseId);
        onIssueLoaded(unresolvedIssue);
    }

    public void onIssueLoaded(Issue unresolvedIssue) {
        mView.onIssueLoaded(unresolvedIssue);
    }
}

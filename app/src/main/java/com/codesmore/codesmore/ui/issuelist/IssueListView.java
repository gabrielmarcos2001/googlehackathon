package com.codesmore.codesmore.ui.issuelist;

import com.codesmore.codesmore.model.pojo.Issue;

import java.util.List;

/**
 * Created by demouser on 11/9/15.
 */


public interface IssueListView {

    void onProblemsLoaded(List<Issue> issues);

    void openCompletedIssueDetail(String issueParseId);

    void showNoItemsAvailableMessage();

}

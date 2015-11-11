package com.codesmore.codesmore.ui;

import com.codesmore.codesmore.model.pojo.Issue;

/**
 * Created by demouser on 11/9/15.
 */
public interface IssueSelectedListener {

    void onIssueSelected(String issueParseId);

    void onIssueUpVoted(Issue issue);

    void onIssueDownVoted(Issue issue);

}

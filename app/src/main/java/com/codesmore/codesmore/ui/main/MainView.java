package com.codesmore.codesmore.ui.main;

import com.codesmore.codesmore.model.pojo.Issue;
import java.util.List;

/**
 * Created by gabrielmarcos on 11/10/15.
 */
public interface MainView {

    void showIssues(List<Issue> issueList);
    void showNewIssue(Issue issue);
    void onIssueUpVoted(Issue issue);
    void onIssueDownVoted(Issue issue);
    void showVotingAreas();
    void hideVotingAreas();
    void showContextualIssue(Issue issue);



}

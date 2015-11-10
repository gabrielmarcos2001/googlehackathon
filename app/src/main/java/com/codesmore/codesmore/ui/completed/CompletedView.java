package com.codesmore.codesmore.ui.completed;

import com.codesmore.codesmore.model.pojo.Issue;
import java.util.List;

/**
 * Created by demouser on 11/9/15.
 */


public interface CompletedView {

    void onProblemsLoaded(List<Issue> issues);

    void openCompletedIssueDetail(long issueId);

}

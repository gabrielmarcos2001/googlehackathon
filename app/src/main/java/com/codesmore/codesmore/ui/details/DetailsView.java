package com.codesmore.codesmore.ui.details;

import com.codesmore.codesmore.model.pojo.Issue;

/**
 * Created by kchang on 11/10/15.
 */
public interface DetailsView {
    void onIssueLoaded(Issue unresolvedIssue);
}

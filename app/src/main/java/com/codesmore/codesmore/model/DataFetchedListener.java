package com.codesmore.codesmore.model;

import com.codesmore.codesmore.model.pojo.Issue;

import java.util.List;

/**
 * Created by demouser on 11/10/15.
 */

public interface DataFetchedListener {

    void onCompletedIssuesLoaded(List<Issue> issues);
}

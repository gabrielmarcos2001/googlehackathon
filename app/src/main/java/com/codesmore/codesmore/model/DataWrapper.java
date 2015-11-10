package com.codesmore.codesmore.model;

import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;

import java.util.List;

/**
 * Created by demouser on 11/9/15.
 */
public interface DataWrapper {

    List<Category> getCategories();
    List<Issue> getResolvedIssues();
    void insertIssue(Issue issue);
    
}

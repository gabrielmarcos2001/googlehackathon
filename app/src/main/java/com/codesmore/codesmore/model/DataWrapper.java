package com.codesmore.codesmore.model;

import com.codesmore.codesmore.model.pojo.Account;
import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;

import java.util.List;

/**
 * Created by demouser on 11/9/15.
 */
public interface DataWrapper {
    List<Category> getCategories();
    List<Issue> getResolvedIssues(double lat, double lon);
    List<Issue> getUnresolvedIssues(double lat, double lon);
    void insertIssue(Issue issue);
    void insertAccount(Account account);
    Issue getIssue(Long id);
    Account getAccount(Long id);
    Category getCategory(Long id);
}

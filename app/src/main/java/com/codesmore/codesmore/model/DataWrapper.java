package com.codesmore.codesmore.model;

import com.codesmore.codesmore.model.pojo.Account;
import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;

import java.util.List;

import rx.Observable;

/**
 * Created by demouser on 11/9/15.
 */
public interface DataWrapper {
    Observable<List<Category>> getCategories();
    Observable<List<Issue>> getResolvedIssues(double lat, double lon);
    Observable<List<Issue>> getUnresolvedIssues(double lat, double lon);

    void insertIssue(Issue issue);
    void insertAccount(Account account);
    Observable<Issue> getIssue(String parseId);
    Account getAccount(Long id);
    Category getCategory(Long id);
    void upVote(Issue issue, Account upvoter);
    void downVote(Issue issue);
    Observable<List<Issue>> getCreatedOrUpvotedIssuesFor(Account owner);
    void resolveIssue(Issue issue, Account resolver);

    /*  The following two methods are needed in order to update category and issue objects with
     * their parse ids.
     */
    void updateIssue(Issue issue);
    void updateCategory(Category category);
}

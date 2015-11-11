package com.codesmore.codesmore.integration.db;

import android.content.ContentResolver;
import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Account;
import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;

import java.util.List;

public class PulseDataWrapper implements DataWrapper {
    private DataWrapper localDataWrapper;

    /**
     * One-argument constructor.
     * @param contentResolver to place calls to.
     */
    public PulseDataWrapper(ContentResolver contentResolver){
        this(new LocalDataWrapper(contentResolver));
    }

    /**
     * Two-argument constructor.
     * @param localDataWrapper to place calls to local
     */
    public PulseDataWrapper(DataWrapper localDataWrapper){
        super();
        this.localDataWrapper = localDataWrapper;
    }

    @Override
    public List<Category> getCategories() {
        return localDataWrapper.getCategories();
    }


    @Override
    public List<Issue> getResolvedIssues(double lat, double lon) {
        return localDataWrapper.getResolvedIssues(lat, lon);
    }

    @Override
    public List<Issue> getUnresolvedIssues(double lat, double lon) {
        return localDataWrapper.getUnresolvedIssues(lat, lon);
    }

    @Override
    public void insertIssue(Issue issue) {
        localDataWrapper.insertIssue(issue);
    }

    @Override
    public void insertAccount(Account account) {
        localDataWrapper.insertAccount(account);
    }

    @Override
    public Issue getIssue(Long id) {
        return localDataWrapper.getIssue(id);
    }

    @Override
    public Account getAccount(Long id) {
        return localDataWrapper.getAccount(id);
    }

    @Override
    public Category getCategory(Long id) {
        return localDataWrapper.getCategory(id);
    }

    @Override
    public void upvote(Issue issue, Account upvoter) {
        localDataWrapper.upvote(issue, upvoter);
    }

    @Override
    public void downvote(Issue issue) {
        localDataWrapper.downvote(issue);
    }

    @Override
    public List<Issue> getCreatedOrUpvotedIssuesFor(Account owner) {
        return localDataWrapper.getCreatedOrUpvotedIssuesFor(owner);
    }

    @Override
    public void resolveIssue(Issue issue, Account resolver) {
        localDataWrapper.resolveIssue(issue, resolver);
    }

    @Override
    public void updateIssue(Issue issue) {
        localDataWrapper.updateIssue(issue);
    }

    @Override
    public void updateCategory(Category category) {
        localDataWrapper.updateCategory(category);
    }
}

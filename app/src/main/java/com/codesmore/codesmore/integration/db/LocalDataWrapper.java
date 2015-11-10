package com.codesmore.codesmore.integration.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;


import com.codesmore.codesmore.model.DataFetchedListener;

import com.codesmore.codesmore.integration.converter.Converter;

import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Account;
import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;
import com.codesmore.codesmore.model.pojo.Upvote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Darryl Staflund on 11/10/2015.
 */
public class LocalDataWrapper implements DataWrapper {
    private ContentResolver contentResolver;
    private Converter<Account> accountConverter;
    private Converter<Category> categoryConverter;
    private Converter<Issue> issueConverter;
    private Converter<Upvote> upvoteConverter;

    /**
     * Default constructor.
     * @param contentResolver to place calls to.
     */
    public LocalDataWrapper(ContentResolver contentResolver){
        this.contentResolver = contentResolver;
    }

    public LocalDataWrapper(
        ContentResolver contentResolver,
        Converter<Account> accountConverter,
        Converter<Category> categoryConverter,
        Converter<Issue> issueConverter,
        Converter<Upvote> upvoteConverter
    ){
        super();
        this.contentResolver = contentResolver;
        this.accountConverter = accountConverter;
        this.categoryConverter = categoryConverter;
        this.issueConverter = issueConverter;
        this.upvoteConverter = upvoteConverter;
    }

    @Override
    public List<Category> getCategories() {
        Cursor cursor = contentResolver.query(
            PulseContract.IssueCategory.CONTENT_URI,
            null,
            null,
            null,
            null
        );

        List<Category> categories = new ArrayList<>();
        if (cursor != null){
            while(cursor.moveToNext()){
                ContentValues values = categoryConverter.convert(cursor);
                Category category = categoryConverter.convert(values);
                categories.add(category);
            }
        }
        return categories;
    }

    @Override
    public void getResolvedIssues(double lat, double lon, DataFetchedListener listener) {
        Cursor cursor = contentResolver.query(
            PulseContract.Issue.CONTENT_URI,
            null,
            PulseContract.Issue.Constraints.BY_RESOLVED_STATUS,
            new String[] { Integer.toString(1) },
            PulseContract.Issue.TABLE_NAME + "." + PulseContract.Issue.Columns.UPVOTES + " DESC"
        );

        List<Issue> issues = new ArrayList<>();
        if (cursor != null){
            while(cursor.moveToNext()){
                ContentValues values = issueConverter.convert(cursor);
                Issue issue = issueConverter.convert(values);
                issues.add(issue);
            }
        }
        listener.onCompletedIssuesLoaded(issues);
        //return issues;
    }

    @Override
    public List<Issue> getUnresolvedIssues(double lat, double lon) {
        return Collections.emptyList();
    }

    @Override
    public void insertIssue(Issue issue) {
        if (issue == null){
            throw new IllegalArgumentException("Issue is required.");
        }

        ContentValues values = issueConverter.convert(issue);
        contentResolver.insert(PulseContract.Issue.CONTENT_URI, values);
    }

    @Override
    public void insertAccount(Account account) {
        //  TODO
    }

    @Override
    public Issue getIssue(Long id) {
        if (id == null){
            return null;
        }

        Cursor cursor = contentResolver.query(
            PulseContract.Issue.Builders.buildForIssueId(id),
            null,
            null,
            null,
            null
        );

        if (cursor == null){
            return null;
        }

        ContentValues values = issueConverter.convert(cursor);
        Issue issue = issueConverter.convert(values);
        return issue;
    }

    @Override
    public Account getAccount(Long id) {
        if (id == null){
            return null;
        }

        Cursor cursor = contentResolver.query(
            PulseContract.Account.Builders.buildForAccountId(id),
            null,
            null,
            null,
            null
        );

        if (cursor == null){
            return null;
        }

        ContentValues values = accountConverter.convert(cursor);
        Account account = accountConverter.convert(values);
        return account;
    }

    @Override
    public Category getCategory(Long id) {
        if (id == null){
            return null;
        }

        Cursor cursor = contentResolver.query(
            PulseContract.IssueCategory.Builders.buildForCategoryId(id),
            null,
            null,
            null,
            null
        );

        if (cursor == null){
            return null;
        }

        ContentValues values = categoryConverter.convert(cursor);
        Category category = categoryConverter.convert(values);
        return category;
    }

    @Override
    public void upvote(Issue issue, Account upvoter) {
    }

    @Override
    public void downvote(Issue issue) {
        //  TODO
    }

    @Override
    public void resolveIssue(Issue issue, Account resolver) {
        if (issue == null || resolver == null) {
            return;
        }

        issue.setFixer(resolver);
        ContentValues values = issueConverter.convert(issue);

        contentResolver.update(
            PulseContract.Issue.Builders.buildForIssueId(issue.getId()),
            values,
            null,
            null
        );
    }

    @Override
    public List<Issue> getCreatedOrUpvotedIssuesFor(Account owner) {
        return null;
        //  TODO
    }
}
